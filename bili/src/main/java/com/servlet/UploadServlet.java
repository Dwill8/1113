package com.servlet;

import com.bean.Video;
import com.service.VideoService;
import com.service.impl.VideoServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class UploadServlet extends HttpServlet {
    private VideoService videoService = new VideoServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UploadServlet的dopost");
        System.out.println(request.getParameter("title"));
        System.out.println(request.getParameter("title2"));
        System.out.println(request.getAttributeNames());
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        request.setCharacterEncoding("utf-8");
        //文件名中文乱码处理也可以如此写
//        upload.setHeaderEncoding("utf-8");

        //设置缓冲区大小与临时文件目录
        factory.setSizeThreshold(1024*1024*10);
        File uploadTemp = new File("D:\\jar_package\\upload_temp");
        uploadTemp.mkdirs();
        factory.setRepository(uploadTemp);

        //设置单个文件大小限制
        upload.setFileSizeMax(1024*1024*10);
        //设置所有文件总和大小限制
        upload.setSizeMax(1024*1024*30);

        String videoTitle = request.getParameter("title");

        try {
            List<FileItem> list = upload.parseRequest(request);
            System.out.println(list);
            for (FileItem fileItem:list){
                // .isFormField()判断数据是一个普通文本表单字段，还是一个文件表单字段，false为文件表单
                // .getName()用来获得文件上传字段中的文件名，如果是普通字段，则返回null
                    if (!fileItem.isFormField() && fileItem.getName() != null && !"".equals(fileItem.getName())){
                        String filName = fileItem.getName();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    String time = sdf.format(new Date());
                    String id = String.valueOf(request.getSession().getAttribute("user_id"));
                    //时间戳+id
                    String fileStoreName = time + "_" +id;

//                    //利用UUID生成伪随机字符串，作为文件名避免重复
//                    String uuid = UUID.randomUUID().toString();
                    //获取文件后缀名
                    String suffix = filName.substring(filName.lastIndexOf("."));
                    //判断径文件类型
                        //
                        //                    //获取文件上传目录路，在项目部署路径下的upload目录里。若想让浏览器不能直接访问到图片，可以放在WEB-INF下
                    //String uploadPath = request.getSession().getServletContext().getRealPath("./WEB-INF/upload");
                    String uploadPath = "D:\\jar_package\\upload_temp";

                    File file = new File(uploadPath);
                    file.mkdirs();
                    //写入文件到磁盘，该行执行完毕后，若有该临时文件，将会自动删除
//                    fileItem.write(new File(uploadPath,uuid + suffix));
                    fileItem.write(new File(uploadPath,fileStoreName + suffix));
                    videoService.saveVideo(new Video(null, fileStoreName + suffix, filName, uploadPath, videoTitle, null, null, null, (Integer)request.getSession().getAttribute("user_id"), null, null, 0, null, 1));

                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
