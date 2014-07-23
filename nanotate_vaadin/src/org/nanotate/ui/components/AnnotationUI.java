package org.nanotate.ui.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.nanotate.model.AnnotationWithBLOBs;
import org.nanotate.model.Ranges;
import org.nanotate.model.Tag;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class AnnotationUI extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private CssLayout mainLayout;
	@AutoGenerated
	private Panel panel_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private Accordion annotation_acc;
	@AutoGenerated
	private TextArea textArea_2;
	@AutoGenerated
	private CssLayout absoluteLayout_2;
	@AutoGenerated
	private Label textArea_1;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_2;
	@AutoGenerated
	private Link link_2;
	@AutoGenerated
	private Link link_1;
	@AutoGenerated
	private Panel panel_3;
	@AutoGenerated
	private HorizontalLayout horizontalLayout_3;
	@AutoGenerated
	private Label label_1;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	
	private ArrayList<Tag> tags;
	private AnnotationWithBLOBs annotation;
	private Ranges range;
	
	public AnnotationUI() {
		buildMainLayout();
		 loadData();
		setCompositionRoot(mainLayout);
	}

	
	
	
	public AnnotationUI(ArrayList<Tag> tags, AnnotationWithBLOBs annotation, Ranges range) {
		this.tags = tags;
		this.annotation = annotation;
		this.range = range;
		buildMainLayout();
		 loadData();
		setCompositionRoot(mainLayout);
		link_1.setCaption("");
		link_2.setCaption("");
		link_1.setIcon(new ClassResource("/org/vaadin/addon/oauthpopup/icons/twitter16.png"));
		link_2.setIcon(new ClassResource("/org/vaadin/addon/oauthpopup/icons/facebook16.png"));
		label_1.setStyleName("date-label");
		gridLayout_1.setColumnExpandRatio(0, 2);
		gridLayout_1.setMargin(new MarginInfo(true,true,false,true));
	}




	private void loadData(){
		
		
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(annotation.getCreated());
		label_1.setValue(annotation.getUser()+", "+date);
		textArea_1.setValue(annotation.getQuote());
		textArea_2.setValue(annotation.getText());
		textArea_1.setReadOnly(true);
		textArea_2.setReadOnly(true);
		
		for(Tag tag : tags){
			Label label = new Label(tag.getTag_label());
			if(tag.getAutomatic_tag())
				label.setStyleName("tag-orange");
			else
				label.setStyleName("tag");
			absoluteLayout_2.addComponent(label);
		}
	}

	public void initClickListener(final Embedded viewer, final String location){
		
		
		
		
		ClickListener listener = new ClickListener(){

			@Override
			public void click(ClickEvent event) {
				viewer.setSource(new ExternalResource(location.substring(0, location.indexOf("#")+1)+annotation.getAnnotation_id()));
				System.out.println("Holi: "+location.substring(0, location.indexOf("#")+1)+annotation.getAnnotation_id());
				
			}};
		
		panel_3.addClickListener(listener);
		
	}




	@AutoGenerated
	private CssLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new CssLayout();
		mainLayout.setStyleName("annotation-ui");
		mainLayout.setImmediate(false);
		mainLayout.setWidth("250px");
		mainLayout.setHeight("-1px");
		
		// top-level component properties
		setWidth("250px");
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
		panel_1.setStyleName("annotation-panel");
		panel_1.setImmediate(false);
		panel_1.setWidth("100.0%");
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
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		
		// gridLayout_1
		gridLayout_1 = buildGridLayout_1();
		verticalLayout_1.addComponent(gridLayout_1);
		verticalLayout_1.setComponentAlignment(gridLayout_1, new Alignment(20));
		
		// textArea_1
		textArea_1 = new Label();
		textArea_1.setStyleName("quote-textarea");
		textArea_1.setImmediate(false);
		textArea_1.setWidth("100.0%");
		textArea_1.setHeight("-1px");
		verticalLayout_1.addComponent(textArea_1);
		verticalLayout_1.setComponentAlignment(textArea_1, new Alignment(48));
		
		// annotation_acc
		annotation_acc = buildAnnotation_acc();
		verticalLayout_1.addComponent(annotation_acc);
		verticalLayout_1.setExpandRatio(annotation_acc, 5.0f);
		verticalLayout_1.setComponentAlignment(annotation_acc,
				new Alignment(48));
		
		return verticalLayout_1;
	}




	@AutoGenerated
	private GridLayout buildGridLayout_1() {
		// common part: create layout
		gridLayout_1 = new GridLayout();
		gridLayout_1.setStyleName("annotation-header-container");
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		gridLayout_1.setHeight("-1px");
		gridLayout_1.setMargin(false);
		gridLayout_1.setColumns(3);
		
		// panel_3
		panel_3 = buildPanel_3();
		gridLayout_1.addComponent(panel_3, 0, 0);
		gridLayout_1.setComponentAlignment(panel_3, new Alignment(6));
		
		// horizontalLayout_2
		horizontalLayout_2 = buildHorizontalLayout_2();
		gridLayout_1.addComponent(horizontalLayout_2, 2, 0);
		
		return gridLayout_1;
	}




	@AutoGenerated
	private Panel buildPanel_3() {
		// common part: create layout
		panel_3 = new Panel();
		panel_3.setStyleName("annotation-header");
		panel_3.setImmediate(false);
		panel_3.setWidth("100.0%");
		panel_3.setHeight("-1px");
		
		// horizontalLayout_3
		horizontalLayout_3 = buildHorizontalLayout_3();
		panel_3.setContent(horizontalLayout_3);
		
		return panel_3;
	}




	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_3() {
		// common part: create layout
		horizontalLayout_3 = new HorizontalLayout();
		horizontalLayout_3.setImmediate(false);
		horizontalLayout_3.setWidth("100.0%");
		horizontalLayout_3.setHeight("100.0%");
		horizontalLayout_3.setMargin(false);
		
		// label_1
		label_1 = new Label();
		label_1.setImmediate(false);
		label_1.setWidth("100.0%");
		label_1.setHeight("-1px");
		label_1.setValue("12-07-2014 11:30:24");
		horizontalLayout_3.addComponent(label_1);
		
		return horizontalLayout_3;
	}




	@AutoGenerated
	private HorizontalLayout buildHorizontalLayout_2() {
		// common part: create layout
		horizontalLayout_2 = new HorizontalLayout();
		horizontalLayout_2.setStyleName("social-buttons");
		horizontalLayout_2.setImmediate(false);
		horizontalLayout_2.setWidth("-1px");
		horizontalLayout_2.setHeight("-1px");
		horizontalLayout_2.setMargin(false);
		horizontalLayout_2.setSpacing(true);
		
		// link_1
		link_1 = new Link();
		link_1.setCaption("Link");
		link_1.setImmediate(false);
		link_1.setWidth("-1px");
		link_1.setHeight("-1px");
		horizontalLayout_2.addComponent(link_1);
		horizontalLayout_2.setComponentAlignment(link_1, new Alignment(34));
		
		// link_2
		link_2 = new Link();
		link_2.setCaption("Link");
		link_2.setImmediate(false);
		link_2.setWidth("-1px");
		link_2.setHeight("-1px");
		horizontalLayout_2.addComponent(link_2);
		horizontalLayout_2.setComponentAlignment(link_2, new Alignment(33));
		
		return horizontalLayout_2;
	}




	@AutoGenerated
	private Accordion buildAnnotation_acc() {
		// common part: create layout
		annotation_acc = new Accordion();
		annotation_acc.setImmediate(true);
		annotation_acc.setWidth("100.0%");
		annotation_acc.setHeight("-1px");
		
		// absoluteLayout_2
		absoluteLayout_2 = new CssLayout();
		absoluteLayout_2.setStyleName("flow-layout");
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("-1px");
		annotation_acc.addTab(absoluteLayout_2, "tags", null);
		
		// textArea_2
		textArea_2 = new TextArea();
		textArea_2.setImmediate(false);
		textArea_2.setWidth("100.0%");
		textArea_2.setHeight("-1px");
		annotation_acc.addTab(textArea_2, "comment", null);
		
		return annotation_acc;
	}
	

}
