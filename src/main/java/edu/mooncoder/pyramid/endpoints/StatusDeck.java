package edu.mooncoder.pyramid.endpoints;

import edu.mooncoder.pyramid.controllers.OutputDeckTreeData;
import edu.mooncoder.pyramid.model.rederers.TreeRenderer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "TreeStatusPath", urlPatterns = {"/status-avltree"})
public class StatusDeck extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("{\"path\":\"/Game/tree.jpg\"}");
    }
}
