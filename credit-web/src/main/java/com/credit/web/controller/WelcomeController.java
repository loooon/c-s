package com.credit.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Michael Chan on 4/14/2017.
 */
@Controller
public class WelcomeController
{
    @RequestMapping("/index")
    public String slideTest()
    {
        return "index";
    }
}
