package org.nanotate.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.nanotate.Nanotate_Properties;
import org.nanotate.Nanotate_UI;
import org.nanotate.util.FileUtils;
import org.vostok.vaadin.addon.button.i18n.I18nButton;
import org.vostok.vaadin.addon.button.push.PushButton;
import org.vostok.vaadin.addon.button.push.PushButton.Mode;
import org.vostok.vaadin.addon.dialog.NotificationExtension;
import org.vostok.vaadin.addon.toolbar.Toolbar;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.VerticalLayout;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.PictureSize;

public class Main extends CustomComponent implements View{

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	
	 public static final String NAME = "main";

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601752554459629015L;
	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Panel contentpanel;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Panel panel_2;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Toolbar toolbar;
	@AutoGenerated
	private Embedded embedded_1;
	
	private Viewer viewer;
	private Home home;
	private MultiFileUpload multiUpload;
	private UploadStateWindow uploadStateWindow = new UploadStateWindow();
	private UploadFinishedHandler uploadFinishedHandler;
	private ProgressBar progress;
	private Panel convertionProgressPanel;
	private AbsoluteLayout absoluteLayout_progress;
	private Label converting;
	
	
	private Nanotate_UI nanotate_UI;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param nanotate_UI 
	 */
	public Main(Nanotate_UI nanotate_UI) {
		
		
	
		this.nanotate_UI=nanotate_UI;
		
		
		buildMainLayout();
		
	
		
		setCompositionRoot(mainLayout);

		
	}
	
	private Panel buildConvertionProgressPanel() {
		// common part: create layout
		convertionProgressPanel = new Panel();
		convertionProgressPanel.setStyleName("convertion-progress-panel");
		convertionProgressPanel.setImmediate(false);
		convertionProgressPanel.setWidth("150px");
		convertionProgressPanel.setHeight("50px");
		
		converting=new Label();
		converting.setValue("Converting...");
		
		progress = new ProgressBar();
		progress.setIndeterminate(true);
		progress.setWidth("20.0px");
		absoluteLayout_progress = new AbsoluteLayout();
		absoluteLayout_progress.setImmediate(false);
		absoluteLayout_progress.setWidth("100.0%");
		absoluteLayout_progress.setHeight("100.0%");
		
		absoluteLayout_progress.addComponent(progress,
				"top:8.0px;left:8.0px;");
		absoluteLayout_progress.addComponent(converting,
				"top:15.0px;left:43.0px;");
		
		convertionProgressPanel.setContent(absoluteLayout_progress);
		
		convertionProgressPanel.setVisible(false);
		
		return convertionProgressPanel;
	}
	
	private MultiFileUpload buildMultiUpload(){
		uploadStateWindow.setWindowPosition(UploadStateWindow.WindowPosition.BOTTOM_RIGHT);
		 uploadFinishedHandler = new UploadFinishedHandler() {
	            @Override
	            public void handleFile(InputStream stream, String fileName, String mimeType, long length) {
	                
	            	if(handleUpload(stream, fileName, mimeType, length))
	            		Notification.show(fileName + " uploaded.");
	            	else
	            		Notification.show("Error uploading "+fileName);
	            }
	        };
	        
	    multiUpload = new MultiFileUpload(uploadFinishedHandler, uploadStateWindow, false);
		 multiUpload.getSmartUpload().setUploadButtonCaptions("Upload file", "Upload files");
		 ArrayList<String> mime = new ArrayList<String>();
		 mime.add("application/pdf");
		 multiUpload.setAcceptedMimeTypes(mime);
		return multiUpload;
	}
	

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		buildConvertionProgressPanel();
		
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// panel_2
		panel_2 = buildPanel_2();
		mainLayout.addComponent(panel_2, "top:10.0px;right:5.0%;left:5.0%;");
		
		
		// contentpanel
		contentpanel = buildContentpanel();
		mainLayout.addComponent(contentpanel,
				"top:70.0px;right:0.0px;bottom:0.0px;left:0.0px;");
		mainLayout.addComponent(convertionProgressPanel, "bottom:10.0px;right:0.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel();
		panel_2.setStyleName("toolbar-panel");
		panel_2.setImmediate(false);
		panel_2.setWidth("100.0%");
		panel_2.setHeight("50px");
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		panel_2.setContent(horizontalLayout_1);
		
		return panel_2;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("100.0%");
		horizontalLayout_1.setHeight("100.0%");
		horizontalLayout_1.setMargin(false);
		
		// embedded_1
		embedded_1 = new Embedded();
		embedded_1.setStyleName("profile-image");
		embedded_1.setImmediate(false);
		embedded_1.setWidth("-1px");
		embedded_1.setHeight("-1px");
		embedded_1
				.setSource(new ExternalResource(
						"https://lh4.googleusercontent.com/-moR4bMbRoGQ/AAAAAAAAAAI/AAAAAAAAAIM/XNHoSAo8ylw/s120-c/photo.jpg"));
		embedded_1.setType(1);
		embedded_1.setMimeType("image/png");
		horizontalLayout_1.addComponent(embedded_1);
		horizontalLayout_1.setComponentAlignment(embedded_1, new Alignment(33));
		
		// toolbar
		toolbar = new Toolbar();
		toolbar.setImmediate(false);
		toolbar.setWidth("80%");
		I18nButton homebutton = new I18nButton("home");
		final I18nButton viewerbutton =  new I18nButton("load-all");
		viewerbutton.setCaption("Viewer");
		viewerbutton.setDescription("Document viewer");
		toolbar.addComponent(new PushButton(new PushButton.PushButtonListener() {
			@Override
			public void buttonPush(Button.ClickEvent event, boolean pushed) {
				if(event.getButton().getCaption().equals("Home"))
						contentpanel.setContent(home);
				else{

					
					String basepath = VaadinService.getCurrent()
			                .getBaseDirectory().getAbsolutePath();
					
					System.out.println(basepath);
					contentpanel.setContent(viewer);
				}
					
			}
		}, homebutton, viewerbutton){
			/**
			 * 
			 */
			private static final long serialVersionUID = 3866159765631265620L;

			{
				setMode(Mode.ONEMANY);
			}
		});
		
		toolbar.addSeparator();
		
		
		Button.ClickListener listenerlogout = new Button.ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -58044467433427934L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				
				
				getSession().setAttribute("user", null);
				getSession().setAttribute("facebook", null);
				getSession().setAttribute("twitter", null);
				getUI().getNavigator().navigateTo(Login.NAME);
			}
		};
		this.buildMultiUpload();
		toolbar.addComponent(multiUpload);
		toolbar.addButton("Logout", "Logout", "exit").addClickListener(listenerlogout);
		toolbar.setMode(Toolbar.DISPLAY.RIGHT);
		horizontalLayout_1.addComponent(toolbar);
		horizontalLayout_1.setComponentAlignment(toolbar, new Alignment(34));
