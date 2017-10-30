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

	private ModelAndView mav;

	private MultipartHttpServletRequest mtfRequest = null;

	public ModelAndView entrance(int serviceCode,Object ...object)  {

		switch(serviceCode) {

		case 1:	

			break;

		case 2:

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

		case 9: // 공지사항 글쓰기 페이지 이동

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

		case 14:	

			break;


		case 15:	

			break;

		case 16:	

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
				sb.append("<select id = 'yearSelect'>");
				sb.append("<option></option>");

				for(int i =0; i < yearCode.size(); i++) {
					board = new BoardBean();
					board.setRoomCode(boardList.get(0).getRoomCode());
					board.setYearCode(yearCode.get(i).getYearCode());
					board.setStudentCode((String)session.getAttribute("stCode"));
					sb.append("<option>"+yearCode.get(i).getYearCode()+"</option>");

					typeSum = dao.learningWANstTypeSum(board);

					sum.append("<br><biv id='"+yearCode.get(i).getYearCode().substring(0, 4)+"' >");

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
					sum.append("</biv>");

					if(i == 0) {
						mav.addObject("lowest", yearCode.get(i).getYearCode());
					}
				}

				sb.append("</select>");
				mav.addObject("size", yearCode.size());
				mav.addObject("yearSelect", sb.toString());
				mav.addObject("typeSumb", sum.toString());

				/////////////////////////////////////////////////////////////////////////

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
				board.setStudentCode((String)session.getAttribute("stCode"));

				boardList = dao.learningWANstListGetOverlap(board);
				yearCode = dao.learningWANstYearCodeOneGet(board);

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
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>제목</td>");
		sb.append("<td>날짜</td>");
		sb.append("<td>작성자</td>");
		sb.append("</tr>");
		for(int i=0; i<ar.size(); i++) {
			sb.append("<tr>");	
			//sb.append("<input type=\"hidden\" name=\"boardTitle\" value='" + board.getBoardTitle() + "'/>");
			sb.append("<td onClick=\"confirm('"+ ar.get(i).getBoardTitle() +"','" + ar.get(i).getBoardDate() + "')\">" + ar.get(i).getBoardTitle() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardDate() + "</td>");
			sb.append("<td>" + ar.get(i).getBoardId() + "</td>");
			sb.append("</tr>");
		}
		sb.append("</table>");

		return sb.toString();
	}

	private ModelAndView stlearningNoticeCTX(BoardBean board) { // 공지사항 내용확인

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
			List<String> list = view.getList(bb);
			System.out.println("디비보드빈 : " + bb.getBoardTitle());
			System.out.println("공지사항 내용확인 메서드 진입"+bb.getBoardTitle()+bb.getRoomCode());

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
		return sb.toString();
	}

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
				
			/*	
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
				sb.append("</table>");
*/
				page="learningWANCXT";
				transaction = true;

			}else {	// 코멘트 없음

				sb.append("<input type='button' value='코멘트가 없습니다.' onClick='windowcloseClick()' />");
				mav.addObject("windowcloseClick", "window.close()");
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

	private ModelAndView questionBoardPage(BoardBean board) { // 질문사항 페이지 리스트
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
		mav.setViewName("learningQuestionStudent");
		return mav;
	}


	private ModelAndView learningQuestionInsert(BoardBean board) { // 질문게시판 인설트
		mav = new ModelAndView();
		boolean transaction = false;
		fileupload(board,mtfRequest);


		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		try {         
			board.setRoomCode((String)session.getAttribute("roomCode"));

			board.setStudentCode((String)session.getAttribute("stCode"));
			System.out.println("learningQuestionInsert");
			System.out.println(board.getRoomCode());
			System.out.println(board.getStudentCode());
			System.out.println(board.getBoardTitle());
			System.out.println(board.getBoardContent());
			System.out.println(board.getBoardRoute());

			session.getAttribute("roomCode");
			if(dao.learningQuestionInsert(board) != 0) {
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
           System.out.println(bb.getCutContent());
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
              sb.append("</tr>");

           }
           sb.append("</table>");
           mav.addObject("taglists", sb.toString());
           
           mav.addObject("roomcode",session.getAttribute("roomCode"));

           mav.setViewName("learningQuestionStudentCXT");
           transaction = true;


        }catch(Exception ex){
           ex.printStackTrace();
        }finally {
           setTransactionResult(transaction);
        }

        return mav;
     }
	private ModelAndView deleteQuestion(BoardBean board) { // 학생 질문 삭제
		mav = new ModelAndView();
		boolean transaction = false;
		setTransactionConf(TransactionDefinition.PROPAGATION_REQUIRED,TransactionDefinition.ISOLATION_READ_COMMITTED,false);
		System.out.println("입구 컷");
		try {   
			if(dao.deleteQuestion(board) != 0) {

				transaction = true;

			}
		}catch(Exception ex){
			ex.printStackTrace();
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
			ex.printStackTrace();
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

		return sb.toString();
	}
	
	private ModelAndView stlearningDebateCTX(BoardBean board) { // 선생님 토론게시판 내용확인
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

			mav.addObject("content", getStlearningDebateCTX(board));

			transaction = true;

		}catch(Exception ex){

		}finally {
			mav.setViewName("learningDebateCTX");
			setTransactionResult(transaction);
		}
		return mav;
	}

	private String getStlearningDebateCTX(BoardBean board) { // 토론게시판 내용 끌고오기
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
		return sb.toString();
	}
}
