package net.xdclass.forum.controller;

import net.xdclass.forum.domain.User;
import net.xdclass.forum.service.UserService;
import net.xdclass.forum.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 用户相关服务
 */
@WebServlet(name = "UserServlet",urlPatterns = "/user")
public class UserServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();
    /*
     * 用户注册
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码已由CharacterEncodingFilter统一处理
        
        User user = new User();
        //直接把map映射给user
        Map<String, String[]> map = request.getParameterMap();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        int i=userService.register(user);
        if(i>0){
            //注册成功跳转登录
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);
        }else {
            //注册失败 返回注册页面
               request.getRequestDispatcher("/user/register.jsp").forward(request,response);
        }
    }
    //登录接口
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 编码已由CharacterEncodingFilter统一处理
        
        String phone = request.getParameter("phone");
        String password = request.getParameter("pwd");

        User user=userService.login(phone,password);
        if(user!=null){
            request.getSession().setAttribute("loginUser",user);
            //跳转页面
            response.sendRedirect("/topic?method=list&&c_id=1");
        }else{
            request.setAttribute("msg","用户名或密码不正确");
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);
        }
    }

    /**
     * 用户退出
     * @param request
     * @param response
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        //跳转
        request.getRequestDispatcher("/topic?method=list&&c_id=1").forward(request,response);
    }








}
