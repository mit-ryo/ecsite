package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware{

	private int count;
	private String pay;
	private Map<String, Object> session;
	private BuyItemCompleteDAO buyItemCompleteDAO = new BuyItemCompleteDAO();
	private String errorMessage;


	public String execute()throws SQLException{
		String result;

		session.put("count", count);

		if(buyItemCompleteDAO.checkStock(session.get("id").toString(),
				session.get("buyItem_price").toString(),
				session.get("count").toString())){

			int intCount = Integer.parseInt(session.get("count").toString());
			int intPrice = Integer.parseInt(session.get("buyItem_price").toString());
			session.put("total_buyItem_price",intCount * intPrice);
			String payment;

			if(pay.equals("1")){
				payment = "現金払い";
				session.put("pay",payment);
			}else{
				payment = "クレジットカード";
				session.put("pay",payment);
			}
			result = SUCCESS;
		}else{
			setErrorMessage("在庫が不足しています。");
			result = ERROR;
		}
		return result;
	}

	public int getCount(){
		return count;
	}

	public void setCount(int count){
		this.count = count;
	}

	public String getPay(){
		return pay;
	}

	public void setPay(String pay){
		this.pay = pay;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session){
		this.session = session;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
