package com.credit.web.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * Created by Michael Chan on 3/29/2017.
 */
@Controller
public class CaptchaController extends AbstractBaseController
{
    @Autowired
    private Producer captchaProducer;

    @RequestMapping(value = "/kapt", method = RequestMethod.GET)
    public String kapt()
    {
        return "sms";
    }

    @RequestMapping(value = "/dun/image", method = RequestMethod.GET)
    public void kaptcha(HttpServletRequest req, HttpServletResponse rsp) throws SystemException
    {
        ServletOutputStream out = null;
        try {
            HttpSession session = req.getSession();
            rsp.setDateHeader("Expires", 0);
            rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
            rsp.setHeader("Pragma", "no-cache");
            rsp.setContentType("image/jpeg");
            String capText = captchaProducer.createText();
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
            BufferedImage image = captchaProducer.createImage(capText);
            out = rsp.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
        }catch(Exception e)
        {
            logger.error("",e.getMessage());

        } finally {
            try {
                out.close();
            } catch (IOException e) {
                logger.error("",e.getMessage());
            }
        }
    }

    @RequestMapping(value = "/dun/kapt", method = RequestMethod.GET)
    @ResponseBody
    public boolean userLogin(String captcha,HttpServletRequest request)
    {
        //从session中取出kaptcha生成的验证码text值
        String expected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //获取用户页面输入的验证码
        if(!captcha.equalsIgnoreCase(expected))
        {
            return false;
        }else
        {
            return true;
        }
    }
}
