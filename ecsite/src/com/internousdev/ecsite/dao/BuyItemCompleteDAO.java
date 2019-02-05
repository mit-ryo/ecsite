package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite.util.DBConnector;
import com.internousdev.ecsite.util.DateUtil;


public class BuyItemCompleteDAO {

	private DateUtil dateUtil = new DateUtil();

	public int buyItemInfo(String item_transaction_id, String user_master_id, String total_price, String total_count, String pay)throws SQLException{

		DBConnector dbConnector= new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql = "INSERT INTO user_buy_item_transaction(item_transaction_id, total_price, total_count, user_master_id, pay, insert_date)VALUES(?,?,?,?,?,?)";
		int count = 0;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, total_price);
			preparedStatement.setString(3, total_count);
			preparedStatement.setString(4, user_master_id);
			preparedStatement.setString(5, pay);
			preparedStatement.setString(6, dateUtil.getDate());

			count = preparedStatement.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}

		return count;


	}

	public boolean checkStock(String id, String itemPrice, String itemCount)throws SQLException{

		DBConnector dbConnector= new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql = "SELECT item_stock FROM item_info_transaction WHERE id = ?";
		boolean result = true;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				if(Integer.parseInt(resultSet.getString("item_stock")) >= Integer.parseInt(itemCount)) {
					result = true;
				}else{
					result = false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	public int reduceStock(String id, int buyItemCount)throws SQLException{
		DBConnector dbConnector= new DBConnector();
		Connection connection = dbConnector.getConnection();
		String sql = "update item_info_transaction set item_stock = item_stock - ? where id = ?";
		int count = 0;

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			//SELECT
			preparedStatement.setInt(1, buyItemCount);
			preparedStatement.setString(2, id);

			count = preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return count;

	}
}
