package net.xdclass.forum.controller;

import net.xdclass.forum.domain.Reply;
import net.xdclass.forum.domain.Topic;
import net.xdclass.forum.domain.User;
import net.xdclass.forum.dto.PageDTO;
import net.xdclass.forum.service.CategoryService;
import net.xdclass.forum.service.TopicService;
import net.xdclass.forum.service.impl.CategoryServiceImpl;
import net.xdclass.forum.service.impl.TopicServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主题详情 话题
 */
@WebServlet(name = "TopicServlet",urlPatterns = "/topic")
    public class TopicServlet extends BaseServlet {
    private TopicService topicService=new TopicServiceImpl();
    private CategoryService categoryService=new CategoryServiceImpl();

     /**
     * 默认分页大小
     */
    private static final int pageSize=4;
    //话题分页
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取分类ID，如果为null则默认为1
        String cIdStr = request.getParameter("c_id");
        int cId = 1; // 默认分类ID为1
        if (cIdStr != null && !cIdStr.trim().isEmpty()) {
            try {
                cId = Integer.parseInt(cIdStr);
            } catch (NumberFormatException e) {
                cId = 1; // 如果解析失败，使用默认值
            }
        }

        //默认第一页
        int page=1;
        String currentPage=request.getParameter("page");

        if(currentPage!=null&&!currentPage.trim().isEmpty()){
            try {
                page=Integer.parseInt(currentPage);
            } catch (NumberFormatException e) {
                page = 1; // 如果解析失败，使用默认值
            }
        }

        PageDTO<Topic> pageDTO=topicService.listTopicPageByCid( cId, page, pageSize);
        System.out.println(pageDTO);
        request.getSession().setAttribute("categoryList", categoryService.list());
        request.setAttribute("topicPage", pageDTO);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
   }

    /**
     * 话题详情
     * @param request
     * @param response
     */
   public  void findDetailById(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        //获取topicid，如果为null则抛出异常或重定向
        String topicIdStr = request.getParameter("topic_id");
        if (topicIdStr == null || topicIdStr.trim().isEmpty()) {
            response.sendRedirect("/topic?method=list&c_id=1");
            return;
        }
        
        int topicId;
        try {
            topicId = Integer.parseInt(topicIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("/topic?method=list&c_id=1");
            return;
        }
        
       //默认第一页
       int page=1;
       String currentPage=request.getParameter("page");

       if(currentPage!=null&& !currentPage.trim().isEmpty()){
           try {
               page=Integer.parseInt(currentPage);
           } catch (NumberFormatException e) {
               page = 1; // 如果解析失败，使用默认值
           }
       }

       //处理浏览量，每次用户访问+1
       String sessionReadKey="is_read_"+topicId;
       Boolean isRead=(Boolean) request.getSession().getAttribute(sessionReadKey);
       if(isRead==null){
           request.getSession().setAttribute(sessionReadKey,true);
           topicService.addOnePv(topicId);
       }


       Topic topic=topicService.findById(topicId);
       PageDTO<Reply> pageDTO=topicService.findReplyPageByTopicId( topicId, page, pageSize);
       System.out.println(pageDTO);
       request.setAttribute("topic", topic);
       request.setAttribute("replyPage", pageDTO);

       request.getRequestDispatcher("/topic_detail.jsp").forward(request,response);
   }

    /**
     * 发布主题
     * @param request
     * @param response
     */
   public void addTopic(HttpServletRequest request, HttpServletResponse response)  throws  Exception {
        //获取用户
       User loginUser=(User)request.getSession().getAttribute("loginUser");
       //判断是否登录
       if(loginUser==null){
           request.getSession().setAttribute("msg","未登录，请登录");
           //跳转登录页面
           response.sendRedirect("/user/login.jsp");
           return;
       }

       String title=request.getParameter("title");
       String content=request.getParameter("content");
       int cId=Integer.parseInt(request.getParameter( "c_id"));
       //保存参数到数据库
       topicService.addTopic(loginUser,title,content,cId);
       //发布主题成功
       response.sendRedirect("/topic?method=list&c_id="+cId);
    }

    /**
     * 盖楼回复
     * @param request
     * @param response
     */
    public void replyByTopicId(HttpServletRequest request, HttpServletResponse response)  throws  Exception {
        //登录判断
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        if(loginUser==null){
            request.getSession().setAttribute("msg","未登录，请登录");
            //跳转登录页面
            request.getRequestDispatcher("/user/login.jsp").forward(request,response);
            return;
        }

        int topicId=Integer.parseInt(request.getParameter("topic_id"));
        String content=request.getParameter("content");
        int rows=topicService.replyByTopicId(loginUser,content,topicId);
        if(rows==1){
            //回复成功
            response.sendRedirect("/topic?method=findDetailById&topic_id="+topicId+"&page=1");
        }
    }



}
