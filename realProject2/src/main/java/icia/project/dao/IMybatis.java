package icia.project.dao;

import java.util.ArrayList;

import icia.project.bean.MemberBean;
import icia.project.bean.BoardBean;
import icia.project.bean.Calendar;
import icia.project.bean.DbBoardBean;
import icia.project.bean.LearningRoomBean;

public interface IMybatis {

	// 선생님 아이디 체크
	public int tcIdCheck(MemberBean member);

	// 선생님 임시비밀번호 추출
	public MemberBean tcPwdGet(MemberBean member);

	// 학생 아이디 체크
	public int stIdCheck(MemberBean member);

	// 학생코드 체크
	public int stCodeCheck(MemberBean member);

	// 학생 임시비밀번호 추출
	public MemberBean stPwdGet(MemberBean member);

	// 학생코드 추출
	public MemberBean stCodeGet(MemberBean member);

	// 선생님 회원가입
	public int tcJoin(MemberBean member);

	// 학생 회원가입
	public int stJoin(MemberBean member);

	// 선생님 아이디 찾기
	public MemberBean tcIdFind(MemberBean member);

	// 학생 아이디 찾기
	public MemberBean stIdFind(MemberBean member);

	// 오늘의 월 추출
	public String yyyyMMGet();

	// 학생 출결 추출
	public ArrayList<LearningRoomBean> stIOHGet(LearningRoomBean room);

	// 선생님 로그인 히스토리
	public int tcLogHistory(MemberBean member);

	// 학생 로그인 히스토리
	public int stLogHistory(MemberBean member);

	// 선생님 나의정보 추출
	public MemberBean tcInformationGet(MemberBean member);

	// 학생 나의정보 추출
	public MemberBean stInformationGet(MemberBean member);

	// 선생님 나의정보 수정
	public int tcInformationChange(MemberBean member);

	// 학생 나의정보 수정
	public int stInformationChange(MemberBean member);

	// 선생님 비밀번호 수정
	public int tcInformationPWDChange(MemberBean member);

	// 학생 비밀번호 수정
	public int stInformationPWDChange(MemberBean member);

	// 선생님 회원탈퇴
	public int tcmemberDelete(MemberBean member);

	// 학생 회원탈퇴
	public int stmemberDelete(MemberBean member);

	// 선생님 학습코드 유무
	public int tclearningCodeCheck(LearningRoomBean room);

	// 학생 학습코드 유무
	public int stlearningRoomCheck(LearningRoomBean room);

	// 학생 학습방 참여 유무
	public int stlearningRoomJoinCheck(LearningRoomBean room);

	// 선생님 학습방 추출
	public ArrayList<LearningRoomBean> tclearningRoomGet(LearningRoomBean room);

	// 학생 학습방 추출1(방습방 코드 추출)
	public ArrayList<LearningRoomBean> stlearningRoomGet1(LearningRoomBean room);

	// 학생 학습방 추출2(방습방 이름 추출)
	public LearningRoomBean stlearningRoomGet2(LearningRoomBean room);

	// 선생님 학습방 유무
	public int tclearningRoomCheck(LearningRoomBean room);

	// 선생님 학습 개설
	public int tclearningOpen(LearningRoomBean room);

	// 학습방 추출
	public ArrayList<LearningRoomBean> learningGet(LearningRoomBean room);

	// 학생 학습참여
	public int stLearningJoin(LearningRoomBean room);

	// 학습 페이지 이동
	public LearningRoomBean learningRoomGo(LearningRoomBean room);

	// 선생님 자료실 등록
	public int referenceInsert(BoardBean board);

	// 선생님 오답노트(전체) 페이지 이동
	public ArrayList<BoardBean> learningWANListGet(BoardBean board);

	// 선생님 공지사항 리스트
	public ArrayList<BoardBean> datalist(BoardBean board);

	// 학습방 과목코드 추출
	public String learningSBCodeGet(BoardBean board);

	// 년도 이름 추출
	public String learningYearNameGet(BoardBean board);

	// 년도 코드 추출
	public ArrayList<BoardBean> learningYearCodeGet(BoardBean board);

	// 문제유형 코드 추출
	public String learningTypeCodeGet(BoardBean board);

	// 문제유형 이름 추출
	public String learningTypeNameGet(BoardBean board);

	// 년도별 문제유형 합
	public ArrayList<BoardBean> learningWANTypeSum(BoardBean board);

	// 년도별 문제
	public ArrayList<BoardBean> learningWANNumberGet(BoardBean board);

