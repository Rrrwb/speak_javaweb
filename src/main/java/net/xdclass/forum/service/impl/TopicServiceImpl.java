package net.xdclass.forum.service.impl;

import net.xdclass.forum.dao.CategoryDao;
import net.xdclass.forum.dao.ReplyDao;
import net.xdclass.forum.dao.TopicDao;
import net.xdclass.forum.domain.Category;
import net.xdclass.forum.domain.Reply;
import net.xdclass.forum.domain.Topic;
import net.xdclass.forum.domain.User;
import net.xdclass.forum.dto.PageDTO;
import net.xdclass.forum.service.TopicService;

import java.util.Date;
import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao=new TopicDao();
    private ReplyDao replyDao=new ReplyDao();
    private CategoryDao categoryDao=new CategoryDao();
    @Override
    public PageDTO<Topic> listTopicPageByCid(int cId, int page, int pageSize) {
        //查询总记录数
        int totalRecord=topicDao.countTotalByCid(cId);
        int from=(page - 1) * pageSize;

        //分页查询
        List<Topic> topicList=topicDao.findListByCid(cId,from,pageSize);
        PageDTO<Topic> pageDTO=new PageDTO<>(page,pageSize,totalRecord);
        pageDTO.setList(topicList);

        return pageDTO;
    }

    @Override
    public Topic findById(int topicId) {
        return topicDao.findById(topicId);
    }

    @Override
    public int addTopic(User loginUser, String title, String content, int cId) {
        Category category=categoryDao.findById(cId);
        if(category==null){
            return 0;
        }
        Topic topic=new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setCreateTime(new Date());
        topic.setPv(1);
        topic.setDelete(0);
        topic.setUpdateTime(new Date());
        topic.setUserId(loginUser.getId());
        topic.setUserImg(loginUser.getImg());
        topic.setUsername(loginUser.getUsername());
        topic.setcId(cId);
        topic.setHot(0);

        int rows= 0;
        try {
            rows = topicDao.save(topic);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rows;
    }

    /**
     * 盖楼回复
     * @param loginUser
     * @param content
     * @param topicId
     * @return
     */
    @Override
    public int replyByTopicId(User loginUser, String content, int topicId) {
        int floor=topicDao.findLatestFloorByTopicid(topicId);
        Reply reply=new Reply();
        reply.setFloor(floor+1);
        reply.setContent(content);
        reply.setCreateTime(new Date());
        reply.setUserId(loginUser.getId());
        reply.setUserImg(loginUser.getImg());
        reply.setTopicId(topicId);
        reply.setDelete(0);
        reply.setUpdateTime(new Date());
        reply.setContent(content);

        int rows=replyDao.save(reply);
        return rows;
    }

    /**
     * 分页查询回复
     * @param topicId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize) {
        //查询总记录数
        int totalRecord=replyDao.countTotalReplyByTopicId(topicId);
        int from=(page - 1) * pageSize;

        //分页查询
        List<Reply> replyList=replyDao.findListByTopicCid(topicId,from,pageSize);
        PageDTO<Reply> pageDTO=new PageDTO<>(page,pageSize,totalRecord);
        pageDTO.setList(replyList);

        return pageDTO;
    }
}
