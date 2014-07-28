package org.nanotate.ui.components;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class TweetComposerWindow extends UI {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7801751019758916279L;
	
	private static boolean twitter_conected;

	@Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Send nanotate to twitter!");
        
       
        setContent(new TweetComposer());
    }

	public static boolean isTwitter_conected() {
		return twitter_conected;
	}

	public static void setTwitter_conected(boolean twitter_conected) {
		TweetComposerWindow.twitter_conected = twitter_conected;
	}


}

