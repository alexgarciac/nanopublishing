package org.annotatorjs.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.annotatorjs.model.Annotation;
import org.annotatorjs.model.Permissions;
import org.annotatorjs.model.Range;
import org.annotatorjs.store.StoreProperties;
import org.annotatorjs.util.BioportalThread;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;





public class Connector { 

	Connection conn = null;
	String DATABASE_HOST;
	String DATABASE_NAME;
	String DATABASE_USER;
	String DATABASE_PASS;



	public Connector() {

		DATABASE_HOST = StoreProperties.getInstance().getProperty("database.host");
		DATABASE_NAME = StoreProperties.getInstance().getProperty("database.name");
		DATABASE_USER = StoreProperties.getInstance().getProperty("database.user");
		DATABASE_PASS = StoreProperties.getInstance().getProperty("database.pass");

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://"+DATABASE_HOST+":3306/"+DATABASE_NAME+"?" +
					"user="+DATABASE_USER+"&password="+DATABASE_PASS);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public ArrayList<Annotation> getAll() {

		ArrayList<Annotation> annotations = new ArrayList<Annotation>();
		Statement stmt = null;
		ResultSet rs = null;

		try {


			stmt = conn.createStatement();

			if (stmt.execute("SELECT * FROM annotations")) {
				rs = stmt.getResultSet();

				while(rs.next()) {

					Annotation annotation =  new Annotation();
					annotation.setId(rs.getString("annotation_id"));
					annotation.setAnnotator_schema_version(rs.getString("annotator_schema_version"));
					annotation.setCreated(rs.getString("created"));
					annotation.setUpdated(rs.getString("updated"));
					annotation.setText(rs.getString("text"));
					annotation.setQuote(rs.getString("quote"));
					annotation.setUri(rs.getString("uri"));
					annotation.setUser(rs.getString("user"));
					annotation.setConsumer(rs.getString("consumer"));

					annotations.add(annotation);

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
		return annotations;
	}

	public ArrayList<Annotation> getAllComplete() {

		return completeAnnotations(getAll());
	}

	public ArrayList<Range> getRangesForAnnotation(String annotation_id){

		ArrayList<Range> ranges = new ArrayList<Range>();
		Statement stmt = null;
		ResultSet rs = null;

		try {


			stmt = conn.createStatement();

			if (stmt.execute("SELECT * FROM ranges WHERE annotation_id=\""+annotation_id+"\"")) {
				rs = stmt.getResultSet();

				while(rs.next()) {

					Range range =  new Range();
					range.setStart(rs.getString("start"));
					range.setEnd(rs.getString("end"));
					range.setStartOffset(rs.getInt("startOffset"));
					range.setEndOffset(rs.getInt("endOffset"));


					ranges.add(range);

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


		return ranges;

	}

	public ArrayList<String> getTagsForAnnotation(String annotation_id){

		ArrayList<String> tags = new ArrayList<String>();
		Statement stmt = null;
		ResultSet rs = null;

		try {


			stmt = conn.createStatement();

			if (stmt.execute("SELECT * FROM tags WHERE annotation_id=\""+annotation_id+"\" AND automatic_tag=0")) {
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

	public Permissions getPermissionsForAnnotation(String annotation_id){

		Permissions permissions =  new Permissions();
		Statement stmt = null;
		ResultSet rs = null;

		try {


			stmt = conn.createStatement();

			if (stmt.execute("SELECT * FROM permissions WHERE annotation_id=\""+annotation_id+"\"")) {
				rs = stmt.getResultSet();

				while(rs.next()) {

					permissions.setAdmin(new ArrayList<String>(Arrays.asList(rs.getString("admin_permission").split("\\s*,\\s*"))));
					permissions.setDelete(new ArrayList<String>(Arrays.asList(rs.getString("delete_permission").split("\\s*,\\s*"))));
					permissions.setRead(new ArrayList<String>(Arrays.asList(rs.getString("read_permission").split("\\s*,\\s*"))));
					permissions.setUpdate(new ArrayList<String>(Arrays.asList(rs.getString("update_permission").split("\\s*,\\s*"))));

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


		return permissions;

	}

	public ArrayList<Annotation> completeAnnotations(ArrayList<Annotation> annotations){

		ArrayList<Annotation> completeAnnotations = new ArrayList<Annotation>();

		for(Annotation annotation : annotations){

			annotation.setRanges(getRangesForAnnotation(annotation.getId()));
			annotation.setTags(getTagsForAnnotation(annotation.getId()));
			annotation.setPermissions(getPermissionsForAnnotation(annotation.getId()));

			completeAnnotations.add(annotation);
		}

		return completeAnnotations;

	}

	public Annotation completeAnnotation(Annotation annotation){

		Annotation completeAnnotation = annotation;
		completeAnnotation.setRanges(getRangesForAnnotation(annotation.getId()));
		completeAnnotation.setTags(getTagsForAnnotation(annotation.getId()));
		completeAnnotation.setPermissions(getPermissionsForAnnotation(annotation.getId()));



		return completeAnnotation;

	}

	public Annotation getAnnotation(String annotation_id){

		Annotation annotation =  new Annotation();
		Statement stmt = null;
		ResultSet rs = null;

		try {


			stmt = conn.createStatement();

			if (stmt.execute("SELECT * FROM annotations WHERE annotation_id=\""+annotation_id+"\"")) {
				rs = stmt.getResultSet();

				while(rs.next()) {


					annotation.setId(rs.getString("annotation_id"));
					annotation.setAnnotator_schema_version(rs.getString("annotator_schema_version"));
					annotation.setCreated(rs.getString("created"));
					annotation.setUpdated(rs.getString("updated"));
					annotation.setText(rs.getString("text"));
					annotation.setQuote(rs.getString("quote"));
					annotation.setUri(rs.getString("uri"));
					annotation.setUser(rs.getString("user"));
					annotation.setConsumer(rs.getString("consumer"));

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


		return annotation;



	}

	public Annotation getCompleteAnnotation(String annotation_id){

		return completeAnnotation(getAnnotation(annotation_id));

	}

	public void deleteAnnotation(String annotation_id){

		String query = "DELETE FROM annotations WHERE annotation_id=?";
		PreparedStatement preparedStmt = null;

		try
		{
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, annotation_id);

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

	public void updateAnnotation(Annotation annotation){


		String query = "UPDATE annotations SET text=?  WHERE annotation_id=?";
		PreparedStatement preparedStmt = null;

		try
		{
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, annotation.getText());
			preparedStmt.setString(2, annotation.getId());

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


		if(annotation.getTags()!=null)
		{
			deleteTags(annotation.getId());
			createTags(annotation.getId(), annotation.getTags());
		}

		if(annotation.getPermissions()!=null)
		{
			deletePermissions(annotation.getId());
			createPermissions(annotation.getId(), annotation.getPermissions());
		}





	}

	public String createAnnotation(Annotation annotation){


		String query = "INSERT INTO annotations(annotation_id, annotator_schema_version, text, quote, uri, user, consumer) "
				+ "VALUES (?,?,?,?,?,?,?)";
		PreparedStatement preparedStmt = null;
		String uuid = UUID.randomUUID().toString().replace("-", "");


		try
		{
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, uuid);
			preparedStmt.setString(2, "v0.0");
			preparedStmt.setString(3, annotation.getText());
			preparedStmt.setString(4, annotation.getQuote());
			preparedStmt.setString(5, annotation.getUri());
			preparedStmt.setString(6, annotation.getUser());
			preparedStmt.setString(7, "annotationjavastore");


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

		createRanges(uuid,annotation.getRanges());
		if(annotation.getTags()!=null)
			createTags(uuid, annotation.getTags());
		if(annotation.getPermissions()!=null)
			createPermissions(uuid, annotation.getPermissions());
		
		BioportalThread bioThread = new BioportalThread(this.conn, uuid, annotation.getQuote());
		Thread thread = new Thread(bioThread);
		thread.start();
		
		return uuid;




	}


	private void createPermissions(String id, Permissions permissions) {

		String query = "INSERT INTO permissions(annotation_id, read_permission, admin_permission, update_permission, delete_permission) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement preparedStmt = null;


		try
		{
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, StringUtils.join(permissions.getRead(),","));
			preparedStmt.setString(3, StringUtils.join(permissions.getAdmin(),","));
			preparedStmt.setString(4, StringUtils.join(permissions.getUpdate(),","));
			preparedStmt.setString(5, StringUtils.join(permissions.getDelete(),","));


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




	private void createTags(String id, ArrayList<String> tags) {

		PreparedStatement preparedStmt = null;

		for(String tag : tags)
			try
		{
				String query = "INSERT INTO tags(annotation_id, tag_label) "
						+ "VALUES (?,?)";
				// create the mysql database connection

				// create the mysql delete statement.
				// i'm deleting the row where the id is "3", which corresponds to my
				// "Barney Rubble" record.

				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.setString(2, tag);



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


	private void createRanges(String id, ArrayList<Range> ranges) {


		PreparedStatement preparedStmt = null;

		for(Range range: ranges)
			try
		{
				String query = "INSERT INTO ranges(annotation_id, start, end, startOffset, endOffset) "
						+ "VALUES (?,?,?,?,?)";
				// create the mysql database connection

				// create the mysql delete statement.
				// i'm deleting the row where the id is "3", which corresponds to my
				// "Barney Rubble" record.

				preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, id);
				preparedStmt.setString(2, range.getStart());
				preparedStmt.setString(3, range.getEnd());
				preparedStmt.setInt(4, range.getStartOffset());
				preparedStmt.setInt(5, range.getEndOffset());



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

	public void deleteTags(String annotation_id){

		String query = "DELETE FROM tags WHERE annotation_id=?";
		PreparedStatement preparedStmt = null;

		try
		{
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, annotation_id);

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

	public void deleteRanges(String annotation_id){

		String query = "DELETE FROM ranges WHERE annotation_id=?";
		PreparedStatement preparedStmt = null;

		try
		{
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, annotation_id);

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

	public void deletePermissions(String annotation_id){

		String query = "DELETE FROM permissions WHERE annotation_id=?";
		PreparedStatement preparedStmt = null;

		try
		{
			// create the mysql database connection

			// create the mysql delete statement.
			// i'm deleting the row where the id is "3", which corresponds to my
			// "Barney Rubble" record.

			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, annotation_id);

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


	public ArrayList<Annotation> searchAnnotationsByUri(String uri, int i) {

		ArrayList<Annotation> annotations = new ArrayList<Annotation>();
		Statement stmt = null;
		ResultSet rs = null;

		try {


			stmt = conn.createStatement();
			String query = "SELECT * FROM annotations WHERE uri=\""+uri+"\"";

			if(i>0)
				query+=" LIMIT "+i;

			if (stmt.execute(query)) {
				rs = stmt.getResultSet();

				while(rs.next()) {

					Annotation annotation =  new Annotation();
					annotation.setId(rs.getString("annotation_id"));
					annotation.setAnnotator_schema_version(rs.getString("annotator_schema_version"));
					annotation.setCreated(rs.getString("created"));
					annotation.setUpdated(rs.getString("updated"));
					annotation.setText(rs.getString("text"));
					annotation.setQuote(rs.getString("quote"));
					annotation.setUri(rs.getString("uri"));
					annotation.setUser(rs.getString("user"));
					annotation.setConsumer(rs.getString("consumer"));

					annotations.add(annotation);

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
		return annotations;


	}


	public ArrayList<Annotation> searchAnnotationsByUriComplete(String uri, int i) {
		System.out.println(uri);
		return completeAnnotations(searchAnnotationsByUri(uri,i));

	}


	






}

