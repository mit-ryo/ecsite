package com.internousdev.ecsite.action;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.ItemListDAO;
import com.internousdev.ecsite.dto.ItemInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{

	public Map<String, Object> session;
	private ItemListDAO itemListDAO = new ItemListDAO();
	private List<ItemInfoDTO> ItemInfoDTOList = new ArrayList<ItemInfoDTO>();

	public String execute()throws SQLException{

		String result = "login";

		if(session.containsKey("id")){
//			BuyItemDAO buyItemDAO = new BuyItemDAO();
//			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
//			session.put("id", buyItemDTO.getId());
//			session.put("buyItem_name", buyItemDTO.getItemName());
//			session.put("buyItem_price", buyItemDTO.getItemPrice());
//
//			List<Integer> productCountList = new ArrayList<Integer>();
//			for(int i=1; i <= Integer.parseInt(buyItemDTO.getItemStock()); i++){
//				productCountList.add(i);
//			}
//			session.put("productCountList", productCountList);
			ItemInfoDTOList = itemListDAO.getItemListInfo();
			result = SUCCESS;
		}
		return result;
	}

	public List<ItemInfoDTO>getItemInfoDTOList(){
		return this.ItemInfoDTOList;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}
