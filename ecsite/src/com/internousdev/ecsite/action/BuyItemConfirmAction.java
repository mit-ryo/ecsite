package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;


public class BuyItemConfirmAction extends ActionSupport implements SessionAware{

	public Map<String, Object> session;
	private BuyItemCompleteDAO buyItemCompleteDAO = new BuyItemCompleteDAO();

	public String execute()throws SQLException{
		String result = ERROR;

		int insertCount = 0;
		int updateCount = 0;

		insertCount = buyItemCompleteDAO.buyItemInfo(
				session.get("id").toString(),
				session.get("login_user_id").toString(),
				session.get("buyItem_price").toString(),
				session.get("count").toString(),
				session.get("pay").toString());

		if (insertCount > 0) {
			updateCount = buyItemCompleteDAO.reduceStock(session.get("id").toString(), Integer.parseInt(session.get("count").toString()));

			if (updateCount > 0) {
				result = SUCCESS;
			}
		}

		return result;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}
}