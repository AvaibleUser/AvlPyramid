package edu.mooncoder.pyramid.endpoints;

import edu.mooncoder.pyramid.controllers.OutputDeckTreeData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ShowLevel", urlPatterns = {"/get-level"})
public class ShowLevel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int level = Integer.parseInt(request.getParameter("level"));
            response.getWriter().print(OutputDeckTreeData.getDeckLevelAsJson(level));
        } catch (NullPointerException e) {
            response.sendError(400, "No se encontro el parametro 'level'.");
        } catch (NumberFormatException e) {
            response.sendError(400, "El nivel enviado en los parametros no se reconocio como un entero.");
        }
    }
}
