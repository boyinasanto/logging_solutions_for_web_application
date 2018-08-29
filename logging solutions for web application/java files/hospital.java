
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/hospital")
public class hospital extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String firstname = request.getParameter("firstname");
      
        String lastname = request.getParameter("lastname");
        
        String email = request.getParameter("email");
        
        String password = request.getParameter ("password");

       
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection con=DriverManager.getConnection ("jdbc:mysql://localhost/hospital","root","praveen/*-+");

        PreparedStatement ps=con.prepareStatement("insert into hospital values(?,?,?,?)");

        ps.setString(1,firstname);
       
        ps.setString(2,lastname);
        ps.setString(3,email);
        ps.setString(4,password);
        int i=ps.executeUpdate();
        if(i>0)
          {
            out.println("You are sucessfully registered");
             RequestDispatcher rs = request.getRequestDispatcher("login.html");
            rs.forward(request, response);
          }
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
}