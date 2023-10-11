package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

@WebServlet("/mvcboard/list.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		MVCBoardDAO dao = new MVCBoardDAO();
		
		Map<String, Object> map = new HashMap<>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		if(searchWord != null) { //검색어 있으면 맵에 저장
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.selectCount(map); //게시물 개수
		
		/*페이지 처리 start*/
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		//현재 페이지 확인
		int pageNum = 1; // 기본값
		String pageTemp = req.getParameter("pageNum");
		if(pageTemp!=null &&!pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp); //요청받은 페이지로 수정
		
		//목록에 출력할 게시물 범위 계산
		int start = (pageNum -1) *pageSize+1;
		int end = pageNum * pageSize;
		map.put("start", start);
		map.put("end", end);
		/*페이지 처리 end*/
		
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		
		//게시물 목록 받기
		dao.close();
		
		//뷰에 전달할 매개변수 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount",totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req,resp);
	}
	
	
}
