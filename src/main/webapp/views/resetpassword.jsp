<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>

<!DOCTYPE html>
<html>
<form action="/servlet/quen-mat-khau" method="post">
	<c:if test="${alert !=null}">
		<h3 class="alert alertdanger">${alert}</h3>
	</c:if>
	<label for="username">Username:</label> <input type="text"
		id="username" name="username" title="username" /> <label
		for="newPassword">New Password:</label> <input type="password"
		id="newPassword" name="newPassword" title="New password" /> <label
		for="confirmPassword">Confirm Password:</label> <input type="password"
		id="confirmPassword" name="confirmPassword"
		title="Confirm new password" /> <input type="submit"
		value="Change Password" title="Change password" />
</form>
</html>