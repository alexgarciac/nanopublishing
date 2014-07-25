package org.nanotate.ui.components;

import com.vaadin.ui.Window;

public class TweetComposerWindow extends Window {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5425315536936800077L;

	public TweetComposerWindow(){
		this.setContent(new TweetComposer());
	}

}
