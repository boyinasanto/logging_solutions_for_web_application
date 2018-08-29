import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Log")
public class Log extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	{
        String query;
        String dbemail, dbPassword;
		String email,password;
		
	    email = req.getParameter("email");
		password = req.getParameter("password");
        
      PrintWriter pn = res.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital", "root", "praveen/*-+");
            Statement stmt =  con.createStatement();
            query = "SELECT email, password FROM hospital";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();

            while(rs.next()){
                dbemail = rs.getString("email");
                dbPassword = rs.getString("password");

                if(dbemail.equals(email) && dbPassword.equals(password)){
                    pn.print("OK");
					RequestDispatcher rsd = req.getRequestDispatcher("home.html");
                    rsd.forward(req, res);
					
                }
                
            }
		}
        catch(Exception se)
        {
            se.printStackTrace();
        }
        

	}
}