package Reservation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Data.DBConnector;
import Data.DataAccessObject;
import User.Login;
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
import libraryInventory.Item;

/**
 *
 * @author brein
 */
@WebServlet(urlPatterns = {"/DeleteReservation"})
public class DeleteReservation extends HttpServlet {

    public DBConnector conn;
    public DataAccessObject dao;

    public DeleteReservation() {
        try {
            conn = new DBConnector();
            dao = new DataAccessObject(conn);
        } catch (Exception e) {

        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Item> reserved = (ArrayList<Item>) request.getAttribute("reserved");
        String uno = (String) request.getSession().getAttribute("userLoggedIn");
        reserved = dao.getReserved(uno);

        if (reserved == null) {
            reserved = dao.getInventory();
        }

        request.setAttribute("reserved", reserved);
        request.getRequestDispatcher("/Reserved.jsp").forward(request, response);

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
        String ino = request.getParameter("ino");
        String uno = (String) request.getSession().getAttribute("userLoggedIn");
        dao.deleteReservation(ino, uno);
        dao.rescountDown(uno);
        processRequest(request, response);
        response.sendRedirect("Reserved.jsp");

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
