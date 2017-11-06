package icia.project.bean;

import org.apache.ibatis.type.Alias;

@Alias("room")
public class LearningRoomBean {	// 학습방 bean
	
	private String studentCode; // 학생학년반번호
	private String id;	// 아이디
	private String roomCode;	// 방코드
	private String roomName;	// 방이름
	private String roomSB;	// 방과목코드
	private String roomIntroduction;	// 방소개
	private String attendanceType;	// 출결 타입
	private String attendanceCode;	// 출결 날짜코드
	private String yyyymm;	// 이번달 코드
	private String attendDate;	// 이번달 코드
	
	public String getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(String attendDate) {
		this.attendDate = attendDate;
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
	public String getAttendanceType() {
		return attendanceType;
	}
	public void setAttendanceType(String attendanceType) {
		this.attendanceType = attendanceType;
	}
	public String getAttendanceCode() {
		return attendanceCode;
	}
	public void setAttendanceCode(String attendanceCode) {
		this.attendanceCode = attendanceCode;
	}
	public String getYyyymm() {
		return yyyymm;
	}
	public void setYyyymm(String yyyymm) {
		this.yyyymm = yyyymm;
	}
	
	
	
	
	

}
