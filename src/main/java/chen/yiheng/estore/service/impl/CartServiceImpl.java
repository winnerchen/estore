package chen.yiheng.estore.service.impl;

import chen.yiheng.estore.dao.CartDao;
import chen.yiheng.estore.domain.Cart;
import chen.yiheng.estore.domain.Good;
import chen.yiheng.estore.service.CartService;
import chen.yiheng.estore.utils.MyFactory;

import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 12:25 2017/10/14
 * @Modified by:
 */
public class CartServiceImpl implements CartService {

    CartDao cartDao = MyFactory.getInstance(CartDao.class);
    @Override
    public void updateCart(int id, int number, int gid) {
        Cart cart = cartDao.findCartByUid(id, gid);

        if (cart == null) {
            cart = new Cart();
            cart.setUid(id);
            cart.setGid(gid);
            cart.setBuynum(number);
            cartDao.addGoodsToCart(cart);
        } else {
            cart.setBuynum(cart.getBuynum() + number);
            cartDao.updateCartNum(cart);

        }
    }

    @Override
    public List<Good> queryCartByUid(int id) {
        return cartDao.queryCartByUid(id);
    }

    @Override
    public long findGoodsCategoryNumByUid(int id) {
        return cartDao.findGoodsCategoryNumByUid(id);
    }

    @Override
    public void delCart(int gid, int uid) {
        cartDao.delCart(gid, uid);
    }
}
