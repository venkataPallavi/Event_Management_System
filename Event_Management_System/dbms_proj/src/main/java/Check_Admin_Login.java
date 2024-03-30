

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check_Admin_Login
 */
@WebServlet("/Check_Admin_Login")
public class Check_Admin_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_Admin_Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String userType="admin";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 try {
        	// Class.forName("com.mysql.cj.jdbc.Driver");
        	 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS","pallavi","Passwd@123");             
        	 String email = request.getParameter("email");
             String pass = request.getParameter("password");
             Statement st = con.createStatement();
             if (checkpassword(email,pass,userType)) {
                 Cookie cookie1=new Cookie("userType",userType);
                 Cookie cookie2=new Cookie("username",email);
                 cookie1.setMaxAge(60*60*24);
                 cookie2.setMaxAge(60*60*24);
                 response.addCookie(cookie1);
                 response.addCookie(cookie2);
                 response.sendRedirect("home.jsp");
             } else {
                 request.setAttribute("error", "Incorrect Username and Password");
                 RequestDispatcher view = request.getRequestDispatcher("error.jsp");
                 view.forward(request, response);
             }
             
        }catch(Exception ex)
        {
        	ex.printStackTrace();
        }
  }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public boolean checkpassword(String email,String pass,String type)
	{
		String query = "select count(*) from Register where Password=\"" + pass +"\" and Email =\""+email+"\" and Usertype = \""+type+"\";";
		PreparedStatement ps;
		try {
		   // Class.forName("com.mysql.cj.jdbc.Driver");
		    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS","pallavi","Passwd@123");           
		    ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    return false;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
	}

}
