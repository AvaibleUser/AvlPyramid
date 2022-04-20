package edu.mooncoder.pyramid.model.contracts;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.stream.Collectors;

public interface ServletTools {
    default String getBody(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining());
    }
}
