package icia.project.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {

	// 생성자
	public DownloadView() {
		// 뷰의 출력 형식을 설정
		setContentType("application/download; charset=utf-8");
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 저장된 데이터를 불러오기 ㅣ- Controller에서 동일한 이름으로 저장
		File file = (File) model.get("downloadFile");

		// 응답 화면의 타입과 크기 설정
		response.setContentLength((int) file.length());
		response.setContentType(getContentType());

		//클라이언트의 운영체제와 브라우저 정보를 가져오기
		//자바스크립트에서는 navigator.userAgent
		String userAgent = request.getHeader("User-Agent");
		//브라우저가 IE인지 확인
		//브라우저 정보에 rv가 포함되어 있으면 IE
		boolean ie = userAgent.indexOf("rv")>-1;
		
		//브라우저 종류에 따라 파일이름 인코딩 하기
		String fileName = null;
		if(ie)
			fileName=URLEncoder.encode(file.getName(),"utf-8");
		else
			fileName=new String(file.getName().getBytes("utf-8"),"iso-8859-1");
		
		//파일 이름과 형식 설정
		response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//파일의 내용을 읽어서 response의 outputStream에 출력하기
		// 실제적인 다운로드
		OutputStream out= response.getOutputStream();
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(file);
			//스프링의 파일 복사 클래스를 이용해서 files의 내용을 out에 복사
			FileCopyUtils.copy(fis, out);
		}
		finally{
			if(fis!=null){
				try{
					fis.close();
				}
				catch(Exception e){}
			}
		}
		out.flush();
	}

}
