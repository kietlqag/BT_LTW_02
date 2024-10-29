<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="${pageContext.request.contextPath }/admin/category/insert" method="post">
	<label for="catename">Category name:</label><br> 
	<input type="text" id="catename" name="catename"><br> 
	<label for="images">Images:</label><br> 
	<input type="file" id="images" name="images"><br>
	<label for="status">Status:</label><br> 
	<input type="text" id="status" name="status"><br>
	<br> <input type="submit" value="Submit">
</form>