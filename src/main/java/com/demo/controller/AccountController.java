package com.demo.controller;

import com.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Leo on 2017/10/26.
 */
@RestController
public class AccountController {

    /**
     * 注入代理类，因为代理类进行了增强的操作
     */

    @Resource(name = "transactionProxyFactory")
    private AccountService accountService;

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String transferMoney() {
        accountService.transfer("aaa", "bbb", 200.0);//aaa转账200给bbb

        return "转账成功！";
    }
}
