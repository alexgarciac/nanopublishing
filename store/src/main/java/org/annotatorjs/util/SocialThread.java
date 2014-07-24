package org.annotatorjs.util;

import org.annotatorjs.model.Annotation;
import org.apache.commons.lang3.StringUtils;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import facebook4j.auth.OAuthSupport;

public class SocialThread implements Runnable {
	
	private void sendToNanotateFacebook(Annotation annotation) {
		facebook4j.conf.ConfigurationBuilder cb = new facebook4j.conf.ConfigurationBuilder();
    	cb.setDebugEnabled(true)
    	  .setOAuthAppId("1427521897511957")
    	  .setOAuthAppSecret("b58edb7c40ded996479db0f617f8ad04");
    	
    	Facebook facebook = new FacebookFactory(cb.build()).getInstance();
    	facebook.setOAuthAccessToken(new AccessToken("CAADW5nmJZCZCcBAIdV6nJtvvufeVlILOWvPC2h3ro6V2Nk7eOPNPufpAjZAZCkQwgVkzO82C4tIZAzjMeOFMUJm1FkgAV9I96ZCeA5azCg9JX0wmZC5QMpxDxgabKLnDHzRxZBaS5CX4EYoiAk7bCDIPV6ZBlRZCrIQ5TtiQLINO22N8ZAkyNbFZC1lK",null));
    	
//    	.setOAuthAppId("1427521897511957")
//	  .setOAuthAppSecret("9a447eb931f93131af68176c006a1a39")
//	  .setOAuthPermissions("public_profile,user_friends,email,publish_stream,publish_actions");
    	
		  
		try{
		String data = facebook.postStatusMessage(annotation.getQuote()+"\n from: "+annotation.getUri());
        Post post = facebook.getPost(data.split("_")[1]);
        
        if(StringUtils.isEmpty(annotation.getText()))
        {	
        	
        	
				

        }
        
        if(!data.equals(""))
        {
        	
        }
	}
		catch(FacebookException e){
			e.printStackTrace();
		}
		
		
	}

	private void sendToNanotateTwitter(Annotation annotation) {

     
	
		twitter4j.conf.ConfigurationBuilder cb = new 	twitter4j.conf.ConfigurationBuilder();
        cb.setDebugEnabled(true)
    .setOAuthConsumerKey("x8P2dt3hnTVcVDaq21smdfLf0")
    .setOAuthConsumerSecret("VfC0A2FiI3Uq1v4NCRJktCpElFgQT7Ri0mxu6E9YsMQuEnpigL");
        
        TwitterFactory tf = new TwitterFactory(cb.build());
       Twitter twitter = tf.getInstance();
     
		twitter.setOAuthAccessToken(new twitter4j.auth.AccessToken("2326910304-Dpi1MBlPBq37KyUxBmd03jkkFxhXLzffds1cG5X","iCmxAjp0XTtmgpuoV7fKItPajXzqTJkj9jetGLTcQnEOG"));
       
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
