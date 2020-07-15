package cn.gly.mapper;

import cn.gly.entity.User;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author grady
 * @email zhangcy1@fosun.com
 * @create 2020/7/14
 * @since 1.0.0
 */
public interface UserMapper {

    List<User> queryUserByParams(Object param);
}