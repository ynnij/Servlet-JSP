package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebFilter(urlPatterns = "/15FilterListener/BasicFilter.jsp")
//@WebFilter(urlPatterns = "*") //모든  JSP
public class BasicFilter1 implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("before - BasicFilter1");
		
		
		//chain.doFilter(req, resp); // 다음 filter의 doFilter()를 호출
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpServletRequest request= (HttpServletRequest )req;
		
		String uri = request.getRequestURI();
		
		if(uri.equals("/MustHaveJSP/HelloJSP.jsp"))
			chain.doFilter(req, resp); 
		else
			response.sendRedirect("/MustHaveJSP/HelloJSP.jsp");
		
		System.out.println("after - BasicFilter1");
	}
	
}
