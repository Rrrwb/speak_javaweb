package net.xdclass.forum.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字符编码过滤器
 * 解决中文乱码问题
 */
@WebFilter(
    urlPatterns = "/*",
    initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8"),
        @WebInitParam(name = "forceEncoding", value = "true")
    }
)
public class CharacterEncodingFilter implements Filter {
    
    private String encoding = "UTF-8";
    private boolean forceEncoding = false;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null && !encodingParam.trim().isEmpty()) {
            this.encoding = encodingParam;
        }
        
        String forceEncodingParam = filterConfig.getInitParameter("forceEncoding");
        if (forceEncodingParam != null) {
            this.forceEncoding = Boolean.parseBoolean(forceEncodingParam);
        }
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 设置请求编码
        if (this.forceEncoding || httpRequest.getCharacterEncoding() == null) {
            httpRequest.setCharacterEncoding(this.encoding);
        }
        
        // 设置响应编码
        if (this.forceEncoding || httpResponse.getCharacterEncoding() == null) {
            httpResponse.setCharacterEncoding(this.encoding);
            httpResponse.setContentType("text/html; charset=" + this.encoding);
        }
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        // 清理资源
    }
}