

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.events.Event;

/**
 * Servlet implementation class cateveservlet
 */
@WebServlet("/cateveservlet")
public class cateveservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cateveservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String cname = request.getParameter("ct");
    	String cat=request.getParameter("cat");
    	System.out.println(cname);
    	Eventmethods dao = new Eventmethods();
    	List<Event> list = dao.viewAllEventswithCategory1(cat,cname);
    	request.setAttribute("EventList", list);
    	request.setAttribute("city", cname);    	 
    	String msg = request.getParameter("error");
        RequestDispatcher view = request.getRequestDispatcher("display.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
