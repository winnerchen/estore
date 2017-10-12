package chen.yiheng.estore.dao;

import chen.yiheng.estore.domain.Good;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 12:15 2017/10/11
 * @Modified by:
 */
public interface GoodsDao {
    List<Good> findAllGoods();

    void addSingleGoods(Good good);
}
