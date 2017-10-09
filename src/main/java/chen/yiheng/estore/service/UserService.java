package chen.yiheng.estore.service;

import chen.yiheng.estore.domain.User;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 21:42 2017/10/9
 * @Modified by:
 */
public interface UserService {
    public User findUserByEmail(String email);
}
