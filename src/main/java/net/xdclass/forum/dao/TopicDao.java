package net.xdclass.forum.dao;

import net.xdclass.forum.domain.Topic;
import net.xdclass.forum.util.DataSourceUtil;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class TopicDao {
    private QueryRunner queryRunner=new QueryRunner(DataSourceUtil.getDataSource());

    //开启驼峰映射
    private BeanProcessor beanProcessor=new GenerousBeanProcessor();
    private RowProcessor processor=new BasicRowProcessor(beanProcessor);

    /**
     * 根据cid查询总行数
     * @param cId
     * @return
     */
    public  int countTotalByCid(int cId) {
        String sql = "select count(*) from topic where c_id=? and 'delete'=0";
        Long count=null;
        try {
            count=(Long)queryRunner.query(sql, new ScalarHandler<>(), cId);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return count.intValue();
    }

    /**
     * 分页查询
     * @param cId
     * @param from
     * @param pageSize
     * @return
     */
    public List<Topic> findListByCid(int cId, int from, int pageSize) {
        String sql = "select * from topic where c_id=? and 'delete'=0 order by update_time desc limit ?,?";
        List<Topic> list=null;
        try {
            list=queryRunner.query(sql, new BeanListHandler<>(Topic.class,processor), cId, from, pageSize);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Topic findById(int topicId) {
        String sql = "select * from topic where id=?";
        Topic topic=null;
        try {
            topic=queryRunner.query(sql, new BeanHandler<>(Topic.class),topicId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return topic;
    }

}
