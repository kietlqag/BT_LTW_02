<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp" %>

<!DOCTYPE html>
<html>
<body>
	<form action="/servlet/dang-nhap" method="post">
		<c:if test="${alert !=null}">
			<h3 class="alert alertdanger">${alert}</h3>
		</c:if>
		<div class="container">
			<label for="username"><b>Username</b></label> <input type="text"
				id="username" name="username" placeholder="Enter Username"
				oninput="updateForgotPasswordLink()" required> <label
				for="password"><b>Password</b></label> <input type="password"
				placeholder="Enter Password" name="password" required>

			<button type="submit">Login</button>
			<label> <input type="checkbox" checked="checked"
				name="remember"> Remember me
			</label>
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<button type="button" class="cancelbtn">Cancel</button>
			<span class="password"> Forgot <a href="/servlet/quen-mat-khau"
				id="forgot-password-link">password?</a>
			</span>
		</div>

		<div class="container signin">
			<p>
				Don't have an account? <a href="/servlet/dang-ky">Sign up</a>.
			</p>
		</div>
	</form>
</body>
</html>
