package chen.yiheng.estore.web.servlet;

import chen.yiheng.estore.domain.City;
import chen.yiheng.estore.utils.JDBCUtils;
import flexjson.JSONSerializer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 16:11 2017/10/16
 * @Modified by:
 */
public class LoadCityServlet extends BaseServlet {
    public void load(HttpServletRequest request, HttpServletResponse response) {
        String pid = request.getParameter("pid");
        String sql = "select * from province_city_district where pid = ?";
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<City> citys = runner.query(sql, new BeanListHandler<City>(City.class), Integer
                    .parseInt(pid));
            // 2: flexjson 将java对象集合 转换 json [{},{},{}]
            JSONSerializer serializer = new JSONSerializer();// json序列化核心对象
            // 3: 定制序列化字段 排除不想要序列化对象属性
            serializer.exclude("pid", "class");
            // serialize序列化json对象字符串 {"class":"cn.itcast.ajax.domain.City","id":12,"name":"天津",
            // "pid":0}
            String jsonstring = serializer.serialize(citys);
            // 4: 回送给ajax引擎
            response.setContentType("text/json;charset=utf-8");// 服务器告知浏览器 响应数据类型 json
            // 5 发送 给 ajax引擎对象 XMLHttpRequest
            System.out.println("服务器序列化的三级联动json数据 = " + jsonstring);
            response.getWriter().print(jsonstring);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
