package com.credit.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.credit.common.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.credit.common.util.NumberFormat;
import com.credit.common.util.RandomUtil;
import com.credit.common.util.time.TimeUtil;
import com.credit.common.web.session.UserSession;
import com.credit.common.web.session.UserSessionFactory;
import com.credit.entity.Applicant;
import com.credit.entity.UserCallDetailsHistory;
import com.credit.service.CallDetailSearchHistoryService;
import com.credit.web.model.ContactCheck;
import com.credit.web.model.ContactCheckModel;
import com.credit.web.model.GrayscaleModel;
import com.credit.web.util.CallDetailUtil;
import com.credit.web.util.GrayscaleUtil;

import static com.credit.web.util.CallDetailUtil.readFile;

/**
 * Created by wangjunling on 2017/3/8.
 */

@Controller
@RequestMapping("/user/grayscale")
public class GrayscaleController extends AbstractBaseController
{
    @Autowired
    private CallDetailSearchHistoryService<UserCallDetailsHistory> callDetailSearchHistoryService;

    private Logger logger = LoggerFactory.getLogger(GrayscaleController.class);

    @Value("${call.details.upload.file.path}")
    private String uploadFilePath;

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public String service(String phone, String name, String idcard, String applyDateStr,
            @RequestParam(defaultValue = "utf-8") String fileCharset, ContactCheckModel contactCheckModel,
            HttpServletRequest request, Model model, RedirectAttributes attributes)
    {
        String REDIRECT_VIEW = "redirect:/user/search/personal";
        Applicant applicant = new Applicant();
        if (StringUtils.isBlank(phone))
        {
            attributes.addFlashAttribute("errorMsg", "手机号码为空");
            return REDIRECT_VIEW;
        }

        try
        {
            String tagPhoneNumber = NumberFormat.formatNumber(phone);
            if (!NumberFormat.isPhoneNumber(tagPhoneNumber))
            {
                logger.debug("输入申请人电话号码:" + phone + ",转换后的号码" + tagPhoneNumber);
                attributes.addFlashAttribute("errorMsg", "申请人电话号码[" + phone + "]不正确");
                return REDIRECT_VIEW;
            }
            applicant.setPhone(tagPhoneNumber);
        }
        catch (Exception e)
        {
            logger.warn("申请人电话号码[" + applicant.getPhone() + "]不正确:", e);
            attributes.addFlashAttribute("errorMsg", "申请人电话号码[" + applicant.getPhone() + "]不正确");
            return REDIRECT_VIEW;
        }
        if (StringUtils.isNotBlank(applyDateStr))
        {
            try
            {
                attributes.addFlashAttribute("errorMsg", "错误信息");
                Date applyDate = TimeUtil.parse(applyDateStr, "yyyy-MM-dd");
                if (applyDate == null)
                {
                    attributes.addFlashAttribute("errorMsg", "申请时间[" + applyDateStr + "]格式不正确");
                    return REDIRECT_VIEW;
                }
                applicant.setApplyDate(applyDate);
            }
            catch (Exception e)
            {
                logger.warn("申请时间[" + applyDateStr + "]格式不正确");
                attributes.addFlashAttribute("errorMsg", "申请时间[" + applyDateStr + "]格式不正确");
                return REDIRECT_VIEW;
            }
        }
        applicant.setIdcard(idcard);
        applicant.setAname(name);

        MultipartFile multipartFile = null;
        try
        {
             multipartFile = CallDetailUtil.getMultipartFile(request,fileCharset);
        } catch (ServiceException e)
        {
            e.printStackTrace();
        }
        List<String> errMsgList = new ArrayList<>();
        String filePath = "";
        String newFileMd5Digest = "";
        if (multipartFile != null)
        {
            try
            {
                errMsgList = CallDetailUtil.readFile(applicant, multipartFile.getInputStream(), fileCharset);
                newFileMd5Digest = DigestUtils.md5Hex(multipartFile.getInputStream());
                List<UserCallDetailsHistory> histories = callDetailSearchHistoryService.searchByMd5(newFileMd5Digest);
                if (CollectionUtils.isEmpty(histories))
                {
                    filePath =  CallDetailUtil.saveFile(multipartFile,uploadFilePath);
                }else{
                    filePath = histories.get(0).getCallDetailsPath();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            } catch (ServiceException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            UserCallDetailsHistory newSearchHistory = new UserCallDetailsHistory();
            UserSession userSession = UserSessionFactory.getUserSession(request);
            Integer userId = userSession.getUserId();
            String userName = userSession.getUserName();
            newSearchHistory.setUserId(userId);
            newSearchHistory.setName(userName);
            newSearchHistory.setSearchUser(name);
            newSearchHistory.setPhone(phone);
            newSearchHistory.setApplyDate(applyDateStr);
            newSearchHistory.setIdcard(idcard);
            newSearchHistory.setCallDetailsPath(filePath);
            newSearchHistory.setFileMd5Digest(newFileMd5Digest);
            newSearchHistory.setCreateDate(new Date());
            if (contactCheckModel != null)
            {
                List<ContactCheck> contactChecks = contactCheckModel.getContactChecks();
                newSearchHistory.setContactChecks(JSON.toJSONString(contactChecks));
            }
            callDetailSearchHistoryService.saveUserCallDetailHistory(newSearchHistory);
        }
        catch (Exception e)
        {
            logger.error("读取详单文件异常", e);
            attributes.addFlashAttribute("errorMsg", "读取详单文件异常");
            return REDIRECT_VIEW;
        }

        if (CollectionUtils.isEmpty(applicant.getCallDetailsList()))
        {
            if (CollectionUtils.isEmpty(errMsgList))
            {
                attributes.addFlashAttribute("errorMsg", "申请人详单内容为空");
                return REDIRECT_VIEW;
            }
            attributes.addFlashAttribute("errMsgList", errMsgList);
            return REDIRECT_VIEW;
        }

        GrayscaleModel grayscaleModel = new GrayscaleModel();
        grayscaleModel.setContactCheck(contactCheckModel.getContactChecks());
        try
        {
            GrayscaleUtil.buildGrayscaleModel(grayscaleModel, applicant);
        }
        catch (Exception e)
        {
            logger.error("详单统计错误", e);
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return REDIRECT_VIEW;
        }
        Date currentDate = new Date();
        String serialNumber = TimeUtil.format(currentDate,"yyyyMMddhhmmss");
        String reportTime = TimeUtil.format(currentDate, "yyyy-MM-dd");
        serialNumber += RandomUtil.genRandomNumberString(3);
        model.addAttribute("serialNumber",serialNumber);
        model.addAttribute("reportTime",reportTime);
        model.addAttribute("grayscaleModel", grayscaleModel);
        return "user/search/report";
    }

    @RequestMapping(value = "/history",method = RequestMethod.POST)
    public String reportHistory(Integer id,Model model,RedirectAttributes attributes)
    {
        String REDIRECT_VIEW = "redirect:/user/search/personal";
        UserCallDetailsHistory userCallDetailsHistory;
        Applicant applicant = new Applicant();
        if (id != null && id != 0)
        {
            userCallDetailsHistory = callDetailSearchHistoryService.searchOneUserCallDetail(id);
            if (userCallDetailsHistory != null)
            {
                try
                {
                    String tagPhoneNumber = NumberFormat.formatNumber(userCallDetailsHistory.getPhone());
                    applicant.setPhone(tagPhoneNumber);
                    String applyDateStr1 = userCallDetailsHistory.getApplyDate();
                    if (StringUtils.isNotBlank(applyDateStr1))
                    {
                        Date applyDate = TimeUtil.parse(applyDateStr1, "yyyy-MM-dd");
                        applicant.setApplyDate(applyDate);
                    }
                    applicant.setIdcard(userCallDetailsHistory.getIdcard());
                    applicant.setAname(userCallDetailsHistory.getSearchUser());

                    String detailPath = userCallDetailsHistory.getCallDetailsPath();
                    readFile(applicant,new FileInputStream(new File(detailPath)) , "UTF-8");
                } catch (Exception e)
                {
                    logger.error("详单历史错误", e);
                    attributes.addFlashAttribute("errorMsg", "详单历史错误:"+e);
                    return REDIRECT_VIEW;
                }
                    GrayscaleModel grayscaleModel = new GrayscaleModel();
                    String contactChecks = userCallDetailsHistory.getContactChecks();
                    List<ContactCheck> contactCheckList = JSON.parseArray(contactChecks, ContactCheck.class);
                    grayscaleModel.setContactCheck(contactCheckList);
                try
                {
                    GrayscaleUtil.buildGrayscaleModel(grayscaleModel, applicant);
                }
                catch (Exception e)
                {
                    attributes.addFlashAttribute("errorMsg", "详单详单历史错误:"+e);
                    return REDIRECT_VIEW;
                }
                Date currentDate = new Date();
                String serialNumber = TimeUtil.format(currentDate,"yyyyMMddhhmmss");
                String reportTime = TimeUtil.format(currentDate, "yyyy-MM-dd");
                serialNumber += RandomUtil.genRandomNumberString(3);
                model.addAttribute("serialNumber",serialNumber);
                model.addAttribute("reportTime",reportTime);
                model.addAttribute("grayscaleModel", grayscaleModel);
            }
        }
        return "user/search/report";
    }
}
