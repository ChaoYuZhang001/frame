/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: StudyServiceImpl
 * Author:   Administrator
 * Date:     2020/7/14 15:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package cn.gly.service;

import cn.gly.entity.User;
import cn.gly.mapper.UserMapper;

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
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> queryUserByParams(Object params) {
        return userMapper.queryUserByParams(params);
    }
}