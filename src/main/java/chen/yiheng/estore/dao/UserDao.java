package chen.yiheng.estore.dao;

import chen.yiheng.estore.domain.User;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 21:57 2017/10/9
 * @Modified by:
 */
public interface UserDao {
    User findUserByEmail(String email);
}
