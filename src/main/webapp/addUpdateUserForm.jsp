<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>User add</title></head>
<body>

<header>
    <div class="backToUserList">
        <a href="<%=request.getContextPath()%>/list">Users</a>
    </div>
</header>

<div class="addUpdateForm">
    <c:if test="${user != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="insert" method="post">
            </c:if>

            <h2>
                <c:if test="${user != null}">
                    Edit User
                </c:if>
                <c:if test="${user == null}">
                    Add New User
                </c:if>
            </h2>

            <c:if test="${user != null}">
                <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
            </c:if>

            <label>First Name</label> <input type="text" value="<c:out value='${user.firstName}' />" name="firstName" required="required"> <br>
            <label>Second Name</label> <input type="text" value="<c:out value='${user.secondName}' />" name="secondName" required="required"> <br>
            <label>Age</label> <input type="text" value="<c:out value='${user.age}' />" name="age" required="required"> <br>
            <label>Email</label> <input type="text" value="<c:out value='${user.email}' />" name="email" required="required"> <br><br>

        <button type="submit" class="addUpdateButton">Save</button>
    </form>
</div>
</body>
</html>