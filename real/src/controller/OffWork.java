package controller;

import dao.Dao;
import dao.FunctionDao;

public class OffWork {	// 퇴근
	
	private Dao dao;
	private FunctionDao fd;
	
	public OffWork() {}
	
	public void offWork(String rfCode,int commute) {

		String stCode;	// 학생 코드
		
		dao = new Dao();

		try {

			dao.setAutoCommit(false);

			stCode = fd.stCodeGet(rfCode);	// 학생 코드 추출
			
			if(fd.goOff(stCode, commute)) {	// 출결등록
				
			}else {
				System.out.println("실패");
			}
			dao.transactionEnd(true);

		}
		catch(Exception ex) {

		}finally {
			dao.closeConnection();
		}

	}
	
	

}
