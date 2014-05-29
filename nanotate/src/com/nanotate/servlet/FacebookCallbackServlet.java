package com.nanotate.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import utils.JsonEncoder;

import com.nanotate.dao.model.DocumentMapper;
import com.nanotate.dao.model.User;
import com.nanotate.dao.model.UserExample;
import com.nanotate.dao.model.UserMapper;
import com.nanotate.dao.util.MyBatis;
import com.nanotate.message.JsonResponse;

import facebook4j.Facebook;
import facebook4j.FacebookException;


public class FacebookCallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 6305643034487441839L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String oauthCode = request.getParameter("code");
        String redirect = "";
        JsonResponse r = new JsonResponse();
        try {
            facebook.getOAuthAccessToken(oauthCode);
            HttpSession session = request.getSession();
            facebook4j.User user = (facebook4j.User) facebook.getUser(facebook.getId());
            String username = user.getUsername();
            String email=user.getEmail();
            String name= user.getFirstName()+" "+user.getLastName();
			SqlSession sqlSession = MyBatis.getSession();
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			UserExample userex = new UserExample();
			userex.createCriteria().andFacebook_usernameEqualTo(username);
			List<User> users = mapper.selectByExample(userex);
			if(users.size()<=0){
				redirect="/register.html?"+"username="+username+"&email="+email+"&name="+URLEncoder.encode(name,"UTF-8");
				r.setData(username);
			}
			else{
				redirect="/index.html";
	    		session.setAttribute("user", username);
//	    		session.setAttribute("imgurl", user.getPicture().getURL());
	    		//setting session to expiry in 30 mins
	    		session.setMaxInactiveInterval(30*60);
	    		Cookie userName = new Cookie("user", username);
	    		userName.setMaxAge(30*60);
	    		response.addCookie(userName);
			}
			
        } catch (Exception e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + redirect);
    }
}
