package com.demo.service;

/**
 * Created by Leo on 2017/10/26.
 */

public interface AccountService {

    /**
     * 转账接口
     * @param out 转出账号
     * @param in    转入账号
     * @param money 转账金额
     */
    void transfer(String out, String in, Double money);
}
