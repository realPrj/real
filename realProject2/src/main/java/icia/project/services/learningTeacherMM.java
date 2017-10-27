package icia.project.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import icia.project.bean.BoardBean;
import icia.project.bean.DbBoardBean;
import icia.project.dao.IMybatis;
import icia.project.dao.TransactionExe;

@Service
public class learningTeacherMM extends TransactionExe {

	@Autowired
	private IMybatis dao;
	@Autowired
	private ProjectUtils session;

	private ModelAndView mav;

	private MultipartHttpServletRequest mtfRequest = null;

	public ModelAndView entrance(int serviceCode,Object ...object)  {

		switch(serviceCode) {

		case 1:	

			break;

		case 2:

			break;

		case 3:	//공지사항 페이지
			mav = learningNoticePage((BoardBean)object[0]);
			break;

		case 4:	//질문게시판 페이지
			mav = learningQuestion((BoardBean)object[0]);
			break;

		case 5:

			break;

		case 6:	

			break;

		case 7:	// 오답노트
			mav = learningWANPage();
			break;

		case 8: // 공지사항 내용확인
			mav = tclearningNoticeCTX((BoardBean)object[0]);
			break;

		case 9: // 공지사항 글쓰기 페이지 이동
			mav = tclearningNoticeInsert((BoardBean)object[0]);
			break;

		case 10: // 공지사항 글쓰기
			mtfRequest = ((MultipartHttpServletRequest)object[1]);
			mav = tclearningNoticeInsertOk((BoardBean)object[0]);
			break;

		case 11: // 공지사항 수정 페이지
			mav = tclearningNoticeUpdatePage((BoardBean)object[0]);
			break;

		case 12:	// 자료실 글쓰기
			mtfRequest = ((MultipartHttpServletRequest)object[1]);
			mav = datahousemain((BoardBean)object[0]);
			break;

		case 13:	// 자료실
			mav = dataview((BoardBean)object[0]);
			break;

		case 14:	// 자료실 글보기
			mav = learningDataCXT((BoardBean)object[0]);
			break;


		case 15:	//자료실 글삭제하기
			mav = learningDataDelete((BoardBean)object[0]);
			break;

		case 16:	// 자료실 글 업데이트
			mav = learningDataUpdate((BoardBean)object[0]);
			break;

		case 17:	// 오답노트 코멘트 페이지
			mav = learningWANCXTPage((BoardBean)object[0]);
			break;

		case 18:	// 오답노트 코멘트 등록 페이지
			mav = learningWANCommentInsert((BoardBean)object[0]);

			break;
		case 19:	// 오답노트 코멘트 수정 페이지
			mav = learningWANCMUpdatePage((BoardBean)object[0]);
			break;
		case 20:	// 오답노트 코멘트 수정
			mav = learningWANCMUpdate((BoardBean)object[0]);
			break;
		case 21:	// 오답노트 코멘트 삭제
			mav = learningWANCMDelete((BoardBean)object[0]);
			break;

		case 30:	// 선생님 공지사항 수정
			mav = tclearningNoticeUpdate((BoardBean)object[0]);
			break;

		case 31:	// 선생님 공지사항 삭제
			mav = tclearningNoticeDelete((BoardBean)object[0]);
			break;



		}

		return mav;

	}

