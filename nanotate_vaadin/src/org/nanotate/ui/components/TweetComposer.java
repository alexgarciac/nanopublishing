package org.nanotate.ui.components;

import org.apache.ibatis.session.SqlSession;
import org.nanotate.model.Annotation;
import org.nanotate.model.AnnotationWithBLOBs;
import org.nanotate.model.TwitterPost;
import org.nanotate.model.TwitterPostMapper;
import org.nanotate.util.MyBatis;
import org.vaadin.csvalidation.CSValidator;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class TweetComposer extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private CssLayout mainLayout;
	@AutoGenerated
	private Panel panel_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_1;
	@AutoGenerated
	private Button tweetit;
	@AutoGenerated
	private TextArea tweetBox;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 * @param popup 
	 */
	

	
	
	public TweetComposer(final PopupView popup, final AnnotationWithBLOBs annotation) {
		buildMainLayout();
		setCompositionRoot(mainLayout);
		CSValidator validator = new CSValidator();
		validator.extend(tweetBox);
		if(annotation.getText().length()>107)
			tweetBox.setValue(annotation.getText().substring(0, 107));
		else
			tweetBox.setValue(annotation.getText());
		validator.setJavaScript("var maxvalue = 107;\n" +
		                      "var charsleft = maxvalue - value.length;\n" +
		                      "var result = null;\n" +
		                      "if (charsleft > 0)\n" +
		                      "    result = \"valid \" + value.length + \" of \" + maxvalue;\n" +
		                      "else\n" +
		                      "    result = \"\" + value.length + \" of \" + maxvalue;\n" +
		                      "result;");
		validator.setValidateInitialEmpty(true);
		
		ClickListener twitterlistener = new ClickListener(){
		

			@Override
			public void buttonClick(ClickEvent event) {
				Twitter twitter = (Twitter) getSession().getAttribute("twitter");
				try {
//					System.out.println("Lenght: "+tweetBox.getValue().length());
					String message = tweetBox.getValue()+" #Nanotate "+annotation.getUri();
					Status data = twitter.updateStatus(message);
					SqlSession sqlSession = MyBatis.getSession();
					TwitterPostMapper mapper = sqlSession.getMapper(TwitterPostMapper.class);
					TwitterPost twitterpost = new TwitterPost();
					twitterpost.setAnnotation_id(annotation.getAnnotation_id());
					twitterpost.setAutomatic_post(false);
					twitterpost.setTwitter_post_url("https://www.twitter.com/"+twitter.getScreenName()+"/"+data.getId());
					
					
					mapper.insert(twitterpost);
					sqlSession.commit();
					sqlSession.close();
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
			}};

			tweetit.addClickListener(twitterlistener);
			

			
			
		
			 
		

		
		// TODO add user code here
	}

	@AutoGenerated
	private CssLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new CssLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// panel_1
		panel_1 = buildPanel_1();
		mainLayout.addComponent(panel_1);
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setStyleName("tweet-composer-panel");
		panel_1.setImmediate(false);
		panel_1.setWidth("-1px");
		panel_1.setHeight("-1px");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		panel_1.setContent(verticalLayout_1);
		
		return panel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(true);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		
		// tweetBox
		tweetBox = new TextArea();
		tweetBox.setImmediate(false);
		tweetBox.setWidth("250px");
		tweetBox.setHeight("100px");
		tweetBox.setMaxLength(107);
		verticalLayout_1.addComponent(tweetBox);
		verticalLayout_1.setComponentAlignment(tweetBox, new Alignment(48));
		
		// horizontalLayout_1
		horizontalLayout_1 = buildHorizontalLayout_1();
		verticalLayout_1.addComponent(horizontalLayout_1);
		verticalLayout_1.setComponentAlignment(horizontalLayout_1,
				new Alignment(20));
		
		return verticalLayout_1;
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_1() {
		// common part: create layout
		horizontalLayout_1 = new HorizontalLayout();
		horizontalLayout_1.setImmediate(false);
		horizontalLayout_1.setWidth("-1px");
		horizontalLayout_1.setHeight("-1px");
		horizontalLayout_1.setMargin(true);
		horizontalLayout_1.setSpacing(true);
		
		// tweetit
		tweetit = new Button();
		tweetit.setCaption("Tweet it!");
		tweetit.setImmediate(true);
		tweetit.setWidth("-1px");
		tweetit.setHeight("-1px");
		horizontalLayout_1.addComponent(tweetit);
		
		return horizontalLayout_1;
	}
	
    


}
