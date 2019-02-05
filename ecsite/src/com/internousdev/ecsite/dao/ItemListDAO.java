package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.ecsite.dto.ItemInfoDTO;
import com.internousdev.ecsite.util.DBConnector;

public class ItemListDAO {

	private DBConnector dbConnector = new DBConnector();
	private Connection connection = dbConnector.getConnection();

	public List<ItemInfoDTO> getItemListInfo()throws SQLException{
		//int itemCount=0;
		List<ItemInfoDTO> ItemInfoDTO = new ArrayList<ItemInfoDTO>();
//ここを直す
		String sql = "SELECT * FROM item_info_transaction ORDER BY id";

		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				ItemInfoDTO dto = new ItemInfoDTO();
				dto.setId(resultSet.getString("id"));
				dto.setItemName(resultSet.getString("item_name"));
				dto.setItemPrice(resultSet.getString("item_price"));
				dto.setItemStock(resultSet.getString("item_stock"));
				dto.setInsert_date(resultSet.getString("insert_date"));
				dto.setUpdate_date(resultSet.getString("update_date"));
				ItemInfoDTO.add(dto);

			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return ItemInfoDTO;
	}
}
