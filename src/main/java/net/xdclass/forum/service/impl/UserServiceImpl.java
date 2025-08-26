package net.xdclass.forum.service.impl;

import net.xdclass.forum.dao.UserDao;
import net.xdclass.forum.domain.User;
import net.xdclass.forum.service.UserService;
import net.xdclass.forum.util.CommonUtil;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Date;
import  java.util.Random;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDao();
    @Override
    public int register(User user) {
        user.setRole(1);
        user.setCreate_time(new Date());
        user.setImg(getRandomImg());

        user.setPwd(CommonUtil.MD5(user.getPwd()));
        try {
            return userDao.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * 放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        int size =  headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }

}
