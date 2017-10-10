package chen.yiheng.estore.service.impl;

import chen.yiheng.estore.dao.UserDao;
import chen.yiheng.estore.domain.User;
import chen.yiheng.estore.service.UserService;
import chen.yiheng.estore.utils.MyFactory;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 21:56 2017/10/9
 * @Modified by:
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findUserByEmail(String email) {
        UserDao dao = MyFactory.getInstance(UserDao.class);
        return dao.findUserByEmail(email);
    }

    @Override
    public void save(User user) {
        UserDao dao = MyFactory.getInstance(UserDao.class);
        dao.save(user);
    }

    @Override
    public User findUserByActiveCode(String activeCode) {
        UserDao dao = MyFactory.getInstance(UserDao.class);
        return dao.findUserByActiveCode(activeCode);
    }

    @Override
    public void deleteUser(int id) {
        UserDao dao = MyFactory.getInstance(UserDao.class);
        dao.deleteUser(id);
    }

    @Override
    public void activeUser(int id) {
        UserDao dao = MyFactory.getInstance(UserDao.class);
        dao.activeUser(id);
    }
}
