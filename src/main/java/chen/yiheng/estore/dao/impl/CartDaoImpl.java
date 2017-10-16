package chen.yiheng.estore.dao.impl;

import chen.yiheng.estore.dao.CartDao;
import chen.yiheng.estore.domain.Cart;
import chen.yiheng.estore.domain.Good;
import chen.yiheng.estore.utils.JDBCUtils;
import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 15:34 2017/10/15
 * @Modified by:
 */
public class CartDaoImpl implements CartDao {
    @Override
    public Cart findCartByUid(int id, int gid) {
        try {
            String sql = "select * from cart where uid = ? and gid = ?";
            QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
            return runner.query(sql, new BeanHandler<>(Cart.class), id, gid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addGoodsToCart(Cart cart) {
        try {
            String sql = "insert into cart values(?,?,?)";
            QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
            runner.update(sql,cart.getUid(),cart.getGid(),cart.getBuynum());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCartNum(Cart cart) {
        try {
            String sql = "update cart set buynum=? where uid=? and gid=?";
            QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
            runner.update(sql, cart.getBuynum(), cart.getUid(), cart.getGid());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Good> queryCartByUid(int id) {
        try {
            // 根据用户id 查询 该用户所有的购买商品明细信息 goods cart uid
            String sql = "select g.*,c.buynum as buynum  from goods as g,cart as c where g.id = c.gid and c.uid = ?";
            QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
            return runner.query(sql, new BeanListHandler<Good>(Good.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public long findGoodsCategoryNumByUid(int id) {
        try {
            String sql = "select count(1) from cart where uid=?";
            QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
            return runner.query(sql, new ScalarHandler<Long>(1), id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delCart(int gid, int uid) {
        try {
            String sql = "delete from cart where gid=? and uid=?";
            QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
            runner.update(sql, gid, uid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
