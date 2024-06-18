package Frame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Login {

	private static HikariDataSource dataSource;
	
	private static final String URL = "jdbc:mysql://192.168.0.116:3306/green?serverTimezone=Asia/Seoul";
	private static final String USER = "tenco1";
	private static final String PASSWORD = "asd123";

	private static final String ADD_COUNT = " select * from 회원 ";

	static {
		// HikariCP 를 사용하기 위한 설정이 필요 하다.
		// HikariConfig --> 제공해줘서 이 클래스를 활용해서 설정을 상세히 할 수 있다.
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.setMaximumPoolSize(10); // 최대 연결 수 설정 10

		dataSource = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	private static void loginCount(Connection conn, Scanner scanner) {
		
		

		try (PreparedStatement pstmt = conn.prepareStatement(ADD_COUNT);
				ResultSet resultset = pstmt.executeQuery()) {
			boolean check = false;
			while (resultset.next()) {
				String checkID = resultset.getString("아이디");

				if (id == checkID) {
					System.out.println("해당되는 아이디가 존재합니다");
					check = true;

					break;
				}
			}

			if (check == false) {
				System.out.println("해당되는 아이디가 없습니다");
				System.out.println("----------------------------------------");
			} else {
				try (PreparedStatement pstmt2 = conn.prepareStatement(ADD_COUNT);
						ResultSet resultSet = pstmt.executeQuery()) {
					while (resultSet.next()) {
						System.out.println(" ID : " + resultSet.getInt("id"));
						System.out.println("이름 : " + resultSet.getString("회원이름"));
						System.out.println("아이디 : " + resultSet.getString("아이디"));
						System.out.println("비번 : " + resultSet.getString("비번"));

						if (!resultSet.isLast()) {
							System.out.println("----------------------------------------");
						}
					}
					
				} catch (SQLException e2) {
					System.out.println("잘못된 입력방식입니다! ERRORCODE - 12");
				}
			}

		} catch (Exception e) {
			System.out.println("잘못된 입력방식입니다! ERRORCODE - 13");
		}

	}
	
}
