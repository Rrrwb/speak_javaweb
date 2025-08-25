package net.xdclass.forum.service.impl;

import net.xdclass.forum.dao.ReplyDao;
import net.xdclass.forum.dao.TopicDao;
import net.xdclass.forum.domain.Reply;
import net.xdclass.forum.domain.Topic;
import net.xdclass.forum.dto.PageDTO;
import net.xdclass.forum.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao=new TopicDao();
    private ReplyDao replyDao=new ReplyDao();
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
