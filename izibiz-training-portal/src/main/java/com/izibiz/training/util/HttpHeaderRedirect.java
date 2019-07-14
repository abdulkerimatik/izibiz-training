package com.izibiz.training.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpHeaderRedirect extends HttpServletResponseWrapper {

	String applicationUrl = "";
	String port = "";

	public HttpHeaderRedirect(HttpServletRequest request, HttpServletResponse response) {
		super(response);
		applicationUrl = request.getServerName();
		port = request.getServerPort() + "";
	}


	@Override
	public void sendRedirect(String location) throws IOException {
		if(!location.startsWith("http"))
			location = "https://"+applicationUrl + ":" + port + location;
		location = location.replaceFirst("http:", "https:");
		super.sendRedirect(location);
	}

}
