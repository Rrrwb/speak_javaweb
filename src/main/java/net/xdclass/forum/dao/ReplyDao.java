package net.xdclass.forum.dao;

import net.xdclass.forum.domain.Reply;
import net.xdclass.forum.domain.Topic;
import net.xdclass.forum.util.DataSourceUtil;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ReplyDao {
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDataSource());

    //开启驼峰映射
    private BeanProcessor beanProcessor = new GenerousBeanProcessor();
    private RowProcessor processor = new BasicRowProcessor(beanProcessor);

    /**
     * 根据topicid查询回复总行数
     * @param topicId
     * @return
     */
    public int countTotalReplyByTopicId(int topicId) {
        String sql = "select count(*) from reply where topic_id=? and 'delete'=0";
        Long count = null;
        try {
            count = (Long) queryRunner.query(sql, new ScalarHandler<>(), topicId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count.intValue();
    }


    public List<Reply> findListByTopicCid(int topicId, int from, int pageSize) {
        String sql = "select * from reply where topic_id=? and 'delete'=0 order by update_time desc limit ?,?";
        List<Reply> replyList=null;
        try {
            replyList=queryRunner.query(sql, new BeanListHandler<>(Reply.class,processor), topicId, from, pageSize);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return replyList;
    }

    /**
     * 添加新回复
     * @param reply
     * @return
     */
    public int save(Reply reply) {
        String sql="insert into  reply (topic_id,floor,content,user_id,username,user_img,create_time,update_time,`delete`) values(?,?,?,?,?,?,?,?,?)";
        Object [] params ={
                reply.getTopicId(),reply.getFloor(),reply.getContent(),reply.getUserId(),reply.getUsername(),reply.getUserImg(),reply.getCreateTime(),reply.getUpdateTime(),reply.getDelete(),
        };
        int rows=0;
        try {
            rows=queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
}
