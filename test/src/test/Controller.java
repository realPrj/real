package test;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

					Connection connection;
					String url = "jdbc:oracle:thin:@192.168.0.246:1521:xe";
					String user = "TEAM3NOMAL";
					String password = "1234";
					PreparedStatement pstmt = null;
					boolean transaction = false;

					BufferedReader br = null;
					BufferedWriter bw = null;
					ArrayList<Bean> dataList = new ArrayList<Bean>();
					Bean b = new Bean();

					String posData = null;

					connection = null;
					Class.forName("oracle.jdbc.OracleDriver");
					connection = DriverManager.getConnection(url, user, password);

					try{
						// csv 파일 읽기(위치 지정)
						br = new BufferedReader(new FileReader("/home/pi/Desktop/merged_log_data.csv"));
						// query문 추가
						String sql = "INSERT INTO ACTIVITY (AT_MRID, AT_DATE, AT_STEP, AT_FLOOR, AT_LOG, AT_LOGDATE) " +
								"VALUES(1,DEFAULT,?,?,DEFAULT,TO_DATE(?,'YYYYMMDDHH24MISS'))";
						// VALUES 첫번째 AT_MRID는 라즈베리 파이 아이디 입니다, 제(신태휘)가 1이니까
						//   서로 다르게 숫자 4자리 이내로 써주시면 됩니다.
						// Transaction Start
						connection.setAutoCommit(false);
						try{
							// 1. CSV 파일 ARRAY LIST에 저장-`
							// 2. query문 실행
							transaction = true;
							try {
								// 1. CSV 파일 ARRAY LIST에 저장
								while((posData = br.readLine()) != null){
									String log_datas[] = posData.split(",");
									//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									System.out.println(log_datas[0]);
									//String parsed = sdf.format(log_datas[0]);
									
									//System.out.println(parsed + "나오냐~!");
									b.setTime(log_datas[0]);
									b.setStep(Integer.parseInt(log_datas[1]));
									b.setFloor(Integer.parseInt(log_datas[2]));
									dataList.add(b);
									try{
										// 2. query문 실행
										pstmt = connection.prepareStatement(sql);
										System.out.println(sql);
										//"INSERT INTO ACTIVITY +
										//(AT_MRID, AT_DATE, AT_STEP, AT_FLOOR, AT_LOG, AT_LOGDATE) +
										// VALUES(1,DEFALUT,?,?,DEFAULT,?)"
										pstmt.setInt(1, dataList.get(0).getStep());
										pstmt.setInt(2, dataList.get(0).getFloor());
										pstmt.setString(3, dataList.get(0).getTime());
										
										if(pstmt.executeUpdate() >= 1){
											transaction = true;
											System.out.println("DB 입력 완료!! ㅎㅎ");

											bw = new BufferedWriter(new FileWriter("/home/pi/Desktop/merged_log_data.csv"));
											String w = "";

											bw.write(w);

										}else{
											System.out.println(pstmt.executeUpdate());
											transaction = false;
											System.out.println("DB 입력 실패!! ㅎㅎ");
										}
									}catch(Exception e){
										e.printStackTrace();
										System.out.println("파일 리드 실패!! ㅎㅎ");
									}
								}
							}catch (IOException e) {
								e.printStackTrace();
								System.out.println("파일 인 실패!! ㅎㅎ");
							}				
						}catch(Exception e){
							e.printStackTrace();
							System.out.println("트랜잭션 실패!! ㅎㅎ");
						}
						if(transaction){connection.commit();
						}else{connection.rollback();}
						if(connection != null){
							DriverManager.getConnection(url, user, password);
						}
					}catch(Exception e){
						e.printStackTrace();
						System.out.println("DB 연결 실패!! ㅎㅎ");
					}finally{
						// DB 접근 종료
						if(br != null) try{br.close();}catch(Exception e){};
						if(pstmt != null) try{pstmt.close();}catch(Exception e){};
						if(connection != null) try{connection.close();}catch(Exception e){};
					}

		
	}

}
