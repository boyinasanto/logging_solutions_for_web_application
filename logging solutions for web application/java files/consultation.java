
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/consultation")
public class consultation extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		
		String fullname = request.getParameter("fullname");
		
		int age = Integer.parseInt(request.getParameter("age"));
		
		String gender = request.getParameter("gender");
	
        String doctor = request.getParameter("doctor");
      
        String doctortime = request.getParameter("doctortime");
        
        String problem = request.getParameter("problem");
		
		String phonenumber = request.getParameter("phonenumber");
        
        

       
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
          Connection con=DriverManager.getConnection ("jdbc:mysql://localhost/hospital","root","praveen/*-+");

        PreparedStatement ps=con.prepareStatement("insert into consultation values(?,?,?,?,?,?,?)");

        ps.setString(1,fullname);
		ps.setInt(2,age);
		ps.setString(3,gender);
		ps.setString(4,doctor);
        ps.setString(5,doctortime);
        ps.setString(6,problem);
		ps.setString(7,phonenumber);
        
        int i=ps.executeUpdate();
        if(i>0)
          {
            out.println("Your appointment has been successfully registered");
             RequestDispatcher rs = request.getRequestDispatcher("home.html");
            rs.forward(request, response);
          }
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
}