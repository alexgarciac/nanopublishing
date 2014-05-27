package com.nanotate.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.nanotate.Settings;
import com.nanotate.dao.model.Annotation;
import com.nanotate.dao.model.AnnotationExample;
import com.nanotate.dao.model.AnnotationMapper;
import com.nanotate.dao.util.MyBatis;
import com.nanotate.message.JsonResponse;
import com.nanotate.thread.FacebookWriter;
import com.nanotate.thread.NanotweetWriter;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import utils.JsonEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

public class AnnotationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7254461811912924374L;

	static Logger log;	
	static Properties config;
	
	static {
		// Debug file 
		DOMConfigurator.configure( Thread.currentThread().getContextClassLoader().getResource("log4j.xml"));
		log = Logger.getLogger(AnnotationServlet.class);
	}
	
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        
    	String action = req.getParameter("action");
    	log.info("AnotateServlet request action= "+action);

    	if ( Settings.SERVICE_LIST.equalsIgnoreCase(action) ) {
    		log.info("AnotateList request");
    		executeListAnnotationsService( req, resp );
    		
    	} else if(Settings.SERVICE_PERSONALIST.equalsIgnoreCase(action)){
    		
    		log.info("AnotatPersonalList request");
    		executePersonalListAnnotationsService( req, resp );

    		
    	}
    	else {
    	
    		executeAnnotateService( req, resp );
    	} 

    	
    }


	private void executeListAnnotationsService(HttpServletRequest servletRequest, HttpServletResponse servletResponse ) {
		
		JsonResponse r = new JsonResponse();
		String doi = servletRequest.getParameter( Settings.PARAM_DOI );

		try {
			SqlSession session = MyBatis.getSession();
			AnnotationMapper mapper = session.getMapper(AnnotationMapper.class);
			
			AnnotationExample example = new AnnotationExample();
			log.info("Anotate example created");
			
			if ( !StringUtils.isEmpty(doi) ) {
				example.createCriteria().andDoiEqualTo(doi);
				log.info("This way");
			} 
			
			example.setOrderByClause("creation DESC");
			
			List<Annotation> annotations = mapper.selectByExample( example );
			
			r.setData( annotations );
			r.setCode( Settings.RESPONSE_CODE_OK );
			r.setMessage( Settings.RESPONSE_MSG_OK );
			session.close();
		} catch (Exception e) {
			r.setCode( Settings.RESPONSE_CODE_TECHNICAL_ERROR );
			r.setMessage( "Sorry, we're having a technical issue with our database" );
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonEncoder.encode(servletResponse, r);
		
	}
	
	private void executePersonalListAnnotationsService(HttpServletRequest servletRequest, HttpServletResponse servletResponse ) {
		
		JsonResponse r = new JsonResponse();
		String doi = servletRequest.getParameter( Settings.PARAM_DOI );
		String username = (String) servletRequest.getSession().getAttribute("user");

		try {
			SqlSession session = MyBatis.getSession();
			AnnotationMapper mapper = session.getMapper(AnnotationMapper.class);
			
			AnnotationExample example = new AnnotationExample();
			log.info("Anotate example created, doi:" +doi);
			log.info("Anotate example created, user:" +username);
			
			if ( !StringUtils.isEmpty(doi) ) {
				example.createCriteria().andDoiEqualTo(doi).andUser_nameEqualTo(username);
				log.info("This way");
			} 
			
			example.setOrderByClause("creation DESC");
			
			List<Annotation> annotations = mapper.selectByExample( example );
			
			for(Annotation annotation: annotations){
				log.info(annotation.getUser_name());
			}
			
			r.setData( annotations );
			r.setCode( Settings.RESPONSE_CODE_OK );
			r.setMessage( Settings.RESPONSE_MSG_OK );
			session.close();
		} catch (Exception e) {
			r.setCode( Settings.RESPONSE_CODE_TECHNICAL_ERROR );
			r.setMessage( "Sorry, we're having a technical issue with our database" );
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonEncoder.encode(servletResponse, r);
		
	}


	private void executeAnnotateService(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		
		JsonResponse r = new JsonResponse();
		log.info("executeAnnotateService request");
		String text = servletRequest.getParameter( Settings.PARAM_TEXT );
		String uuid = servletRequest.getParameter( Settings.PARAM_UUID );
        String comment = servletRequest.getParameter( Settings.PARAM_COMMENT );
		
		if ( StringUtils.isEmpty(text) ) {
			r.setCode( Settings.RESPONSE_CODE_PARAM_NOT_VALID );
			r.setMessage( "Text parameter is missing" );
			
		} else if ( StringUtils.isEmpty(uuid) ) {
			r.setCode( Settings.RESPONSE_CODE_PARAM_NOT_VALID );
			r.setMessage( "Document uuid parameter is missing" );
			
		} else {
			NanotweetWriter nanotweetWriter = new NanotweetWriter( text, uuid, (String) servletRequest.getSession().getAttribute("user"));
            //FacebookWriter facebookWriter = new FacebookWriter( text, uuid, comment );

			( new Thread( nanotweetWriter ) ).start();
            //( new Thread( facebookWriter ) ).start();


			r.setCode(Settings.RESPONSE_CODE_OK);
			r.setMessage(Settings.RESPONSE_MSG_OK );
		}
		
		JsonEncoder.encode(servletResponse, r);
	}


    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		doGet(req,resp);
    }
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {






    }

}
