package net.xdclass.forum.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //获取请求方法
        String method=req.getParameter("method");

        if(method!=null && !method.equals("")){
            try {
                Method targetMethod =this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
                //执行对应方法
                targetMethod.invoke(this, req, resp);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
