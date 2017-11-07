package icia.project.bean;

import org.apache.ibatis.type.Alias;

@Alias("calendar")
public class Calendar {
	
	private String month; // 이번달 월
	
	private String Monday;
	private String Tuesday;
	private String Wednesday;
	private String Thursday;
	private String Friday;
	private String Saturday;
	private String Sunday;
	
	private int allSize;	// 사이즈
	
	

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
