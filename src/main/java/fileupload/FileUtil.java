package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	// 파일 업로드
	public static String uploadFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException {
		Part part = req.getPart("ofile");
		
		String partHeader = part.getHeader("content-disposition");
		
		// 헤더값에서 파일명 잘라내기
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", ""); // "파일명.jpg" ==> 파일명.jpg 
		originalFileName =originalFileName.replace(" ", "_"); // 파일명 사이 공백 ==> 언더바로 변경
		
		// 전송된 파일이 있다면 디렉토리에 저장
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory+File.separator+originalFileName); // file separator => / 또는 \ (os에 따라 자동으로 바꿔줌)
		}
		return originalFileName ;
	}
	
	// 파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
		//원본 파일의 확장자 잘라내기
		String ext = fileName.substring(fileName.lastIndexOf("."));
		
		//날짜 및 시간을 통해 파일명 생성
		String now = new SimpleDateFormat("yyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		
		//기존 파일명을 새로운 파일명으로 변경
		File oldFile = new File(sDirectory+File.separator+fileName);
		File newFile = new File(sDirectory+File.separator+newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
	
	//multiple 속성 추가로 2개 이상의 파일 업로드
	public static ArrayList<String> multpleFile(HttpServletRequest req, String sDirectory) throws ServletException, IOException {
		ArrayList<String> listFileName = new ArrayList<>();
		Collection<Part> parts = req.getParts();
		for(Part part : parts) {
			if(!part.getName().equals("ofile")) continue;
			String partHeader = part.getHeader("content-disposition");
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"", "");
			if(!originalFileName.isEmpty()) {
				part.write(sDirectory+File.separator+originalFileName);
			}
			listFileName.add(originalFileName);
		}
		
		return listFileName;
	}
	
	//명시한 파일을 찾아 다운로드
	public static void download(HttpServletRequest req, HttpServletResponse resp, String directory, String sfileName,String ofileName) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		try {
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64")==-1) {
				ofileName = new String(ofileName.getBytes("UTF-8"),"ISO-8859-1");
			} else {
				ofileName = new String(ofileName.getBytes("KSC5601"),"ISO-8859-1");
			}
			
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\""+ofileName+"\"");
			resp.setHeader("Content-Length",""+file.length());
			
			OutputStream oStream = resp.getOutputStream();
			
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = iStream.read(b))>0) {
				oStream.write(b,0,readBuffer);
			}
			iStream.close();
			oStream.close();
		} catch(FileNotFoundException e) {
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		} catch(Exception e) {
			System.out.println("예외가 발생하였습니다. ");
			e.printStackTrace();
		}
	}
	
	//지정한 위치의 파일 삭제
	public static void deleteFile(HttpServletRequest req, String directory, String filename) {
		String sDirectory = req.getServletContext().getRealPath(directory);
		File file = new File(sDirectory + File.separator+filename);
		if(file.exists()) {
			file.delete();
		}
	}
}
