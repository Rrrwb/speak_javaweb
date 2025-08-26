package net.xdclass.forum.controller;

import net.xdclass.forum.domain.User;
import net.xdclass.forum.service.UserService;
import net.xdclass.forum.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;


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
    public void register(HttpServletRequest request, HttpServletResponse response) {
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
            //注册成功跳转登录 TODO
        }else {
            //注册失败 返回注册页面 TODO
        }

    }

}
