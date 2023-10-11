package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/12Servlet/Mission")
public class Mission extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String heightStr = req.getParameter("heightStr");
		String timeStr = req.getParameter("timeStr");
		if(heightStr!=null && timeStr!=null) {
			int h = Integer.parseInt(heightStr);
			int t = Integer.parseInt(timeStr);
			double resultDouble = h -0.5 * 9.81 * Math.pow(t, 2);
			String result = t+"초 후 위치 : "+resultDouble+"m";
			result+="<br/>"+(15-t)+"초 후 지면 도착";
			if(resultDouble<0) result = "지면";
			req.setAttribute("result", result);
		}
		req.getRequestDispatcher("/12Servlet/Mission.jsp").forward(req, resp);
		
		
		
	}
}
