package net.xdclass.forum.service;

import net.xdclass.forum.domain.Reply;
import net.xdclass.forum.domain.Topic;
import net.xdclass.forum.dto.PageDTO;

public interface TopicService {
    PageDTO<Topic> listTopicPageByCid(int cId,int page,int pageSize);

    PageDTO<Reply> findReplyPageByTopicId(int topicId, int page, int pageSize);

    Topic findById(int topicId);

}

