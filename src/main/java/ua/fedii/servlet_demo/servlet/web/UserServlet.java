package ua.fedii.servlet_demo.servlet.web;

import ua.fedii.servlet_demo.servlet.dao.UserDAOIml;
import ua.fedii.servlet_demo.servlet.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserDAOIml userDAO;
    //private final Map<String, Command> commandMap = new HashMap<>();

    @Override
    public void init() {
        userDAO = new UserDAOIml();
//        super.init();
//        commandMap.put("select", new SelectUser());
//        commandMap.put("insert", new InsertUser());
//        commandMap.put("update", new UpdateUser());
//        commandMap.put("delete", new DeleteUser());
   }

//    @Override
//    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addUpdateUserForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        User editUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addUpdateUserForm.jsp");
        request.setAttribute("user", editUser);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        User newUser = new User(firstName, secondName, age, email);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        User updateUser = new User(id, firstName, secondName, age, email);
        userDAO.updateUser(updateUser);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet was destroyed!");
        super.destroy();
    }

}