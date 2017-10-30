package icia.project.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

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
			
		case 26:   // 선생님 학생 전체보기 자세히 보기
			mav = teacherLearningSTadminCXT((BoardBean)object[0]);
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
		DbBoardBean bb;
		ViewService view = new ViewService(); 

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			bb = dao.tclearningNoticeConfirm(board);

			bb.setBoardCode((String)session.getAttribute("identity"));

			bb.setCutRoute(bb.getBoardRoute().substring(0,68));	// 루트만

			bb.setCutContent(bb.getBoardRoute().substring(68));	// 파일이름

			System.out.println("디비보드빈 : " + bb.getBoardTitle());
			System.out.println("공지사항 내용확인 메서드 진입"+bb.getBoardTitle()+bb.getRoomCode());
			List<String> list = view.getList(bb);
			mav.addObject("list",list);
			mav.addObject("boardTitle",bb.getBoardTitle());
			mav.addObject("boardContent",bb.getBoardContent());
			mav.addObject("boardDate",bb.getBoardDate());
			mav.addObject("boardId",bb.getBoardId());
			mav.addObject("file",bb.getCutContent());
			mav.addObject("content", getTclearningNoticeCTX(bb));


			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningNoticeCXT");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getTclearningNoticeCTX(DbBoardBean bb) { // 공지사항 내용 끌고오기

		/*mav.addObject("list",list);
		mav.addObject("theme",bb.getBoardTitle());
		mav.addObject("content",bb.getBoardContent());
		mav.addObject("date",bb.getBoardDate());
		mav.addObject("writeId",bb.getBoardId());
		mav.addObject("route",route);
		mav.addObject("file",bb.getCutContent());*/

		StringBuffer sb = new StringBuffer();
		/*sb.append("<table>");
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
		sb.append("</table>");*/
		sb.append("<input type=\"button\" value=\"목록\" onClick=\"menu('3','"+ bb.getBoardCode() +"')\"/>");
		sb.append("<input type=\"button\" value=\"수정\" onClick=\"update('"+ bb.getBoardTitle() +"','"+ bb.getBoardContent() +"','"+ bb.getBoardDate() +"')\"/>");
		sb.append("<input type=\"button\" value=\"삭제\" onClick=\"boardDelete('"+ bb.getRoomCode() +"','"+ bb.getBoardDate() +"')\"/>");
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
			bb = dao.learningQuestionlist(board);
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
		}
		catch(Exception ex){
			ex.printStackTrace();
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
				sb.append("아이디");
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
					board.setStudentName(boardList.get(i).getStudentName());
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
					sb.append("<input type='button' value='선생님 코멘트' onClick='commentCheck("+boardList.get(i).getBoardCode()+")' />");
					sb.append("</td>");
					sb.append("</tr>");
				}

				sb.append("</table>");
				mav.addObject("content", sb.toString());

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
		ViewService view = new ViewService(); 
		boolean transaction = false;
		String page = null;
		StringBuffer sb = new StringBuffer();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));

			if(dao.learningWANCommentCheck(board) != 0) {	// 코멘트 있음

/*				board = dao.learningWANCommentGet(board);

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
				sb.append("</table>");*/

				DbBoardBean bb = dao.learningWANCommentGet(board);	// 전체 루트(파일이름까지)

				bb.setCutRoute(bb.getBoardRoute().substring(0,68));	// 루트만
				String route = bb.getCutRoute();

				bb.setCutContent(bb.getBoardRoute().substring(68));	// 파일이름

				List<String> list = view.getList(bb);
			
				mav.addObject("content",bb.getBoardContent());
				mav.addObject("list",list);
				mav.addObject("file",bb.getCutContent());
				mav.addObject("date",bb.getBoardDate());
				mav.addObject("writeId",bb.getBoardId());
				mav.addObject("route",route);
				mav.addObject("rommCode", bb.getRoomCode());
				mav.addObject("boardCode", bb.getBoardCode());
				
				sb.append("<tr>");
				sb.append("<td><input type='button' value='수정' onClick='learningWANCMUpdatePage("+bb.getBoardCode()+")' /></td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td><input type='button' value='삭제' onClick='learningWANCMDelete("+bb.getBoardCode()+","+bb.getRoomCode()+")' /></td></tr>");

				page="learningWANCXT";
				transaction = true;

			}else {	// 코멘트 없음

				sb.append("<input type='button' value='코멘트 등록' onClick='commentInsertPage("+board.getBoardCode()+")' />");
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
			
			System.out.println(board.getBoardContent());
			System.out.println(board.getBoardRoute());
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
			mav.addObject("boardContent", board.getBoardContent());
			mav.addObject("boardRoute", "Ggg");

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
		}
		catch(Exception ex){
			ex.printStackTrace();
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
					sb.append("<input type='button' value='선생님 코멘트' onClick='commentCheck("+boardList.get(i).getBoardCode()+")' />");
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

				sb.append("<table>");
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
				mav.addObject("title1", stName+"이 자주 질문하는 문제유형");




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
			ex.printStackTrace();
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
		sb.append("<input type=\"button\" value=\"글쓰기\" onClick=\"debateInsert()\"/>");

		return sb.toString();
	}

	private ModelAndView tclearningDebateCTX(BoardBean board) { // 선생님 토론게시판 내용확인
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			System.out.println("토론게시판 신분:"+session.getAttribute("identity"));
			board = dao.tclearningDebateCTX(board);
			board.setBoardCode((String)session.getAttribute("identity"));
			System.out.println("보드한번더더더더더:"+board.getBoardCode());

			mav.addObject("content", getTclearningDebateCTX(board));

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningDebateCTX");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getTclearningDebateCTX(BoardBean board) { // 토론게시판 내용 끌고오기
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
		sb.append("<td>내용 : " + board.getBoardContent() + "</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type=\"button\" value=\"목록\" onClick=\"menu('5','"+ board.getBoardCode() +"')\"/>");
		sb.append("<input type=\"button\" value=\"수정\" onClick=\"update('"+ board.getBoardTitle() +"','"+ board.getBoardContent() +"','"+ board.getBoardDate() +"')\"/>");
		sb.append("<input type=\"button\" value=\"삭제\" onClick=\"boardDelete('"+ board.getRoomCode() +"','"+ board.getBoardDate() +"')\"/>");
		return sb.toString();
	}

	private ModelAndView tclearningDebateUpdatePage(BoardBean board) { // 선생님 토론게시판 수정 페이지
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
			if(dao.tclearningDebateUpdate(board) != 0) {
				System.out.println("토론게시판 업데이트 완료");
				transaction = true;
			}
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

			if(dao.tclearningDebateInsert(board) != 0) {
				System.out.println("토론게시판 글쓰기 성공");
				transaction = true;
			}
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
			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");

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

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");


			DbBoardBean bb = dao.questionBoardCXT(board);   // 전체 루트(파일이름까지)

			bb.setCutRoute(bb.getBoardRoute().substring(0,68));   // 루트만
			String route = bb.getCutRoute();
			bb.setCutContent(bb.getBoardRoute().substring(68));   // 파일이름

			List<String> list = view.getList(bb);
			mav.addObject("list",list);
			mav.addObject("theme",bb.getBoardTitle());
			mav.addObject("content",bb.getBoardContent());
			mav.addObject("date",bb.getBoardDate());
			mav.addObject("writeId",bb.getBoardId());
			mav.addObject("roomCode",bb.getRoomCode());
			mav.addObject("route",route);
			mav.addObject("file",bb.getCutContent());

			taglist = dao.learningQuestionTagCXT(board);

			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td>내용</td>");
			sb.append("<td>날짜</td>");
			sb.append("<td>아이디</td>");
			sb.append("</tr>");
			for(int i=0; i<taglist.size(); i++) {
				sb.append("<tr>");
				sb.append("<td>" + taglist.get(i).getTagContent() + "</td>");
				sb.append("<td>" + taglist.get(i).getTagDate() + "</td>");
				sb.append("<td>" + taglist.get(i).getTagId() + "</td>");
				//sb.append("<td>" + "<input type=\"button\" value=\" 삭제\" onClick=\"stadmin('"+ar.get(i).getStudentCode() +"')\"/>" + "</td>");
				sb.append("</tr>");

			}
			sb.append("</table>");

			mav.addObject("message", message);
			mav.addObject("taglists", sb.toString());

			mav.addObject("roomcode",session.getAttribute("roomCode"));

			mav.setViewName("learningQuestionCXT");
			transaction = true;


		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}
	private ModelAndView learningQuestionTag(BoardBean board) { // 질문 댓글 쓰기

		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			board.setId((String)session.getAttribute("tcId"));
			System.out.println(board.getId());
			board.setTagId(board.getId());
			System.out.println(board.getTagId());

			board.setRoomCode((String)session.getAttribute("roomCode"));


			if(dao.learningQuestionTag(board) !=0) {
				System.out.println("성공은햇어");
			}else {
				System.out.println("실패");
			}
			mav.setViewName("learningQuestionCXT");
			transaction = true;


		}catch(Exception ex){
			ex.printStackTrace();
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
	         board.setId((String)session.getAttribute("identity"));
	         //mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
	         ar = dao.teacherLearningSTadmin(board);
	         mav.addObject("content", tclearningNoticeList(board,ar));
	         ;
	         sb.append("<table>");
	         sb.append("<tr>");
	         sb.append("<td>과목</td>");
	         sb.append("<td>학년/반/번호</td>");
	         sb.append("</tr>");
	         for(int i=0; i<ar.size(); i++) {
	            sb.append("<tr>");   
	            sb.append("<td>" + ar.get(i).getRoomCode() + "</td>");
	            sb.append("<td>" + ar.get(i).getStudentCode() + "</td>");
	            sb.append("<td>" + "<input type=\"button\" value=\"학생 자세히 보기\" onClick=\"stadmin('"+ar.get(i).getStudentCode() +"')\"/>" + "</td>");
	            sb.append("</tr>");
	         }
	         sb.append("</table>");
	         mav.addObject("teacherLearningSTadmin", sb.toString());
	         transaction = true;

	      }catch(Exception ex){

	      }finally {
	         mav.setViewName("teacherLearningSTadmin");
	         setTransactionResult(transaction);
	      }
	      return mav;
	   }
	   
	   
	   private ModelAndView teacherLearningSTadminCXT(BoardBean board) { // 선생님 학생보기 자세히 보기

	      mav = new ModelAndView();
	      boolean transaction = false;
	      StringBuffer sb = new StringBuffer();
	      ArrayList<BoardBean> ar = null;
	      setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

	      try {
	         session.getAttribute("roomCode");

	         board.setRoomCode((String)session.getAttribute("roomCode"));
	         board.setId((String)session.getAttribute("identity"));
	         //mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
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
	   }
	   
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
		         ex.printStackTrace();
		      }finally {
		         setTransactionResult(transaction);
		      }

		      return mav;
		   }
}

