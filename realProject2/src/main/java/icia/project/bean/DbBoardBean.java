package icia.project.bean;


import org.apache.ibatis.type.Alias;


import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("dbBoard")
public class DbBoardBean {	// dbboard bean
	
	private String roomCode;	// 방코드
	private String boardCode;	// 게시글 번호
	private String boardTitle;	// 게시글 제목
	private String boardContent;	// 게시글 내용
	private String boardRoute;	// 게시글 파일첨부 경로
	private String boardId; // 게시글 작성자
	private String boardDate;	// 게시글 날짜
	
	private String cutRoute;
	private String cutContent;
	
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
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
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
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


	
	
	

}
