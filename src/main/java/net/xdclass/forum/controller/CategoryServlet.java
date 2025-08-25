package net.xdclass.forum.controller;

import net.xdclass.forum.domain.Category;
import net.xdclass.forum.service.CategoryService;
import net.xdclass.forum.service.impl.CategoryServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService=new CategoryServiceImpl();
    //返回全部分类列表
   public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
            List<Category> list =categoryService.list();
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(list);
            System.out.println(list.toString());
   }
}
