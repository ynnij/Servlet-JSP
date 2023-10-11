package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";

		// 전체 페이지 수 계산
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

		// 이전 페이지 블록 바로가기 출력
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if (pageTemp != 1) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1) + "'>[이전 블록]</a>";
		}

		// 각 페이지 번호 출력
		int blockCount = 1;
		while (blockCount <= blockPage && pageTemp <= totalPages) {
			if (pageTemp == pageNum) {
				pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
			} else {
				pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}

		// 다음 페이지 블록 바로가기 출력
		if (pageTemp <= totalPages) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>[다음 블록]</a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + "'>[마지막 페이지]</a>";
		}

		return pagingStr;
	}

	public static String pagingStr1(int totalCount, int pageSize, int blockPage, int pageNum, String reqUrl) {
		String pagingStr = "";

		// 전체 페이지 수 계산
		int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));
		int cnt = (blockPage/2)+1;
		System.out.println(blockPage);
		
		// 이전 페이지 블록 바로가기 출력
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		System.out.println(pageNum);
		if (pageNum > cnt) {
			pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
			pagingStr += "&nbsp;";
		}

		// 각 페이지 번호 출력
		int blockCount = 1;
		int pageTemp2 = pageNum-(blockPage/2); 
		if((pageNum>=1 && pageNum<= cnt-1)) {
			while (blockCount <= blockPage && pageTemp <= totalPages) {
				if (pageTemp == pageNum) {
					pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
				} else {
					pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp + "'>" + pageTemp + "</a>&nbsp;";
				}
				pageTemp++;
				blockCount++;
			}
		}
		if(pageNum>=cnt && pageNum<=totalPages) {
			while(pageTemp2<=pageNum+(blockPage/2)) {
				if(pageTemp2>totalPages) break;
				if(pageTemp2==pageNum) {
					pagingStr += "&nbsp;" + pageTemp2 + "&nbsp;";
				}
				else {
					pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp2 + "'>" + pageTemp2 + "</a>&nbsp;";
				}
				pageTemp2++;
			}
		}
		

		// 다음 페이지 블록 바로가기 출력
		if (pageNum <= totalPages-cnt-1) {
			pagingStr += "&nbsp;";
			pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages + "'>[마지막 페이지]</a>";
		}

		return pagingStr;
	}
}
