package edu.mooncoder.pyramid.endpoints;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TreeStatusPath", urlPatterns = {"/status-avltree"})
public class StatusDeck extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("{\"path\":\"/Game/tree.jpg\"}");
    }
}
