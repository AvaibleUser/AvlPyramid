package edu.mooncoder.pyramid.endpoints;

import edu.mooncoder.pyramid.controllers.DeckTreeManager;
import edu.mooncoder.pyramid.controllers.OutputDeckTreeData;
import edu.mooncoder.pyramid.model.enums.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ShowDeck", urlPatterns = {"/avltree"})
public class ShowDeck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String transversal = request.getParameter("transversal");

        try {
            Order order = Order.valueOf(transversal.toUpperCase());

            response.getWriter().print(OutputDeckTreeData.getDeckAsJson(order));
        } catch (NullPointerException e) {
            response.sendError(400, "No se encontro el parametro 'transversal'.");
        }
    }
}
