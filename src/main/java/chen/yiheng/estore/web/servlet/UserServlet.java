package chen.yiheng.estore.web.servlet;

import chen.yiheng.estore.domain.User;
import chen.yiheng.estore.service.UserService;
import chen.yiheng.estore.utils.ApacheMailUtils;
import chen.yiheng.estore.utils.MD5Utils;
import chen.yiheng.estore.utils.MyFactory;
import chen.yiheng.estore.utils.ProxyUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

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
            try {
                if (user != null) {
                    response.getWriter().print("false");
                } else {
                    response.getWriter().print("true");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        String code = request.getParameter("code");
        String sessionCode = (String) request.getSession().getAttribute("code");
        if (code != null) {
            if (code.trim().equalsIgnoreCase(sessionCode)) {
                response.getWriter().print("true");
            } else {
                response.getWriter().print("false");
            }
        }
    }

    public String save(HttpServletRequest request, HttpServletResponse response) {
        try {
            //create a new user
            User user = new User();
            //get all parameters
            Map<String, String[]> map = request.getParameterMap();
            //encapsulate parameters into user
            BeanUtils.populate(user, map);
            //remove checkcode stored in session
            request.getSession().removeAttribute("code");
            String activeCode = UUID.randomUUID().toString().replace("-", "");
            user.setActiveCode(activeCode);
            user.setPassword(MD5Utils.md5(user.getPassword()));
            UserService service = MyFactory.getInstance(UserService.class);
            service.save(user);
            String msg = "<a href='127.0.0.1:8080/userServlet?method=activeCode&activecode="
                    + activeCode + "'>当你看到这个邮件的时候就说明你注册成功啦,赶紧点击激活吧!</a>";
            ApacheMailUtils.sendMail(user.getEmail(), "Estore商城激活邮件通知", msg);
            return "/#login.jsp";
        } catch (Exception e){
            throw new RuntimeException("fail to register");
        }
    }

    public String activeCode(HttpServletRequest request, HttpServletResponse response) {
        String activeCode = request.getParameter("activecode");
        UserService service = MyFactory.getInstance(UserService.class);
        User existUser = service.findUserByActiveCode(activeCode);
        if (existUser == null) {
            request.setAttribute("activecode_error", "fail to register, please try again");
            return "/register.jsp";
        } else {
            long currentTime = System.currentTimeMillis();
            Timestamp registerTime = existUser.getRegisterTime();
            long time = registerTime.getTime();
            // 如果查询到用户但是激活的链接超时,跳转到注册页面并提示错误信息
            if (currentTime - time > 3600 * 24 * 1000) {
                request.setAttribute("activecode_error", "链接已超时,请重新注册");
                // 从数据库中移除用户
                service.deleteUser(existUser.getId());
                return "/register.jsp";
            } else if (existUser.getStatus() == 1) {
                // 如果用户已激活,重定向到登录页面
                request.setAttribute("activecode_error", "您已激活,请登录");
                return "/login.jsp";

            } else {
                // 如果用户未激活切链接未超时,那么激活用户后跳转到登录页面
                service.activeUser(existUser.getId());
                return "/#login.jsp";
            }
        }

    }
    public String login(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            user.setPassword(MD5Utils.md5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserService service = MyFactory.getInstance(UserService.class);
        //UserService proxyService = new ProxyUtils<UserService>(service).getProxy();
        User existUser = service.login(user);
        if (existUser == null) {
            request.setAttribute("login_error_msg", "用户名或密码错误");
            return "/login.jsp";
        } else {
            if (existUser.getStatus() == 1) {
                request.getSession().setAttribute("loginUser", existUser);
                if (request.getParameter("remember") != null) {
                    Cookie c = new Cookie("userInfo", user.getEmail() + "_"
                            + user.getPassword());
                    c.setMaxAge(3600 * 24);
                    c.setPath("/");
                    response.addCookie(c);
                }
                // logger.info("用户登录,用户的信息是: "+existUser);
                return "/#index.jsp";
            } else {
                request.setAttribute("login_error_msg", "您未激活用户,请激活.");
                return "/login.jsp";
            }

        }

    }
}
