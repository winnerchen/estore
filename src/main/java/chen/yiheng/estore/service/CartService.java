package chen.yiheng.estore.service;

import chen.yiheng.estore.domain.Good;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 11:58 2017/10/14
 * @Modified by:
 */
public interface CartService {

    void updateCart(int id, int number, int gid);

    List<Good> queryCartByUid(int id);

    long findGoodsCategoryNumByUid(int id);

    void delCart(int gid, int id);
}