	private ModelAndView learningNoticePage(BoardBean board) { // 공지사항 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("identity"));
			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			ar = dao.tclearningNoticeList(board);
			mav.addObject("content", tclearningNoticeList(board,ar));


			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNotice");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String tclearningNoticeList(BoardBean board, ArrayList<BoardBean> ar) { // 공지사항 리스트
		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>제목</td>");
		sb.append("<td>날짜</td>");
		sb.append("<td>작성자</td>");
		sb.append("</tr>");
		for(int i=0; i<ar.size(); i++) {
			sb.append("<tr>");	
			//sb.append("<input type=\"hidden\" name=\"boardTitle\" value='" + board.getBoardTitle() + "'/>");
			sb.append("<td onClick=\"confirm('"+ ar.get(i).getBoardTitle() +"','" + ar.get(i).getBoardDate() + "','"+ board.getId() +"')\">" + ar.get(i).getBoardTitle() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardDate() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardId() + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");
		sb.append("<input type=\"button\" value=\"글쓰기\" onClick=\"noticeInsert()\"/>");

		return sb.toString();
	}


	private ModelAndView tclearningNoticeCTX(BoardBean board) { // 공지사항 내용확인

		mav = new ModelAndView();
		boolean transaction = false;
		DbBoardBean bb = new DbBoardBean();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			bb.setRoomCode((String)session.getAttribute("roomCode"));
			
			board = dao.tclearningNoticeConfirm(board);

			System.out.println("공지사항 내용확인 메서드 진입"+board.getBoardTitle()+board.getRoomCode());
			mav.addObject("content", getTclearningNoticeCTX(board));


			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeCXT");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getTclearningNoticeCTX(BoardBean board) { // 공지사항 내용 끌고오기
		/*ViewService view = new ViewService(); 

		bb.setCutRoute(bb.getBoardRoute().substring(0,68));	// 루트만
		String route = bb.getCutRoute();

		bb.setCutContent(bb.getBoardRoute().substring(68));	// 파일이름

		List<String> list = view.getList(bb);*/
		/*mav.addObject("list",list);
		mav.addObject("theme",bb.getBoardTitle());
		mav.addObject("content",bb.getBoardContent());
		mav.addObject("date",bb.getBoardDate());
		mav.addObject("writeId",bb.getBoardId());
		mav.addObject("route",route);
		mav.addObject("file",bb.getCutContent());*/

		StringBuffer sb = new StringBuffer();
		/*sb.append("<c:forEach var=\"file\" items='"+ list +"'>");
		sb.append("<tr>");
		sb.append("<td><a href=\"download.action?name='"+ bb.getCutContent() +"'\">'"+ bb.getCutContent() +"'</a></td>");
		sb.append("</tr>");
		sb.append("</c:forEach>");*/
		sb.append("<table>");
		/*sb.append("<tr>");
		sb.append("<td>" + route + "</td>");
		sb.append("</tr>");*/
/*		sb.append("<tr>");
		sb.append("<td>" + board.getCutContent() + "</td>");
		sb.append("</tr>");*/
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardTitle() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardDate() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardId() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardContent() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + board.getBoardRoute() + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type=\"button\" value=\"목록\" onClick=\"menu('3')\"/>");
		sb.append("<input type=\"button\" value=\"수정\" onClick=\"update('"+ board.getBoardTitle() +"','"+ board.getBoardContent() +"','"+ board.getBoardDate() +"')\"/>");
		sb.append("<input type=\"button\" value=\"삭제\" onClick=\"boardDelete('"+ board.getRoomCode() +"','"+ board.getBoardDate() +"')\"/>");
		return sb.toString();
	}


	private ModelAndView tclearningNoticeInsert(BoardBean board) { // 공지사항 글쓰기 페이지		
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");


			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeInsert");
			setTransactionResult(transaction);
		}
		return mav;

	}

	private ModelAndView learningQuestion(BoardBean board) { // 질문게시판 페이지
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			mav.addObject("content",session.getAttribute("roomCode") + "의 질문게시판");

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningQuestion");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningWANPage() { // 오답노트 페이지

		mav = new ModelAndView();
		BoardBean board;
		ArrayList<BoardBean> boardList = null;
		ArrayList<BoardBean> typeSum = null;
		ArrayList<BoardBean> yearCode = null;
		StringBuffer sb;
		StringBuffer sum;
		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board = new BoardBean();
			board.setRoomCode((String)session.getAttribute("roomCode"));

			boardList = dao.learningWANListGet(board);

			yearCode = dao.learningWANYearCodeOneGet(board);

			sb = new StringBuffer();
			sum = new StringBuffer();
			sb.append("<select id = 'yearSelect'>");
			sb.append("<option></option>");

			for(int i =0; i < yearCode.size(); i++) {
				board = new BoardBean();
				board.setRoomCode(boardList.get(0).getRoomCode());
				board.setYearCode(yearCode.get(i).getYearCode());

				sb.append("<option>"+yearCode.get(i).getYearCode()+"</option>");

				typeSum = dao.learningWANTypeSum(board);

				sum.append("<div id='"+yearCode.get(i).getYearCode().substring(0, 4)+"' >");
				for(int y = 0; y < typeSum.size(); y++) {
					board = new BoardBean();
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setYearCode(yearCode.get(i).getYearCode());
					board.setRoomSB(dao.learningSBCodeGet(board));
					board.setTypeCode(typeSum.get(y).getTypeCode());
					board.setTypeName(dao.learningTypeNameGet(board));
					board.setTypeSum(typeSum.get(y).getTypeSum());	
					sum.append(board.getTypeName()+" : "+ board.getTypeSum()+"<br>");

				}
				sum.append("</div>");

				if(i == 0) {
					mav.addObject("lowest", yearCode.get(i).getYearCode());
				}

			}

			sb.append("</select>");
			mav.addObject("size", yearCode.size());
			mav.addObject("yearSelect", sb.toString());
			mav.addObject("typeSumb", sum.toString());

			sb = new StringBuffer();
			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("게시글 번호");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("년도");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("문제유형");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("문제 번호");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("선생님 코멘트");
			sb.append("</td>");
			sb.append("</tr>");

			for(int i = 0; i < boardList.size(); i++ ) {

				board = new BoardBean();
				board.setRoomCode(boardList.get(0).getRoomCode());
				board.setYearCode(boardList.get(i).getYearCode());
				board.setTypeCode(boardList.get(i).getTypeCode());
				board.setRoomSB(dao.learningSBCodeGet(board));
				board.setYearName(dao.learningYearNameGet(board));
				board.setTypeName(dao.learningTypeNameGet(board));

				sb.append("<tr>");
				sb.append("<td>");
				sb.append(i+1);
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getYearName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getTypeName());
				sb.append("</td>");
				sb.append("<td>");
				sb.append(boardList.get(i).getNumberCode());
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<input type='button' value='선생님 코멘트' onClick='commentCheck("+boardList.get(i).getBoardCode()+")' />");
				sb.append("</td>");
				sb.append("</tr>");
			}

