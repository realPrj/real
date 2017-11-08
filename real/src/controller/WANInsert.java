package controller;

import dao.Dao;
import dao.FunctionDao;

public class WANInsert {	// 오답노트 인설트
	
	private Dao dao;
	private FunctionDao fd;
	
	public WANInsert() {}
	
	public void wanInsert(String rfCode,String roomcode,String unit,String type,String question) {

		String stCode;	// 학생 코드
		String stName; // 학생 이름
		String roomSbCode;	// 과목 코드
		int boardCode = 0;	// 게시판 글 번호
		boolean distinction = false;
		
		dao = new Dao();

		try {

			dao.setAutoCommit(false);
			
			while(distinction) {	// 학습코드 유무 체크

				boardCode = (int)(Math.random() *89999)+10000;

				if(fd.boardCodeCheck(boardCode)) {	
					distinction = true;						
				}else {
					distinction = false;
				}

			}

			stCode = fd.stCodeGet(rfCode);	// 학생 코드 추출
			stName = fd.stNameGet(stCode);	// 학생 이름 추출
			roomSbCode = fd.sbCodeGet(roomcode);	// 학습방 과목 코드 추출
			
			if(fd.wanInsert(Integer.toString(boardCode), roomcode, stCode, stName, roomSbCode, unit, type, question)) {
				
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
