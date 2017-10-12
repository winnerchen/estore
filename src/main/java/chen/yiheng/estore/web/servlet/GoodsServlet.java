package chen.yiheng.estore.web.servlet;

import chen.yiheng.estore.domain.Good;
import chen.yiheng.estore.service.GoodsService;
import chen.yiheng.estore.utils.MyFactory;
import chen.yiheng.estore.utils.UploadUtils;
import com.mysql.jdbc.EscapeTokenizer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @Author: Yiheng Chen
 * @Description:
 * @Date: Created in 12:09 2017/10/11
 * @Modified by:
 */
public class GoodsServlet extends BaseServlet {
    public String list(HttpServletRequest request, HttpServletResponse response) {
        GoodsService goodsService = MyFactory.getInstance(GoodsService.class);
        List<Good> list = goodsService.findAllGoods();
        request.setAttribute("goodsList", list);
        return "/goods.jsp";
    }
    public void save(HttpServletRequest request, HttpServletResponse response) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            throw new RuntimeException("您提交的表单不是一个有效的上传请求,请设置enctype=multipart/form-data");
        }
        DiskFileItemFactory itemFactory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(itemFactory);
        // 设置上传文件的总大小
        upload.setSizeMax(1024 * 1024 * 10);// 10m
        // 设置上传文件单个大小
        upload.setFileSizeMax(1024 * 1024 * 2);// 2M long 时间 ms 大小 字节 byte int

        try {
            List<FileItem> items = upload.parseRequest(request);
            if (items != null && items.size() != 0) {
                Good good = new Good();
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // 是普通表单元素,获取该项的name属性值,通过if语句判断,并设置相应的value的值
                        String name = item.getFieldName();
                        if ("name".equals(name)) {
                            good.setName(item.getString("utf-8"));
                        } else if ("estoreprice".equals(name)) {
                            good.setEstoreprice(Double.parseDouble(item
                                    .getString("utf-8")));
                        } else if ("marketprice".equals(name)) {
                            good.setMarketPrice(Double.parseDouble(item
                                    .getString("utf-8")));
                        } else if ("category".equals(name)) {
                            good.setCategory(item.getString("utf-8"));
                        } else if ("num".equals(name)) {
                            good.setNum(Integer.parseInt(item.getString("utf-8")));
                        } else if ("description".equals(name)) {
                            good.setDescription(item.getString("utf-8"));
                        }

                    } else {
                        //如果是文件上传
                        String fileName = item.getName();
                        //解决浏览器兼容问题
                        fileName = UploadUtils.subFileName(fileName);
                        //生成随机文件名
                        fileName = UploadUtils.generateRandomFileName(fileName);
                        String randomDir = UploadUtils.generateRandomDir(fileName);
                        good.setImgUrl("/upload" + randomDir + "/"
                                + fileName);
                        String realPath = getServletContext().getRealPath("/upload" + randomDir);
                        File file = new File(realPath);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        try {
                            item.write(new File(file, fileName));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            item.delete();
                        }
                    }
                }
                GoodsService service = MyFactory.getInstance(GoodsService.class);
                service.addGoods(good);
                response.setContentType("text/html;charset=utf-8");
                String contextPath = getServletContext().getContextPath();

                response.getWriter()
                        .println(
                                "<a href='javascript:void(0);' onclick='window.history.go(-1);'>继续添加</a>&nbsp;<a href='"
                                        + contextPath
                                        + "/goodsServlet?method=findAll'>查询商品列表</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
