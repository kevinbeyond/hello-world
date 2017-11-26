package com.demo.service.impl;

import com.demo.dao.AccountMapper;
import com.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Leo on 2017/10/26.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    //声明式事务管理
    public void transfer(String out, String in, Double money) {
        accountMapper.outMoney(out, money);//出账
//        int i = 1/0;
        accountMapper.inMoney(in, money);//入账
    }

//    @Autowired
//    private TransactionTemplate transactionTemplate;
//
//
//    //编程式事务管理
//    public void transfer(final String out, final String in, final Double money) {
////        accountMapper.outMoney(out, money);//出账
////        accountMapper.inMoney(in, money);//入账
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            //TransactionStatus事务的状态
//            @Override
//            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//                accountMapper.outMoney(out, money);//出账
//                int i = 1/0;
//                accountMapper.inMoney(in, money);//入账
//            }
//        });
//    }


}