	// 중복 년도 코드 추출
	public ArrayList<BoardBean> learningWANYearCodeOneGet(BoardBean board);

	// 선생님 학습 페이지 이동
	public LearningRoomBean telearningRoomGo(LearningRoomBean room);

	// 선생님 공지사항 리스트
	public ArrayList<BoardBean> tclearningNoticeList(BoardBean board);

	// 선생님 공지사항 내용확인
	public DbBoardBean tclearningNoticeConfirm(BoardBean board);

	// 선생님 오답노트 코멘트 유무 확인
	public int learningWANCommentCheck(BoardBean board);

	// 오답노트 코멘트 추출
	public DbBoardBean learningWANCommentGet(BoardBean board);

	// 오답노트 코멘트 추출
	public DbBoardBean learningWANCommentGet2(BoardBean board);

	// 오답노트 코멘트 등록 
	public int learningWANCommentInsert(BoardBean board);

	// 오답노트 코멘트 수정
	public int learningWANCMUpdate(BoardBean board);

	// 오답노트 코멘트 삭제
	public int learningWANCMDelete(BoardBean board);

	// 선생님 공지사항 글쓰기
	public int tclearningNoticeInsert(BoardBean board);

	// 자료실 글 내용 자세히보기
	public DbBoardBean learningDataCXT(BoardBean board);

	// 선생님 공지사항 글 수정하기
	public int tclearningNoticeUpdate(BoardBean board);

	// 선생님 공지사항 글 삭제
	public int tclearningNoticeDelete(BoardBean board);

	// 학생 이름 추출
	public String stNameGet(BoardBean board);

	// 학생이 물어본 문제수
	public int allWANSum(BoardBean board);

	// 학생 오답노트(전체) 페이지 이동
	public ArrayList<BoardBean> learningWANstListGet(BoardBean board);

	// 학생 중복 년도 코드 추출
	public ArrayList<BoardBean> learningWANstYearCodeOneGet(BoardBean board);

	// 학생 년도별 문제유형 합
	public ArrayList<BoardBean> learningWANstTypeSum(BoardBean board);

	// 자료실 글삭제
	public int learningDataDelete(BoardBean board);

	// 학습방 오답문제 총 평균 구하기1 (학년 총 질문수)
	public double learningWANstAverage1(BoardBean board);

	// 학습방 오답문제 총 평균 구하기2 (학년 총 인원수) 
	public ArrayList<BoardBean> learningWANstAverage2(BoardBean board);

	// 학습방 오답문제 총 평균 구하기22 (학년 총 인원수) 
	public double learningWANstAverage22(BoardBean board);

	// 학습방 오답문제 총 평균 구하기3 ( 방 학년 총 질문수)
	public double learningWANstAverage3(BoardBean board);

	// 학습방 오답문제 총 평균 구하기4 ( 방 학년 총 인원수)
	public double learningWANstAverage4(BoardBean board);

	// 학습방 오답문제 총 평균 구하기5 학생(본인) 오답노트 추출 (중복제외)
	public ArrayList<BoardBean> learningWANstListGetOverlap(BoardBean board);

	// (학습방 전체) 오답노트 그래프
	public ArrayList<BoardBean> learningWANAllRoomGraph(BoardBean board);

	// 학생(본인) 최근 문제유형 5개 추출(중복제외)
	public ArrayList<BoardBean> learningWANAllRoomGraph2(BoardBean board);

	// (학습방) 오답노트 그래프
	public ArrayList<BoardBean> learningWANRommGraph(BoardBean board);

	// 선생님 -> 학습방 학생코드 추출
	public ArrayList<BoardBean> learningWANAllStudentCode(BoardBean board);

	// 학습방 오답노트 게시글 유무
	public int learningWANCheck(BoardBean board);

	// 자료실 글 수정
	public int learningDataUpdate(BoardBean board);

	// 학생 자료실 리스트
	public ArrayList<BoardBean> datalistStudent(BoardBean board);

	// 학생 질문게시판 등록
	public int learningQuestionInsert(BoardBean board);

	// 학생 질문게시판 리스트
	public ArrayList<BoardBean> learningQuestionlist(BoardBean board);

	//  질문게시판 자세히보기
	public DbBoardBean questionBoardCXT(BoardBean board);

	// 질문 게시판 삭제
	public int deleteQuestion(BoardBean board);

	// 학생 질문게시판 글 수정
	public int learningQuUpdate(BoardBean board);

