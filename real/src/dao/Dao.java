package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {	// DB 연결

	private Context context; 
	private DataSource datasource; 
	private Connection connection; 
	
	public Dao() {

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
	
	public void closeConnection() {
		try {
			if(connection != null) {connection.close();}
			System.out.println("===== Oracle DisConnection Success =====");
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("===== Oracle DisConnection Failure =====");
		}
	}

	/*Transaction Start 내가 수동으로 관리 하겠다.*/
	public void setAutoCommit(boolean autoCommit) throws Exception{
		connection.setAutoCommit(autoCommit);
	}
	/*Transaction End 내가 수동으로 관리하는 것을 마치겠다.*/
	public void transactionEnd(boolean transaction) throws Exception{
		if(transaction){connection.commit();}
		else{connection.rollback();}
	}

	// 입력,수정,삭제 : true
	public boolean convertToBoolean(int value){
		return (value == 1)? true: false;
	}
	

}
