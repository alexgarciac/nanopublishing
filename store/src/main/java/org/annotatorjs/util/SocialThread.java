package org.annotatorjs.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	Annotation annotation;
	Connection conn;
	String uuid;



	public SocialThread(Connection conn, String uuid, Annotation annotation) {
		this.annotation = annotation;
		this.conn = conn;
		this.uuid=uuid;
	}

	private void sendToNanotateFacebook() {
		facebook4j.conf.ConfigurationBuilder cb = new facebook4j.conf.ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthAppId("1427521897511957")
		.setOAuthAppSecret("b58edb7c40ded996479db0f617f8ad04");

		Facebook facebook = new FacebookFactory(cb.build()).getInstance();
//		facebook.setOAuthAccessToken(new AccessToken("CAADW5nmJZCZCcBAIdV6nJtvvufeVlILOWvPC2h3"
//				+ "ro6V2Nk7eOPNPufpAjZAZCkQwgVkzO82C4tIZAzjMeOFMUJm1FkgAV9I96ZCeA5azCg9JX0wmZC"
//				+ "5QMpxDxgabKLnDHzRxZBaS5CX4EYoiAk7bCDIPV6ZBlRZCrIQ5TtiQLINO22N8ZAkyNbFZC1lK",null));
		facebook.setOAuthAccessToken(new AccessToken("CAAUSUtecPBUBANcs6LwugtHweJXHc5e9W6UnVx4SclxiNJDR"
				+ "XIZBmEywMUDuwb8JzZAC0vR2kF5KM7ZCbofY15iwXjAuP0gyezOtPJz7NJPDb8LRUW8HtJ4mxradJKX4lTMvw"
				+ "SdXmKfe2mmysZAeP6z8SxZATPx1HBZB8BxaGZACpDzZCBZAmTT20BSltuDUw7wrGJnVP18dHWAZDZD",null));

		//    	.setOAuthAppId("1427521897511957")
		//	  .setOAuthAppSecret("9a447eb931f93131af68176c006a1a39")
		//	  .setOAuthPermissions("public_profile,user_friends,email,publish_stream,publish_actions");


		try{
			String data = facebook.postStatusMessage(annotation.getQuote()+"\n from: "+annotation.getUri());
			Post post = facebook.getPost(data.split("_")[1]);

			if(!StringUtils.isEmpty(annotation.getText()))
			{	
				facebook.commentPost(post.getId(), annotation.getUser()+" said: "+annotation.getText());
			}

			if(!data.equals(""))
			{
			  this.createFacebookPost("https://www.facebook.com/nanotateapp/posts/"+data.split("_")[1]);
			}
		}
		catch(FacebookException e){
			e.printStackTrace();
		}


	}

	private void sendToNanotateTwitter() {



		twitter4j.conf.ConfigurationBuilder cb = new 	twitter4j.conf.ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("x8P2dt3hnTVcVDaq21smdfLf0")
		.setOAuthConsumerSecret("VfC0A2FiI3Uq1v4NCRJktCpElFgQT7Ri0mxu6E9YsMQuEnpigL");

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

//		twitter.setOAuthAccessToken(new twitter4j.auth.AccessToken("2326910304-Dpi1MBlPBq37KyUxBmd03jkkFxhXLzffds1cG5X",
//				"iCmxAjp0XTtmgpuoV7fKItPajXzqTJkj9jetGLTcQnEOG"));
		twitter.setOAuthAccessToken(new twitter4j.auth.AccessToken("2326910304-oss3EGjWxQZ1W6nChG9VxMfHypcVt2WEJBSisFm",
				"7cc75WMlDw8Q1FLx6SsaryrmBQymCKBChs4ellSOmZgG4"));


		ArrayList<String> tags =this.getAllTagsForAnnotation();

		String message = "";

		for (String s : tags)
		{
			message += s + ", ";
		}

		if(message.length()>105)
			message=message.substring(0,100)+"...";


		try {
			Status data = twitter.updateStatus(message.toLowerCase().substring(0,message.lastIndexOf(","))+" #Nanotate "+annotation.getUri());
			this.createTwitterPost("http://www.twitter.com/"+data.getUser().getScreenName()+"/"+data.getId());
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void createFacebookPost(String url) {

		PreparedStatement preparedStmt = null;


		try
		{
			String query = "INSERT INTO facebook_posts(annotation_id, facebook_post_url) "
					+ "VALUES (?,?)";
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, uuid);
			preparedStmt.setString(2, url);



			// execute the preparedstatement
			preparedStmt.execute();


		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (preparedStmt != null) {
				try {
					preparedStmt.close();
				} catch (SQLException sqlEx) { } // ignore

				preparedStmt = null;
			}


		}

	}
	
	private void createTwitterPost(String url) {

		PreparedStatement preparedStmt = null;


		try
		{
			String query = "INSERT INTO twitter_posts(annotation_id, twitter_post_url) "
					+ "VALUES (?,?)";
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1,uuid);
			preparedStmt.setString(2, url);



			// execute the preparedstatement
			preparedStmt.execute();


		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (preparedStmt != null) {
				try {
					preparedStmt.close();
				} catch (SQLException sqlEx) { } // ignore

				preparedStmt = null;
			}


		}

	}
	
	
	public ArrayList<String> getAllTagsForAnnotation(){

		ArrayList<String> tags = new ArrayList<String>();
		Statement stmt = null;
		ResultSet rs = null;

		try {


			stmt = conn.createStatement();

			if (stmt.execute("SELECT * FROM tags WHERE annotation_id=\""+uuid+"\"")) {
				rs = stmt.getResultSet();

				while(rs.next()) {

					String tag = rs.getString("tag_label");


					tags.add(tag);

				}

			}


			// Do something with the Connection

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) { } // ignore

				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) { } // ignore

				stmt = null;
			}


		}


		return tags;

	}


	@Override
	public void run() {
		this.sendToNanotateFacebook();
		this.sendToNanotateTwitter();

	}

}
