package org.nanotate.util;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.ibatis.session.SqlSession;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nanotate.Nanotate_Properties;
import org.nanotate.Nanotate_UI;
import org.nanotate.model.Document;
import org.nanotate.model.DocumentMapper;
import org.nanotate.model.User;
import org.nanotate.model.UserExample;
import org.nanotate.model.UserMapper;

import com.ibm.icu.util.Calendar;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
 
public class FileUtils implements Runnable{
	
	private File file;
	private String path;
	private String docid;
	private String docuuid;
	private String doi;
	private Panel progress;
	private Nanotate_UI nanotate_UI;
	private ProgressBar progress2;
	private Document document;
	private String username;
	private SQLContainer container;

	
	
	
	public FileUtils( SQLContainer container, String docuuid, String username, File file, String path, Panel progress, ProgressBar progress2, Nanotate_UI nanotate_UI) {
		this.file = file;
		this.path = path;
		this.progress = progress;
		this.progress2 = progress2;
		this.nanotate_UI = nanotate_UI;
		this.doi="";
		this.username=username;
		this.docuuid=docuuid;
		this.container=container;
		this.document = new Document();
	}

	public String sendPDFToBoxView(){
		
		docid="";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost uploadFile = new HttpPost("https://upload.view-api.box.com/1/documents");
		uploadFile.setHeader("Authorization","Token 7tpuie82v51mlnfemhs76f28lid4gzof");

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
		builder.addBinaryBody("file", file, ContentType.APPLICATION_OCTET_STREAM, "file.ext");
		HttpEntity multipart = builder.build();

		uploadFile.setEntity(multipart);

		HttpResponse response;
		try {
			response = httpClient.execute(uploadFile);
			HttpEntity responseEntity = response.getEntity();
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(responseEntity.getContent(), writer, "UTF8");
			String theString = writer.toString();
			JSONObject json = new JSONObject(theString);
			docid=json.getString("id");
			System.out.println(theString+" "+docid);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return docid;
		
	}
	
	public void downloadHTML(){
		
	
		while(true){
			
			
			
			try {
				if(StringUtils.equalsIgnoreCase(getDocStatus(), "done")){
					
					BufferedInputStream in = null;
					FileOutputStream fout = null;
					try
					{                                                                                                 
						URL url = new URL("https://view-api.box.com/1/documents/"+docid+"/content.zip");
						URLConnection conn = url.openConnection();
						conn.addRequestProperty("User-Agent", 
							    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
						conn.addRequestProperty("Authorization", 
							    "Token 7tpuie82v51mlnfemhs76f28lid4gzof");

						in = new BufferedInputStream(conn.getInputStream());
						fout = new FileOutputStream(path+"/content.zip");
						byte data[] = new byte[1024];
						int count;
						while ((count = in.read(data, 0, 1024)) != -1)
						{
						 fout.write(data, 0, count);
						}
					}
					finally
					{
						if (in != null)
						in.close();
						if (fout != null)
						fout.close();
					}   
					
					break;
					
				}
			else if(StringUtils.equalsIgnoreCase(getDocStatus(), "error"))
			{
				break;
			}
			else
			{
				try {
				    Thread.sleep(5000);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
				break;
			}
			
			
		}
		
 
	}
	
	public String getDocStatus() throws Exception {
		 
		String url = "https://view-api.box.com/1/documents/"+docid;
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Authorization", "Token 7tpuie82v51mlnfemhs76f28lid4gzof");
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		JSONObject json = new JSONObject(response.toString());
		System.out.println("Response status : " + json.getString("status"));
		return json.getString("status");
		
 
	}
	
	public void unZipIt(){
		 
	     byte[] buffer = new byte[1024];
	 
	     try{
	 
	    	//create output directory is not exists
	    	File folder = new File(path);
	    	if(!folder.exists()){
	    		folder.mkdir();
	    	}
	 
	    	//get the zip file content
	    	ZipInputStream zis = 
	    		new ZipInputStream(new FileInputStream(path+"/content.zip"));
	    	//get the zipped file list entry
	    	ZipEntry ze = zis.getNextEntry();
	 
	    	while(ze!=null){
	 
	    	   String fileName = ze.getName();
	           File newFile = new File(path + File.separator + fileName);
	 
	           System.out.println("file unzip : "+ newFile.getAbsoluteFile());
	 
	            //create all non exists folders
	            //else you will hit FileNotFoundException for compressed folder
	            new File(newFile.getParent()).mkdirs();
	 
	            FileOutputStream fos = new FileOutputStream(newFile);             
	 
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	 
	            fos.close();   
	            ze = zis.getNextEntry();
	    	}
	 
	        zis.closeEntry();
	    	zis.close();
	 
	    	System.out.println("Done");
	 
	    }catch(IOException ex){
	       ex.printStackTrace(); 
	    }
	   }
	
	public void getDoi(){
		
		PDDocument pdDoc;
		try {
			pdDoc = PDDocument.load(file);
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(1);
			stripper.setEndPage(1);
			String text=stripper.getText(pdDoc);
			int doi_begin_index=0;
			
				if(text.indexOf("DOI: http://dx.doi.org/")>0)
					doi_begin_index=text.indexOf("DOI: http://dx.doi.org/")+23;
				else if(text.indexOf("doi: http://dx.doi.org/")>0)
					doi_begin_index=text.indexOf("doi: http://dx.doi.org/")+23;
				else if(text.indexOf("http://dx.doi.org/")>0)
					doi_begin_index=text.indexOf("http://dx.doi.org/")+18;
				else if(text.indexOf("DOI: ")>0)
					doi_begin_index=text.indexOf("DOI: ")+5;
				else if(text.indexOf("doi: ")>0)
					doi_begin_index=text.indexOf("doi: ")+5;
				else
					doi_begin_index=-1;
				
			if(doi_begin_index>0){
				int doi_end_index=-1;
				if(text.indexOf(" ", doi_begin_index)>0 ){
					doi_end_index=text.indexOf(" ", doi_begin_index);
				}	
				else if(text.indexOf("\n", doi_begin_index)>0)
					doi_end_index=text.indexOf("\n", doi_begin_index);
				
				
				System.out.println("text= "+ text.substring(doi_begin_index, doi_begin_index+17));
				System.out.println("text.indexOf"+text.indexOf("\n", doi_begin_index));
				System.out.println("doi_begin_index= "+ doi_begin_index);
				System.out.println("doi_end_index= "+ doi_end_index);
				
				doi= text.substring(doi_begin_index, doi_end_index)
						.replace("\n", "")
						.replace(" ", "")
						.replace("\r", "")
						.replace("\f", "");
			}
			else
			{
				PDDocumentInformation pdinfo = pdDoc.getDocumentInformation();
				if(pdinfo!=null)
				{
					document.setTitle(pdinfo.getTitle());
					document.setYear(pdinfo.getCreationDate().get(Calendar.YEAR));
				}
				
			}
			pdDoc.close();
			
		} catch (IOException e) {
			doi="";
			e.printStackTrace();
		}
		
		
	}
	
	private void registerDoc() {
		SqlSession sqlSession;
		try {
			sqlSession = MyBatis.getSession();
			DocumentMapper mapper = sqlSession.getMapper(DocumentMapper.class);
			mapper.insert(document);	
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	
	public void getDocData(){
		String url;
		document = new Document();
		document.setUploaded_by(username);
		document.setDoc_uuid(docuuid);
		try {
			url = "http://search.crossref.org/dois?q="+URLEncoder.encode(doi,"UTF-8");
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 
			// optional default is GET
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept-Charset", "UTF-8");
			con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
	 
	 
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			//print result
			JSONArray json = new JSONArray(response.toString());
			if(json!=null)
			for( int i=0; i<json.length(); i++){
				
				JSONObject jobject = json.getJSONObject(i);
				document.setDoi(doi);
				document.setTitle(jobject.getString("title"));
				document.setFull_citation(jobject.getString("fullCitation"));
				document.setYear(jobject.getInt("year"));
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
	}
	
	
	private void generateIndex() {
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(path+"/var.part", "UTF-8");
			writer.println("<script type=\"text/javascript\">");
			writer.println("var host='"+Nanotate_Properties.getInstance().getProperty("host")+"';");
			writer.println("var docurl=host+'/repository/"+docuuid+"/assets/';");
			if(StringUtils.isNotEmpty(doi))
				writer.println("var docuri='"+doi+"';");
			else
				writer.println("var docuri=host+'/repository/"+docuuid+"/index.html';");
			writer.println("var username='"+username+"';");
			writer.println("</script>");
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		File index = new File(path+"/index.html") ;
		File var = new File(path+"/var.part");
		File header = new File(Nanotate_Properties.getInstance().getProperty("repository.dir")+"index0.part");
		File body = new File(Nanotate_Properties.getInstance().getProperty("repository.dir")+"index1.part");
		
		
			try {
				if(!index.exists())
					index.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		File[] files = {header, var, body};
		
		mergeFiles(files, index);
		
		var.delete();
		
	}
	
	public void mergeFiles(File[] files, File mergedFile) {
		 
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(mergedFile, true);
			 out = new BufferedWriter(fstream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 
		for (File f : files) {
			System.out.println("merging: " + f.getName());
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));
 
				String aLine;
				while ((aLine = in.readLine()) != null) {
					out.write(aLine);
					out.newLine();
				}
 
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
 
	@Override
    public void run() {
		
		nanotate_UI.access(new Runnable() {
            @Override
            public void run() {
                // Here the UI is locked and can be updated
            	progress2.setVisible(true);
            	progress.setVisible(true);
            	
            	
            }
        });
		
		this.sendPDFToBoxView();
		this.downloadHTML();
		this.unZipIt();
		this.getDoi();
		this.getDocData();
		this.registerDoc();
		this.generateIndex();
		nanotate_UI.access(new Runnable() {
            @Override
            public void run() {
                // Here the UI is locked and can be updated
            	progress2.setVisible(false);
            	progress.setVisible(false);
            	container.refresh();
            }
        });
		
	}

	

	
	
		
		
	
}