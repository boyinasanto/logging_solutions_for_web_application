
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/medicine")
public class medicine extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String medicines = request.getParameter("medicines");
      
        int mg = Integer.parseInt(request.getParameter("mg"));
        
        int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		String state = request.getParameter("state");
		
		String city = request.getParameter("city");
		
		String address = request.getParameter("address");
		
		String pincode = request.getParameter("pincode");
		
		String payment = request.getParameter("payment");

        
        

       
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection con=DriverManager.getConnection ("jdbc:mysql://localhost/hospital","root","praveen/*-+");

        PreparedStatement ps=con.prepareStatement("insert into medicine values(?,?,?,?,?,?,?,?)");

        ps.setString(1,medicines);
        ps.setInt(2,mg);
        ps.setInt(3,quantity);
        ps.setString(4,state);
		ps.setString(5,city);
		ps.setString(6,address);
		ps.setString(7,pincode);
		ps.setString(8,payment);
        int i=ps.executeUpdate();
        if(i>0)
          {
            out.println("Your order is succesfully placed");
             RequestDispatcher rs = request.getRequestDispatcher("home.html");
            rs.forward(request, response);
          }
		 else
		    out.println("Your order is insuccessful");
			RequestDispatcher rs = request.getRequestDispatcher("medicines.html");
            rs.forward(request, response);
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
}