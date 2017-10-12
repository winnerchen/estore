package chen.yiheng.estore.service;

import chen.yiheng.estore.domain.Good;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 12:11 2017/10/11
 * @Modified by:
 */
public interface GoodsService {

    List<Good> findAllGoods();

    void addGoods(Good good);
}
