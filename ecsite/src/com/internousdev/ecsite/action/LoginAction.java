package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

//import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.ItemListDAO;
import com.internousdev.ecsite.dao.LoginDAO;
import com.internousdev.ecsite.dto.ItemInfoDTO;
import com.internousdev.ecsite.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware{
	private String loginUserId;
	private String loginPassword;
	private Map<String, Object> session;
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	//private BuyItemDAO buyItemDAO = new BuyItemDAO();

	private ItemListDAO itemListDAO = new ItemListDAO();
	private List<ItemInfoDTO> ItemInfoDTOList = new ArrayList<ItemInfoDTO>();

	public String execute()throws SQLException{
		String result = ERROR;
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);
		session.put("loginUser", loginDTO);

		if((((LoginDTO) session.get("loginUser")).getAdminFlg() != null)
			&& (((LoginDTO) session.get("loginUser")).getAdminFlg().equals("1"))){
			result = "admin";
		}else if(((LoginDTO)session.get("loginUser")).getLoginFlg()){

			session.put("login_user_id",loginDTO.getLoginId());
			ItemInfoDTOList = itemListDAO.getItemListInfo();
			result = SUCCESS;

//			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
//
//			session.put("login_user_id",loginDTO.getLoginId());
//			session.put("id",buyItemDTO.getId());
//			session.put("buyItem_name",buyItemDTO.getItemName());
//			session.put("buyItem_price",buyItemDTO.getItemPrice());
//			session.put("buyItem_stock",buyItemDTO.getItemStock());
//			List<Integer> productCountList = new ArrayList<Integer>();
//			for(int i=1; i <= Integer.parseInt(buyItemDTO.getItemStock()); i++){
//				productCountList.add(i);
//			}
//			session.put("productCountList", productCountList);
			//return result;
		}
		return result;
	}


	public String getLoginUserId(){
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId){
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword(){
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword){
		this.loginPassword = loginPassword;
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
