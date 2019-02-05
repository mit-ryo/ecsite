package com.internousdev.ecsite.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;


public class UserCreateCompleteDAO {


	public void createUser(String loginUserId, String loginUserPassword,String userName)throws SQLException{

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		DateUtil dateUtil = new DateUtil();

		String sql = "INSERT INTO login_user_transaction(login_id,login_pass,user_name,insert_date)VALUES(?,?,?,?)";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginUserId);
			preparedStatement.setString(2, loginUserPassword);
			preparedStatement.setString(3, userName);
			preparedStatement.setString(4, dateUtil.getDate());

			preparedStatement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

	public boolean checkUserId(String loginUserId)throws SQLException{
		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
//		String sql2 = "SELECT * FROM login_user_transaction where login_id = ?";
		String sql2 = "SELECT COUNT(login_id) AS COUNT FROM login_user_transaction where login_id = ?";
		boolean result = false;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql2);
			preparedStatement.setString(1, loginUserId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				//if(resultSet.getString("login_id").equals(loginUserId)){
				if(resultSet.getInt("COUNT") > 0) {
					result = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}