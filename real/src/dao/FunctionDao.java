package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class FunctionDao {	// 기능 dao
	
	private Context context = null; 
	private DataSource datasource = null; 
	private Connection connection = null; 
	private PreparedStatement pstmt = null; 
	private ResultSet rs = null; 
	
	private Dao dao;
	
	public FunctionDao() {
		
		try{ 
			context = new InitialContext(); 
			datasource =  
			(DataSource)context.lookup("java:comp/env/YHDB"); 
			connection = datasource.getConnection(); 
			System.out.println("***** DB Connect Successfull *****"); 
			}catch(Exception ex){ 
			System.out.println("xxxxx DB Connect Failure xxxxx"); 
			ex.printStackTrace(); 
			} 
		
	}
	
	
	  public String stCodeGet(String rfCode) {   // 학생 코드 추출
	         
	      String result = null;
	      
	      dao = new Dao();
	      
	      String sql = "SELECT RF_STUDENTCODE FROM RFID WHERE RF_CODE = ?";
	      
	      try {
	    	  
	         pstmt = connection.prepareStatement(sql);
	         
	         pstmt.setString(1, rfCode);

	         rs = pstmt.executeQuery();

	         while(rs.next()){
	            result = rs.getString(1);
	         }
	         
	      }catch(Exception ex) {
	         
	      }finally {
	         
	         if(rs != null){try{rs.close();}catch(Exception ex){}}
	         if(pstmt != null){try{pstmt.close();}catch(Exception ex){}}
	         
	      }
	      
	      return result;
	   
	   }
	  
	  public boolean goOff(String stCode, int commute) {   // 출결 등록
          
	      int result = 0;
	      boolean returnboolean = false;
	      
	      dao = new Dao();
	      
	      String sql = "INSERT INTO INOUTHISTORY(IOH_RFSTUDENTCODE,IOH_TYPE,IOH_DATE) VALUES(?,?,DEFAULT)";
	      
	      try {
	         
	         pstmt = connection.prepareStatement(sql);
	         
	         pstmt.setString(1, stCode);
	         pstmt.setInt(2, commute);
	         
	         result = pstmt.executeUpdate();
	         
	         returnboolean = dao.convertToBoolean(result);
	         
	      }catch(Exception ex) {
	         ex.printStackTrace();
	      }finally {

	         if(pstmt != null){try{pstmt.close();}catch(Exception ex){}}
	      }
	      
	      return returnboolean;
	      
	   }
	  
	  public String stNameGet(String stCode) {   // 학생 이름 추출
	         
	      String result = null;
	      
	      dao = new Dao();
	      
	      String sql = "SELECT ST_NAME FROM STUDENT WHERE ST_RFSTUDENTCODE = ?";
	      
	      try {
	    	  
	         pstmt = connection.prepareStatement(sql);
	         
	         pstmt.setString(1, stCode);

	         rs = pstmt.executeQuery();

	         while(rs.next()){
	            result = rs.getString(1);
	         }
	         
	      }catch(Exception ex) {
	         
	      }finally {
	         
	         if(rs != null){try{rs.close();}catch(Exception ex){}}
	         if(pstmt != null){try{pstmt.close();}catch(Exception ex){}}
	         
	      }
	      
	      return result;
	   
	   }
	  
	  public String sbCodeGet(String roomcode) {   // 과목 코드 추출
	         
	      String result = null;
	      
	      dao = new Dao();
	      
	      String sql = "SELECT LR_SBCODE FROM LEARNINGROOM WHERE LR_CODE = ?";
	      
	      try {
	    	  
	         pstmt = connection.prepareStatement(sql);
	         
	         pstmt.setString(1, roomcode);

	         rs = pstmt.executeQuery();

	         while(rs.next()){
	            result = rs.getString(1);
	         }
	         
	      }catch(Exception ex) {
	         
	      }finally {
	         
	         if(rs != null){try{rs.close();}catch(Exception ex){}}
	         if(pstmt != null){try{pstmt.close();}catch(Exception ex){}}
	         
	      }
	      
	      return result;
	   
	   }
	  
	  public boolean wanInsert(String boardCode,String roomcode,String stCode,String stName,String roomSbCode,String unit,String type,String question) {   // 오답노트 등록
          
	      int result = 0;
	      boolean returnboolean = false;
	      
	      dao = new Dao();
	      
	      String sql = "INSERT INTO WRONGANSWERNOTES VALUES(?,?,?,?,?,?,?,DEFAULT)";
	      
	      try {
	         
	         pstmt = connection.prepareStatement(sql);
	         
	         pstmt.setString(1, boardCode);
	         pstmt.setString(2, roomcode);
	         pstmt.setString(3, stCode);
	         pstmt.setString(4, stName);
	         pstmt.setString(5, roomSbCode);
	         pstmt.setString(6, unit);
	         pstmt.setString(7, type);
	         pstmt.setString(8, question);
	         
	         result = pstmt.executeUpdate();
	         
	         returnboolean = dao.convertToBoolean(result);
	         
	      }catch(Exception ex) {
	         ex.printStackTrace();
	      }finally {

	         if(pstmt != null){try{pstmt.close();}catch(Exception ex){}}
	      }
	      
	      return returnboolean;
	      
	   }
	  
	  public boolean boardCodeCheck(int roomcode) {   // 게시글 번호 유무
	         
	      int result = 0;
	      boolean returnboolean = false;
	      
	      dao = new Dao();
	      
	      String sql = "SELECT COUNT(*) FROM WRONGANSWERNOTES WHERE WAN_CODE = ?";
	      
	      try {
	    	  
	         pstmt = connection.prepareStatement(sql);
	         
	         pstmt.setInt(1, roomcode);

	         rs = pstmt.executeQuery();

	         while(rs.next()){
	            result = rs.getInt(1);
	         }
	         
	         returnboolean = dao.convertToBoolean(result);
	         
	      }catch(Exception ex) {
	         
	      }finally {
	         
	         if(rs != null){try{rs.close();}catch(Exception ex){}}
	         if(pstmt != null){try{pstmt.close();}catch(Exception ex){}}
	         
	      }
	      
	      return returnboolean;
	   
	   }
	
	  
	  
	  
	  
}
