package com.credit.web.controller.open.blackbox;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.credit.common.result.ResultJson;
import com.credit.common.util.NumberFormat;
import com.credit.common.util.RandomUtil;
import com.credit.common.util.pdf.PdfUtil;
import com.credit.common.util.servlet.ResponseUtil;
import com.credit.common.util.time.TimeUtil;
import com.credit.entity.Applicant;
import com.credit.web.controller.AbstractBaseController;
import com.credit.web.model.GrayscaleModel;
import com.credit.web.util.CallDetailUtil;
import com.credit.web.util.ContactCheckUtil;
import com.credit.web.util.GrayscaleUtil;
import com.credit.web.util.VelocityEngineUtil;

/**
 * Created by wangjunling on 2017/3/14.
 */
@Controller
public class CallDetailNumberMarkPdfController extends AbstractBaseController
{

    @RequestMapping(value = "/service", method = RequestMethod.POST, params = "t=call_detail_number_mark_report")
    public void service(String phone, String name, String idcard, String imei, String imsi,
            @RequestParam(name = "apply_date", required = false) String applyDateStr,
            @RequestParam(name = "file_charset", defaultValue = "UTF-8") String fileCharset,
            @RequestParam(name = "contacts", required = false) String contactsJson, HttpServletRequest request,
            HttpServletResponse response)
    {

        if (StringUtils.isBlank(phone))
        {
            printResult(response, new ResultJson().paramError("手机号码为空"));
            return;
        }
        Applicant applicant = new Applicant();
        try
        {
            String tagPhoneNumber = NumberFormat.formatNumber(phone);
            if (!NumberFormat.isPhoneNumber(tagPhoneNumber))
            {
                logger.debug("输入申请人电话号码:" + phone + ",转换后的号码" + tagPhoneNumber);
                printResult(response, new ResultJson().paramError("申请人电话号码[" + phone + "]不正确"));
                return;
            }
            applicant.setPhone(tagPhoneNumber);
        }
        catch (Exception e)
        {
            logger.warn("申请人电话号码[" + applicant.getPhone() + "]不正确:", e);
            printResult(response, new ResultJson().paramError("申请人电话号码[" + applicant.getPhone() + "]不正确"));
            return;
        }
        if (StringUtils.isNotBlank(applyDateStr))
        {
            try
            {
                Date applyDate = TimeUtil.parse(applyDateStr, "yyyy-MM-dd");
                if (applyDate == null)
                {
                    printResult(response, new ResultJson().paramError("申请时间[" + applyDateStr + "]格式不正确"));
                    return;
                }
                applicant.setApplyDate(applyDate);
            }
            catch (Exception e)
            {
                logger.warn("申请时间[" + applyDateStr + "]格式不正确");
                printResult(response, new ResultJson().paramError("申请时间[" + applyDateStr + "]格式不正确"));
                return;
            }
        }

        applicant.setIdcard(idcard);
        applicant.setAname(name);
        applicant.setImei(imei);
        applicant.setImsi(imsi);
        applicant.setQueryTime(new Date());
        List<String> errMsgList = new ArrayList<>();
        try
        {
            MultipartFile multipartFile = CallDetailUtil.getMultipartFile(request, fileCharset);
            if (multipartFile != null)
            {
                errMsgList = CallDetailUtil.readFile(applicant, multipartFile.getInputStream(), fileCharset);
            }
        }
        catch (Exception e)
        {
            logger.error("读取详单文件异常", e);
            printResult(response, new ResultJson().paramError(e.getMessage()));
            return;
        }
        String callDetailStr = request.getParameter("call_details");
        if (CollectionUtils.isEmpty(applicant.getCallDetailsList()) && StringUtils.isBlank(callDetailStr))
        {
            if (CollectionUtils.isNotEmpty(errMsgList))
            {
                printResult(response, new ResultJson().paramError(JSONArray.toJSONString(errMsgList)));
                return;
            }
            printResult(response, new ResultJson().paramError("详单为空"));
            return;
        }
        try
        {
            if (StringUtils.isNotBlank(callDetailStr))
            {
                List<String> readCallDetailJsonArrayErrMsg = CallDetailUtil.readCallDetailJsonArray(applicant,
                        callDetailStr);
                errMsgList.addAll(readCallDetailJsonArrayErrMsg);
            }
        }
        catch (Exception e)
        {
            logger.error("详单Json字符串转化异常", e);
            printResult(response, new ResultJson().paramError("详单参数值格式有误【必须为JSON数组字符串】"));
            return;
        }
        if (CollectionUtils.isEmpty(applicant.getCallDetailsList()))
        {
            if (CollectionUtils.isEmpty(errMsgList))
            {
                printResult(response, new ResultJson().paramError("申请人详单内容为空"));
                return;
            }
            printResult(response, new ResultJson().paramError(JSONArray.toJSONString(errMsgList)));
            return;
        }

        GrayscaleModel grayscaleModel = new GrayscaleModel();
        try
        {
            ContactCheckUtil.readContacts(grayscaleModel.getContactCheck(), contactsJson);
        }
        catch (Exception e)
        {
            logger.warn("联系人JSON数组字符串转为JSON数组对象失败:", e);
            printResult(response, new ResultJson().paramError("联系人参数值格式有误【必须为JSON数组字符串】"));
            return;
        }
        try
        {
            GrayscaleUtil.buildGrayscaleModel(grayscaleModel, applicant);
        }
        catch (Exception e)
        {
            logger.error("详单统计错误", e);
            printResult(response, new ResultJson().error("详单统计错误"));
            return;
        }

        Map<String, Object> model = new HashMap<String, Object>();
        Date currentDate = new Date();
        String serialNumber = TimeUtil.format(currentDate, "yyyyMMddhhmmss");
        String reportTime = TimeUtil.format(currentDate, "yyyy-MM-dd");
        serialNumber += RandomUtil.genRandomNumberString(3);
        model.put("serialNumber", serialNumber);
        model.put("reportTime", reportTime);
        model.put("grayscaleModel", grayscaleModel);
        model.put("basePath", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/");
        String text = VelocityEngineUtils.mergeTemplateIntoString(VelocityEngineUtil.getInstance(),
                "template/call_detail_result.vm", "utf-8", model);
        // System.out.println(text);
        OutputStream out = null;
        try
        {
            byte[] bytes = PdfUtil.html2pdf(text,
                    request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/"),
                    getFileName(name, phone));
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + getFileName(name, phone));
            out = response.getOutputStream();
            out.write(bytes);
            out.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printResult(HttpServletResponse response, ResultJson resultJson)
    {
        ResponseUtil.printJsonString(response, resultJson);
    }

    private String getFileName(String name, String phone)
    {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(phone))
        {
            stringBuilder.append(phone);
            stringBuilder.append("_");
        }
        if (StringUtils.isNotBlank(name))
        {
            stringBuilder.append(name);
            stringBuilder.append("_");
        }
        String format = TimeUtil.format(new Date(), TimeUtil.FORMAT_COMPACT);
        stringBuilder.append(format);
        stringBuilder.append(".pdf");
        return stringBuilder.toString();
    }
}
