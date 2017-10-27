package icia.project.bean;


import org.apache.ibatis.type.Alias;


import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("dbBoard")
public class DbBoardBean {	// 게시판 bean
	
	private String caCode;	// 카테고리 코드

	private String studentCode; // 학생학년반번호
	private String studentName; // 학생이름
	private String id;	// 아이디
	private String roomCode;	// 방코드
	private String roomName;	// 방이름
	private String roomIntroduction;	// 방소개
	
	private String roomSB;	// 방과목 코드
	private String yearCode;	// 년도코드
	private String typeCode;	// 문제유형코드
	private String numberCode;	// 문제코드
	
	private String subjectName;	// 과목 이름
	private String yearName;	// 년도 이름
	private String typeName;	// 문제유형 이름
	
	private String typeSum;	// 타입별 합
	
	private String boardCode;	// 게시글 번호
	private String boardTitle;	// 게시글 제목
	private String boardContent;	// 게시글 내용
	private String boardRoute;	// 게시글 파일첨부 경로
	private String boardId; // 게시글 작성자
	private String boardDate;	// 게시글 날짜
	
	private String cutRoute;
	private String cutContent;


	
	public String getCutRoute() {
		return cutRoute;
	}

	public void setCutRoute(String cutRoute) {
		this.cutRoute = cutRoute;
	}

	public String getCutContent() {
		return cutContent;
	}

	public void setCutContent(String cutContent) {
		this.cutContent = cutContent;
	}

	public String getTypeSum() {
		return typeSum;
	}

	public void setTypeSum(String typeSum) {
		this.typeSum = typeSum;
	}

	

	public String getBoardRoute() {
		return boardRoute;
	}
	public void setBoardRoute(String boardRoute) {
		this.boardRoute = boardRoute;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getCaCode() {
		return caCode;
	}
	public void setCaCode(String caCode) {
		this.caCode = caCode;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomSB() {
		return roomSB;
	}
	public void setRoomSB(String roomSB) {
		this.roomSB = roomSB;
	}
	public String getRoomIntroduction() {
		return roomIntroduction;
	}
	public void setRoomIntroduction(String roomIntroduction) {
		this.roomIntroduction = roomIntroduction;
	}
	public String getYearCode() {
		return yearCode;
	}
	public void setYearCode(String yearCode) {
		this.yearCode = yearCode;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getNumberCode() {
		return numberCode;
	}
	public void setNumberCode(String numberCode) {
		this.numberCode = numberCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getYearName() {
		return yearName;
	}
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	

}