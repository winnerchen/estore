package chen.yiheng.estore.web.servlet;

import chen.yiheng.estore.domain.User;
import chen.yiheng.estore.service.UserService;
import chen.yiheng.estore.utils.MyFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 20:37 2017/10/9
 * @Modified by:
 */
public class UserServlet extends BaseServlet {
    public void validEmail(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        if (email != null) {
            UserService service = MyFactory.getInstance(UserService.class);
            User user = service.findUserByEmail(email);
            try{
                if (user != null) {
                    response.getWriter().print("false");
                } else {
                    response.getWriter().print("true");
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