//		horizontalLayout_1.addComponent(multiUpload);
		return horizontalLayout_1;
	}

	@AutoGenerated
	private Panel buildContentpanel() {
		// common part: create layout
		contentpanel = new Panel();
		contentpanel.setStyleName("content-panel");
		contentpanel.setImmediate(false);
		contentpanel.setWidth("100.0%");
		contentpanel.setHeight("100.0%");
		
		// verticalLayout_1
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		progress.setVisible(true);
		progress.setIndeterminate(true);
		
		
		return contentpanel;
	}
	
	private synchronized boolean handleUpload (InputStream stream, String fileName, String mimeType, long length){
		
		OutputStream outputStream = null;
        boolean ret = true;
        File file = null;
        String docUUID ="";
    	try {
    		// read this file into InputStream
     
    		// write the inputStream to a FileOutputStream
    		
    		docUUID = UUID.randomUUID().toString().replace("-", "");
    		
    		new File(Nanotate_Properties.getInstance().getProperty("repository.dir")+"/"+docUUID+"/").mkdir();
    		
    		file = new File(Nanotate_Properties.getInstance().getProperty("repository.dir")+"/"+docUUID+"/"+docUUID+".pdf");
    		
    		outputStream = new FileOutputStream(file);
     
    		int read = 0;
    		byte[] bytes = new byte[1024];
     
    		while ((read = stream.read(bytes)) != -1) {
    			outputStream.write(bytes, 0, read);
    		}
     
    		System.out.println("Done!");
     
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (stream != null) {
    			try {
    				stream.close();
    			} catch (IOException e) {
    				ret=false;
    				e.printStackTrace();
    			}
    		}
    		if (outputStream != null) {
    			try {
    				// outputStream.flush();
    				outputStream.close();
    			} catch (IOException e) {
    				ret=false;
    				e.printStackTrace();
    			}
     
    		}
    	}
    	if(!Boolean.parseBoolean(Nanotate_Properties.getInstance().getProperty("use.crocodoc")))
    	{
			try {
				Runtime.getRuntime().exec("pdf2htmlex "+Nanotate_Properties.getInstance().getProperty("repository.dir")+fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ret=false;
				e.printStackTrace();
			}
    	}else
    	{
    		
    	   FileUtils fileUtils = new FileUtils(file,Nanotate_Properties.getInstance().getProperty("repository.dir")+"/"+docUUID+"/", convertionProgressPanel, progress, nanotate_UI);
    	   Thread thread = new Thread (fileUtils);
    	   thread.start();
    	   
    	}
    	
		
		return ret;
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
		String username = String.valueOf(getSession().getAttribute("user"));

        if(StringUtils.equals(username, "null"))
        {
        	getUI().getNavigator().navigateTo(Login.NAME);
        }
        	  
		
		
		embedded_1.setSource(new ExternalResource(
				(String) getSession().getAttribute("imgurl")));
		
		viewer = new Viewer();
		home = new Home();
		home.setImageURL((String) getSession().getAttribute("imgurl"));
		contentpanel.setContent(home);
			
		
	
        
		
	}
	
	

}
