package chen.yiheng.estore.service.impl;

import chen.yiheng.estore.dao.GoodsDao;
import chen.yiheng.estore.domain.Good;
import chen.yiheng.estore.service.GoodsService;
import chen.yiheng.estore.utils.MyFactory;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 12:14 2017/10/11
 * @Modified by:
 */
public class GoodsServiceImpl implements GoodsService {

    @Override
    public List<Good> findAllGoods() {
        GoodsDao goodsDao = MyFactory.getInstance(GoodsDao.class);
        return goodsDao.findAllGoods();
    }

    @Override
    public void addGoods(Good good) {
        GoodsDao goodsDao = MyFactory.getInstance(GoodsDao.class);
        goodsDao.addSingleGoods(good);
    }

    @Override
    public Good findGoodsById(int id) {
        GoodsDao goodsDao = MyFactory.getInstance(GoodsDao.class);
        return goodsDao.findGoodsById(id);
    }
}
