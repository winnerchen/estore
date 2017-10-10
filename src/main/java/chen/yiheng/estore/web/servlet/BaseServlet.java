package chen.yiheng.estore.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 20:11 2017/10/9
 * @Modified by:
 */
public class BaseServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("method");
        if (methodName == null) {
            req.getRequestDispatcher("/index").forward(req, resp);

        } else {
            try {
                Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                String result= (String)method.invoke(this, req, resp);
                if (result != null) {
                    if (result.startsWith("/#")) {
                        result = result.substring(2);
                        resp.sendRedirect(req.getContextPath() + "/" + result);
                    } else {
                        req.getRequestDispatcher(result).forward(req, resp);
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
