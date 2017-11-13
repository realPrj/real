package icia.project.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

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
	@Autowired
	private ChatWebSocketHandler chat;


	private ModelAndView mav;

	private MultipartHttpServletRequest mtfRequest = null;

	public ModelAndView entrance(int serviceCode,Object ...object)  {

		switch(serviceCode) {

		case 1:   // 선생님 댓글 달기
			mav = learningQuestionTag((BoardBean)object[0]);
			break;

		case 2: //선생님 질문게시판 자세히보기
			mav = learningDataCXTPage((BoardBean)object[0]);
			break;

		case 3:	//공지사항 페이지
			mav = learningNoticePage((BoardBean)object[0]);
			break;

		case 4:	//질문게시판 페이지
			mav = learningQuestion((BoardBean)object[0]);
			break;

		case 5: // 학생자료실 페이지 
			mav = learningDataStudent((BoardBean)object[0]);
			break;

		case 6:	//학생 자료실 자세히보기
			mav = learningDataCXTStudent((BoardBean)object[0]);
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

		case 18:	// 오답노트 코멘트 등록
			mtfRequest = ((MultipartHttpServletRequest)object[1]);
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

		case 22:	// 오답노트 학습방 명단 쏘기
			mav = learningWANStudentList();
			break;

		case 23:	// 오답노트 학생별 정보 페이지
			mav = learningSTInformationPage((BoardBean)object[0]);
			break;

		case 24:   // 질문게시판 댓글 선생님 삭제

			mav = learningQuestionTagDelete((BoardBean)object[0]);
			break;

		case 25:   // 선생님 학생 전체보기
			mav = teacherLearningSTadmin((BoardBean)object[0]);
			break;

		case 27:   // 과제 페이지
			mav = learningTaskPage((BoardBean)object[0]);
			break;

		case 28:   // 과제 등록
			mav = learningTaskInsertform((BoardBean)object[0]);
			break;

		case 29:  // 과제 수정
			mav = learningTaskUpdate((BoardBean)object[0]);
			break;


		case 30:	// 선생님 공지사항 수정
			mtfRequest = ((MultipartHttpServletRequest)object[1]);
			mav = tclearningNoticeUpdate((BoardBean)object[0]);
			break;

		case 31:	// 선생님 공지사항 삭제
			mav = tclearningNoticeDelete((BoardBean)object[0]);
			break;

		case 32 : // 선생님 토론게시판 페이지
			mav = tclearningDebatePage((BoardBean)object[0]);
			break;

		case 33 : // 선생님 토론게시판 내용확인
			mav = tclearningDebateCTX((BoardBean)object[0]);
			break;

		case 34 : // 선생님 토론게시판 내용수정 페이지
			mav = tclearningDebateUpdatePage((BoardBean)object[0]);
			break;

		case 35 : // 선생님 토론게시판 내용수정
			mav = tclearningDebateUpdate((BoardBean)object[0]);
			break;

		case 36 : // 선생님 토론게시판 등록
			mav = tclearningDebateInsert((BoardBean)object[0]);
			break;

		case 37 : // 선생님 토론게시판 등록 페이지
			mav = tclearningDebateInsertPage((BoardBean)object[0]);
			break;

		case 38 : // 선생님 토론게시판 삭제
			mav = tclearningDebateDelete((BoardBean)object[0]);
			break;

		case 39 : // 선생님 과제제출 확인
			mav = learningTesk((BoardBean)object[0]);
			break;

		case 41: // 쪽지보내기 페이지
			mav = learningSendMessagePage((BoardBean)object[0]);
			break;

		case 42: // 받은쪽지 리스트 페이지
			mav = learningReceiveMessagePage((BoardBean)object[0]);
			break;

		case 43: // 보낸쪽지 리스트 페이지
			mav = learningSentMessagePage((BoardBean)object[0]);
			break;

		case 44 : // 쪽지페이지
			mav = learningMessagePage((BoardBean)object[0]);
			break;

		case 45:   // 과제삭제
			mav = learningTaskCXTDelete((BoardBean)object[0]);
			break;	

		case 40 :   // 채팅
			mav = adminChating();
			break;

		case 46:   // 쪽지 보내기 페이지
			mav = learningSendMessagePage((BoardBean)object[0]);
			break;	

		case 47:   // 쪽지 보내기
			mav = learningSendMessage((BoardBean)object[0]);
			break;	

		case 48:   // 보낸쪽지 내용확인
			mav = learningSentMessageCTX((BoardBean)object[0]);
			break;

		case 49:   // 받은쪽지 내용확인
			mav = learningGetMessageCTX((BoardBean)object[0]);
			break;

		case 50:   // 받은쪽지 삭제
			mav = learningGetMessageDelete((BoardBean)object[0]);
			break;

		case 51:   // 보낸쪽지 삭제
			mav = learningSentMessageDelete((BoardBean)object[0]);
			break;

		case 52:   // 강의계획서 페이지
			mav = learningPlanPage((BoardBean)object[0]);
			break;

		case 53:   // 강의 계획서 자세히 보기 페이지
			mav = learningPlanCTXPage((BoardBean)object[0]);
			break;

		case 54:   // 강의 계획서 등록
			mtfRequest = ((MultipartHttpServletRequest)object[1]);
			mav = learningPlanInsert((BoardBean)object[0]);
			break;

		case 55:   // 강의 계획서 수정
			mav = learningPlanUpdate((BoardBean)object[0]);
			break;

		case 56:   // 강의 계획서 삭제
			mav = learningPlanDelete((BoardBean)object[0]);
			break;

		case 57:   // 과제 점수 등록,수정 페이지
			mav = scoreInsertPage((BoardBean)object[0]);
			break;

		case 58:   // 과제 점수 등록
			mav = scoreInsertgo((BoardBean)object[0]);
			break;

		case 59:   // 과제 점수 수정
			mav = scoreUpdate((BoardBean)object[0]);
			break;

		case 60:   // 과제 점수 페이지
			mav = taskScorePage();
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

			ar = dao.tclearningNoticeList(board);
			mav.addObject("content", tclearningNoticeList(board,ar));
			mav.addObject("identity", session.getAttribute("identity"));
			mav.addObject("button", pageButton(ar));


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
		sb.append("<table class=\"table table-hover\">");
		sb.append("<tr>");
		sb.append("<td><b>게시글번호</b></td>");
		sb.append("<td><b>제목</b></td>");
		sb.append("<td><b>작성자</b></td>");
		sb.append("<td><b>날짜</b></td>");
		sb.append("</tr>");


		int forI = 0; // 크게 한사람
		int forB = 0;	// 내용물
		int pageCount = 5; // 
		int pageCount2 = pageCount; // 

		double sizeDouble = ar.size() / (double)pageCount;

		for(forI=0; forI < sizeDouble; forI++) {

			if(ar.size()< pageCount) {
				pageCount= ar.size();
			}

			sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");
			for(forB=forB; forB<pageCount; forB++) {
				sb.append("<tr>");	
				sb.append("<td>"+ (forB+1) +"</td>");
				//sb.append("<input type=\"hidden\" name=\"boardTitle\" value='" + board.getBoardTitle() + "'/>");
				sb.append("<td class=\"CTX\" onClick=\"confirm('"+ ar.get(forB).getBoardTitle() +"','" + ar.get(forB).getBoardDate() + "','"+ board.getId() +"')\">" + ar.get(forB).getBoardTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardId() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardDate() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");	

			pageCount+=pageCount2;


		}
		sb.append("</table>");
		sb.append("<input class=\"btn\" type=\"button\" value=\"글쓰기\" onClick=\"noticeInsert()\"/>");

		return sb.toString();
	}


	private ModelAndView tclearningNoticeCTX(BoardBean board) { // 공지사항 내용확인
		mav = new ModelAndView();
		boolean transaction = false;
		DbBoardBean bb;
		ViewService view = new ViewService(); 
		StringBuffer sb = new StringBuffer();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			bb = dao.tclearningNoticeConfirm(board);

			bb.setIdentity((String)session.getAttribute("identity"));

			bb.setCutRoute(bb.getBoardRoute().substring(0,68));	// 루트만

			bb.setCutContent(bb.getBoardRoute().substring(68));	// 파일이름

			List<String> list = view.getList(bb);
			mav.addObject("list",list);
			mav.addObject("boardTitle",bb.getBoardTitle());
			mav.addObject("boardContent",bb.getBoardContent().replace("\r\n", "<br/>"));
			mav.addObject("boardDate",bb.getBoardDate());
			mav.addObject("boardId",bb.getBoardId());
			mav.addObject("file",bb.getCutContent());
			mav.addObject("identity", bb.getIdentity());
			mav.addObject("roomCode", bb.getRoomCode());



			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeCXT");
			setTransactionResult(transaction);
		}
		return mav;
	}

	/*private String getTclearningNoticeCTX(DbBoardBean bb) { // 공지사항 내용 끌고오기

		mav.addObject("list",list);
		mav.addObject("theme",bb.getBoardTitle());
		mav.addObject("content",bb.getBoardContent());
		mav.addObject("date",bb.getBoardDate());
		mav.addObject("writeId",bb.getBoardId());
		mav.addObject("route",route);
		mav.addObject("file",bb.getCutContent());

		StringBuffer sb = new StringBuffer();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>" + bb.getBoardTitle() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + bb.getBoardDate() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + bb.getBoardId() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + bb.getBoardContent() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>" + bb.getBoardRoute() + "</td>");
		sb.append("</tr>");
		sb.append("</table>");

		return sb.toString();
	}*/


	private ModelAndView tclearningNoticeInsert(BoardBean board) { // 공지사항 글쓰기 페이지		
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			mav.addObject("identity", session.getAttribute("identity"));
			mav.addObject("studentList", "학생관리");

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeInsert");
			setTransactionResult(transaction);
		}
		return mav;

	}

	private ModelAndView learningQuestion(BoardBean board) { // 선생님 질문게시판 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> bb = null;
		String message = "";
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");
			mav.addObject("content",session.getAttribute("roomCode") + "자료실");
			board.setRoomCode((String)session.getAttribute("roomCode"));

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			bb = dao.learningQuestionlist(board);
			sb.append("<table class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td><b>게시글번호</b></td>");
			sb.append("<td><b>제목</b></td>");
			sb.append("<td><b>작성자</b></td>");
			sb.append("<td><b>날짜</b></td>");
			sb.append("</tr>");

			// 페이지
			int forI = 0; // 크게 한사람
			int forB = 0;	// 내용물
			int pageCount = 5;
			int pageCount2 = pageCount;

			double sizeDouble = bb.size() / (double)pageCount;

			for(forI=0; forI < sizeDouble; forI++) {

				if(bb.size() < pageCount) {
					pageCount= bb.size();
				}

				sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

				for(forB=forB; forB<pageCount; forB++) {
					sb.append("<tr>");
					sb.append("<td>");
					sb.append(forB+1);
					sb.append("</td>");
					sb.append("<td class=\"CTX\" onClick=\"viewData('"+bb.get(forB).getRoomCode() +"','" + bb.get(forB).getBoardDate() + "')\">" +bb.get(forB).getBoardTitle() + "</td>");
					sb.append("<td>" + bb.get(forB).getBoardId() + "</td>");
					sb.append("<td>" + bb.get(forB).getBoardDate() + "</td>");
					sb.append("</tr>");

				}
				sb.append("</tbody>");	

				pageCount+=pageCount2;

			}

			sb.append("</table>");
			mav.addObject("datalist", sb.toString());
			mav.addObject("message", message);
			sb = new StringBuffer();

			sb.append("<div class='text-center'>");
			sb.append("<ul class='pagination'>");

			for(int y=0; y < sizeDouble; y++) {// 페이지 버튼

				//sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");
				sb.append("<li><a onClick='pageNumber("+y+")'>"+(y+1)+"</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");

			mav.addObject("content2", sb.toString());

		}
		catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}
		mav.setViewName("learningQuestion");
		return mav;
	}

	private ModelAndView learningWANPage() { // 오답노트 페이지

		mav = new ModelAndView();
		BoardBean board;
		ArrayList<BoardBean> boardList = null;
		ArrayList<BoardBean> typeSum = null;
		ArrayList<BoardBean> yearCode = null;
		ArrayList<BoardBean> allGraph = null;
		StringBuffer sb;
		StringBuffer sum;
		boolean transaction = false;
		String page = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			Gson gson = new Gson();

			ArrayList<BoardBean> sbCode = new ArrayList<BoardBean>();

			board = new BoardBean();

			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setRoomSB(dao.learningSBCodeGet(board));	// 과목 코드 추출

			board.setSubjectName(dao.subjectNameGet(board));	// 과목 이름 추출

			sbCode.add(board);

			String jsonPaser = gson.toJson(sbCode);

			System.out.println(jsonPaser);

			board = new BoardBean();
			board.setRoomCode((String)session.getAttribute("roomCode"));

			if(dao.learningWANCheck(board) != 0) {

				boardList = dao.learningWANListGet(board);

				////////////////////////////////////////////////////////////////


				sb = new StringBuffer();
				board = new BoardBean();
				board.setRoomSB(boardList.get(0).getRoomSB());

				allGraph = dao.learningWANAllRoomGraph(board);

				for(int i =0; i < allGraph.size(); i++) {
					board = new BoardBean();
					board.setRoomSB(boardList.get(0).getRoomSB());
					board.setYearCode(allGraph.get(i).getYearCode());
					board.setTypeCode(allGraph.get(i).getTypeCode());

					board.setYearName(dao.learningYearNameGet(board));
					board.setTypeName(dao.learningTypeNameGet(board));
					board.setTypeSum(allGraph.get(i).getTypeSum());

					if(i == allGraph.size()-1) {
						sb.append("['"+board.getYearName()+board.getTypeName()+"',"+board.getTypeSum()+"]");
						break;
					}

					sb.append("['"+board.getYearName()+board.getTypeName()+"',"+board.getTypeSum()+"],");


				}

				mav.addObject("allgraph", sb.toString());
				mav.addObject("title1", "전국 학생 모르는 문제 질문 비중");

				sb = new StringBuffer();
				board = new BoardBean();
				board.setRoomSB(boardList.get(0).getRoomSB());
				board.setRoomCode(boardList.get(0).getRoomCode());
				allGraph = dao.learningWANRommGraph(board);

				for(int i =0; i < allGraph.size(); i++) {
					board = new BoardBean();
					board.setRoomSB(boardList.get(0).getRoomSB());
					board.setYearCode(allGraph.get(i).getYearCode());
					board.setTypeCode(allGraph.get(i).getTypeCode());

					board.setYearName(dao.learningYearNameGet(board));
					board.setTypeName(dao.learningTypeNameGet(board));
					board.setTypeSum(allGraph.get(i).getTypeSum());

					if(i == allGraph.size()-1) {
						sb.append("['"+board.getYearName()+board.getTypeName()+"',"+board.getTypeSum()+"]");
						break;
					}

					sb.append("['"+board.getYearName()+board.getTypeName()+"',"+board.getTypeSum()+"],");


				}

				mav.addObject("roomgraph", sb.toString());
				mav.addObject("title2", "학습방 학생 모르는 문제 질문 비중");


				///////////////////////////////////////////////////////////////

				board.setRoomCode((String)session.getAttribute("roomCode"));

				yearCode = dao.learningWANYearCodeOneGet(board);

				sb = new StringBuffer();
				sum = new StringBuffer();
				sb.append("<select id = 'yearSelect' class='btn-sm'>");
				sb.append("<option></option>");

				for(int i =0; i < yearCode.size(); i++) {
					board = new BoardBean();
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setYearCode(yearCode.get(i).getYearCode());

					sb.append("<option>"+yearCode.get(i).getYearCode()+"</option>");

					typeSum = dao.learningWANTypeSum(board);

					sum.append("<div style='margin-left: 550px' name='div' id="+yearCode.get(i).getYearCode().substring(0, 6)+" >");
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


				}

				sb.append("</select>");
				mav.addObject("yearSelect", sb.toString());
				mav.addObject("typeSumb", sum.toString());

				sb = new StringBuffer();
				sb.append("<table id='tableList' class=\"table table-hover\">");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<b>게시글 번호</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>학생이름</b>");
				sb.append("</td>");
				sb.append("<td style=\"text-align:center\">");
				sb.append("<b>년도</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>문제유형</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>문제 번호</b>");
				sb.append("</td>");
				sb.append("<td style=\"text-align:center\">");
				sb.append("<b>등록일</b>");
				sb.append("</td>");
				sb.append("<td style=\"text-align:center\">");
				sb.append("<b>선생님 코멘트<b/>");
				sb.append("</td>");
				sb.append("<td><b>응답여부<b></td>");
				sb.append("</tr>");

				mav.addObject("content3", sb.toString());
				sb = new StringBuffer();


				// 페이지
				int forI = 0; // 크게 한사람
				int forB = 0;	// 내용물
				int pageCount = 5; // 
				int pageCount2 = pageCount; // 

				double sizeDouble = boardList.size() / (double)pageCount;

				for(forI=0; forI < sizeDouble; forI++) {

					if(boardList.size()< pageCount) {
						pageCount= boardList.size();
					}

					sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

					for(forB = forB; forB < pageCount; forB++){//복사
						board = new BoardBean();
						board.setRoomCode(boardList.get(0).getRoomCode());
						board.setStudentName(boardList.get(forB).getStudentName());
						board.setYearCode(boardList.get(forB).getYearCode());
						board.setTypeCode(boardList.get(forB).getTypeCode());
						board.setRoomSB(dao.learningSBCodeGet(board));
						board.setYearName(dao.learningYearNameGet(board));
						board.setTypeName(dao.learningTypeNameGet(board));
						board.setBoardCode(boardList.get(forB).getBoardCode());
						String check =((dao.learningWANCommentCheck(board) == 1)? "O" : "X");

						sb.append("<tr>");
						sb.append("<td>");
						sb.append(forB+1);
						sb.append("</td>");
						sb.append("<td>");
						sb.append(board.getStudentName());
						sb.append("</td>");
						sb.append("<td>");
						sb.append(board.getYearName());
						sb.append("</td>");
						sb.append("<td>");
						sb.append(board.getTypeName());
						sb.append("</td>");
						sb.append("<td>");
						sb.append(boardList.get(forB).getNumberCode());
						sb.append("</td>");
						sb.append("<td>");
						sb.append(boardList.get(forB).getBoardDate());
						sb.append("</td>");
						sb.append("<td>");
						sb.append("<input style=\"text-align:center\" type='button' name='cmClick' class='btn btn-md' id="+boardList.get(forB).getBoardCode()+" value='선생님 코멘트'  />");
						sb.append("</td>");
						sb.append("<td style=\"text-align:center\">");
						sb.append(check);
						sb.append("</td>");
						sb.append("</tr>");

					}
					//복사
					sb.append("</tbody>");	

					pageCount+=pageCount2;


				}

				sb.append("</table>");
				mav.addObject("content", sb.toString());

				sb = new StringBuffer();

				sb.append("<div class='text-center'>");
				sb.append("<ul class='pagination'>");


				for(int y=0; y < sizeDouble; y++) {// 페이지 버튼

					//sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");
					sb.append("<li><a onClick='pageNumber("+y+")'>"+(y+1)+"</a></li>");
				}
				sb.append("</ul>");
				sb.append("</div>");


				mav.addObject("content2", sb.toString());



				/*	

			for(int i = 0; i < boardList.size(); i++ ) {

					board = new BoardBean();
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setStudentName(boardList.get(i).getStudentName());
					board.setYearCode(boardList.get(i).getYearCode());
					board.setTypeCode(boardList.get(i).getTypeCode());
					board.setRoomSB(dao.learningSBCodeGet(board));
					board.setYearName(dao.learningYearNameGet(board));
					board.setTypeName(dao.learningTypeNameGet(board));
					board.setBoardCode(boardList.get(i).getBoardCode());
					String check =((dao.learningWANCommentCheck(board) == 1)? "O" : "X");

					sb.append("<tr>");
					sb.append("<td>");
					sb.append(i+1);
					sb.append("</td>");
					sb.append("<td>");
					sb.append(board.getStudentName());
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
					sb.append(boardList.get(i).getBoardDate());
					sb.append("</td>");
					sb.append("<td>");
					sb.append("<input type='button-sm' class='btn' value='선생님 코멘트' onClick='commentCheck("+boardList.get(i).getBoardCode()+")' />");
					sb.append("</td>");
					sb.append("<td>");
					sb.append(check);
					sb.append("</td>");
					sb.append("</tr>");
				}

			sb.append("</table>");
			mav.addObject("content", sb.toString());*/

			}	// if 끝

			else {
				mav.addObject("content","게시글이 없습니다.");
			}

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

			bb = dao.datalist(board);
			sb.append("<br>");
			sb.append("<table class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td><b>게시글번호</b></td>");
			sb.append("<td><b>제목</b></td>");
			sb.append("<td><b>작성자</b></td>");
			sb.append("<td><b>날짜</b></td>");
			sb.append("</tr>");


			int forI = 0; // 크게 한사람
			int forB = 0;	// 내용물
			int pageCount = 5; // 
			int pageCount2 = pageCount; // 

			double sizeDouble = bb.size() / (double)pageCount;

			for(forI=0; forI < sizeDouble; forI++) {

				if(bb.size()< pageCount) {
					pageCount= bb.size();
				}

				sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

				for(forB=forB; forB<pageCount; forB++) {
					sb.append("<tr>");
					sb.append("<td>");
					sb.append(forB+1);
					sb.append("</td>");
					sb.append("<td class=\"CTX\" onClick=\"viewData('"+bb.get(forB).getRoomCode() +"','" + bb.get(forB).getBoardDate() + "')\">" +bb.get(forB).getBoardTitle() + "</td>");
					sb.append("<td>" + bb.get(forB).getBoardId() + "</td>");
					sb.append("<td>" + bb.get(forB).getBoardDate() + "</td>");
					sb.append("</tr>");

				}
				sb.append("</tbody>");	

				pageCount+=pageCount2;


			}

			sb.append("</table>");
			mav.addObject("datalist", sb.toString());
			mav.addObject("message", message);
			sb = new StringBuffer();

			sb.append("<div class='text-center'>");
			sb.append("<ul class='pagination'>");


			for(int y=0; y < sizeDouble; y++) {// 페이지 버튼

				//sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");
				sb.append("<li><a onClick='pageNumber("+y+")'>"+(y+1)+"</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");


			mav.addObject("content2", sb.toString());

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		mav.setViewName("learningData");
		return mav;
	}

	private ModelAndView learningWANCXTPage(BoardBean board) { // 오답노트 코멘트 페이지 이동

		mav = new ModelAndView();
		ViewService view = new ViewService(); 
		boolean transaction = false;
		String page = null;
		StringBuffer sb = new StringBuffer();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));

			if(dao.learningWANCommentCheck(board) != 0) {	// 코멘트 있음

				DbBoardBean bb = dao.learningWANCommentGet(board);	// 전체 루트(파일이름까지)

				bb.setCutRoute(bb.getBoardRoute().substring(0,68));	// 루트만
				String route = bb.getCutRoute();

				bb.setCutContent(bb.getBoardRoute().substring(68));	// 파일이름

				List<String> list = view.getList(bb);
				mav.addObject("comment", "Comment");
				mav.addObject("content",bb.getBoardContent().replace("\r\n", "<br/>"));
				mav.addObject("list",list);
				mav.addObject("file",bb.getCutContent());
				mav.addObject("date",bb.getBoardDate());
				mav.addObject("writeId",bb.getBoardId());
				mav.addObject("route",route);
				mav.addObject("rommCode", bb.getRoomCode());
				mav.addObject("boardCode", bb.getBoardCode());

				sb.append("<tr>");
				sb.append("<td><input type='button' class=\"button\" value='수정' onClick='learningWANCMUpdatePage("+bb.getBoardCode()+")' /></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td><input type='button' class=\"button\" value='삭제' onClick='learningWANCMDelete("+bb.getBoardCode()+","+bb.getRoomCode()+")' /></td></tr>");
				mav.addObject("inputButton", sb.toString());

				page="learningWANCXT";
				transaction = true;

			}else {	// 코멘트 없음
				sb.append("등록된 코멘트가 없습니다. <br/><br/>코멘트를 등록하시려면 등록 버튼을 눌러주세요.<br/><br/><br/>");
				sb.append("<input class=\"CTX\" type='button' value='코멘트 등록' onClick='commentInsertPage("+board.getBoardCode()+")' />");
				page = "learningWANCXT";
				transaction = true;
				mav.addObject("InsertButton", sb.toString());
				mav.addObject("checkCM", 1);
			}


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

	private ModelAndView learningWANCommentInsert(BoardBean board) { // 오답노트 코멘트 등록

		mav = new ModelAndView();
		boolean transaction = false;
		fileupload(board,mtfRequest);
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

			System.out.println(board.getBoardCode());
			System.out.println(board.getRoomCode());

			DbBoardBean bb = dao.learningWANCommentGet2(board);
			System.out.println(bb.getBoardContent());
			mav.addObject("boardContent", bb.getBoardContent());
			mav.addObject("boardCode", bb.getBoardCode());

			page="learningWANCMUpdate";
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
			System.out.println("루트 : " + board.getBoardRoute()); 


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
			mav.addObject("boardContent", board.getBoardContent().replace("<br/>","\r\n" ));
			mav.addObject("identity", session.getAttribute("identity"));

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
		fileupload(board,mtfRequest);

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setRoomCode((String)session.getAttribute("roomCode"));
			System.out.println(board.getBoardTitle());
			if(dao.tclearningNoticeUpdate(board) != 0) {
				System.out.println("공지사항 수정 완료");
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
	private ModelAndView learningDataUpdate(BoardBean board) { // 선생님 자료실 수정
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

	private ModelAndView learningDataStudent(BoardBean board) { //학생 자료실 메뉴 보기
		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> bb = null;
		String message = "";
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			System.out.println(board.getRoomCode());
			session.getAttribute("roomCode");
			mav.addObject("content",session.getAttribute("roomCode") + "자료실");
			board.setRoomCode((String)session.getAttribute("roomCode"));


			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			bb = dao.datalistStudent(board);
			sb.append("<table class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td><b>게시글번호</b></td>");
			sb.append("<td><b>제목</b></td>");
			sb.append("<td><b>작성자</b></td>");
			sb.append("<td><b>날짜</b></td>");
			sb.append("</tr>");
			int forI = 0; // 크게 한사람
			int forB = 0;	// 내용물
			int pageCount = 5; // 
			int pageCount2 = pageCount; // 

			double sizeDouble = bb.size() / (double)pageCount;

			for(forI=0; forI < sizeDouble; forI++) {

				if(bb.size()< pageCount) {
					pageCount= bb.size();
				}

				sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

				for(forB = forB; forB < pageCount; forB++){//복사
					sb.append("<tr>");
					sb.append("<td>");
					sb.append(forB+1);
					sb.append("</td>");
					sb.append("<td class=\"CTX\" onClick=\"viewData('"+bb.get(forB).getRoomCode() +"','" + bb.get(forB).getBoardDate() + "')\">" +bb.get(forB).getBoardTitle() + "</td>");

					sb.append("<td>" + bb.get(forB).getBoardId() + "</td>");
					sb.append("<td>" + bb.get(forB).getBoardDate() + "</td>");

					sb.append("</tr>");

				}
				sb.append("</tbody>");	

				pageCount+=pageCount2;


			}

			sb.append("</table>");
			mav.addObject("datalist", sb.toString());
			mav.addObject("message", message);
			sb = new StringBuffer();

			sb.append("<div class='text-center'>");
			sb.append("<ul class='pagination'>");


			for(int y=0; y < sizeDouble; y++) {// 페이지 버튼

				//sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");
				sb.append("<li><a onClick='pageNumber("+y+")'>"+(y+1)+"</a></li>");
			}
			sb.append("</ul>");
			sb.append("</div>");


			mav.addObject("content2", sb.toString());

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		mav.setViewName("learningDataStudent");
		return mav;
	}


	private ModelAndView learningWANStudentList() { // 오답노트 학생명단 쏘기

		mav = new ModelAndView();
		BoardBean board;
		boolean transaction = false;
		ArrayList<BoardBean> allSTCode = null;
		ArrayList<BoardBean> allSTName = new ArrayList<BoardBean>();
		StringBuffer sb = new StringBuffer();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {	
			board = new BoardBean();

			board.setRoomCode((String)session.getAttribute("roomCode"));

			allSTCode = dao.learningWANAllStudentCode(board);

			sb.append("<select id='stClick' >");
			sb.append("<option>");
			sb.append("학생이름");
			sb.append("</option>");

			for(int i = 0; i < allSTCode.size(); i++) {
				board = new BoardBean();
				board.setStudentCode(allSTCode.get(i).getStudentCode());

				board.setStudentName(dao.stNameGet(board));
				allSTName.add(board);

				sb.append("<option value='"+allSTCode.get(i).getStudentCode()+"'>");
				sb.append(allSTName.get(i).getStudentName());
				sb.append("</option>");
			}

			sb.append("</select>");

			mav.addObject("studentList", sb.toString());
			mav.setViewName("learningWANSTInformation");
			transaction = true;

		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView learningSTInformationPage(BoardBean board) { // 오답노트 학생별 정보 페이지

		mav = new ModelAndView();

		String stCode = null;
		String stName = null;
		BoardBean board2;
		ArrayList<BoardBean> allGraph = null;
		ArrayList<BoardBean> boardList = null;
		ArrayList<BoardBean> allRoomCode = null;
		ArrayList<BoardBean> allSTCode = null;
		ArrayList<BoardBean> allSTName = new ArrayList<BoardBean>();
		StringBuffer sb;
		boolean transaction = false;
		String page = null;
		double questionCount = 0;
		double peopleCount = 0;
		double peopleCount2 = 0;
		double value1 = 0;
		double value2 = 0;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {		

			stCode = board.getStudentCode();

			board.setRoomCode((String)session.getAttribute("roomCode"));

			/////////////////////////////////////////////////////////////////////////

			sb = new StringBuffer();

			allSTCode = dao.learningWANAllStudentCode(board);

			sb.append("<select id='stClick' >");
			sb.append("<option>");
			sb.append("학생이름");
			sb.append("</option>");

			for(int i = 0; i < allSTCode.size(); i++) {
				board = new BoardBean();
				board.setStudentCode(allSTCode.get(i).getStudentCode());

				board.setStudentName(dao.stNameGet(board));
				allSTName.add(board);

				sb.append("<option value='"+allSTCode.get(i).getStudentCode()+"'>");
				sb.append(allSTName.get(i).getStudentName());
				sb.append("</option>");
			}

			sb.append("</select>");

			mav.addObject("studentList", sb.toString());

			/////////////////////////////////////////////////////////////////////////

			board.setStudentCode(stCode);
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setStudentName(dao.stNameGet(board));	// 학생 이름 추출
			stName = board.getStudentName();
			mav.addObject("studentName", board.getStudentName());
			mav.addObject("stHalf", board.getStudentCode().substring(2, 4));
			mav.addObject("stNumber", board.getStudentCode().substring(4, 6));

			if(dao.allWANSum(board) != 0) {

				board.setAllSum(dao.allWANSum(board));		// 학생이 물어본 총 문제 수


				mav.addObject("allSum", board.getAllSum());

				/////////////////////////////////////////////////////////////////////////

				boardList = dao.learningWANstListGet(board);


				sb = new StringBuffer();
				sb.append("<table class=\"table table-hover\">");
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
				sb.append("날짜");
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
					board.setBoardCode(boardList.get(i).getBoardCode());
					String check =((dao.learningWANCommentCheck(board) == 1)? "O" : "X");

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
					sb.append(boardList.get(i).getBoardDate());
					sb.append("</td>");
					sb.append("<td>");
					sb.append("<input type='button' class='btn' value='선생님 코멘트' onClick='commentCheck("+boardList.get(i).getBoardCode()+")' />");
					sb.append("</td>");
					sb.append("<td>");
					sb.append(check);
					sb.append("</td>");
					sb.append("</tr>");
				}

				sb.append("</table>");

				mav.addObject("content", sb.toString());

				////////////////////////////////////////////////////////////////////////

				board = new BoardBean();

				board.setRoomCode((String)session.getAttribute("roomCode"));
				board.setStudentCode(stCode);

				boardList = dao.learningWANstListGetOverlap(board);

				sb = new StringBuffer();

				sb.append("<table class=\"table table-hover\">");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("모의고사 기출년월");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("문제유형");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("문제 번호");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("전국 평균 질문률");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("학습방 평균 질문률");
				sb.append("</td>");
				sb.append("</tr>");

				for(int i =0; i < boardList.size(); i++) {
					board = new BoardBean();
					board2 = new BoardBean();
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setRoomSB(boardList.get(0).getRoomSB());
					board.setTypeCode(boardList.get(i).getTypeCode());
					board.setNumberCode(boardList.get(i).getNumberCode());
					board.setYearCode(boardList.get(i).getYearCode());
					board.setStudentCode(stCode);
					board.setStudentCode(board.getStudentCode().substring(0, 2)+"%");
					board.setTypeName(dao.learningTypeNameGet(board));
					board.setYearName(dao.learningYearNameGet(board));

					questionCount = dao.learningWANstAverage1(board);	// 학년 총 질문수	

					allRoomCode = dao.learningWANstAverage2(board);	// 과목이 같은 학습방 코드

					for(int y = 0; y < allRoomCode.size(); y++) {
						board2 = new BoardBean();
						board2.setStudentCode(board.getStudentCode());
						board2.setRoomCode(allRoomCode.get(y).getRoomCode());
						peopleCount2 = dao.learningWANstAverage22(board2);

						peopleCount += peopleCount2;

					}

					value1 = (questionCount / peopleCount) * 100;
					String num1 = String.format("%.1f" , value1);

					questionCount = dao.learningWANstAverage3(board);	// 학습방 학년 총 질문수

					peopleCount = dao.learningWANstAverage4(board);	// 학습방 학년 총 인원수

					value2 = (questionCount / peopleCount) * 100;
					String num2 = String.format("%.1f" , value2);

					sb.append("<tr>");
					sb.append("<td>");
					sb.append(board.getYearName());
					sb.append("</td>");
					sb.append("<td>");
					sb.append(board.getTypeName());
					sb.append("</td>");
					sb.append("<td>");
					sb.append(board.getNumberCode());
					sb.append("</td>");
					sb.append("<td>");
					sb.append(num1+"%");
					sb.append("</td>");
					sb.append("<td>");
					sb.append(num2+"%");
					sb.append("</td>");	
					sb.append("</tr>");

				}

				sb.append("</table>");

				mav.addObject("average", sb.toString());

				///////////////////////////////////////////////////////////


				sb = new StringBuffer();
				board = new BoardBean();
				board.setRoomSB(boardList.get(0).getRoomSB());
				board.setRoomCode((String)session.getAttribute("roomCode"));
				board.setStudentCode(stCode);

				allGraph = dao.learningWANAllRoomGraph2(board);

				for(int i =0; i < allGraph.size(); i++) {
					board = new BoardBean();
					board.setRoomSB(boardList.get(0).getRoomSB());
					board.setYearCode(allGraph.get(i).getYearCode());
					board.setTypeCode(allGraph.get(i).getTypeCode());

					board.setYearName(dao.learningYearNameGet(board));
					board.setTypeName(dao.learningTypeNameGet(board));
					board.setTypeSum(allGraph.get(i).getTypeSum());

					if(i == allGraph.size()-1) {
						sb.append("['"+board.getYearName()+board.getTypeName()+"',"+board.getTypeSum()+"]");
						break;
					}

					sb.append("['"+board.getYearName()+board.getTypeName()+"',"+board.getTypeSum()+"],");


				}

				mav.addObject("allgraph", sb.toString());
				mav.addObject("title1", stName+"학생이 자주 질문하는 문제유형");

				///////////////////////////////////////////////////////////////////////

			}// if 끝

			page = "learningWANSTInformation";
			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}


	private ModelAndView learningDataCXTStudent(BoardBean board) { // 자료실 페이지 자세히 보기

		ViewService view = new ViewService(); 
		mav = new ModelAndView();
		boolean transaction = false;
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

			mav.setViewName("learningDataCXTStudent");
			transaction = true;
			
		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView tclearningDebatePage(BoardBean board) { // 선생님 토론 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("identity"));
			ar = dao.tclearningDebateList(board);

			mav.addObject("content", tclearningDebateList(board,ar));
			mav.addObject("identity", session.getAttribute("identity"));
			mav.addObject("button", pageButton(ar));
			transaction = true;

		}catch(Exception ex){
		}finally {

			mav.setViewName("learningDebate");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String tclearningDebateList(BoardBean board, ArrayList<BoardBean> ar) { // 토론게시판 리스트 출력
		StringBuffer sb = new StringBuffer();
		sb.append("<table class=\"table table-hover\">");
		sb.append("<tr>");
		sb.append("<td><b>게시글번호</b></td>");
		sb.append("<td><b>제목</b></td>");
		sb.append("<td><b>작성자</b></td>");
		sb.append("<td><b>날짜</b></td>");
		sb.append("</tr>");
		int forI = 0; // 크게 한사람
		int forB = 0;	// 내용물
		int pageCount = 5; // 
		int pageCount2 = pageCount; // 

		double sizeDouble = ar.size() / (double)pageCount;

		for(forI=0; forI < sizeDouble; forI++) {

			if(ar.size()< pageCount) {
				pageCount= ar.size();
			}

			sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");
			for(forB=forB; forB<pageCount; forB++) {
				sb.append("<tr>");	
				sb.append("<td>"+ (forB+1) +"</td>");
				//sb.append("<input type=\"hidden\" name=\"boardTitle\" value='" + board.getBoardTitle() + "'/>");
				sb.append("<td class=\"CTX\" onClick=\"confirm('"+ ar.get(forB).getBoardTitle() +"','" + ar.get(forB).getBoardDate() + "','"+ board.getId() +"')\">" + ar.get(forB).getBoardTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardId() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardDate() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");	

			pageCount+=pageCount2;
		}
		sb.append("</table>");
		sb.append("<input class=\"btn\" type=\"button\" value=\"글쓰기\" onClick=\"debateInsert()\"/>");


		return sb.toString();
	}

	private String pageButton(ArrayList<BoardBean> ar) {
		StringBuffer sb = new StringBuffer();

		int pageCount = 5; // 

		double sizeDouble = ar.size() / (double)pageCount;

		sb.append("<div class='text-center'>");
		sb.append("<ul class='pagination'>");


		for(int y=0; y < sizeDouble; y++) {// 페이지 버튼

			//sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");
			sb.append("<li><a onClick='pageNumber("+y+")'>"+(y+1)+"</a></li>");
		}
		sb.append("</ul>");
		sb.append("</div>");

		return sb.toString();

	}


	private ModelAndView tclearningDebateCTX(BoardBean board) { // 선생님 토론게시판 내용확인, 댓글 목록
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			board = dao.tclearningDebateCTX(board);
			board.setIdentity((String)session.getAttribute("identity"));
			//mav.addObject("content", getTclearningDebateCTX(board));
			mav.addObject("boardTitle", board.getBoardTitle());
			mav.addObject("boardId", board.getBoardId());
			mav.addObject("boardDate", board.getBoardDate());
			mav.addObject("boardContent", board.getBoardContent().replace("\r\n", "<br/>"));
			mav.addObject("roomCode", board.getRoomCode());

			ar = dao.learningDebateTagList(board);
			mav.addObject("debateTagList", getlearningDebateTagList(ar));
			mav.addObject("identity", session.getAttribute("identity"));
			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("learningDebateCTX");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getlearningDebateTagList(ArrayList<BoardBean> ar) { // 토론게시판 댓글 리스트
		StringBuffer sb = new StringBuffer();

		/*sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td> 작성자 </td>");
		sb.append("<td> 내용 </td>");
		sb.append("<td> 날짜 </td>");
		sb.append("</tr>");*/
		for(int i=0; i < ar.size(); i++) {
			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td class=\"tag_id\">" + ar.get(i).getStudentName()+ "</td>");
			sb.append("<td class=\"tag_date\">" + ar.get(i).getTagDate() + "</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"tag_content\" colspan=\"3\">" + ar.get(i).getTagContent().replace("\r\n", "<br/>") + "</td>");
			sb.append("</tr>");
			sb.append("</table>");
			/*sb.append("<tr>");
			sb.append("<td>" + ar.get(i).getStudentName() + "</td>");
			sb.append("<td>" + ar.get(i).getTagContent() + "</td>");
			sb.append("<td>" + ar.get(i).getTagDate() + "</td>");
			sb.append("</tr>");*/
		}
		//sb.append("</table>");

		return sb.toString();
	}

	/*private String getTclearningDebateCTX(BoardBean board) { // 토론게시판 내용 끌고오기
		StringBuffer sb = new StringBuffer();
		System.out.println("신분한번터 : " + board.getBoardCode());
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>제목 : " + board.getBoardTitle() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>작성일 : " + board.getBoardDate() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>작성자 : " + board.getBoardId() + "</td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>내용 : " + board.getBoardContent().replace("\r\n", "<br/>") + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type=\"button\" value=\"목록\" onClick=\"menu('5','"+ board.getBoardCode() +"')\"/>");
		sb.append("<input class=\"CTXbtn\" type=\"button\" value=\"수정\" onClick=\"update('"+ board.getBoardTitle() +"','"+ board.getBoardContent() +"','"+ board.getBoardDate() +"')\"/>");
		sb.append("<input type=\"button\" value=\"삭제\" onClick=\"boardDelete('"+ board.getRoomCode() +"','"+ board.getBoardDate() +"')\"/>");



		sb.append("<input class=\"CTXbtn\" style=\"background:#FFFFFF\" type=\"button\" value=\"목록\" onClick=\"menu('5','"+ board.getIdentity() +"')\"/>");
		sb.append("<input class=\"CTXbtn\" style=\"background:#FFFFFF\"type=\"button\" value=\"수정\" onClick=\"update('"+ board.getBoardTitle() +"','"+ board.getBoardContent() +"','"+ board.getBoardDate() +"')\"/>");
		sb.append("<input class=\"CTXbtn_end\"type=\"button\" value=\"삭제\" onClick=\"boardDelete('"+ board.getRoomCode() +"','"+ board.getBoardDate() +"')\"/>");
		return sb.toString();

	}*/

	private ModelAndView tclearningDebateUpdatePage(BoardBean board) { // 선생님 토론게시판 수정 페이지
		mav = new ModelAndView();
		StringBuffer sb = new StringBuffer();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			mav.addObject("boardTitle", board.getBoardTitle());
			mav.addObject("boardContent", board.getBoardContent().replace("<br/>", "\r\n"));

			sb.append("<input type=\"hidden\" name=\"boardDate\" value='"+ board.getBoardDate() +"'/>");
			mav.addObject("boardDate", sb.toString());
			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("learningDebateUpdate");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView tclearningDebateUpdate(BoardBean board) { // 선생님 토론게시판 수정
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setRoomCode((String)session.getAttribute("roomCode"));
				transaction = true;
				
		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView tclearningDebateInsert(BoardBean board) { // 선생님 토론게시판 글등록 
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setBoardId((String)session.getAttribute("tcId"));

			dao.tclearningDebateInsert(board);

			transaction = true;
		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView tclearningDebateInsertPage(BoardBean board) { // 토론게시판 등록 페이지 이동
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);


		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("identity"));


			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("learningDebateInsert");
			setTransactionResult(transaction);
		}
		return mav;
	}



	private ModelAndView learningDataCXTPage(BoardBean board) { // 질문 페이지 자세히 보기

		ViewService view = new ViewService(); 
		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> taglist = null;
		String message = "";
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board.setId((String)session.getAttribute("tcId"));

			board.setTagId(board.getId());

			session.getAttribute("roomCode");
			mav.addObject("content",session.getAttribute("roomCode") + "자료실");
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setStudentCode((String)session.getAttribute("stCode"));

			DbBoardBean bb = dao.questionBoardCXT(board);   // 전체 루트(파일이름까지)

			bb.setCutRoute(bb.getBoardRoute().substring(0,68));   // 루트만
			String route = bb.getCutRoute();
			bb.setCutContent(bb.getBoardRoute().substring(68));   // 파일이름

			List<String> list = view.getList(bb);
			mav.addObject("list",list);
			mav.addObject("theme",bb.getBoardTitle());
			mav.addObject("content",bb.getBoardContent().replace("\r\n", "<br/>"));
			mav.addObject("date",bb.getBoardDate());
			mav.addObject("writeId",bb.getBoardId());
			mav.addObject("roomCode",bb.getRoomCode());
			mav.addObject("route",route);
			mav.addObject("file",bb.getCutContent());
			board.setBoardTitle(bb.getBoardTitle());
			taglist = dao.learningQuestionTagCXT(board);

			for(int i=0; i<taglist.size(); i++) {
				if(taglist.size()!=0) {
					sb.append("<table>");
					sb.append("<tr>");
					sb.append("<td class=\"tag_id\">" + taglist.get(i).getTagId()+ "</td>");
					sb.append("<td class=\"tag_date\">" + taglist.get(i).getTagDate() + "</td>");
					sb.append("<td class=\"tag_delete\">"+ "<input type='button' class=\"CTXbtn\" style=\"border:none\" value='삭제'onClick=\"tagDelete(\'"+bb.getBoardTitle()+"\',"+"\'"+bb.getBoardDate()+"\',"+"\'"+bb.getRoomCode()+"\',"+"\'"+bb.getBoardId()+"\',"+"\'"+taglist.get(i).getTagDate()+"\',"+"\'"+taglist.get(i).getTagId()+"\')\" />"+"</td>");
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td class=\"tag_content\" colspan=\"3\">" + taglist.get(i).getTagContent().replace("\r\n", "<br/>") + "</td>");
					sb.append("</tr>");
					sb.append("</table>");
				}else {
					break;
				}
			}


			mav.addObject("message", message);
			mav.addObject("taglists", sb.toString());

			mav.addObject("roomcode",session.getAttribute("roomCode"));

			mav.setViewName("learningQuestionCXT");
			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}
	private ModelAndView learningQuestionTag(BoardBean board) { // 질문 댓글 쓰기

		mav = new ModelAndView();
		boolean transaction = false;
		chat = new ChatWebSocketHandler();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board.setId((String)session.getAttribute("tcId"));
			System.out.println(board.getId());
			board.setTagId(board.getId());
			System.out.println(board.getTagId());

			board.setRoomCode((String)session.getAttribute("roomCode"));


			if(dao.learningQuestionTag(board) !=0) {
				chat.msg(board.getId()+"댓글을 입력해셨습니다");
				System.out.println("성공은햇어");
			}
			mav.setViewName("learningQuestionCXT");
			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}



	private ModelAndView tclearningDebateDelete(BoardBean board) { // 선생님 토론게시판 삭제
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setRoomCode((String)session.getAttribute("roomCode"));
			if(dao.tclearningDebateDelete(board) != 0) {
				System.out.println("토론게시판 삭제 성공");
				transaction = true;
			}
		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView teacherLearningSTadmin(BoardBean board) { // 선생님 학생보기

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			//board.setId((String)session.getAttribute("identity"));
			board.setIdentity((String)session.getAttribute("identity"));
			ar = dao.teacherLearningSTadmin(board);
			//mav.addObject("content", tclearningNoticeList(board,ar));

			sb.append("<table  class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td><b>학년/반/번호</b></td>");
			sb.append("<td><b>아이디</b></td>");
			sb.append("<td><b>이름</b></td>");
			sb.append("<td><b>이메일</b></td>");
			sb.append("<td><b>핸드폰 번호</b></td>");
			sb.append("<td><b>쪽지 보내기</b></td>");
			sb.append("</tr>");

			int forI = 0; // 크게 한사람
			int forB = 0;	// 내용물
			int pageCount = 5; // 
			int pageCount2 = pageCount; //

			double sizeDouble = ar.size() / (double)pageCount;

			for(forI=0; forI < sizeDouble; forI++) {

				if(ar.size()< pageCount) {
					pageCount= ar.size();
				}

				sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");
				for(forB=forB; forB<pageCount; forB++) {
					//for(int i=0; i<ar.size(); i++) {
					sb.append("<tr>");   
					sb.append("<td>" + ar.get(forB).getStudentCode() + "</td>");
					sb.append("<td>" + ar.get(forB).getId() + "</td>");
					sb.append("<td>" + ar.get(forB).getStudentName() + "</td>");
					sb.append("<td>" + ar.get(forB).getEmail() + "</td>");
					sb.append("<td>" + ar.get(forB).getPhone() + "</td>");
					sb.append("<td><input type=\"button\"class='btn' value=\"쪽지보내기\" onClick=\"sendMessage('"+ ar.get(forB).getStudentCode() +"','"+ board.getIdentity() +"')\" /></td>");
					sb.append("</tr>");
				}
				sb.append("</tbody>");	

				pageCount+=pageCount2;
			}
			sb.append("</table>");
			mav.addObject("teacherLearningSTadmin", sb.toString());
			mav.addObject("button", pageButton(ar));
			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("teacherLearningSTadmin");
			setTransactionResult(transaction);
		}
		return mav;
	}


	/*	private ModelAndView teacherLearningSTadminCXT(BoardBean board) { // 선생님 학생보기 자세히 보기

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("identity"));

			ar = dao.teacherLearningSTadminCXT(board);

			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>학년/반/번호</td>");
			sb.append("<td>아이디</td>");
			sb.append("<td>이름</td>");
			sb.append("<td>이메일</td>");
			sb.append("<td>핸드폰 번호</td>");
			sb.append("</tr>");
			sb.append("<tr>");   
			sb.append("<td>" + ar.get(0).getStudentCode() + "</td>");
			sb.append("<td>" + ar.get(0).getId() + "</td>");
			sb.append("<td>" + ar.get(0).getStudentName() + "</td>");
			sb.append("<td>" + ar.get(0).getEmail() + "</td>");
			sb.append("<td>" + ar.get(0).getPhone() + "</td>");
			sb.append("<td>" + "<input type=\"button\" value=\"메일 발송\" onClick=\"sendMail('"+ ar.get(0).getEmail() +"')\"/>" + "</td>");
			sb.append("</tr>");
			sb.append("</table>");

			mav.addObject("list", sb.toString());

			transaction = true;
		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}

		return mav;
	}*/

	private ModelAndView learningQuestionTagDelete(BoardBean board) { // 질문 댓글 삭제

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.learningQuestionTagDelete(board) !=0) {
				System.out.println("성공은햇어");
			}else {
				System.out.println("실패");
			}

			mav.setViewName("learningQuestionCXT");
			transaction = true;


		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}


	private ModelAndView learningTaskPage(BoardBean board) { // 과제 페이지

		mav = new ModelAndView();

		ArrayList<BoardBean> al;
		String roomcode = null;
		StringBuffer sb = null;
		ArrayList<BoardBean> li;
		boolean transaction = false;
		String sexual = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			String identity = (String)session.getAttribute("identity");
			
			mav.addObject("identity", identity);

			sb = new StringBuffer();

			roomcode = (String)session.getAttribute("roomCode");

			board.setRoomCode(roomcode);
			
			if(board.getBoardCode() != null) {	// 게시글 내용,댓글 보여주기

				board = dao.learningTaskGet(board);	// 게시글 내용

				mav.addObject("title", board.getBoardTitle());
				mav.addObject("date",board.getBoardDate());
				mav.addObject("content", board.getBoardContent().replace("\r", "<br/>"));
				mav.addObject("submissionCheck", "<tr><td><input type='button' value='제출자확인' onClick='' /></td></tr>");

				sb.append("<table id='tableText'>");

				sb.append("<tr>");
				sb.append("<td>" + "<button class=\"CTXbtn\" onClick=update("+board.getBoardCode()+","+board.getRoomCode()+",'"+board.getBoardTitle()+"','"+board.getBoardContent()+"') />" + "수정" + "</button>" + "</td>");
				sb.append("<td>" + "<button class=\"CTXbtn_end\" onClick=deleteCXT("+board.getBoardCode()+","+board.getRoomCode()+") />" + "삭제" + "</button>" + "</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<br/>");
				mav.addObject("inputButton", sb	.toString());




				// 게시글 댓글(너가 여기서부터 댓글 뽑아내면되)
				li= dao.teacherTaskTag(board);
				sb = new StringBuffer();
				sb.append("<br/>");
				sb.append("<h3 style=\"padding-left:10px\">제출자명단</h3>");
				sb.append("<table style='text-align:center' class=\"table table-hover\">");
				sb.append("<tr>");
				sb.append("<td>학생 이름 </td>");
				sb.append("<td>올린 날짜 </td>");
				sb.append("<td>파일 보기 </td>");
				sb.append("<td>점수 등록 </td>");
				sb.append("<td>과제 점수 </td>");
				sb.append("</tr>");
				for(int i = 0; i < li.size(); i++) {

					board = new BoardBean();
					board.setStudentCode(li.get(i).getStudentCode());
					board.setTagCode(li.get(i).getTagCode());
					board.setStudentName(dao.stNameGet(board));	
					board.setBoardCode(li.get(0).getBoardCode());
					board.setRoomCode(roomcode);

					if(dao.taskScoreGet(board) != null) {
						sexual = dao.taskScoreGet(board)+"점";
					}else {
						sexual = "점수 미등록";
					}

					sb.append("<tr>");
					sb.append("<td>");
					sb.append(board.getStudentName());
					sb.append("</td>");
					sb.append("<td>");
					sb.append(li.get(i).getBoardDate());
					sb.append("</td>");
					sb.append("<td class=\"CTX\" style=\"color:blue\" onClick=checkFilec("+board.getBoardCode()+","+board.getRoomCode()+",'"+li.get(i).getStudentCode()+"')>" + "자세히 보기" + "</td>");
					//sb.append("<button class =\"CTXbtn\" style=\"border:none; color:blue;\" onClick=checkFilec("+board.getBoardCode()+","+board.getRoomCode()+",'"+li.get(i).getStudentCode()+"') />" + "자세히 보기" + "</button>");
					//sb.append("</td>");
					sb.append("<td class=\"CTX\" style=\"color:blue\" onClick=scorePage("+board.getTagCode()+","+board.getRoomCode()+",'"+board.getStudentCode()+"') />" + "점수 등록" + "</td>");
					//sb.append("<button class =\"CTXbtn\" style=\"border:none; color:blue;\" onClick=scorePage("+board.getTagCode()+","+board.getRoomCode()+",'"+board.getStudentCode()+"') />" + "점수 등록" + "</button>");
					//sb.append("</td>");
					sb.append("<td>");
					sb.append(sexual);
					sb.append("</td>");
					sb.append("</tr>");    
				}
				sb.append("</table>");
				mav.addObject("taskLists", sb.toString());


				mav.addObject("checkContent", 1);

			}

			board = new BoardBean();
			sb = new StringBuffer();

			board.setRoomCode(roomcode);

			if(dao.learningTaskCheck(board) != 0) {    // 리스트 출력

				al = dao.learningTaskList(board);    // 리스트 담기
				sb.append("<table style='text-align:center' class=\"table table-hover\">");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<b>게시물 번호</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>제목</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>날짜</b>");
				sb.append("</td>");
				sb.append("</tr>");

				int forI = 0; // 크게 한사람
				int forB = 0;	// 내용물
				int pageCount = 5; // 
				int pageCount2 = pageCount; // 

				double sizeDouble = al.size() / (double)pageCount;

				for(forI=0; forI < sizeDouble; forI++) {

					if(al.size()< pageCount) {
						pageCount= al.size();
					}

					sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

					for(forB=forB; forB<pageCount; forB++) {

						sb.append("<tr>");
						sb.append("<td>");
						sb.append(forB+1);
						sb.append("</td>");
						sb.append("<td class=\"CTX\" id='click' onClick=\"questionCXT(\'"+ al.get(forB).getBoardCode() +"\')\">"+ al.get(forB).getBoardTitle() + "</td>");
						sb.append("<td>");
						sb.append(al.get(forB).getBoardDate());
						sb.append("</td>");
						sb.append("</tr>");    
					}
					pageCount+=pageCount2;

					sb.append("</table>");
				}
				mav.addObject("taskList", sb.toString());

				sb = new StringBuffer();

				sb.append("<div class='text-center'>");
				sb.append("<ul class='pagination'>");

				for(int y=0; y < sizeDouble; y++) {// 페이지 버튼
					sb.append("<li><a onClick='pageNumber("+y+")'>"+(y+1)+"</a></li>");
				}
				sb.append("</ul>");
				sb.append("</div>");

				mav.addObject("button2", sb.toString());

			}
			mav.setViewName("learningTask");

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView learningTaskInsertform(BoardBean board) { // 과제 등록

		mav = new ModelAndView();
		boolean transaction = false;
		boolean distinction = true;
		int boardCode = 0;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("tcId"));

			while(distinction) {	// 과제코드 유무 체크

				boardCode = (int)(Math.random() *89999)+10000;

				board.setBoardCode(Integer.toString(boardCode));

				if(dao.learningTaskBoardCheck(board) == 0) {				
					distinction = false;				
				}else {
					distinction = true;
				}

			}

			dao.learningTaskInsert(board);

			mav.addObject("message", "alert('과제 생성 되었습니다.')");

			mav.setViewName("learningTask");
			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}


	private ModelAndView learningTaskUpdate(BoardBean board) { // 과제 수정

		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			dao.learningTaskUpdate(board);

			mav.addObject("message", "alert('과제 수정 되었습니다.')");

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}


	private ModelAndView adminChating() { // 채팅

		mav = new ModelAndView();

		BoardBean board = new BoardBean();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setId((String)session.getAttribute("tcId"));
			board.setRoomCode((String)session.getAttribute("roomCode"));

			mav.addObject("id",board.getId());
			mav.addObject("roomCode", board.getRoomCode());

			if(Integer.parseInt(board.getRoomCode()) !=447) {
				mav.setViewName("adminChating");
			}else {
				mav.setViewName("adminChating1");
			}
			transaction = true;


		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView learningTaskCXTDelete(BoardBean board) { // 선생님 공지사항 삭제
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.learningTaskCXTDelete(board) != 0) {

				transaction = true;
			}
		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}


	private ModelAndView learningTesk(BoardBean board) { //과제 페이지 자세하게 보기

		mav = new ModelAndView();

		ViewService view = new ViewService(); 
		System.out.println(board.getBoardCode());
		System.out.println(board.getRoomCode());
		System.out.println(board.getStudentCode());
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board.setStudentCode(board.getStudentCode());
			board.setStudentName(dao.stNameGet(board));	

			DbBoardBean bb = dao.learningTesk(board);   // 전체 루트(파일이름까지)

			bb.setCutRoute(bb.getBoardRoute().substring(0,68));   // 루트만

			bb.setCutContent(bb.getBoardRoute().substring(68));   // 파일이름

			List<String> list = view.getList(bb);

			mav.addObject("list",list);
			mav.addObject("name",board.getStudentName());
			mav.addObject("date",bb.getBoardDate());


			mav.setViewName("learningTaskTeacherCheck");

			transaction = true;


		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}


	private ModelAndView learningMessagePage(BoardBean board) { // 쪽지 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));


			mav.addObject("identity", session.getAttribute("identity"));

			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("learningMSG");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningSendMessagePage(BoardBean board) { // 쪽지 보내기 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));	

			if(board.getStudentCode() == null) {
				mav.addObject("messageOther", board.getMessageOther());
			}else {
				mav.addObject("messageOther", board.getStudentCode());
			}

			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("learningSendMSG");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningReceiveMessagePage(BoardBean board) { // 받은쪽지 리스트 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setMessageCode("G");
			board.setMessageId((String)session.getAttribute("tcId"));

			ar = dao.getMessageList(board);

			board.setIdentity((String)session.getAttribute("identity"));
			mav.addObject("messageList", getlearningGetMessageList(ar, board));
			mav.addObject("button", pageButton(ar));

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningGetMSG");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningSentMessagePage(BoardBean board) { // 보낸쪽지 리스트 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			System.out.println("뭐야?");
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setMessageCode("S");
			board.setMessageId((String)session.getAttribute("tcId"));
			board.setIdentity((String)session.getAttribute("identity"));
			ar = dao.sentMessageList(board);
			mav.addObject("messageList", getlearningSentMessageList(ar,board));
			mav.addObject("button", pageButton(ar));

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningSentMSG");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getlearningSentMessageList(ArrayList<BoardBean> ar, BoardBean board) { // 보낸쪽지 리스트 끌고오기
		StringBuffer sb = new StringBuffer();


		int forI = 0; // 크게 한사람
		int forB = 0;	// 내용물
		int pageCount = 5; // 
		int pageCount2 = pageCount; // 

		double sizeDouble = ar.size() / (double)pageCount;

		for(forI=0; forI < sizeDouble; forI++) {

			if(ar.size()< pageCount) {
				pageCount= ar.size();
			}

			sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

			for(forB=forB; forB<pageCount; forB++) {
				sb.append("<tr>");
				sb.append("<td>"+ (forB+1) +"</td>");
				sb.append("<td>" + ar.get(forB).getMessageOther() + "</td>");
				sb.append("<td style=\"cursor:pointer\" onClick=\"messageCTX('" + board.getMessageCode() + "','" + board.getRoomCode() + "','"+ ar.get(forB).getMessageDate() +"','"+ board.getIdentity() +"')\">" + ar.get(forB).getMessageTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getMessageDate() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			pageCount+=pageCount2
					;

		}

		return sb.toString();
	}

	private ModelAndView learningSendMessage(BoardBean board) { // 쪽지 보내기
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setMessageCode("S");
			board.setMessageId((String)session.getAttribute("tcId"));

			if(dao.sendMessageS(board) != 0) {
				board.setMessageCode("G");
				if(dao.sendMessageG(board) != 0) {
					System.out.println("쪽지 보내기 성공");
				}
			}


			transaction = true;

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getlearningGetMessageList(ArrayList<BoardBean> ar, BoardBean board) { // 받은쪽지 리스트 끌고오기
		StringBuffer sb = new StringBuffer();

		int forI = 0; // 크게 한사람
		int forB = 0;	// 내용물
		int pageCount = 5; // 
		int pageCount2 = pageCount; // 

		double sizeDouble = ar.size() / (double)pageCount;

		for(forI=0; forI < sizeDouble; forI++) {

			if(ar.size()< pageCount) {
				pageCount= ar.size();
			}

			sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");
			//sb.append("<tbody id=\"myTable\">");
			for(forB=forB; forB<pageCount; forB++) {
				//sb.append("<tbody id=\"myTable\">");

				sb.append("<tr>");
				sb.append("<td>"+ (forB+1) +"</td>");
				sb.append("<td>" + ar.get(forB).getMessageOther() + "</td>");
				sb.append("<td onClick=\"messageCTX('"+ board.getMessageCode() +"','"+ ar.get(forB).getRoomCode() +"','"+ ar.get(forB).getMessageDate() +"','"+board.getIdentity()+"')\">" + ar.get(forB).getMessageTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getMessageDate() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			pageCount+=pageCount2;

		}

		return sb.toString();
	}

	private ModelAndView learningSentMessageCTX(BoardBean board) { // 보낸쪽지 내용확인
		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setMessageCode("S");
			board.setMessageId((String)session.getAttribute("tcId"));

			board = dao.sentMessageCTX(board);

			mav.addObject("id", "받은사람");
			mav.addObject("messageOther", board.getMessageOther());
			mav.addObject("title", "제목");
			mav.addObject("messageTitle", board.getMessageTitle());
			mav.addObject("content", "내용");
			mav.addObject("messageContent", board.getMessageContent());

			board.setIdentity((String)session.getAttribute("identity"));

			sb.append("<input type=\"button\" value=\"목록\" onClick=\"message('"+3+"','"+board.getIdentity()+"')\"/>");
			sb.append("<input type=\"button\" value=\"삭제\" onClick=\"messageDelete('"+board.getIdentity()+"','"+board.getRoomCode()+"','"+board.getMessageCode()+"','"+board.getMessageDate()+"')\"/>");
			mav.addObject("button", sb.toString());

			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("learningMSGctx");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningGetMessageCTX(BoardBean board) { // 받은쪽지 내용확인
		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setMessageCode("G");
			board.setMessageId((String)session.getAttribute("tcId"));

			board = dao.getMessageCTX(board);

			mav.addObject("id", "보낸사람");
			mav.addObject("messageOther", board.getMessageOther());
			mav.addObject("title", "제목");
			mav.addObject("messageTitle", board.getMessageTitle());
			mav.addObject("content", "내용");
			mav.addObject("messageContent", board.getMessageContent());

			board.setIdentity((String)session.getAttribute("identity"));

			sb.append("<input class=\"btn btn-sm\" type=\"button\" value=\"답장\" onClick=\"reply('"+board.getMessageOther()+"','"+session.getAttribute("identity")+"')\"/>");

			sb.append("<input class=\"btn btn-sm\" type=\"button\" value=\"목록\" onClick=\"message('"+2+"','"+board.getIdentity()+"')\"/>");
			sb.append("<input class=\"btn btn-sm\" type=\"button\" value=\"삭제\" onClick=\"messageDelete('"+board.getIdentity()+"','"+board.getRoomCode()+"','"+board.getMessageCode()+"','"+board.getMessageDate()+"')\"/>");
			mav.addObject("button", sb.toString());


			transaction = true;

		}catch(Exception ex){
		}finally {
			mav.setViewName("learningMSGctx");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningGetMessageDelete(BoardBean board) { // 받은쪽지 삭제
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setMessageCode("G");
			board.setMessageId((String)session.getAttribute("tcId"));

			dao.getMessageDelete(board);

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningSentMessageDelete(BoardBean board) { // 보낸쪽지 삭제
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setMessageCode("S");
			board.setMessageId((String)session.getAttribute("tcId"));

			dao.sentMessageDelete(board);

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningPlanPage(BoardBean board) { // 강의 계획서 페이지

		mav = new ModelAndView();

		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		String nowYear2 = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			String nowYear = dao.nowYearGet();
			int subtract = 12 - Integer.parseInt(nowYear.substring(4));
			nowYear2 = nowYear;

			sb.append("<select id = 'yearSelect' class='btn-sm'>");
			sb.append("<option>월 선택</option>");

			for(int i = 0; i <= subtract; i++) {
				nowYear2 = Integer.toString(Integer.parseInt(nowYear)+i);
				sb.append("<option value="+nowYear2+">"+nowYear2.substring(0, 4)+"년"+nowYear2.substring(4)+"월"+"</option>");
			}
			sb.append("</select>");

			mav.addObject("select", sb.toString());
			mav.setViewName("learningPlan");
			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningPlanCTXPage(BoardBean board) { // 강의 계획서 자세히 보기 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ViewService view = new ViewService(); 
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));

			if(dao.planCheck(board) != 0) { // 있음

				DbBoardBean bb = dao.planCTX(board);
				bb.setCutRoute(bb.getBoardRoute().substring(0,68));   // 루트만

				bb.setCutContent(bb.getBoardRoute().substring(68));   // 파일이름

				List<String> list = view.getList(bb);

				mav.addObject("list",list);
				mav.addObject("title", "<input type='text' name='boardTitle' value="+bb.getBoardTitle()+" readonly/>");
				mav.addObject("content","<input type='text' name='boardContent' value="+bb.getBoardContent()+" readonly/>" );
				mav.addObject("check", 1);
				sb.append("<input type='button' value='강의계획 수정' class='btn' onClick=planUpdatePage("+bb.getBoardCode()+","+bb.getRoomCode()+") />");
				sb.append("<input type='button' value='강의계획 삭제' class='btn' onClick=planDelete("+bb.getBoardCode()+","+bb.getRoomCode()+") />");
				mav.addObject("button", sb.toString());

			}else {
				sb.append("<input type='button' value='강의계획 등록' class='btn' onClick=planInsert("+board.getBoardCode()+") />");
				mav.addObject("button", sb.toString());
				mav.addObject("check", 0);
			}

			mav.setViewName("learningPlanCTX");

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningPlanInsert(BoardBean board) { // 강의 계획서 등록

		boolean transaction = false;
		fileupload(board,mtfRequest);
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board.setId((String)session.getAttribute("tcId"));
			board.setRoomCode((String)session.getAttribute("roomCode"));

			dao.planInsert(board);

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningPlanUpdate(BoardBean board) { // 강의 계획서 수정

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			dao.planUpdate(board);

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return null;
	}

	private ModelAndView learningPlanDelete(BoardBean board) { // 강의 계획서 삭제

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			dao.planDelete(board);

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return null;
	}

	private ModelAndView scoreInsertPage(BoardBean board) { // 과제 점수 등록 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			if(dao.taskScoreGet(board) != null) {

				sb.append("<div class=\"content\">");
				sb.append("현재 점수 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+dao.taskScoreGet(board)+"점</br>");
				sb.append("</div>");
				sb.append("<div class=\"content\">");
				sb.append("점수수정 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				sb.append("<select name='number'>");
				sb.append("<option value='0'>0</option>");
				sb.append("<option value='1'>1</option>");
				sb.append("<option value='2'>2</option>");
				sb.append("<option value='3'>3</option>");
				sb.append("<option value='4'>4</option>");
				sb.append("<option value='5'>5</option>");
				sb.append("<option value='6'>6</option>");
				sb.append("<option value='7'>7</option>");
				sb.append("<option value='8'>8</option>");
				sb.append("<option value='9'>9</option>");
				sb.append("<option value='10'>10</option>");
				sb.append("</select>");
				sb.append("<input class=\"CTX\" type='button' value='점수 수정' onClick='sexualUpdate()' />");
				sb.append("</div>");

			}else {
				sb.append("<div>");
				sb.append("점수등록");
				sb.append("<select id='selectid' name='number'>");
				sb.append("<option value='0'>0</option>");
				sb.append("<option value='1'>1</option>");
				sb.append("<option value='2'>2</option>");
				sb.append("<option value='3'>3</option>");
				sb.append("<option value='4'>4</option>");
				sb.append("<option value='5'>5</option>");
				sb.append("<option value='6'>6</option>");
				sb.append("<option value='7'>7</option>");
				sb.append("<option value='8'>8</option>");
				sb.append("<option value='9'>9</option>");
				sb.append("<option value='10'>10</option>");
				sb.append("</select>");
				sb.append("<input type='button' value='점수 등록' onClick='sexualInsert()' />");
				sb.append("</div>");
			}

			mav.addObject("content", sb.toString());
			mav.addObject("tagCode", board.getTagCode());
			mav.addObject("roomCode", board.getRoomCode());
			mav.addObject("studentCode", board.getStudentCode());

			mav.setViewName("learningTaskScoreInsert");
			transaction = true;

		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView scoreInsertgo(BoardBean board) { // 과제 점수 등록

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			dao.taskScoreInsert(board);

			mav.addObject("reload", "opener.location.reload()");
			mav.addObject("windowclose", "window.close()");
			mav.setViewName("learningTaskScoreInsert");

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView scoreUpdate(BoardBean board) { // 과제 점수 수정

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			dao.taskScoreUpdate(board);

			mav.addObject("reload", "opener.location.reload()");
			mav.addObject("windowclose", "window.close()");
			mav.setViewName("learningTaskScoreInsert");

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView taskScorePage() { // 과제 점수 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> al = null;
		StringBuffer sb = new StringBuffer();
		BoardBean board;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			sb.append("<select id='selectid' class='btn btn-primary' name='number'>");
			sb.append("<option>학생선택</option>");

			for(int i = 0; i < al.size(); i++) {
				board = new BoardBean();
				board.setStudentCode(al.get(i).getStudentCode());
				board.setStudentName(dao.stNameGet(board));

				sb.append("<option value="+board.getStudentCode()+">"+board.getStudentName()+"</option>");

			}
			sb.append("</select>");

			mav.addObject("select", sb.toString());
			mav.setViewName("learningTaskScore");

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}




}


