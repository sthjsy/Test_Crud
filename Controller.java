package mypack;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import dao.MyConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            String callflag = request.getParameter("callflag");
            //Connection
            Connection conn = MyConnection.createConnection();
            PreparedStatement pst;
            ResultSet rs;
            Handler handler = new Handler();
            //Insert Record
            if (callflag.equals("insert")) {
                System.out.println(callflag);
                List<String> list = new ArrayList<>();
                list.add(request.getParameter("fname"));
                list.add(request.getParameter("lname"));
                list.add(request.getParameter("dob"));
                list.add(request.getParameter("gender"));
                list.add(request.getParameter("mno"));
                list.add(request.getParameter("email"));
                out.print(handler.insertData(list));
            } //Delete Record
            else if (callflag.equals("delete")) {
                String recordid = request.getParameter("recordid");
                System.out.println(recordid);
                int result = handler.deleteData(recordid);
                out.print(result + " Record Deleted");
                System.out.print(result + " Record Deleted");
            } //Update Record
            else if (callflag.equals("update")) {
                System.out.println(callflag);
                List<String> list = new ArrayList<>();
                System.out.println(request.getParameter("fname"));
                int id = Integer.parseInt(request.getParameter("uniqueID"));
                System.out.println(Integer.parseInt(request.getParameter("uniqueID")));
                list.add(request.getParameter("fname"));
                System.out.println(request.getParameter("fname"));
                list.add(request.getParameter("lname"));
                list.add(request.getParameter("dob"));
                list.add(request.getParameter("gender"));
                list.add(request.getParameter("mno"));
                list.add(request.getParameter("email"));
                list.add(request.getParameter("UpdateBtn"));
                out.print(handler.updateData(list, id));

            } else if (callflag.equals("search")) {
                Integer id = Integer.parseInt(request.getParameter("id"));

                JsonArray ja = handler.searchData(id);
                out.print(ja);
                System.out.println(ja);
                out.print("Search Method " + id);

            } //Show All Record 
            else if (callflag.equals("showAll")) {
                System.out.println("inside method");
                JsonArray ja = handler.showAll();
                System.out.println("Data Return :: " + new Gson().toJson(ja));
                out.print(ja);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
