package edu.mooncoder.pyramid.endpoints;

import com.google.gson.JsonSyntaxException;
import edu.mooncoder.pyramid.controllers.JsonToDeckSet;
import edu.mooncoder.pyramid.exceptions.ExceptionWithStatus;
import edu.mooncoder.pyramid.model.contracts.ServletTools;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteCard", urlPatterns = {"/delete"})
public class DeleteCard extends HttpServlet implements ServletTools {
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = getBody(request);

        try {
            JsonToDeckSet.getInstance().deleteCards(json);
        } catch (ExceptionWithStatus e) {
            response.sendError(e.getStatus(), e.getMessage());
        } catch (JsonSyntaxException e) {
            response.sendError(400, "El json enviado tiene errores, verificar a continuacion:\n\n" + e.getMessage());
        }
    }
}
