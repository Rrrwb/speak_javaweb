package net.xdclass.forum.controller;

import net.xdclass.forum.domain.Reply;
import net.xdclass.forum.domain.Topic;
import net.xdclass.forum.domain.User;
import net.xdclass.forum.dto.PageDTO;
import net.xdclass.forum.service.TopicService;
import net.xdclass.forum.service.impl.TopicServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 主题详情 话题
 */
@WebServlet(name = "TopicServlet",urlPatterns = "/topic")
public class TopicServlet extends BaseServlet {
    private TopicService topicService=new TopicServiceImpl();
    /**
     * 默认分页大小
     */
    private static final int pageSize=2;
    //话题分页
    public void list(HttpServletRequest request, HttpServletResponse response)  {
        int cId=Integer.parseInt(request.getParameter("c_id"));

        //默认第一页
        int page=1;
        String currentPage=request.getParameter("page");

        if(currentPage!=null&&currentPage!=""){
            page=Integer.parseInt(currentPage);
        }

        PageDTO<Topic> pageDTO=topicService.listTopicPageByCid( cId, page, pageSize);
        System.out.println(pageDTO);
        request.setAttribute("topicPage", pageDTO);
   }

    /**
     * 话题详情
     * @param request
     * @param response
     */
   public  void findDetailById(HttpServletRequest request, HttpServletResponse response)  {
        //获取topicid
       int topicId=Integer.parseInt(request.getParameter("topic_id"));
       //默认第一页
       int page=1;
       String currentPage=request.getParameter("page");

       if(currentPage!=null&& !currentPage.equals("")){
           page=Integer.parseInt(currentPage);
       }

       Topic topic=topicService.findById(topicId);
       PageDTO<Reply> pageDTO=topicService.findReplyPageByTopicId( topicId, page, pageSize);
       System.out.println(pageDTO);
       request.setAttribute("topic", topic);
       request.setAttribute("replyPage", pageDTO);
   }

    /**
     * 发布主题
     * @param request
     * @param response
     */
   public void addTopic(HttpServletRequest request, HttpServletResponse response)  {
        //获取用户
       User loginUser=(User)request.getSession().getAttribute("loginUser");
       //判断是否登录
       if(loginUser==null){
           request.getSession().setAttribute("msg","未登录，请登录");
           //跳转登录页面 TODO
           return;
       }

       String title=request.getParameter("title");
       String content=request.getParameter("content");
       int cId=Integer.parseInt(request.getParameter( "c_id"));
       int row=topicService.addTopic(loginUser,title,content,cId);
       if(row==1){
           //成功
       }else{
           //失败
       }
    }

    /**
     * 盖楼回复
     * @param request
     * @param response
     */
    public void replyByTopicId(HttpServletRequest request, HttpServletResponse response)  {
        //登录判断
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        if(loginUser==null){
            request.getSession().setAttribute("msg","未登录，请登录");
            //跳转登录页面 TODO
            return;
        }

        int topicId=Integer.parseInt(request.getParameter("topic_id"));
        String content=request.getParameter("content");
        int rows=topicService.replyByTopicId(loginUser,content,topicId);
        if(rows==1){
            //发布成功
        }else{
            //发布失败
        }
    }



}
