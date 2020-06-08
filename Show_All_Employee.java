package controller;
import dao.EmployeeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee_Model;
public class Show_All_Employee extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  {
            EmployeeDao employeeDao=new EmployeeDao();
            Employee_Model empm=new Employee_Model();
            List<Employee_Model> result=employeeDao.showAll();
            Iterator<Employee_Model> itr=result.iterator();
            while(itr.hasNext())
            {
                itr.next();
                out.print(empm.getFirstName()+" "+empm.getLastName()+" "+empm.getDob()+" "+empm.getGender()+" "+empm.getMobileNumber()+" "+empm.getEmail()+"\n");
            }
        }
        catch(ClassNotFoundException | SQLException ex)
        {
            out.print(ex);
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
