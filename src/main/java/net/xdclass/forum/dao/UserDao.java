package net.xdclass.forum.dao;

import net.xdclass.forum.domain.User;
import net.xdclass.forum.util.DataSourceUtil;
import org.apache.commons.dbutils.*;

import java.sql.SQLException;

public class UserDao {
    private QueryRunner queryRunner=new QueryRunner(DataSourceUtil.getDataSource());

    //开启驼峰映射
    private BeanProcessor beanProcessor=new GenerousBeanProcessor();
    private RowProcessor processor=new BasicRowProcessor(beanProcessor);

    public int save(User user)throws SQLException {
        String sql="insert into user (phone,pwd,sex,img,create_time,role,username) values(?,?,?,?,?,?,?)";
        Object[]parmas={
                user.getPhone(),user.getPwd(),user.getSex(),user.getImg(),user.getCreateTime(),user.getRole(),user.getUsername()
        };
        int i;
        try {
            i=queryRunner.update(sql,parmas);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return  i;
    }

}
