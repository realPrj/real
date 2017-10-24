package icia.project.dao;

import java.util.ArrayList;

import icia.project.bean.MemberBean;
import icia.project.bean.BoardBean;
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

	// 선생님 오답노트(전체) 페이지 이동
	public BoardBean learningWANListGet(BoardBean board);


	// 선생님 자료실 등록
	public BoardBean referenceInsert(BoardBean board);
}
