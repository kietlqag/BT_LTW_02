<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<form action="/servlet/quen-mat-khau" method="post">

	${username}
	
	<label for="newPassword">New Password:</label> 
	<input type="password" id="newPassword" name="newPassword" title="New password" /> 
	<label for="confirmPassword">Confirm Password:</label> 
	<input type="password" id="confirmPassword" name="confirmPassword" title="Confirm new password" />
	<input type="submit" value="Change Password" title="Change password" />
</form>
</html>