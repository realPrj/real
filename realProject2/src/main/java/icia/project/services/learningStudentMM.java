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

import icia.project.bean.BoardBean;
import icia.project.bean.DbBoardBean;
import icia.project.dao.IMybatis;
import icia.project.dao.TransactionExe;

@Service
public class learningStudentMM extends TransactionExe {


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

		case 1:	 // 학생 과제 삭제
			mav = deleteTask((BoardBean)object[0]);
			break;

		case 2: // 과제 페이지 유뮤 확인
			mav = learningSelect((BoardBean)object[0]);
			break;

		case 3:	//공지사항 페이지
			mav = stlearningNoticeList((BoardBean)object[0]);
			break;

		case 4:	//질문게시판 페이지
			mav = questionBoardPage((BoardBean)object[0]);
			break;
		case 5: // 질문게시판 인설트
			mtfRequest = ((MultipartHttpServletRequest)object[1]);
			mav = learningQuestionInsert((BoardBean)object[0]);
			break;

		case 6: //질문게시판 자세히 보기   
			mav = questionBoardCXT((BoardBean)object[0]);
			break;

		case 7:	// 오답노트
			mav = learningWANPage((BoardBean)object[0]);
			break;

		case 8: // 공지사항 내용확인
			mav = stlearningNoticeCTX((BoardBean)object[0]);
			break;

		case 9: // 수강생
			mav = studentLearningSTadmin((BoardBean)object[0]);
			break;

		case 10: // 공지사항 글쓰기

			break;

		case 11: // 공지사항 수정 페이지

			break;

		case 12:   // 질문게시판 삭제
			mav = deleteQuestion((BoardBean)object[0]);
			break;

		case 13:   //질문 수정
			mav = learningQuUpdate((BoardBean)object[0]);
			break;

		case 14:	//과제 보기
			mav = learningTaskPage((BoardBean)object[0]);
			break;

		case 15:	// 과제 제출
			mtfRequest = ((MultipartHttpServletRequest)object[1]);
			mav = learningSubmitTaskInsert((BoardBean)object[0]);
			break;

		case 16:	 //학생 과제 확인
			mav = checkFile((BoardBean)object[0]);
			break;

		case 17:	// 오답노트 코멘트 페이지
			mav = learningWANCMCXTPage((BoardBean)object[0]);
			break;

		case 18:	// 오답노트 등록

			break;

		case 21:	// 오답노트 삭제

			break;

		case 32 : // 학생 토론게시판 페이지
			mav = stlearningDebatePage((BoardBean)object[0]);
			break;

		case 33 : // 학생 토론게시판 내용확인
			mav = stlearningDebateCTX((BoardBean)object[0]);
			break;

		case 34 : // 학생 토론게시판 댓글등록
			mav = learningDebateTagInsert((BoardBean)object[0]);
			break;

		case 35 : // 학생 토론게시판 댓글삭제
			mav = learningDebateTagDelete((BoardBean)object[0]);
			break;

		case 36 : // 받은 쪽지 페이지
			mav = learningReceiveMessagePage((BoardBean)object[0]);
			break;

		case 37:   // 받은쪽지 내용확인
			mav = learningGetMessageCTX((BoardBean)object[0]);
			break;

		case 38:   // 보낸쪽지 리스트 페이지
			mav = learningSentMessagePage((BoardBean)object[0]);
			break;

		case 39:   // 쪽지 보내기 페이지
			mav = learningSendMessagePage((BoardBean)object[0]);
			break;	

		case 40:   // 쪽지 보내기
			mav = learningSendMessage((BoardBean)object[0]);
			break;

		case 41:   // 보낸쪽지 내용확인
			mav = learningSentMessageCTX((BoardBean)object[0]);
			break;

		case 42:   // 받은쪽지 삭제
			mav = learningGetMessageDelete((BoardBean)object[0]);
			break;

		case 43:   // 보낸쪽지 삭제
			mav = learningSentMessageDelete((BoardBean)object[0]);
			break;

		case 44:   // 강의계획서
			mav = learningPlanPage((BoardBean)object[0]);
			break;

		case 45:   // 강의계획서자세히 보내기
			mav = learningPlanCTXPage((BoardBean)object[0]);
			break;