			sb.append("</table>");
			mav.addObject("content", sb.toString());
			page = "learningWAN";
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView datahousemain(BoardBean board) { // 자료실 인설트
		mav = new ModelAndView();
		boolean transaction = false;
		fileupload(board,mtfRequest);


		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {			
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("tcId"));
			
			
			session.getAttribute("roomCode");
			if(dao.referenceInsert(board) != 0) {
				System.out.println("나 성공햇다 ");
				transaction = true;
			}else {
				System.out.println("실패");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}finally {
			setTransactionResult(transaction);
		}
		dataview(board);
		mav.setViewName("learningData");
		return mav;
	}

	private ModelAndView dataview(BoardBean board) { // 자료실 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> bb = null;
		String message = "";
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			mav.addObject("content",session.getAttribute("roomCode") + "자료실");
			board.setId((String)session.getAttribute("tcId"));
			board.setRoomCode((String)session.getAttribute("roomCode"));
	

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			bb = dao.datalist(board);
			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>제목</td>");
			sb.append("<td>날짜</td>");
			sb.append("<td>아이디</td>");
			sb.append("</tr>");
			for(int i=0; i<bb.size(); i++) {
				sb.append("<tr>");
				sb.append("<td>" + "<input type='button' value='"+bb.get(i).getBoardTitle()+"' onClick=viewData(\'"+bb.get(i).getRoomCode()+"\',"+"\'"+bb.get(i).getBoardTitle()+"\',"+"\'"+bb.get(i).getBoardDate()+"\') />" + "</td>");
				sb.append("<td>" + bb.get(i).getBoardDate() + "</td>");
				sb.append("<td>" + bb.get(i).getBoardId() + "</td>");
				sb.append("</tr>");

			}
			sb.append("</table>");

			mav.addObject("message", message);
			mav.addObject("datalist", sb.toString());
			mav.setViewName("learningData");

			transaction = true;

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		mav.setViewName("learningData");
		return mav;
	}

