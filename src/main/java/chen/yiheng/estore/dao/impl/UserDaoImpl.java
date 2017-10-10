package chen.yiheng.estore.dao.impl;

import chen.yiheng.estore.dao.UserDao;
import chen.yiheng.estore.domain.User;
import chen.yiheng.estore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 21:59 2017/10/9
 * @Modified by:
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByEmail(String email) {
        try {
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

            String sql = "select * from users where email=?";
            return  qr.query(sql, new BeanHandler<>(User.class),
                    email);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to find user by email");
        }
    }

    @Override
    public void save(User user) {
        try {
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

            String sql = "insert into users values(null,?,?,?,?,?,null,?)";
            qr.update(sql, user.getNickname(), user.getEmail(),
                    user.getPassword(), user.getActiveCode(), user.getStatus(),
                    user.getRole());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to save user");
        }
    }

    @Override
    public User findUserByActiveCode(String activeCode) {
        try {
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

            String sql = "select * from users where activecode=?";
            return  qr.query(sql, new BeanHandler<>(User.class),
                    activeCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to find user by activecode");
        }
    }

    @Override
    public void activeUser(int id) {
        try {
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "update users set status=1 where id=?";
            qr.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to active user");
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

            String sql = "delete from users where id=?";
            qr.update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("fail to delete user");
        }
    }
}
