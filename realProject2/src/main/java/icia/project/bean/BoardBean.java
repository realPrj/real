package icia.project.bean;

import org.apache.ibatis.type.Alias;

@Alias("board")
public class BoardBean {	// 게시판 bean
	
	private String caCode;	// 카테고리 코드
	private String studentCode; // 학생학년반번호
	private String id;	// 아이디
	private String roomCode;	// 방코드
	private String roomName;	// 방이름
	private String roomSB;	// 방과목코드
	private String roomIntroduction;	// 방소개
	
	private String boardCode;	// 게시글 번호
	private String boardTitle;	// 게시글 제목
	private String boardContent;	// 게시글 내용
	private String boardroute;	// 게시글 파일첨부 경로
	
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
	public String getBoardroute() {
		return boardroute;
	}
	public void setBoardroute(String boardroute) {
		this.boardroute = boardroute;
	}
	

}
