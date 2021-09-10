<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
    </head>
<body>

<div class="userList">
    <h3 class="text-center">List of Users</h3>
    <hr>

    <div>
        <a href="<%=request.getContextPath()%>/new" class="addUser">Add New User</a>
    </div>

    <br>
    <table class="listView">
        <thead>
            <tr>
                <th>Id</th>
                <th>First Name</th>
                <th>Second Name</th>
                <th>Age</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.firstName}" /></td>
                    <td><c:out value="${user.secondName}" /></td>
                    <td><c:out value="${user.age}" /></td>
                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
                        <a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>