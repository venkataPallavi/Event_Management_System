

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEvent
 */
@WebServlet("/AddEvent")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
        try {
           // Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS","pallavi","Passwd@123");	          
            String itemInsertQuery = "insert into Event_details values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement Item = con.prepareStatement(itemInsertQuery);
            String cname = request.getParameter("item_name");
            Item.setString(1, cname);
            String category = request.getParameter("item_category");
            Item.setString(2, category);
            String ename = request.getParameter("event_name");
            Item.setString(3, ename);
            String date = request.getParameter("item_price");
            Item.setString(4, date);
            String time = request.getParameter("item_item");
            Item.setString(5, time);
            int total = Integer.parseInt(request.getParameter("totalseats"));
            Item.setInt(6, total);
            int total1 = Integer.parseInt(request.getParameter("availableseats"));
            Item.setInt(7, total1);
            int total2 = Integer.parseInt(request.getParameter("ticketprice"));
            Item.setInt(8, total2);
            String img = request.getParameter("item_img");
            Item.setString(9, img);
            Item.execute();
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            response.sendRedirect("home.jsp");
        } catch (Exception ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
