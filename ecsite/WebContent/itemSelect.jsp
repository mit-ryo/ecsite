<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>ItemSelect画面</title>

<style type="text/css">
/* =====TAG LAYUT=====*/
body{
margin:0;
padding:0;
line-height:1.6;
letter-spacing:1px;
font-family:Verdana, Helvetica, sans-serif;
font-size:12px;
color:#333;
background:#fff;
}

table{
text-align:center;
margin:0 auto;
}

/* =====ID LAYOUT===== */
#top{
width:780px;
margin:30px auto;
border:1px solid #333;
}

#header{
width: 100%;
height: 80px;
background-color: black;
}

#main{
width:100%;
height: 500px;
text-align: center;
}

#footer{
width: 100%;
height: 80px;
background-color: black;
clear:both;
}
</style>

</head>
<body>
	<div id="header">
		<div id="pr">
		</div>
	</div>

	<div id="main">
		<div id="top">
			<p>ItemSelect</p>
		</div>
		<s:if test="message == null">
			<s:if test="errorMessage != ''">
				<s:property value="errorMessage" escape="false" />
			</s:if>
			<s:form action="ItemSelectAction">
				<h3>希望する商品番号を入力してください。</h3>
				<s:textfield name="selectItem"/>
				<s:submit value="完了"/>
			</s:form>
<%-- 			<s:form action="ItemSelectAction"> --%>
<%-- 				<s:select name="selectItem" list="session.itemNumberList"/>個 --%>
<%-- 			</s:form> --%>
			<h3>商品情報は以下になります。</h3>
				<table border="1">
					<tr>
						<th>id</th>
						<th>商品名</th>
						<th>値段</th>
						<th>在庫</th>
						<th>登録日</th>
						<th>更新日</th>
					</tr>
					<s:iterator value="itemInfoDTOList">
						<tr>
							<td><s:property value="id" /></td>
							<td><s:property value="itemName" /></td>
							<td><s:property value="itemPrice" /><span>円</span></td>
							<td><s:property value="itemStock" /><span>個</span></td>
							<td><s:property value="insert_date" /></td>
							<td><s:property value="update_date" /></td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
			<div>
				<p>前画面に戻る場合は
					<a href='<s:url action="GoHomeAction" />'>こちら</a>
				</p>
				<p>マイページは
					<a href='<s:url action="MyPageAction" />'>こちら</a>
				</p>
			</div>
		</div>
	<div id="footer">
		<div id="pr">
		</div>
	</div>
</body>
</html>