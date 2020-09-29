<%@ page language="java" contentType="text/html;" pageEncoding="UTF-16"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>ResetPassword</title>
</head>
<body>
	<form:form action="passwordreset" method="POST" modelAttribute="PasswordResetModel">
		

		<table>
		      <tr>
               <td><form:label path = "newpassword">New Password</form:label></td>
               <td><form:password path = "newpassword" /></td>
            </tr>
            <tr>
               <td><form:label path = "confirmpassword">Confirm New Password</form:label></td>
               <td><form:password path = "confirmpassword" /></td>
            </tr> 
			<tr>
			
			<td><input type="hidden" name="token"
						value="${passwordResetModel.token}"></td>
			
			
			</tr>
			<tr>
				  <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
			</tr>
		</table>
	</form:form>

</body>
</html>