		case 46:   // 강의계획서자세히 보내기
			mav = sttaskScorePage();
			break;



		}

		return mav;

	}


	private ModelAndView learningWANPage(BoardBean board) { // 오답노트 페이지

		mav = new ModelAndView();

		BoardBean board2;
		ArrayList<BoardBean> boardList = null;
		ArrayList<BoardBean> typeSum = null;
		ArrayList<BoardBean> yearCode = null;
		ArrayList<BoardBean> allRoomCode = null;
		ArrayList<BoardBean> allGraph = null;
		StringBuffer sb;
		StringBuffer sum;
		boolean transaction = false;
		String page = null;
		double questionCount = 0;
		double peopleCount = 0;
		double peopleCount2 = 0;
		double value1 = 0;
		double value2 = 0;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setStudentCode((String)session.getAttribute("stCode"));
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setStudentName(dao.stNameGet(board));	// 학생 이름 추출

			mav.addObject("studentName", board.getStudentName());
			mav.addObject("stHalf", board.getStudentCode().substring(2, 4));
			mav.addObject("stNumber", board.getStudentCode().substring(4, 6));

			if(dao.allWANSum(board) != 0) {

				board.setAllSum(dao.allWANSum(board));		// 학생이 물어본 총 문제 수


				mav.addObject("allSum", board.getAllSum());

				/////////////////////////////////////////////////////////////////////////

				boardList = dao.learningWANstListGet(board);
				yearCode = dao.learningWANstYearCodeOneGet(board);

				sb = new StringBuffer();
				sum = new StringBuffer();
				sb.append("<select id = 'yearSelect' class='btn-sm'>");
				sb.append("<option></option>");

				for(int i =0; i < yearCode.size(); i++) {
					board = new BoardBean();
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setYearCode(yearCode.get(i).getYearCode());
					board.setStudentCode((String)session.getAttribute("stCode"));
					sb.append("<option>"+yearCode.get(i).getYearCode()+"</option>");

					typeSum = dao.learningWANstTypeSum(board);

					sum.append("<br><biv style=' text-shadow: 0.1em 0.1em 0.15em #FF5E00; text-align:center; margin-left: 680px;border:1px solid #FAE0D4;  width:300px;' name='hidediv' id='"+yearCode.get(i).getYearCode().substring(0, 6)+"' >");

					for(int y = 0; y < typeSum.size(); y++) {
						board = new BoardBean();
						board.setRoomCode(boardList.get(0).getRoomCode());
						board.setYearCode(yearCode.get(i).getYearCode());
						board.setRoomSB(dao.learningSBCodeGet(board));
						board.setTypeCode(typeSum.get(y).getTypeCode());
						board.setTypeName(dao.learningTypeNameGet(board));
						board.setTypeSum(typeSum.get(y).getTypeSum());	
						sum.append("<div>"+board.getTypeName()+" : "+ board.getTypeSum()+"개</div><br>");

					}
					sum.append("</biv>");

				}

				sb.append("</select>");
				mav.addObject("yearSelect", sb.toString());
				mav.addObject("typeSumb", sum.toString());

				/////////////////////////////////////////////////////////////////////////

				sb = new StringBuffer();
				sb.append("<table style='text-align:center' id='tableList' class=\"table table-hover\">");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<b>게시글 번호</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>년도</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>문제유형</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>문제 번호</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>날짜</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>선생님 코멘트</b>");
				sb.append("</td>");
				sb.append("<td>");
				sb.append("<b>응답여부</b>");
				sb.append("</td>");
				sb.append("</tr>");

				mav.addObject("content3", sb.toString());
				sb = new StringBuffer();

				// 1
				int forI = 0;
				int forB = 0;
				int pageCount = 5;
				int pageCount2 = pageCount;

				double sizeDouble = boardList.size() / (double)pageCount;

				for(forI=0; forI < sizeDouble; forI++) {

					if(boardList.size()< pageCount) {
						pageCount= boardList.size();
					}

					sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");

					for(forB = forB; forB < pageCount; forB++){

						board = new BoardBean();
						board.setRoomCode(boardList.get(0).getRoomCode());
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
						sb.append("<input type='button-sm' name='cmClick' class='btn' id="+boardList.get(forB).getBoardCode()+" value='선생님 코멘트'  />");
						sb.append("</td>");
						sb.append("<td>");
						sb.append(check);
						sb.append("</td>");
						sb.append("</tr>");
					}

					sb.append("</tbody>");	

					pageCount+=pageCount2;

				}
				sb.append("</table>");

				mav.addObject("content", sb.toString());

				sb = new StringBuffer();

				sb.append("<div class='text-center'>");
				sb.append("<ul class='pagination'>");


				for(int y=0; y < sizeDouble; y++) {

					//sb.append("<li><input class='btn-sm' type='button' value="+(y+1)+" onClick='pageNumber("+y+")' /></li>");
					sb.append("<li><a onClick='pageNumber("+y+")'>"+(y+1)+"</a></li>");
				}
				sb.append("</ul>");
				sb.append("</div>");


				mav.addObject("content2", sb.toString());


				////////////////////////////////////////////////////////////////////////

				board = new BoardBean();

				board.setRoomCode((String)session.getAttribute("roomCode"));
				board.setStudentCode((String)session.getAttribute("stCode"));

				boardList = dao.learningWANstListGetOverlap(board);
				yearCode = dao.learningWANstYearCodeOneGet(board);

				sb = new StringBuffer();

				sb.append("<table style='text-align:center' class=\"table table-hover\">");
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
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setRoomSB(boardList.get(0).getRoomSB());
					board.setTypeCode(boardList.get(i).getTypeCode());
					board.setNumberCode(boardList.get(i).getNumberCode());
					board.setYearCode(boardList.get(i).getYearCode());
					board.setStudentCode((String)session.getAttribute("stCode"));
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


				////////////////////////////////////그래프////////////////////////////////////

				sb = new StringBuffer();
				board = new BoardBean();
				board.setRoomSB(boardList.get(0).getRoomSB());
				board.setRoomCode((String)session.getAttribute("roomCode"));
				board.setStudentCode((String)session.getAttribute("stCode"));

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
				mav.addObject("title1", "내가 자주 질문하는 문제유형");

			}	// if 끝

			////////////////////////////////////그래프 끝///////////////////////////////////

			page = "learningStudentWAN";
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView stlearningNoticeList(BoardBean board) { // 공지사항 리스트
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");
			ar = dao.tclearningNoticeList(board);
			mav.addObject("content", stlearningNoticeList(board,ar));
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

	private String stlearningNoticeList(BoardBean board, ArrayList<BoardBean> ar) { // 공지사항 리스트
		StringBuffer sb = new StringBuffer();
		sb.append("<table style='text-align:center' class=\"table table-hover\">");
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
				sb.append("<td class=\"CTX\" onClick=\"confirm('"+ ar.get(forB).getBoardTitle() +"','" + ar.get(forB).getBoardDate() + "')\">" + ar.get(forB).getBoardTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardId() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardDate() + "</td>");
				sb.append("</tr>");
			}

			sb.append("</tbody>");
			pageCount+=pageCount2;
		}
		sb.append("</table>");
		return sb.toString();
	}

	private ModelAndView stlearningNoticeCTX(BoardBean board) { // 공지사항 내용확인

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
			bb.setBoardCode((String)session.getAttribute("identity"));

			bb.setCutRoute(bb.getBoardRoute().substring(0,68));	// 루트만

			bb.setCutContent(bb.getBoardRoute().substring(68));	// 파일이름
			List<String> list = view.getList(bb);

			mav.addObject("list",list);
			mav.addObject("boardTitle",bb.getBoardTitle());
			mav.addObject("boardContent",bb.getBoardContent().replace("\r\n", "<br/>"));
			mav.addObject("boardDate",bb.getBoardDate());
			mav.addObject("boardId",bb.getBoardId());
			mav.addObject("file",bb.getCutContent());
			sb.append("<br/><input class=\"CTXbtn\" style=\"border:none; margin-left:55%\" type=\"button\" value=\"목록\" onClick=\"menu('3','"+ bb.getBoardCode() +"')\"/>");
			mav.addObject("content", sb.toString());


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
		sb.append("<input type=\"button\" value=\"목록\" onClick=\"menu('3','"+ bb.getBoardCode() +"')\"/>");
		return sb.toString();
	}*/

	private ModelAndView learningWANCMCXTPage(BoardBean board) { // 오답노트 코멘트 페이지 이동

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

				mav.addObject("content",bb.getBoardContent());
				mav.addObject("list",list);
				mav.addObject("file",bb.getCutContent());
				mav.addObject("date",bb.getBoardDate());
				mav.addObject("writeId",bb.getBoardId());
				mav.addObject("route",route);

			}else {	// 코멘트 없음

				sb.append("<input type='button' class='btn-sm' value='코멘트가 없습니다.' onClick='windowcloseClick()' />");
				mav.addObject("windowcloseClick", "window.close()");
				mav.addObject("content", sb.toString());
			}

			page="learningWANCXT";
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName(page);
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView questionBoardPage(BoardBean board) { // 질문사항 페이지 리스트
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
			sb.append("<table style='text-align:center' class=\"table table-hover\">");
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
					//sb.append("<td>" + "<input type='button'class='btn' value='"+bb.get(i).getBoardTitle()+"' onClick=viewData(\'"+bb.get(i).getRoomCode()+"\',"+"\'"+bb.get(i).getBoardDate()+"\') />" + "</td>");
					sb.append("<td>" + bb.get(forB).getBoardId() + "</td>");
					sb.append("<td>" +bb.get(forB).getBoardDate()+"</td>");
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
		mav.setViewName("learningQuestionStudent");
		return mav;
	}


	private ModelAndView learningQuestionInsert(BoardBean board) { // 질문게시판 인설트
		mav = new ModelAndView();
		boolean transaction = false;
		fileupload(board,mtfRequest);

		chat = new ChatWebSocketHandler();

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {         
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setStudentCode((String)session.getAttribute("stCode"));

			session.getAttribute("roomCode");
			if(dao.learningQuestionInsert(board) != 0) {
				board.setStudentCode(board.getStudentCode());
				board.setStudentName(dao.stNameGet(board));	
				chat.msg("질문 게시판에 "+board.getStudentName()+"님이"+board.getBoardTitle()+"등록하셨습니다.");

			}
			transaction = true;
		}
		catch(Exception ex){
		}finally {
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

	private ModelAndView questionBoardCXT(BoardBean board) { //질문 페이지 자세히 보기

		ViewService view = new ViewService(); 
		mav = new ModelAndView();
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> taglist = null;
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

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
					sb.append("</tr>");
					sb.append("<tr>");
					sb.append("<td class=\"tag_content\" colspan=\"3\">" + taglist.get(i).getTagContent().replace("\r\n", "<br/>") + "</td>");
					sb.append("</tr>");
					sb.append("</table>");
				}else {
					break;
				}

			}
			sb.append("</table>");
			mav.addObject("taglists", sb.toString());

			mav.addObject("roomcode",session.getAttribute("roomCode"));

			mav.setViewName("learningQuestionStudentCXT");
			transaction = true;


		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}
	private ModelAndView deleteQuestion(BoardBean board) { // 학생 질문 삭제
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {   
			if(dao.deleteQuestion(board) != 0) {

				transaction = true;

			}
		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}

		mav.setViewName("learningQuestion");

		return mav;
	}

	private ModelAndView learningQuUpdate(BoardBean board) { // 학생 질문 업데이트
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {   
			if(dao.learningQuUpdate(board) != 0) {
				transaction = true;

			}
		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView stlearningDebatePage(BoardBean board) { // 학생 토론 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("identity"));

			ar = dao.tclearningDebateList(board);

			mav.addObject("content", stlearningDebateList(board,ar));
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

	private String stlearningDebateList(BoardBean board, ArrayList<BoardBean> ar) { // 토론게시판 리스트 출력
		StringBuffer sb = new StringBuffer();
		sb.append("<table style='text-align:center' class=\"table table-hover\">");
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
				sb.append("<td  class=\"CTX\" onClick=\"confirm('"+ ar.get(forB).getBoardTitle() +"','" + ar.get(forB).getBoardDate() + "','"+ board.getId() +"')\">" + ar.get(forB).getBoardTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardId() + "</td>");
				sb.append("<td>" + ar.get(forB).getBoardDate() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			pageCount+=pageCount2;
		}
		sb.append("</table>");
		return sb.toString();
	}

	private ModelAndView stlearningDebateCTX(BoardBean board) { // 토론게시판 내용확인
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board = dao.tclearningDebateCTX(board);
			board.setBoardCode((String)session.getAttribute("identity"));
			mav.addObject("content", getStlearningDebateCTX(board));
			mav.addObject("boardTitle", board.getBoardTitle());
			mav.addObject("boardId", board.getBoardId());
			mav.addObject("boardDate", board.getBoardDate());
			mav.addObject("boardContent", board.getBoardContent().replace("\r\n", "<br/>"));

			ar = dao.learningDebateTagList(board);
			mav.addObject("debateTagList", getlearningDebateTagList(ar,board));

			StringBuffer sb = new StringBuffer();
			sb.append("<textarea onkeydown=\"resize(this)\" name=\"tagContent\" placeholder=\"댓글을 입력하세요\"></textarea>"); 
			sb.append("<input class=\"insert\" type=\"button\" value=\"댓글입력\" id=\"sendBtn\" onClick=\"debateTagInsert('"+ board.getBoardTitle() +"','"+ board.getBoardDate() +"','"+ board.getBoardId() +"')\"/>");
			mav.addObject("tagInsert", sb.toString());
			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningDebateCTX");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getlearningDebateTagList(ArrayList<BoardBean> ar, BoardBean board) throws Exception { // 토론게시판 댓글 리스트
		StringBuffer sb = new StringBuffer();

		/*sb.append("<input type=\"text\"  name=\"tagContent\" placeholder=\"댓글을 입력하세요\" />");
		sb.append("<input type=\"button\"class='btn-sm'  value=\"댓글등록\" "
				+ "onClick=\"debateTagInsert('"+ board.getBoardTitle() +"','"+ board.getBoardDate() +"','"+ board.getBoardId() +"')\" />");
		 */
		/*sb.append("<table  class=\"table table-hover\">");
		sb.append("<tr>");
		sb.append("<td> 작성자 </td>");
		sb.append("<td> 내용 </td>");
		sb.append("<td> 날짜 </td>");
		sb.append("</tr>");*/
		for(int i=0; i < ar.size(); i++) {
			/*sb.append("<tr>");
			sb.append("<td>" + ar.get(i).getStudentName() + "</td>");
			sb.append("<td>" + ar.get(i).getTagContent() + "</td>");
			sb.append("<td>" + ar.get(i).getTagDate() + "</td>");
			if(session.getAttribute("stCode").equals(ar.get(i).getStudentCode())) {
				sb.append("<td><input type=\"button\" class='btn' value=\"삭제\" onClick=\"TagDelete('"+ ar.get(i).getTagDate() +"','"+ board.getBoardDate() +"')\"/></td>");
			}
			sb.append("</tr>");*/
			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td class=\"tag_id\">" + ar.get(i).getStudentName()+ "</td>");
			sb.append("<td class=\"tag_date\">" + ar.get(i).getTagDate() + "</td>");
			if(session.getAttribute("stCode").equals(ar.get(i).getStudentCode())) {
				sb.append("<td class=\"tag_delete\">"+ "<input type='button'class=\"CTXbtn\" style=\"border:none\" value='삭제' onClick=\"TagDelete('\"+ ar.get(i).getTagDate() +\"','\"+ board.getBoardDate() +\"')\"/>"+"</td>");
			}
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td class=\"tag_content\" colspan=\"3\">" + ar.get(i).getTagContent().replace("\r\n", "<br/>") + "</td>");
			sb.append("</tr>");
			sb.append("</table>");
		}
		//sb.append("</table>");

		return sb.toString();
	}

	private String getStlearningDebateCTX(BoardBean board) { // 토론게시판 내용 끌고오기
		/*	StringBuffer sb = new StringBuffer();
		sb.append("<table class=\"table table-hover\">");
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
		sb.append("</br>");*/
		StringBuffer sb = new StringBuffer();
		sb.append("<input class=\"CTXbtn_end\" style=\"border:none; margin-left:55%\" type=\"button\" value=\"목록\" onClick=\"menu('5','"+ board.getBoardCode() +"')\"/>");
		return sb.toString();
	}


	private ModelAndView learningDebateTagInsert(BoardBean board) { // 토론게시판 댓글 등록

		mav = new ModelAndView();
		boolean transaction = false;
		chat = new ChatWebSocketHandler();
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));


			if(dao.learningDebateTagInsert(board) != 0) {

				board.setStudentName(dao.stNameGet(board));	

				chat.msg(board.getStudentName()+" 댓글을 입력해셨습니다");

				transaction = true;
			}
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
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			String identity = (String)session.getAttribute("identity");
			mav.addObject("identity", identity);

			sb = new StringBuffer();

			roomcode = (String)session.getAttribute("roomCode");

			board.setRoomCode(roomcode);

			if(board.getBoardCode() != null) {   // 게시글 내용,댓글 보여주기

				board = dao.learningTaskGet(board);   // 게시글 내용

				/*mav.addObject("title", board.getBoardTitle());
		            mav.addObject("date",board.getBoardDate());
		            mav.addObject("content", board.getBoardContent());   */   
				sb.append("<br/>");
				sb.append("<h3 style=\"padding-left:10px; font-family: 'Nanum Gothic', sans-serif\">과제정보</h3>");
				sb.append("<center>");
				sb.append("<table id=\"ctx\" class=\"taskinfo\">");
				sb.append("<tr>");
				sb.append("<td class=\"title\">제목</td>");
				sb.append("<td class=\"title\" style=\"width:550px\">"+ board.getBoardTitle()+"</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td class=\"title\">등록일</td>");
				sb.append("<td class=\"title\">"+board.getBoardDate()+"</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td class=\"title\" style=\"border:none\"></td>");
				sb.append("<td colspan=\"2\" class=\"title\" style=\"padding:20px 3px; border:none\">"+board.getBoardContent().replace("\r", "<br/>")+"</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("</center>");
				mav.addObject("taskInfo", sb.toString());
				sb = new StringBuffer();
				sb.append("<input type='button' class=\"insert\" value='과제 제출' onClick=\"confirm('"+ board.getBoardTitle() +"','" + board.getRoomCode() + "','"+ board.getBoardCode() +"')\">");
				sb.append("<br/>");
				sb.append("<br/>");
				mav.addObject("inputButton", sb.toString());

				board.setStudentCode((String)session.getAttribute("stCode"));
				board.setRoomCode(roomcode);

				al = dao.learningTaskSelect(board);   // 댓글 내용
				sb = new StringBuffer();
				sb.append("<br/>");
				sb.append("<h3 style=\"padding-left:10px\">제출한 과제</h3>");
				sb.append("<table style='text-align:center' class=\"table table-hover\">");
				sb.append("<tr>");
				sb.append("<td><b>학생 이름 </b></td>");
				sb.append("<td><b>제출 날짜 </b></td>");
				sb.append("<td><b>파일 보기 </b></td>");
				sb.append("</tr>");

				for(int i = 0; i < al.size(); i++) {

					board.setStudentCode(al.get(i).getStudentCode());
					board.setStudentName(dao.stNameGet(board));   

					sb.append("<tr>");
					sb.append("<td>" +board.getStudentName() + "</td>");
					sb.append("<td>" +al.get(i).getBoardDate() + "</td>");
					sb.append("<td class=\"CTX\" style=\"color:blue\" onClick='checkFile("+board.getRoomCode()+","+al.get(i).getBoardCode()+")'>" + "제출한 파일"+ "</td>");
					sb.append("</tr>");

				}
				sb.append("</table>"); 
				mav.addObject("tagcontent", sb.toString());

				mav.addObject("checkContent", 1);

			}

			board = new BoardBean();
			sb = new StringBuffer();

			board.setRoomCode(roomcode);

			if(dao.learningTaskCheck(board) != 0) {    

				al = dao.learningTaskList(board);    // 과제 리스트 담기
				sb.append("<table style='text-align:center' class=\"table table-hover\">");
				sb.append("<tr>");
				sb.append("<td>");
				sb.append("<b>게시물 번호</b>");
				sb.append("</td>");
				sb.append("<td><b>제목</b></td>");
				sb.append("<td><b>날짜</b></td>");
				sb.append("</tr>");


				int forI = 0; // 크게 한사람
				int forB = 0;   // 내용물
				int pageCount = 5; // 
				int pageCount2 = pageCount; // 

				double sizeDouble = al.size() / (double)pageCount;

				for(forI=0; forI < sizeDouble; forI++) {

					if(al.size()< pageCount) {
						pageCount= al.size();
					}

					sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");


					for(forB=forB; forB<pageCount; forB++) {   // 과제 리스트 출력
						sb.append("<tr>");
						sb.append("<td>"+(forB+1)+"</td>");
						sb.append("<td class=\"CTX\" onClick=\"questionCXT(\'"+ al.get(forB).getBoardCode() +"\')\">"+ al.get(forB).getBoardTitle() + "</td>");
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
			mav.addObject("reload", "opener.location.reload()");
			mav.addObject("windowclose", "window.close()");
			mav.setViewName("learningTaskStudent");
			transaction = true;


		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}


	private ModelAndView learningDebateTagDelete(BoardBean board) { // 토론게시판 댓글 삭제
		mav = new ModelAndView();
		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));

			if(dao.learningDebateTagDelete(board) != 0) {

				transaction = true;
			}
		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}



	/*private ModelAndView learningSubmitTaskInsert(BoardBean board) { // 과제 제출
		mav = new ModelAndView();
		boolean transaction = false;
		fileupload(board,mtfRequest);

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {			
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));

			int Code = (int)(Math.random() *89999)+10000;
			board.setFileName(Integer.toString(Code));

			dao.learningSubmitTaskInsert(board);
			mav.addObject("reload", "opener.location.reload()");
			mav.addObject("windowclose", "window.close()");
			mav.setViewName("learningTaskSubmitInsert");

			transaction = true;

		}
		catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}*/
	private ModelAndView learningSubmitTaskInsert(BoardBean board) { // 과제 제출
		mav = new ModelAndView();
		boolean transaction = false;
		fileupload(board,mtfRequest);

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {         
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));

			int Code = (int)(Math.random() *89999)+10000;
			board.setFileName(Integer.toString(Code));

			dao.learningSubmitTaskInsert(board);
			board.setStudentCode(board.getStudentCode());
			board.setStudentName(dao.stNameGet(board));	
			chat.msg("과제 게시판에 "+board.getStudentName()+"님이 과제를 제출하셨습니다.");
			mav.addObject("reload", "opener.location.reload()");
			mav.addObject("windowclose", "window.close()");
			mav.setViewName("learningTaskSubmitInsert");

			transaction = true;

		}
		catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}

	private ModelAndView checkFile(BoardBean board) { //과제 페이지 자세하게 보기

		mav = new ModelAndView();

		ViewService view = new ViewService(); 

		boolean transaction = false;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board.setStudentCode((String)session.getAttribute("stCode"));

			//mav.addObject("content",session.getAttribute("roomCode") + "의 공지사항");

			DbBoardBean bb = dao.checkFile(board);   // 전체 루트(파일이름까지)
			board.setStudentCode(bb.getStudentCode());
			board.setStudentName(dao.stNameGet(board));	
			bb.setCutRoute(bb.getBoardRoute().substring(0,68));   // 루트만

			bb.setCutContent(bb.getBoardRoute().substring(68));   // 파일이름

			List<String> list = view.getList(bb);


			mav.addObject("list",list);
			mav.addObject("name",board.getStudentName());
			mav.addObject("date",bb.getBoardDate());


			mav.setViewName("learningTaskStudentCheck");

			transaction = true;


		}catch(Exception ex){

		}finally {
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
			board.setMessageId((String)session.getAttribute("stCode"));
			board.setIdentity((String)session.getAttribute("identity"));
			ar = dao.getMessageList(board);

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

	private String getlearningGetMessageList(ArrayList<BoardBean> ar, BoardBean board) { // 받은쪽지 리스트 끌고오기
		StringBuffer sb = new StringBuffer();
		sb.append("<table style=\"text-align:center\" class=\"table table-hover\" >");
		sb.append("<tr>");
		sb.append("<td><b>쪽지번호</b></td>");
		sb.append("<td><b>발신인</b></td>");
		sb.append("<td><b>제목</b></td>");
		sb.append("<td><b>날짜</b></td>");
		sb.append("</tr>");	 		
		int forI = 0; // 크게 한사람
		int forB = 0;	// 내용물
		int pageCount = 5; // 
		int pageCount2 = pageCount; 

		double sizeDouble = ar.size() / (double)pageCount;

		for(forI=0; forI < sizeDouble; forI++) {

			if(ar.size()< pageCount) {
				pageCount= ar.size();
			}

			sb.append("<tbody name=tbody"+forI+" id=tbody"+forI+">");
			for(forB=forB; forB<pageCount; forB++) {
				//sb.append("<tbody id=\"myTable\">");

				sb.append("<tr>");
				sb.append("<td>"+ (forB+1) +"</td>");
				sb.append("<td>" + ar.get(forB).getMessageOther() + "</td>");
				sb.append("<td style=\"cursor:pointer\" onClick=\"messageCTX('"+ board.getMessageCode() +"','"+ ar.get(forB).getRoomCode() +"','"+ ar.get(forB).getMessageDate() +"','"+ board.getIdentity() +"')\">" + ar.get(forB).getMessageTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getMessageDate() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			pageCount+=pageCount2;
		}
		sb.append("</table>");
		return sb.toString();
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

			sb.append("<br/>");
	        sb.append("<h3 style=\"text-align:center; font-family: 'Nanum Gothic', sans-serif\">받은쪽지 내용</h3><br/><br/>");
	        sb.append("<center>");
	        sb.append("<table id=\"ctx\" class=\"taskinfo\">");
	        sb.append("<tr>");
            sb.append("<td class=\"title\">보낸사람</td>");
            sb.append("<td class=\"title\" style=\"width:550px\">"+ board.getMessageOther()+"</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td class=\"title\">제목</td>");
            sb.append("<td class=\"title\">"+board.getMessageTitle()+"</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td class=\"title\" style=\"border:none\">내용</td>");
            sb.append("<td colspan=\"2\" class=\"title\" style=\"padding:20px 3px; border:none\">"+board.getMessageContent().replace("\r", "<br/>")+"</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</center>");
            sb.append("<br/>");
            mav.addObject("messageCTX", sb.toString());

			board.setIdentity((String)session.getAttribute("identity"));
			sb = new StringBuffer();
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

	private ModelAndView learningSentMessagePage(BoardBean board) { // 보낸쪽지 리스트 페이지
		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setMessageCode("S");
			board.setMessageId((String)session.getAttribute("stCode"));
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
			//sb.append("<tbody id=\"myTable\">");
			for(forB=forB; forB<pageCount; forB++) {
				//sb.append("<tbody id=\"myTable\">");

				sb.append("<tr>");
				sb.append("<td>"+ (forB+1) +"</td>");
				sb.append("<td>" + ar.get(forB).getMessageOther() + "</td>");
				sb.append("<td style=\"cursor:pointer\" onClick=\"messageCTX('" + board.getMessageCode() + "','" + board.getRoomCode() + "','"+ ar.get(forB).getMessageDate() +"','"+ board.getIdentity() +"')\">" + ar.get(forB).getMessageTitle() + "</td>");
				sb.append("<td>" + ar.get(forB).getMessageDate() + "</td>");
				sb.append("</tr>");
			}
			sb.append("</tbody>");
			pageCount+=pageCount2;
		}

		return sb.toString();
	}

	private ModelAndView studentLearningSTadmin(BoardBean board) { // 선생님 학생보기

		mav = new ModelAndView();
		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		ArrayList<BoardBean> ar = null;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setId((String)session.getAttribute("identity"));
			board.setStudentCode((String)session.getAttribute("stCode"));

			ar = dao.getStudentList(board);
			//mav.addObject("content", tclearningNoticeList(board,ar));

			board = dao.getTeacherInfo(board);
			// 선생님
			sb.append("<div class=\"card\"><br/>");
			sb.append("<h2 style=\"font-family: 'Nanum Gothic', sans-serif\">");
			sb.append("<b>선생님</b>");
			sb.append("</h2>");
			sb.append("<br/>");
			sb.append("<table  style='text-align:center' class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td><b>아이디</b></td>");
			sb.append("<td><b>이름</b></td>");
			sb.append("<td><b>이메일</b></td>");
			sb.append("<td><b>핸드폰 번호</b></td>");
			sb.append("<td><b>쪽지 보내기</b></td>");
			sb.append("<tr>");
			sb.append("<td>"+ board.getId() +"</td>");
			sb.append("<td>"+ board.getName() +"</td>");
			sb.append("<td>"+ board.getEmail() +"</td>");
			sb.append("<td>"+ board.getPhone() +"</td>");
			sb.append("<td><input type=\"button\"class='btn' value=\"쪽지보내기\" onClick=\"sendMessage('"+ board.getId() +"')\" /></td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</div>");
			mav.addObject("teacherInfo", sb.toString());

			// 학생
			sb = new StringBuffer();
			sb.append("<table  style='text-align:center' class=\"table table-hover\">");
			sb.append("<tr>");
			sb.append("<td>학년/반/번호</td>");
			sb.append("<td>아이디</td>");
			sb.append("<td>이름</td>");
			sb.append("<td>이메일</td>");
			sb.append("<td>핸드폰 번호</td>");
			sb.append("<td>쪽지 보내기</td>");
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

					sb.append("<td><input type=\"button\"class='btn' value=\"쪽지보내기\" onClick=\"sendMessage('"+ ar.get(forB).getStudentCode() +"')\" /></td>");


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

	private ModelAndView learningSendMessage(BoardBean board) { // 쪽지 보내기
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			session.getAttribute("roomCode");

			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setMessageCode("S");
			board.setMessageId((String)session.getAttribute("stCode"));

			if(dao.sendMessageS(board) != 0) {
				board.setMessageCode("G");
				if(dao.sendMessageG(board) != 0) {

				}
			}


			transaction = true;

		}catch(Exception ex){

		}finally {

			setTransactionResult(transaction);
		}
		return mav;
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
			
			sb.append("<br/>");
	        sb.append("<h3 style=\"text-align:center; font-family: 'Nanum Gothic', sans-serif\">보낸쪽지 내용</h3><br/><br/>");
	        sb.append("<center>");
	        sb.append("<table id=\"ctx\" class=\"taskinfo\">");
	        sb.append("<tr>");
            sb.append("<td class=\"title\">받은사람</td>");
            sb.append("<td class=\"title\" style=\"width:550px\">"+ board.getMessageOther()+"</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td class=\"title\">제목</td>");
            sb.append("<td class=\"title\">"+board.getMessageTitle()+"</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td class=\"title\" style=\"border:none\">내용</td>");
            sb.append("<td colspan=\"2\" class=\"title\" style=\"padding:20px 3px; border:none\">"+board.getMessageContent().replace("\r", "<br/>")+"</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</center>");
            mav.addObject("messageCTX", sb.toString());
			board.setIdentity((String)session.getAttribute("identity"));
			sb = new StringBuffer();
			sb.append("<input type=\"button\" class=\"btn btn-sm\" value=\"목록\" onClick=\"message('"+3+"','"+board.getIdentity()+"')\"/>");
			sb.append("<input type=\"button\" class=\"btn btn-sm\" value=\"삭제\" onClick=\"messageDelete('"+board.getIdentity()+"','"+board.getRoomCode()+"','"+board.getMessageCode()+"','"+board.getMessageDate()+"')\"/>");
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
			board.setMessageId((String)session.getAttribute("stCode"));

			if(dao.getMessageDelete(board) != 0) {

			}

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
			board.setMessageId((String)session.getAttribute("stCode"));

			if(dao.sentMessageDelete(board) != 0) {

			}

			transaction = true;

		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}
		return mav;
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
	private ModelAndView learningPlanPage(BoardBean board) { // 강의 계획서 페이지

		mav = new ModelAndView();

		boolean transaction = false;
		StringBuffer sb = new StringBuffer();
		String nowYear2 = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {
			String nowYear = dao.nowYearGet();
			nowYear2 = nowYear;

			sb.append("<select id = 'yearSelect' class='btn-sm'>");
			sb.append("<option>월 선택</option>");

			for(int i = 0; i <= 11; i++) {
				nowYear2 = Integer.toString(Integer.parseInt(nowYear.substring(0, 4)+"01")+i);
				sb.append("<option value="+nowYear2+">"+nowYear2.substring(0, 4)+"년"+nowYear2.substring(4)+"월"+"</option>");
			}
			sb.append("</select>");

			mav.addObject("select", sb.toString());
			mav.setViewName("learningPlanStudent");
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

				DbBoardBean bb  = dao.planCTX(board);

				bb.setCutRoute(bb.getBoardRoute().substring(0,68));   // 루트만

				bb.setCutContent(bb.getBoardRoute().substring(68));   // 파일이름

				List<String> list = view.getList(bb);

				mav.addObject("list",list);
				mav.addObject("title", "<input type='text'  name='boardTitle' value="+bb.getBoardTitle()+" readonly/>");
				mav.addObject("content","<input type='text'style='width: 150px;height:250px' name='boardContent' value="+bb.getBoardContent()+" readonly/>" );
				mav.addObject("check", 1);


			}else {
				mav.addObject("title", "등록된 계획이 없습니다");
				mav.addObject("check", 0);
			}

			mav.setViewName("learningPlanCTXStudent");

			transaction = true;

		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}


	public void getmethod(String today) {
		chat = new ChatWebSocketHandler();

		try {

			chat.msg(today);

		}catch(Exception ex) {

		}
		finally {

		}
	}

	private ModelAndView sttaskScorePage() { // 학생 과제 점수 페이지

		mav = new ModelAndView();
		boolean transaction = false;
		ArrayList<BoardBean> al = null;
		ArrayList<BoardBean> bb = null;
		ArrayList<BoardBean> score = new ArrayList<BoardBean>();
		StringBuffer sb = null;
		BoardBean board = null;

		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {

			board = new BoardBean();

			String stcode = (String)session.getAttribute("stCode");
			String roomcode = (String)session.getAttribute("roomCode");

			String ran = null;

			board.setRoomCode(roomcode);

			al = dao.learningTaskList(board);	// 게시 리스트

			bb = dao.learningTeskScoreRank(board);	// 등수

			for(int i = 0; i < bb.size(); i++) {	
				if(bb.get(i).getStudentCode().equals(stcode)){		
					ran = bb.get(i).getCaCode();
					break;	
				}		
			}

			for(int i = 0; i < al.size(); i++) {
				board = new BoardBean();
				board.setRoomCode(roomcode);
				board.setBoardTitle(al.get(i).getBoardTitle());
				board.setBoardCode(al.get(i).getBoardCode());
				board.setStudentCode(stcode);
				board.setStudentName(dao.stNameGet(board));
				if(dao.learningTeskSubmitCodeCheck(board) != 0) {
					board.setTagCode(dao.learningTeskSubmitCodeGet(board));	
					if(dao.taskScoreCheck(board)!= 0) {
						board.setTypeSum(dao.taskScoreGet(board));
						board.setAllSum(dao.learningTeskScoreAllSum(board));
					}
					else {board.setTypeSum("점수 미등록");}
				}
				else {
					board.setTypeSum("미제출");
				}
				board.setStNumber(Integer.toString(dao.learningRoomstAll(board)));
				board.setAllSum(dao.learningTeskScoreAllSum(board));
				board.setAverage(String.format("%.1f",board.getAllSum() / (double)dao.learningTeskScoreCount(board)));
				board.setRank(ran);				
				board.setPercentage(String.format("%.2f",Integer.parseInt(board.getRank())/(double)Integer.parseInt(board.getStNumber())*100));	
				score.add(board);
			}
			sb = new StringBuffer();

			sb.append("</br><table align=center>");
			sb.append("<tbody align=center>");
			sb.append("<tr>");
			sb.append("<td colspan='2'>");
			sb.append("<h3>"+score.get(0).getStudentName()+"학생의 과제 성적</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</tbody>");
			sb.append("<tbody align=center>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<h4>게시글 제목</h4>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<h4>점수</h4>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</tbody>");
			for(int i =0; i < score.size(); i++) {

				sb.append("<tbody align=center>");
				sb.append("<tr>");
				sb.append("<td>"+score.get(i).getBoardTitle()+"</td>");
				sb.append("<td>"+score.get(i).getTypeSum()+"</td>");
				sb.append("</tr>");
				sb.append("</tbody>");

			}
			sb.append("</table>");	
			mav.addObject("scoreId", sb.toString());

			sb = new StringBuffer();

			sb.append("</br><table align=center>");
			sb.append("<tbody align=center>");
			sb.append("<tr>");
			sb.append("<td colspan='4'>");
			sb.append("<h3>과제 평가</h3>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</tbody>");
			sb.append("<tbody align=center>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<h4>총점</h4>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<h4>평균</h4>");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("<h4>반등수</h4>");
			sb.append("  </td>");
			sb.append("<td>");
			sb.append("<h4>백분율</h4>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</tbody>");

			sb.append("<tbody align=center>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append(score.get(0).getAllSum());
			sb.append("</td>");
			sb.append("<td>");
			sb.append(score.get(0).getAverage());
			sb.append("</td>");
			sb.append("<td>");
			sb.append(score.get(0).getRank()+"/"+score.get(0).getStNumber());
			sb.append("</td>");
			sb.append("<td>");
			sb.append(score.get(0).getPercentage());
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</tbody>");

			sb.append("</table>");	
			mav.addObject("scoreId2", sb.toString());

			mav.setViewName("learningTaskSTScore");

			transaction = true;

		}catch(Exception ex){
		}finally {
			setTransactionResult(transaction);
		}
		return mav;
	}

	private ModelAndView learningSelect(BoardBean board) { // 학생 질문 업데이트
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);

		try {   
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));

			if(dao.learningSelect(board) !=1) {

				mav.addObject("boardCode",board.getBoardCode());
				mav.addObject("roomCode",board.getRoomCode());
				mav.addObject("title", board.getBoardTitle());
			}else {

				mav.addObject("reload", "opener.location.reload()");
				mav.addObject("windowclose", "window.close()");
				mav.addObject("message", "alert('이미 제출하셨습니다.')");	  
			}
			mav.setViewName("learningTaskSubmitInsert");

		}catch(Exception ex){

		}finally {
			setTransactionResult(transaction);
		}

		return mav;
	}
	private ModelAndView deleteTask(BoardBean board) {
		mav=new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {
			board.setRoomCode((String)session.getAttribute("roomCode"));
			board.setStudentCode((String)session.getAttribute("stCode"));

			if(dao.deleteTask(board) != 0) {
				mav.addObject("reload", "opener.location.reload()");
				mav.addObject("windowclose", "window.close()");
				mav.addObject("message", "alert('삭제 성공.')");	  
				mav.setViewName("learningTaskStudentCheck");
			}


			transaction=true;
		}catch(Exception ex) {

		}finally {
			setTransactionResult(transaction);
		}
		return mav;

	}

}
