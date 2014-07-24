package org.annotatorjs.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;


	public class BioportalThread implements Runnable{
		
		String text;
		String uuid;
		Connection conn;
		public static final String annotatorUrl = "http://data.bioontology.org/annotator?";
				
		public BioportalThread(Connection conn, String uuid, String text){
			
			this.text=text;
			this.uuid=uuid;
			this.conn=conn;
		}

		@Override
		public void run() {
			
			String result = getBioportalAnnotations(text);
			ArrayList<String> array = getTagsFromJSON(result);
			this.createTags(uuid, array);
			
		}
		
		public String  getBioportalAnnotations(String text){

			StringBuffer result = new StringBuffer();

			try {

				HttpClient client =  new DefaultHttpClient();

				String nameValuePairs = "";
				// Configure the form parameters
				nameValuePairs =  nameValuePairs.concat("longest_only=true");
				nameValuePairs =  nameValuePairs.concat("&whole_word_only=true");
				nameValuePairs =  nameValuePairs.concat("&exclude_numbers=true");
				//method.appendParameter("stop_words=I,a,above,after,against,all,alone,always,am,amount,an,and,any,are,around,as,at,back,be,before,behind,below,between,bill,both,bottom,by,call,can,co,con,de,detail,do,done,down,due,during,each,eg,eight,eleven,empty,ever,every,few,fill,find,fire,first,five,for,former,four,from,front,full,further,get,give,go,had,has,hasnt,he,her,hers,him,his,i,ie,if,in,into,is,it,last,less,ltd,many,may,me,mill,mine,more,most,mostly,must,my,name,next,nine,no,none,nor,not,nothing,now,of,off,often,on,once,one,only,or,other,others,out,over,part,per,put,re,same,see,serious,several,she,show,side,since,six,so,some,sometimes,still,take,ten,then,third,this,thick,thin,three,through,to,together,top,toward,towards,twelve,two,un,under,until,up,upon,us,very,via,was,we,well,when,while,who,whole,will,with,within,without,you,yourself,yourselves");
				//        nameValuePairs =  nameValuePairs.concat("&withDefaultStopWords=true");
				//        nameValuePairs =  nameValuePairs.concat("&isTopWordsCaseSensitive=false");
				nameValuePairs =  nameValuePairs.concat("&minimum_match_length=3");
				//        nameValuePairs =  nameValuePairs.concat("&scored=true");
				nameValuePairs =  nameValuePairs.concat("&include_synonyms=true"); 
				nameValuePairs =  nameValuePairs.concat("&ontologies=APAONTO");
				//        nameValuePairs =  nameValuePairs.concat("&ontologiesToKeepInResult="); 
				//        nameValuePairs =  nameValuePairs.concat("&isVirtualOntologyId=true"); 
				nameValuePairs =  nameValuePairs.concat("&semantic_types="); 
				nameValuePairs =  nameValuePairs.concat("&max_level=0");
				nameValuePairs =  nameValuePairs.concat("&mappings=true"); //null, Automatic, Manual 
				nameValuePairs =  nameValuePairs.concat("&text="+ URLEncoder.encode(text, "UTF-8"));  //"Melanoma is a malignant tumor of melanocytes which are found predominantly in skin but also in the bowel and the eye");
				nameValuePairs =  nameValuePairs.concat("&format=json"); //Options are 'text', 'xml', 'tabDelimited'   
				nameValuePairs =  nameValuePairs.concat("&apikey=028697e9-f8b5-4086-a252-a446b60b232b");
				System.out.println(annotatorUrl+nameValuePairs);
				HttpGet  method = new HttpGet(annotatorUrl+nameValuePairs);
				// Execute the POST method


				//        BufferedReader rd = new BufferedReader(
				//    			new InputStreamReader(method.getEntity().getContent()));
				//    	 
				//    		StringBuffer result = new StringBuffer();
				//    		String line = "";
				//    		while ((line = rd.readLine()) != null) {
				//    			result.append(line);
				//    			result.append("\n");
				//    		}
				//    		System.out.println(result);

				HttpResponse statusCode = client.execute(method);

				if( statusCode.getStatusLine().getStatusCode() != -1 ) {
					BufferedReader rd = new BufferedReader(
							new InputStreamReader(statusCode.getEntity().getContent()));


					String line = "";
					while ((line = rd.readLine()) != null) {
						result.append(line);
						result.append("\n");
					}
					System.out.println(result);
				}
			}
			catch( Exception e ){
				e.printStackTrace();
				return "";
			}
			return result.toString();

		}

		public ArrayList<String> getTagsFromJSON(String result)
		{

			String ret="";



			JSONArray jsonObjects = new JSONArray(result);
			for( int i=0; i<jsonObjects.length(); i++)
			{
				JSONObject jobject = jsonObjects.getJSONObject(i);
				JSONArray annotations = (JSONArray) jobject.get("annotations");

				for( int j=0; j<annotations.length(); j++)
				{
					JSONObject jsonAnnotation=annotations.getJSONObject(j);
					String tag = (String) jsonAnnotation.get("text");
					if(!ret.contains(tag))
						ret+=tag+",";
				}



			}
			if(ret!="")
				ret=ret.substring(0, ret.length()-1);
			System.out.println(ret);
			
			ArrayList<String> array = new ArrayList<String>();
			array.addAll( Arrays.asList(ret.split(",")));
			
			return array;
		}
		
		private void createTags(String id, ArrayList<String> tags) {

			PreparedStatement preparedStmt = null;

			for(String tag : tags)
				try
			{
					String query = "INSERT INTO tags(annotation_id, tag_label, automatic_tag) "
							+ "VALUES (?,?,?)";
					// create the mysql database connection

					// create the mysql delete statement.
					// i'm deleting the row where the id is "3", which corresponds to my
					// "Barney Rubble" record.

					preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, id);
					preparedStmt.setString(2, tag);
					preparedStmt.setBoolean(3, true);



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
		
	}