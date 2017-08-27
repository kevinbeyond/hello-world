package com.demo.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 可处理异步请求的Servlet
 * Created by Leo on 2017/8/27.
 */

/**
 * 异步请求是在处理比较耗时的业务时，先将request返回，然后另起线程处理耗时的业务，处理完后再返回给用户
 * HTTP协议是单向的，只能客户端拉不能服务器主动推，Servlet对异步请求的支持并没有修改HTTP协议，而是对HTTP的巧妙利用
 * 异步请求核心原理有两大类：（1）轮询（2）长连接
 * （1）轮询：定时自动发起请求检查有没有需要返回的数据，对资源浪费较大
 * （2）长连接：在客户端发起请求，服务端处理并返回后并不结束连接，这样就可以在后面继续返回给客户端数据
 * Servlet对异步请求的支持采用的是长连接的方式，异步请求在原始的请求返回的时候并没有关闭连接，关闭的只是处理请求的那个线程
 * 只有在异步请求全部处理完之后才会关闭连接
 */
public class WorkServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置ContentType，关闭缓存
        resp.setContentType("text/plain;charset=UTF-8");
        resp.setHeader("Cache-Control", "private");
        resp.setHeader("Pragma", "no-cache");

        //原始请求可以做一些简单业务的处理
        final PrintWriter writer = resp.getWriter();
        writer.println("老板检查当前需要做的工作");
        writer.flush();

        //jobs表示需要做的工作，使用循环模拟初始化
        final List<String> jobs = new ArrayList<String>();
        for (int i=0;i<10;i++){
            jobs.add("job" + i);
        }

        //使用request的startAsync方法开启异步处理AsyncContext，异步请求上下文（异步请求容器）
        //多次调用startAsync返回的是同一个AsyncContext
        final AsyncContext asyncContext = req.startAsync();

        //添加两个监听器
        asyncContext.addListener(new BossListener());
        asyncContext.addListener(new LeaderListener());

        //具体处理请求，内部处理启用了新线程，不会阻塞当前线程
        doWork(asyncContext, jobs);
        writer.println("老板布置完工作就走了");
        writer.flush();

    }

    private void doWork(final AsyncContext asyncContext, final List<String> jobs) {
        //设置超时时间1小时
        asyncContext.setTimeout(1*60*60*1000L);

        asyncContext.start(new Runnable() {
            public void run() {
                //从AsyncContext获取到Response，进而获取到Writer
                try {
                    PrintWriter writer = asyncContext.getResponse().getWriter();
                    for (String job:jobs) {
                        writer.println("\"" + job + "\"请求处理中...");
                        Thread.sleep(1*3000L);
                        writer.flush();
                    }
                    //发出请求处理完成通知，告诉容器请求已处理完成
                    asyncContext.complete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class BossListener implements AsyncListener {

    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理完成");
    }

    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理超时");
    }

    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理出错，详情如下：\t" +
            asyncEvent.getThrowable().getMessage());
    }

    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理开始");
    }
}

class LeaderListener implements AsyncListener {

    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理完成");
    }

    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理超时");
    }

    public void onError(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理出错，详情如下：\t" +
                asyncEvent.getThrowable().getMessage());
    }

    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        System.out.println("在" + format.format(new Date()) + "工作处理开始");
    }
}
