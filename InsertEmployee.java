package controller;
import dao.EmployeeDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employee_Model;
public class InsertEmployee extends HttpServlet {
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        try
        {
            
        String callflag=request.getParameter("callflag");
        String fname=request.getParameter("fname");
        String lname=request.getParameter("lname");
        String gender=request.getParameter("gender");
        String email=request.getParameter("email");
        String mno=request.getParameter("mno");
        String dob=request.getParameter("dob");
        System.out.println(fname);
        System.out.println(lname);
        System.out.println(email);
        System.out.println(dob);
        System.out.println(mno);
        System.out.println(gender);
        System.out.println(callflag);
            Employee_Model empmodel=new Employee_Model();
            empmodel.setDob(dob);
            empmodel.setEmail(email);
            empmodel.setFirstName(fname);
            empmodel.setGender(gender);
            empmodel.setLastName(lname);
            empmodel.setMobileNumber(mno);
            EmployeeDao empdao=new EmployeeDao();
            int result=empdao.insertData(empmodel);
            System.out.println(result+" Record Added");
            out.print(result+" Record Added");
        }
   
   catch(ClassNotFoundException | SQLException ex)
   {
       System.out.println(ex);
    }
   }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
