package com.hanains.mysite.dao;

import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.UserVo;
@Repository
public class UserDao {
	
	
	@Autowired
	private OracleDataSource oracleDataSource;
	
	
	@Autowired
	private SqlSession sqlSession;
	/*
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			//1.드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2.커넥션 만들기(ORACLE DB)
			String dbURL  = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "webdb", "webdb" );
			
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		}
		
		return connection;
	}
	*/
	public UserVo get( UserVo vo){
		//vo를 못 쓸땐. user.xml에서 parameter에 map
		//Map<String,Object> map = new HashMap<String,Object>();
	//	map.put("email",email);
	//	map.put("password",password);
		//UserVo userVo =sqlSession.selectOne("user.getByEmailAndPassword",map);

		UserVo userVo =sqlSession.selectOne("user.getByEmailAndPassword",vo);

		return userVo;
	}
	public UserVo get(Long no){
		UserVo vo=sqlSession.selectOne("user.getByNo",no);
		return vo;
	}
	/*
	public UserVo get( String email, String password )throws RepositoryException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserVo vo = null;
		
		try{
			//1. get Connection
			conn = oracleDataSource.getConnection();
			
			//2. prepare statement
			String sql = 
				" select no, name, email" +
				"   from member" +
				"  where email=?"+
				"    and password=?";
			pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, email );
			pstmt.setString( 2, password );
			
			//4. execute SQL
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String email2 = rs.getString( 3 );
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email2);
			}
			
		} catch( SQLException ex ) {
			//log처리 해줘야함.
			System.out.println( "SQL Error:" + ex );
			throw new RepositoryException(ex.toString());
			//전환을 해서 throw new Re
		} finally {
			//5. clear resources
			try{
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		
		return vo;
	}*/
	public void insert( UserVo vo ){
		sqlSession.insert("user.insert",vo);
	}
	/*
	public void insert( UserVo vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. DB Connection
			conn = oracleDataSource.getConnection();
			
			//2. prepare statment
			String sql = 
				" insert" +
				" into member" +
				" values ( member_no_seq.nextval, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getEmail() );
			pstmt.setString( 3, vo.getPassword() );
			pstmt.setString( 4, vo.getGender() );
			
			//4. execute SQL
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "sql error:" + ex );
			ex.printStackTrace();
		} finally {
			//5. clear resources
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
	}*/
}