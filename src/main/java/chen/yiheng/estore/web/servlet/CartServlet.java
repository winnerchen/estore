package chen.yiheng.estore.web.servlet;

import chen.yiheng.estore.domain.Good;
import chen.yiheng.estore.domain.User;
import chen.yiheng.estore.service.CartService;
import chen.yiheng.estore.utils.MyFactory;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 11:45 2017/10/14
 * @Modified by:
 */
public class CartServlet extends BaseServlet {
    public String addToCart(HttpServletRequest request, HttpServletResponse response) {
        //获取商品购买数量
        int number = Integer.parseInt(request.getParameter("buynum"));
        //获取购买商品id
        int gid = Integer.parseInt(request.getParameter("gid"));

        //获取用户
        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if (loginUser == null) {
            return "/#login.jsp";
        }
        CartService cartService = MyFactory.getInstance(CartService.class);
        //调用业务层添购物车方法
        cartService.updateCart(loginUser.getId(), number, gid);
        return "/#buyorcart.jsp";
    }

    public String queryCart(HttpServletRequest request, HttpServletResponse response) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "/#login.jsp";
        }
        CartService cartService = MyFactory.getInstance(CartService.class);
        List<Good> cartList = cartService.queryCartByUid(loginUser.getId());
        request.setAttribute("cartList", cartList);
        return "/cart.jsp";
    }

    public void findGoodsCategoryNumByUid(HttpServletRequest request, HttpServletResponse
            response) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.getWriter().print(0);
        } else {
            CartService cartService = MyFactory.getInstance(CartService.class);
            long num = cartService.findGoodsCategoryNumByUid(loginUser.getId());
            response.getWriter().print(num);
        }
    }

    public String deleteGood(HttpServletRequest request, HttpServletResponse response) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        CartService cartService = MyFactory.getInstance(CartService.class);
        int gid = Integer.parseInt(request.getParameter("gid"));
        cartService.delCart(gid, loginUser.getId());
        return "/#cartServlet?method=queryCart";
    }
}
