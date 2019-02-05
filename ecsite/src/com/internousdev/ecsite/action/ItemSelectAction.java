package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.ItemListDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.ItemInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ItemSelectAction extends ActionSupport implements SessionAware{

	private String selectItem;
	public Map<String, Object> session;
	private String message;
	private String errorMessage;
	private BuyItemDAO buyItemDAO = new BuyItemDAO();
	private ItemListDAO itemListDAO = new ItemListDAO();
	private List<ItemInfoDTO> ItemInfoDTOList = new ArrayList<ItemInfoDTO>();

	public String execute()throws SQLException{

		String result = SUCCESS;
		BuyItemDTO buyItemDTO = new BuyItemDTO();
		if(!(selectItem.equals(""))){

			try{
				Integer.parseInt(selectItem);
				buyItemDTO = buyItemDAO.getBuyItemInfo(selectItem);

				if (buyItemDTO == null) {
					setErrorMessage("存在しない商品番号です。");
					ItemInfoDTOList = itemListDAO.getItemListInfo();
					result = ERROR;
				}else {
					session.put("id",buyItemDTO.getId());
					session.put("buyItem_name",buyItemDTO.getItemName());
					session.put("buyItem_price",buyItemDTO.getItemPrice());
					session.put("buyItem_stock",buyItemDTO.getItemStock());
					List<Integer> productCountList = new ArrayList<Integer>();
					for(int i=1; i <= Integer.parseInt(buyItemDTO.getItemStock()); i++){
						productCountList.add(i);
					}
					session.put("productCountList", productCountList);
				}

			}catch(NumberFormatException e){
				setErrorMessage("商品番号に数値以外が入力されています。");
				ItemInfoDTOList = itemListDAO.getItemListInfo();
				result = ERROR;
			}


		}
		return result;
	}

	public String getSelectItem(){
		return selectItem;
	}

	public void setSelectItem(String selectItem){
		this.selectItem = selectItem;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

	public String getMessage(){
		return this.message;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getErrorMessage(){
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public List<ItemInfoDTO> getItemInfoDTOList() {
		return ItemInfoDTOList;
	}

	public void setItemInfoDTOList(List<ItemInfoDTO> itemInfoDTOList) {
		ItemInfoDTOList = itemInfoDTOList;
	}


}
