package User;

import Data.DBConnector;
import Data.DataAccessObject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import libraryInventory.Item;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private String username;
    private String password;

    public DBConnector conn;
    public DataAccessObject dao;

    public Login() {
        try {
            conn = new DBConnector();
            dao = new DataAccessObject(conn);
        } catch (Exception e) {

        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        HttpSession session = request.getSession();

        try {
            username = request.getParameter("username");
            password = request.getParameter("userpass");

            String uno = dao.getUnoUserPass(username, password);


            if (uno == null) {
                session.setAttribute("loggedIn", false);
                response.sendRedirect("register.html");
            } else {
                session.setAttribute("loggedIn", true);
                session.setAttribute("userLoggedIn", uno);
                ArrayList<Item> inventory = (ArrayList<Item>) request.getAttribute("inventory");
                if (inventory == null) {
                    inventory = dao.getInventory();
                }
                request.setAttribute("inventory", inventory);
                request.getSession().getAttribute("uno");
                request.getRequestDispatcher("/LibraryMain.jsp").forward(request, response);
            }

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
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
            Logger.getLogger(Login.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class
                    .getName()).log(Level.SEVERE, null, ex);
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
