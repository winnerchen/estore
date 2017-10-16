package chen.yiheng.estore.dao;

import chen.yiheng.estore.domain.Cart;
import chen.yiheng.estore.domain.Good;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 15:31 2017/10/15
 * @Modified by:
 */
public interface CartDao {
    Cart findCartByUid(int id, int gid);

    void addGoodsToCart(Cart cart);

    void updateCartNum(Cart cart);

    List<Good> queryCartByUid(int id);

    long findGoodsCategoryNumByUid(int id);

    void delCart(int gid, int uid);
}
