package model;

import java.sql.*;
import java.util.*;

public class MemberDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;

	public MemberDAO() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";

		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberDO getMember(String id) {
		MemberDO member = null;
		this.sql = "select id, passwd, name, to_char(regdate, 'YYYY-MM-DD HH24:MI:SS') as regdate, grade "
				+ "from member where id = ?";

		try {
			this.pstmt = conn.prepareStatement(sql);

			this.pstmt.setString(1, id);
			rs = this.pstmt.executeQuery();

			if (this.rs.next()) {
				member = new MemberDO();

				member.setId(this.rs.getString("id"));
				member.setPasswd(this.rs.getString("passwd"));
				member.setName(this.rs.getString("name"));
				member.setRegdate(this.rs.getString("regdate"));
				member.setGrade(this.rs.getInt("grade"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return member;
	}

	public int insertMember(MemberDO member) throws Exception {
		// 나를 호출한 놈에게 예외를 던진다. throws Exception : 문제상황을 알려주기 위함임.
		int rowCount = 0;
		boolean isIdDuplicate = false;

		try {
			this.conn.setAutoCommit(false);
			// setAutoCommit을 사용할 경우 하나의 메서드에서 모두 적용해야한다. (순수JDBC를 사용하는 경우)

			this.sql = "select id from member where id = ?";
//			dbMember = this.getMember(member.getId());
//			위의 코드를 작성하면 안됨. 하나의 트랜젝션에서 모두 해결해야함.

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			this.rs = pstmt.executeQuery();

			if (!rs.next()) {
				// true이면 중복된 행이 있다는 의미

				this.sql = "insert into member (id, passwd, name) values (?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPasswd());
				pstmt.setString(3, member.getName());

				rowCount = pstmt.executeUpdate();
				this.conn.commit();
			} else {
				isIdDuplicate = true;
				this.conn.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.conn.setAutoCommit(true);

				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (isIdDuplicate) {
			throw new Exception("아이디가 중복되었습니다.");
		}
		return rowCount;
	}

	public ArrayList<MemberDO> getAllMembers() {
		ArrayList<MemberDO> memberList = new ArrayList<MemberDO>();
		this.sql = "select id, name, to_char(regdate, 'YYYY-MM-DD HH24:MI:SS') as regdate "
				+ " from member order by regdate";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			MemberDO member = null;

			while (rs.next()) {
				member = new MemberDO();

				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setRegdate(rs.getString("regdate"));

				memberList.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!stmt.isClosed()) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return memberList;
	}

	public int changePasswd(MemberDO member) {
		int rowCount = 0;
		this.sql = "update member set passwd = ? where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getNewPasswd());
			pstmt.setString(2, member.getId());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	public int changeGrade(MemberDO member) {
		int rowCount = 0;
		this.sql = "update member set grade = ? where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getGrade());
			pstmt.setString(2, member.getId());
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}

	public int deleteMember(String id) {
		int rowCount = 0;
		this.sql = "delete from member where id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rowCount;
	}
	public void closeConn() {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//ORM 방식으로 작성한 예시
//package model;
//
//import java.sql.*;
//import java.util.*;
//
//public class MemberDAO {
//
//	private Connection conn;
//	private Statement stmt;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//	private String sql;
//
//	public MemberDAO() {
//		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
//		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
//
//		try {
//			Class.forName(jdbc_driver);
//			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public MemberDO getMember(String id) {
//		MemberDO member = null;
//		this.sql = "select id, passwd, name, to_char(regdate, 'YYYY-MM-DD HH24:MI:SS') as regdate, grade "
//				+ "from member where id = ?";
//
//		try {
//			this.pstmt = conn.prepareStatement(sql);
//
//			this.pstmt.setString(1, id);
//			rs = this.pstmt.executeQuery();
//
//			if (this.rs.next()) {
//				member = new MemberDO();
//
//				member.setId(this.rs.getString("id"));
//				member.setPasswd(this.rs.getString("passwd"));
//				member.setName(this.rs.getString("name"));
//				member.setRegdate(this.rs.getString("regdate"));
//				member.setGrade(this.rs.getInt("grade"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return member;
//	}
//
//	public int insertMember(MemberDO member) throws Exception {
//		int rowCount = 0;
//		this.sql = "insert into member(id, passwd, name) values (?, ?, ?)";
//		MemberDO dbMember = null;
//		
//		try {
//			conn.setAutoCommit(false);
//
//			// id 중복 검사
//			// dbMember가 null일 때만 insert작업을 수행
//			dbMember = this.getMember(member.getId());
//			if (dbMember == null) {
//				pstmt = conn.prepareStatement(sql);
//
//				pstmt.setString(1, member.getId());
//				pstmt.setString(2, member.getPasswd());
//				pstmt.setString(3, member.getName());
//
//				rowCount = pstmt.executeUpdate();
//
//				conn.commit();
//			} 
//// java.lang에는 exception 클래스가 있다. 이 클래스의 인스턴스를 만들어서 예외 메시지를 넣어줄 수 있음.
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//
//			try {
//				conn.rollback();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		} finally {
//			try {
//				conn.setAutoCommit(true);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//
//		if(dbMember != null) {
//			throw new Exception("아이디가 중복되었습니다.");
//		}
//		
//		return rowCount;
//	}
//}
