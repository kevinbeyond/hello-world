package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Leo on 2017/8/26.
 */
@Controller
public class DemoController {

    @RequestMapping(value = "/",method = RequestMethod.HEAD)
    public String checkWebStatus() {

        return "index.jsp";
    }
}