	// 학생 질문게시판 댓글   
	public int learningQuestionTag(BoardBean board);

	// 질문게시판 내용 보기   
	public ArrayList<BoardBean> learningQuestionTagCXT(BoardBean board);

	// 질문게시판 댓글 삭제
	public int learningQuestionTagDelete(BoardBean board);

	//선생님 학생 관리  보기
	public ArrayList<BoardBean> teacherLearningSTadmin(BoardBean board);

	//선생님 학생 관리  보기 자세히 
	public ArrayList<BoardBean> teacherLearningSTadminCXT(BoardBean board);

	// 선생님 토론게시판 리스트
	public ArrayList<BoardBean> tclearningDebateList(BoardBean board);

	// 선생님 토론게시판 내용확인
	public BoardBean tclearningDebateCTX(BoardBean board);

	// 선생님 토론게시판 수정
	public int tclearningDebateUpdate(BoardBean board);

	// 선생님 토론게시판 등록
	public int tclearningDebateInsert(BoardBean board);

	// 선생님 토론게시판 삭제
	public int tclearningDebateDelete(BoardBean board);

	// 토론게시판 댓글 목록
	public ArrayList<BoardBean> learningDebateTagList(BoardBean board);

	// 선생님 비밀번호찾기
	public int findPwd(MemberBean member);

	// 선생님 비밀번호 업데이트
	public int updatePwd(MemberBean member);

	// 학생 비밀번호찾기
	public int findstPwd(MemberBean member);

	// 학생 비밀번호 업데이트
	public int updatestPwd(MemberBean member);

	// 과제 유무
	public int learningTaskCheck(BoardBean board);

	// 과제 리스트 출력
	public ArrayList<BoardBean> learningTaskList(BoardBean board);

	// 과제 내용 출력
	public BoardBean learningTaskGet(BoardBean board);

	// 과제 유무
	public int learningTaskInsert(BoardBean board);

	// 과제 게시글 번호 유무
	public int learningTaskBoardCheck(BoardBean board);

	// 토론게시판 댓글 등록
	public int learningDebateTagInsert(BoardBean board);

	// 토론게시판 댓글 수정
	public int learningDebateTagUpdate(BoardBean board);

	// 토론게시판 댓글 삭제
	public int learningDebateTagDelete(BoardBean board);

	// 선생님 공지사항 글 삭제
	public int learningTaskCXTDelete(BoardBean board);

	// 과제 수정 
	public int learningTaskUpdate(BoardBean board);

	// 선생님 자료실 등록
	public int learningSubmitTaskInsert(BoardBean board);

	// 과제 댓글 내용 출력
	public ArrayList<BoardBean> learningTaskSelect(BoardBean board);

	// 과목 이름 출력
	public String subjectNameGet(BoardBean board);

	// 학생 과제 확인
	public DbBoardBean checkFile(BoardBean board);

	// 회원탈퇴 패스워드 체크
	public MemberBean checkPwd(MemberBean member);

	// 학생 과제체출 회원탈퇴
	public int tasksubmitdelete(MemberBean member);

	// 학생 오답노트 회원탈퇴
	public int WRONGANSWERNOTES(MemberBean member);

	// 학생 토론 태그 탈티
	public int DEBATETAGdelete(MemberBean member);

	// 학생 질문 탈퇴
	public int QUERYdelete(MemberBean member);

	// 학생 질문 탈퇴
	public int MESSAGEdelete(MemberBean member);

	// 학생 학습방 탈퇴
	public int learningstudentdelete(MemberBean member);

	// 학생 회원 탈퇴 업데이트
	public int updateState(MemberBean member);

	// 선생님 비밀번호 체크
	public MemberBean checkTeacherPwd(MemberBean member);

	// 선생님 상태 업데이트
	public int updateTeacherState(MemberBean member);

	// 과제 리스트 출력
	public ArrayList<BoardBean> teacherTaskTag(BoardBean board);

	// 선생님 과제 확인
	public DbBoardBean learningTesk(BoardBean board);

	// 학생 로그 추출
	public ArrayList<LearningRoomBean> attendanceGet(LearningRoomBean room);

	// 보낸 쪽지 리스트
	public ArrayList<BoardBean> sentMessageList(BoardBean board);

	// 선생님 상태코드 확인(로그인시)
	public String stateCodeCheck(MemberBean member);

	// 선생님 로그 추출
	public ArrayList<LearningRoomBean> tcattendanceGet(LearningRoomBean room);

