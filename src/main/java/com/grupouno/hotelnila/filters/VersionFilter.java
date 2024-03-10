package com.grupouno.hotelnila.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VersionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String apiVersion = req.getHeader("X-API-VERSION");

        if (apiVersion == null || (!apiVersion.equals("1") && !apiVersion.equals("2"))) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid API Version");
            return;
        }

        chain.doFilter(request, response);
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

    // Otros métodos...
}