package main.webapp.web.servlet;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet{
    public HelloServlet(){
        System.out.println("1 构造器方法");
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
//service方法是专门处理请求和响应的
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("HelloServlet被访问了");
        //类型转换，因为它有getMethod()方法
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //获取请求的方式
        String method = httpServletRequest.getMethod();

         if("GET".equals(method)){
             doGet();
         }else if("POST".equals(method)){
             doPost();
         }

    }

    //get请求操作
    public void doGet(){

    }
    //post请求操作
    public void doPost(){

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
