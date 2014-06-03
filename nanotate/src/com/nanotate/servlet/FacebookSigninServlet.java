package com.nanotate.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.conf.ConfigurationBuilder;

public class FacebookSigninServlet extends HttpServlet {
    private static final long serialVersionUID = -7453606094644144082L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ConfigurationBuilder cb = new ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthAppId("236285369909239")
    	  .setOAuthAppSecret("df356774233762558c63281d90d21368")
    	  .setOAuthPermissions("basic_info,email,publish_stream");
//    	  .setOAuthAppId("1427521897511957")
//    	  .setOAuthAppSecret("9a447eb931f93131af68176c006a1a39")
//    	  .setOAuthPermissions("public_profile,user_friends,email,publish_stream,publish_actions");
    	Facebook facebook = new FacebookFactory(cb.build()).getInstance();
        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/facebookcallback");
        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
    }
}
