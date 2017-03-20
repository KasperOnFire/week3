package User;

import Data.DBConnector;
import Data.DataAccessObject;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "registeruser", urlPatterns = {"/registeruser"})
public class registeruser extends HttpServlet {

    private String uno;
    private String username;
    private String password;
    private String address;
    private String phone;
    private String mail;
    private String ssn;

    public DBConnector conn;
    public DataAccessObject dao;

    public registeruser() {
        try {
            conn = new DBConnector();
            dao = new DataAccessObject(conn);
        } catch (Exception e) {

        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            username = request.getParameter("username");
            password = request.getParameter("userpass");
            address = request.getParameter("useraddress");
            phone = request.getParameter("userphone");
            mail = request.getParameter("usermail");
            ssn = request.getParameter("userssn");
            uno = dao.registerUser(username, password, address, phone, mail, ssn);
            if (uno != null) {
                session.setAttribute("loggedIn", true);
                session.setAttribute("userLoggedIn", uno);
                request.getRequestDispatcher("LibraryServlet").forward(request, response);
            };
        } catch (Exception e) {
            System.out.println("ERROR RegisterUser:");
            e.printStackTrace();
            request.getRequestDispatcher("index.html").forward(request, response);
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
