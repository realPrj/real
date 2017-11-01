package icia.project.bean;


import org.apache.ibatis.type.Alias;


@Alias("board")
public class BoardBean {	// 게시판 bean

	private String caCode;	// 카테고리 코드

	private String studentCode; // 학생학년반번호
	private String studentName; // 학생이름
	private String id;	// 아이디
	private String identity;	// 신분
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
	private int allSum;	// 총 합

	private String boardCode;	// 게시글 번호
	private String boardTitle;	// 게시글 제목
	private String boardContent;	// 게시글 내용
	private String boardRoute;	// 게시글 파일첨부 경로
	private String boardId; // 게시글 작성자
	private String boardDate;	// 게시글 날짜
	private String fileName;	// 파일 이름

	private String stHalf;	// 학생 반
	private String stNumber;	// 학생 번호

	private String tagCode;	// 댓글 코드
	private String tagId;	// 댓글 아이디
	private String tagDate;	// 댓글 날짜
	private String tagContent;	// 댓글 내용

	private String email;   // 이메일   
	private String phone;   // 폰번호



	public String getTagCode() {
		return tagCode;
	}

	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagDate() {
		return tagDate;
	}

	public void setTagDate(String tagDate) {
		this.tagDate = tagDate;
	}

	public String getTagContent() {
		return tagContent;
	}

	public void setTagContent(String tagContent) {
		this.tagContent = tagContent;
	}

	public int getAllSum() {
		return allSum;
	}

	public void setAllSum(int allSum) {
		this.allSum = allSum;
	}

	public String getStHalf() {
		return stHalf;
	}

	public void setStHalf(String stHalf) {
		this.stHalf = stHalf;
	}

	public String getStNumber() {
		return stNumber;
	}

	public void setStNumber(String stNumber) {
		this.stNumber = stNumber;
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

