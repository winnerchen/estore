package chen.yiheng.estore.dao.impl;

import chen.yiheng.estore.dao.GoodsDao;
import chen.yiheng.estore.domain.Good;
import chen.yiheng.estore.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 12:16 2017/10/11
 * @Modified by:
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Good> findAllGoods() {
        try {
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "select * from goods";
            return qr.query(sql, new BeanListHandler<>(Good.class));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询商品列表异常!");
        }
    }

    @Override
    public void addSingleGoods(Good good) {
        try {
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
            String sql = "insert into goods values(null,?,?,?,?,?,?,?)";
            qr.update(sql, good.getName(), good.getMarketPrice(),
                    good.getEstoreprice(), good.getCategory(), good.getNum(),
                    good.getImgUrl(), good.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }
}
