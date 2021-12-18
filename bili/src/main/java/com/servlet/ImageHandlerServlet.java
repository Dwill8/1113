package com.servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.utils.PicZoom;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageHandlerServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        //图片的路径
        String srcImgFileName = request.getParameter("imgPath");
        if(null == srcImgFileName || "".equals(srcImgFileName)) {
            throw new ServletException("图像参数错误！");
        }
        response.setContentType("image/jpeg");

        ServletOutputStream sos = response.getOutputStream();

        //调用PicZoom类的静态方法zoom对原始图像进行缩放。
        BufferedImage buffImg = PicZoom.zoom(srcImgFileName);
        //创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。
        JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(sos);
        //编码BufferedImage对象到JPEG数据输出流。
        jpgEncoder.encode(buffImg);
        sos.close();
    }
}
