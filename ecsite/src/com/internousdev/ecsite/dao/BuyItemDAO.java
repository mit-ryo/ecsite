package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.util.DBConnector;

public class BuyItemDAO {

	DBConnector dbConnector = new DBConnector();
	Connection connection = dbConnector.getConnection();


	public BuyItemDTO getBuyItemInfo(String id){
		BuyItemDTO buyItemDTO = null;

		//String sql = "SELECT id,item_name,item_price, item_stock FROM item_info_transaction WHERE id = ?";
		String sql = "SELECT id , item_name, item_price, item_stock FROM item_info_transaction WHERE id = ?";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				buyItemDTO = new BuyItemDTO();
				buyItemDTO.setId(resultSet.getInt("id"));
				buyItemDTO.setItemName(resultSet.getString("item_name"));
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));
				buyItemDTO.setItemStock(resultSet.getString("item_stock"));

			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return buyItemDTO;
	}
}