	private ModelAndView learningWANCXTPage(BoardBean board) { // 오답노트 코멘트 페이지 이동

		mav = new ModelAndView();
		boolean transaction = false;
		String page = null;
		StringBuffer sb = new StringBuffer();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));

			if(dao.learningWANCommentCheck(board) != 0) {	// 코멘트 있음

				board = dao.learningWANCommentGet(board);

				sb.append("<table>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("내용");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append(board.getBoardContent());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("파일첨부");
				sb.append("</td>");
				sb.append("<td>");
				sb.append(board.getBoardRoute());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("날짜 : "+board.getBoardDate());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("아이디 : "+board.getBoardId());
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<input type='button' value='수정' onClick='learningWANCMUpdatePage("+board.getBoardCode()+")'/>" 
						+ "<input type='button' value='삭제' onClick=learningWANCMDelete('"+board.getBoardCode()+"','"+board.getRoomCode()+"') />");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");

				page="learningWANCXT";
				transaction = true;

			}else {	// 코멘트 없음

				sb.append("<input type='button' value='코멘트 등록' onClick='commentInsertPage("+board.getBoardCode()+")' />");
				page = "learningWANCXT";
				transaction = true;
			}

			mav.addObject("content", sb.toString());

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView fileupload(BoardBean board,MultipartHttpServletRequest mtfRequest) {

		mav = new ModelAndView();
		
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		String load = mtfRequest.getParameter("load");
		String path = "E:\\realTest\\realProject2\\src\\main\\webapp\\WEB-INF\\uploadFiles\\"+load+"\\";

		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); // 원본 파일 명
			long fileSize = mf.getSize(); // 파일 사이즈
			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);
			String safeFile = path + originFileName;
			board.setBoardRoute(safeFile);
			try {
				mf.transferTo(new File(safeFile));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return mav;
	}

	private ModelAndView learningWANCommentInsert(BoardBean board) { // 오답노트 코멘트 페이지 이동

		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("tcId"));

			dao.learningWANCommentInsert(board);

			transaction = true;

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningWANCMUpdatePage(BoardBean board) { // 오답노트 코멘트 수정 페이지 이동

		mav = new ModelAndView();
		boolean transaction = false;
		String page = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("tcId"));

			board = dao.learningWANCommentGet(board);

			page="learningWANCMUpdate";
			mav.addObject("boardContent", board.getBoardContent());
			mav.addObject("boardRoute", board.getBoardRoute());
			mav.addObject("boardCode", board.getBoardCode());
			transaction = true;

		}catch(Exception ex){


			mav.addObject("message", "alert('코멘트 등록을 실패 하셨습니다')");
			mav.addObject("windowclose", "window.close()");
			page="learningWANCXT";


		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}


	private ModelAndView learningWANCMUpdate(BoardBean board) { // 오답노트 코멘트 페이지 이동

		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));

			dao.learningWANCMUpdate(board);

			transaction = true;

		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}


	private ModelAndView learningWANCMDelete(BoardBean board) { // 오답노트 코멘트 삭제 이동

		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			dao.learningWANCMDelete(board);

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView tclearningNoticeInsertOk(BoardBean board) { // 공지사항 글쓰기
		mav = new ModelAndView();
		boolean transaction = false;
		fileupload(board,mtfRequest);
		
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setBoardId((String)session.getAttribute("tcId"));
			System.out.println(session.getAttribute("tcId"));

			if(dao.tclearningNoticeInsert(board) != 0) {
				System.out.println("글좀쓰자제발");
				transaction = true;
			}

		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView learningDataCXT(BoardBean board) { // 자료실 페이지 자세히 보기

		ViewService view = new ViewService(); 
		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		String message = "";
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");
			mav.addObject("content",session.getAttribute("roomCode") + "자료실");
			board.setId((String)session.getAttribute("tcId"));
			board.setRoomCode((String)session.getAttribute("roomCode"));

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");

			



			DbBoardBean bb = dao.learningDataCXT(board);	// 전체 루트(파일이름까지)

			bb.setCutRoute(bb.getBoardRoute().substring(0,68));	// 루트만
			String route = bb.getCutRoute();

			bb.setCutContent(bb.getBoardRoute().substring(68));	// 파일이름

			List<String> list = view.getList(bb);
			mav.addObject("list",list);
			mav.addObject("theme",bb.getBoardTitle());
			mav.addObject("content",bb.getBoardContent());
			mav.addObject("date",bb.getBoardDate());
			mav.addObject("writeId",bb.getBoardId());
			mav.addObject("route",route);
			mav.addObject("file",bb.getCutContent());
		
			mav.addObject("roomcode",session.getAttribute("roomCode"));

			


			mav.setViewName("learningDataCXT");
			transaction = true;


		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}




	private ModelAndView tclearningNoticeUpdatePage(BoardBean board) { // 공지사항 수정 페이지
		mav = new ModelAndView();
		StringBuffer sb = new StringBuffer();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			mav.addObject("boardTitle", board.getBoardTitle());
			mav.addObject("boardContent", board.getBoardContent());

			sb.append("<input type=\"hidden\" name=\"boardDate\" value='"+ board.getBoardDate() +"'/>");
			mav.addObject("boardDate", sb.toString());
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeUpdate");
			setTransactionResult(transaction);
		}
		return mav;

	}

	private ModelAndView tclearningNoticeUpdate(BoardBean board) {	// 선생님 공지사항 수정
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setRoomCode((String)session.getAttribute("roomCode"));
			if(dao.tclearningNoticeUpdate(board) != 0) {
				transaction = true;
			}
		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView tclearningNoticeDelete(BoardBean board) { // 선생님 공지사항 삭제
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setRoomCode((String)session.getAttribute("roomCode"));
			if(dao.tclearningNoticeDelete(board) != 0) {
				transaction = true;
			}
		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}
	private ModelAndView learningDataDelete(BoardBean board) { // 선생님 자료실 삭제
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {	
		if(dao.learningDataDelete(board) != 0) {
				transaction = true;
		
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}
	private ModelAndView learningDataUpdate(BoardBean board) { // 선생님 자료실 삭제
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {	
			if(dao.learningDataUpdate(board) != 0) {
				transaction = true;
			
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}
}
