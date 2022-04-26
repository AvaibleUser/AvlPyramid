package edu.mooncoder.pyramid.endpoints;

import edu.mooncoder.pyramid.controllers.OutputDeckTreeData;
import edu.mooncoder.pyramid.model.rederers.TreeRenderer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ImagePlaceholder", urlPatterns = {"/tree.jpg"})
public class ImagePlaceholder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputDeckTreeData.renderDeckAsImage();
        TreeRenderer renderer = TreeRenderer.getInstance();

        response.setContentType("image/jpeg");
        renderer.writeAll(response.getOutputStream());
    }
}