	// 쪽지 보내기 1
	public int sendMessageS(BoardBean board);

	//쪽지 보내기 2
	public int sendMessageG(BoardBean board);

	// 받은 쪽지 리스트
	public ArrayList<BoardBean> getMessageList(BoardBean board);

	// 받은 쪽지 내용확인
	public BoardBean getMessageCTX(BoardBean board);

	// 보낸 쪽지 내용확인
	public BoardBean sentMessageCTX(BoardBean board);

	// 보낸 쪽지 삭제
	public int sentMessageDelete(BoardBean board);

	// 받은 쪽지 삭제
	public int getMessageDelete(BoardBean board);

	// 선생님 리스트
	public BoardBean getTeacherInfo(BoardBean board);

	// 학생 수강생 리스트
	public ArrayList<BoardBean> getStudentList(BoardBean board);

	// 달력출력
	public ArrayList<Calendar> calendarGet(Calendar calendar);

	// 현재 년도 추출
	public String nowYearGet();
	
	// 현재 년월 추출
	public String nowYearMMGet();

	// 강의계획서 체크
	public int planCheck(BoardBean board);

	// 일요일 체크
	public int CheckSunday(Calendar cd);

	// 월요일 체크
	public int CheckMonday(Calendar cd);

	// 화요일 체크
	public int CheckTuesday(Calendar cd);

	// 수요일 체크
	public int CheckWednesday(Calendar cd);

	// 목요일 체크
	public int CheckThursday(Calendar cd);

	// 금요일 체크
	public int CheckFriday(Calendar cd);

	// 토요일 체크
	public int CheckSaturday(Calendar cd);

	// 강의계획서 내용
	public DbBoardBean planCTX(BoardBean board);

	// 강의계획서 등록
	public int planInsert(BoardBean board);

	// 강의계획서 수정
	public int planUpdate(BoardBean board);

	// 강의계획서 삭제
	public int planDelete(BoardBean board);

	// admin id check
	public int adminIdCheck(MemberBean member);

	// admin pwd check
	public int adminPassowrd(MemberBean member);

	// all student
	public int allStudent(MemberBean member);
	// all student
	public int allteacher(MemberBean member);
	// student all
	public ArrayList<MemberBean> student(MemberBean member);
	// student all
	public ArrayList<MemberBean> teacher(MemberBean member);
	// 학생 질문개수
	public int studnetWorngCount(MemberBean member);
	// 학생  질문개수
	public int studentQueryCount(MemberBean member);
	// 학생 ㅌㅗㄹㄴ 질문개수
	public int studentDebateCount(MemberBean member);
	//clas,wrong,refer,notice,debate,wronganswer count
	public int classCount(MemberBean member);
	public int wrongTagcount(MemberBean member);
	public int refercount(MemberBean member);
	public int noticecount(MemberBean member);
	public int debatecount(MemberBean member);
	public int wrongecount(MemberBean member);
	// the end
	// 학생 상태코드 확인(로그인시)
	public String stateSTCodeCheck(MemberBean member);

	// 학생 이름 추출
	public String stMmNameGet(MemberBean member);

	// 과제 점수 추출
	public String taskScoreGet(BoardBean board);

	// 과제 점수 등록
	public int taskScoreInsert(BoardBean board);

	// 과제 점수 수정
	public int taskScoreUpdate(BoardBean board);

	// 과제 제출 번호 추출
	public String learningTeskSubmitCodeGet(BoardBean board);

	// 과제 제출 번호 유무
	public int learningTeskSubmitCodeCheck(BoardBean board);

	// 과제 점수 유무
	public int taskScoreCheck(BoardBean board);

	// 과제 성적 개인 총 합
	public int learningTeskScoreAllSum(BoardBean board);

	// 과제 성적 학습방 과제 수
	public int learningTeskScoreCount(BoardBean board);

	// 과제 성적 학습방 등수
	public ArrayList<BoardBean> learningTeskScoreRank(BoardBean board);

	// 학습방 총 학생 인원
	public int learningRoomstAll(BoardBean board);

	// 과제글 유뮤 확인
	public int learningSelect(BoardBean board);

	// 선생님 과제  제출 삭제
	public int deleteTask(BoardBean board);

	// 과제 점수 유무
	public int learningTaskScoreCheck(BoardBean board);

	// 회원 추방
	public int byeStudent(BoardBean board);

	// 학습방 수정
	public int learningRoomUpdate(LearningRoomBean room);

}

