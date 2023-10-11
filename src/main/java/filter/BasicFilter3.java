package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

//@WebFilter(urlPatterns = "/15FilterListener/BasicFilter.jsp")
//@WebFilter(urlPatterns = "*") //모든  JSP
public class BasicFilter3 implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("before - BasicFilter3");
		
		
		chain.doFilter(req, resp);
		
		
		System.out.println("after - BasicFilter3");
	}
	
}
