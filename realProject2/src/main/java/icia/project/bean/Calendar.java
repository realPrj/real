package icia.project.bean;

import org.apache.ibatis.type.Alias;

@Alias("calendar")
public class Calendar {
	
	private String roomCode;
	
	private String boardCode;
	
	private String month; // 이번달 월
	
	private String Monday;
	private String Tuesday;
	private String Wednesday;
	private String Thursday;
	private String Friday;
	private String Saturday;
	private String Sunday;
	
	private int allSize;	// 사이즈
	
	private String CheckMonday;
	private String CheckTuesday;
	private String CheckWednesday;
	private String CheckThursday;
	private String CheckFriday;
	private String CheckSaturday;
	private String CheckSunday;
	
	

	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public String getCheckMonday() {
		return CheckMonday;
	}
	public void setCheckMonday(String checkMonday) {
		CheckMonday = checkMonday;
	}
	public String getCheckTuesday() {
		return CheckTuesday;
	}
	public void setCheckTuesday(String checkTuesday) {
		CheckTuesday = checkTuesday;
	}
	public String getCheckWednesday() {
		return CheckWednesday;
	}
	public void setCheckWednesday(String checkWednesday) {
		CheckWednesday = checkWednesday;
	}
	public String getCheckThursday() {
		return CheckThursday;
	}
	public void setCheckThursday(String checkThursday) {
		CheckThursday = checkThursday;
	}
	public String getCheckFriday() {
		return CheckFriday;
	}
	public void setCheckFriday(String checkFriday) {
		CheckFriday = checkFriday;
	}
	public String getCheckSaturday() {
		return CheckSaturday;
	}
	public void setCheckSaturday(String checkSaturday) {
		CheckSaturday = checkSaturday;
	}
	public String getCheckSunday() {
		return CheckSunday;
	}
	public void setCheckSunday(String checkSunday) {
		CheckSunday = checkSunday;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public int getAllSize() {
		return allSize;
	}
	public void setAllSize(int allSize) {
		this.allSize = allSize;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonday() {
		return Monday;
	}
	public void setMonday(String monday) {
		Monday = monday;
	}
	public String getTuesday() {
		return Tuesday;
	}
	public void setTuesday(String tuesday) {
		Tuesday = tuesday;
	}
	public String getWednesday() {
		return Wednesday;
	}
	public void setWednesday(String wednesday) {
		Wednesday = wednesday;
	}
	public String getThursday() {
		return Thursday;
	}
	public void setThursday(String thursday) {
		Thursday = thursday;
	}
	public String getFriday() {
		return Friday;
	}
	public void setFriday(String friday) {
		Friday = friday;
	}
	public String getSaturday() {
		return Saturday;
	}
	public void setSaturday(String saturday) {
		Saturday = saturday;
	}
	public String getSunday() {
		return Sunday;
	}
	public void setSunday(String sunday) {
		Sunday = sunday;
	}
		

}
