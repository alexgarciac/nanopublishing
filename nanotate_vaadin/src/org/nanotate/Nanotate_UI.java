package org.nanotate;

import javax.servlet.annotation.WebServlet;

import org.nanotate.ui.Login;
import org.nanotate.ui.Main;
import org.nanotate.ui.SignUp;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("nanotate")
@Push
public class Nanotate_UI extends UI {
	
	Navigator navigator;
	


	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Nanotate_UI.class, widgetset="org.nanotate.widgetset.Nanotate_vaadinWidgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		
		getPage().setTitle("Nanotate");
        
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
		
        navigator.addView("", new Login());
        navigator.addView("signup", new SignUp());
        navigator.addView("main", new Main());
        
        
		

		
	}

}