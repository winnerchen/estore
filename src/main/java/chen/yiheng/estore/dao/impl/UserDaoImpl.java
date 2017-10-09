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
            throw new RuntimeException("通过邮件查询用户失败");
        }
    }
}
