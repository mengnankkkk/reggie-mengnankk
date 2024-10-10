package org.mengnankk.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.mengnankk.comon.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 日志输出，记录拦截的请求URI
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}", requestURI);

        // 定义不需要拦截的路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        // 检查是否需要放行
        boolean shouldSkip = check(urls, requestURI);

        if (shouldSkip) {
            log.info("本次请求不需要处理",requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        if(request.getSession().getAttribute("employee")!=null){
            log.info("用户已经登录{}",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }
        response.getWriter().write(JSON.toJSONString(R.error("Notlogin")));
        return;
    }

    // 检查请求路径是否与定义的路径匹配
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }
}
