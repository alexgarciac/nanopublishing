package org.nanotate.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import org.nanotate.Nanotate_Properties;
import org.nanotate.model.AnnotationWithBLOBs;
import org.nanotate.model.Ranges;
import org.nanotate.model.Tag;
import org.nanotate.ui.components.AnnotationUI;
import org.nanotate.util.AnnotationUtils;
import org.vostok.vaadin.addon.button.i18n.I18nButton;
import org.vostok.vaadin.addon.button.push.PushButton;
import org.vostok.vaadin.addon.button.push.PushButton.Mode;
import org.vostok.vaadin.addon.toolbar.Toolbar;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class Viewer extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Panel annotations;
	@AutoGenerated
	private VerticalLayout verticalLayout_3;
	@AutoGenerated
	private Toolbar toolbar_1;
	@AutoGenerated
	private Panel docs;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;
	@AutoGenerated
	private Embedded embedded_1;
	private Table docs_table;
	
	final private I18nButton userbutton = new I18nButton("user");
	final private I18nButton globebutton =  new I18nButton("globe");
	private ArrayList<AnnotationUI> annotationuis;
	private Label docs_label;
	
	SQLContainer container;
	protected String actual_source;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8416626488644424865L;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public Viewer() {
		
		
		buildMainLayout();
		setCompositionRoot(mainLayout);
		
		annotationuis=new ArrayList<AnnotationUI>();
		userbutton.setCaption("Mine");
		userbutton.setDescription("Mine annotations");
		globebutton.setCaption("All");
		globebutton.setDescription("All annotations");
		toolbar_1.setStyleName("annotations-toolbar");
		toolbar_1.addComponent(new PushButton(new PushButton.PushButtonListener() {
			@Override
			public void buttonPush(Button.ClickEvent event, boolean pushed) {
				
				Object rowId = docs_table.getValue();
				if(event.getButton().getCaption().equals("Mine")){
					clearAnnotations();
					loadAnnotations((String)docs_table.getContainerProperty(rowId,"doi").getValue(), (String) getSession().getAttribute("user"));
				}		
				else{

					clearAnnotations();
					loadAnnotations((String)docs_table.getContainerProperty(rowId,"doi").getValue(), "all");
					
				}
					
			}
		}, userbutton, globebutton){
			/**
			 * 
			 */
			private static final long serialVersionUID = 3866159765631265620L;

			{
				setMode(Mode.ONEMANY);
			}
		});

		// TODO add user code here
	}
	
	public SQLContainer getContainer(){
		return container;
	}
	
	
	public void initContainer(String user){
		try {
			JDBCConnectionPool pool = new SimpleJDBCConnectionPool(
			        "com.mysql.jdbc.Driver",
			        "jdbc:mysql://localhost:3306/annotatorjs", "annotatorjs", "annotator7541", 2, 5);
			TableQuery tq = new TableQuery("documents", pool);
			tq.setVersionColumn("upload_date");
			container = new SQLContainer(tq);
			container.addContainerFilter(new Compare.Equal("uploaded_by", user));
			docs_table= new Table();
			docs_table.setContainerDataSource(container);
			docs_table.setSelectable(true);
			docs_table.setNullSelectionAllowed(false);
			docs_table.setMultiSelect(false);
			docs_table.setColumnCollapsingAllowed(true);
			docs_table.setColumnCollapsed("doc_uuid", true);
			docs_table.setColumnCollapsed("first_author", true);
			docs_table.setColumnCollapsed("uploaded_by", true);
			docs_table.setColumnCollapsed("upload_date", true);
			docs_table.setColumnCollapsed("doi", true);
			docs_table.setColumnCollapsed("full_citation", true);
			docs_table.setColumnWidth("title", 210);
			docs_table.addValueChangeListener(new Property.ValueChangeListener() {
			 

				/**
				 * 
				 */
				private static final long serialVersionUID = -4944536175063979025L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					
					Object rowId = docs_table.getValue(); // get the selected rows id
					String uuid = (String) docs_table.getContainerProperty(rowId,"doc_uuid").getValue();
					actual_source=Nanotate_Properties.getInstance().getProperty("host")+"/repository/"+uuid+"/index.html#";
					embedded_1.setSource(new ExternalResource(actual_source+"username="+getSession().getAttribute("user")));
					userbutton.click();
					clearAnnotations();
					loadAnnotations((String)docs_table.getContainerProperty(rowId,"doi").getValue(), (String) getSession().getAttribute("user"));
					
				}
			});
			
			docs_table.setWidth("-1");
			docs_table.setHeight("-1");
			absoluteLayout_2.addComponent(docs_table,"top:20.0px;right:20px;bottom:20.0px;left:20px;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadAnnotations(String doi, String user){
		
		ArrayList<AnnotationWithBLOBs> annotations =  AnnotationUtils.getAnnotations(doi, user);
		
		
	 for(AnnotationWithBLOBs annotation :  annotations){
		 
		 ArrayList<Tag> tags = AnnotationUtils.getTags(annotation.getAnnotation_id());
		 
		 Ranges ranges =  AnnotationUtils.getRanges(annotation.getAnnotation_id());
		 
		 AnnotationUI annotationUI = new AnnotationUI(tags, annotation, ranges);
		 ExternalResource ext = (ExternalResource) this.embedded_1.getSource();
		 annotationUI.initClickListener(embedded_1, ext.getURL().toString());
		 this.annotationuis.add(annotationUI);
		 verticalLayout_3.addComponent(annotationUI);
		 verticalLayout_3.setComponentAlignment(annotationUI, new Alignment(48));
		 
		 
	 }
	 
	 
		
	 
		
	}
	
	private void clearAnnotations(){
		 for(AnnotationUI annotationui:annotationuis){
			 verticalLayout_3.removeComponent(annotationui);
		 }
		 annotationuis.clear();
	 }

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// embedded_1
		embedded_1 = new Embedded();
		embedded_1.setImmediate(false);
		embedded_1.setWidth("100.0%");
		embedded_1.setHeight("100.0%");
		embedded_1
				.setSource(new ExternalResource(
						"http://local.host:8080/repository/ad06b023d1204f08af56d4e95d2d8cae/index.html"));
		embedded_1.setType(2);
		embedded_1.setMimeType("image/png");
		mainLayout.addComponent(embedded_1,
				"top:0.0px;right:5.0%;bottom:0.0px;left:5.0%;");
		
		// docs
		docs = buildDocs();
		mainLayout.addComponent(docs, "top:2.5%;bottom:2.5%;left:0.0px;");
		
		// annotations
		annotations = buildAnnotations();
		mainLayout.addComponent(annotations,
				"top:2.5%;right:0.0px;bottom:2.5%;");
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildDocs() {
		// common part: create layout
		docs = new Panel();
		docs.setStyleName("docs-panel");
		docs.setImmediate(false);
		docs.setWidth("300px");
		docs.setHeight("100.0%");
		
		// absoluteLayout_2
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");
		docs.setContent(absoluteLayout_2);
		
		return docs;
	}

	@AutoGenerated
	private Panel buildAnnotations() {
		// common part: create layout
		annotations = new Panel();
		annotations.setStyleName("annotations-panel");
		annotations.setImmediate(false);
		annotations.setWidth("300px");
		annotations.setHeight("100.0%");
		
		// verticalLayout_3
		verticalLayout_3 = buildVerticalLayout_3();
		annotations.setContent(verticalLayout_3);
		
		return annotations;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_3() {
		// common part: create layout
		verticalLayout_3 = new VerticalLayout();
		verticalLayout_3.setImmediate(false);
		verticalLayout_3.setWidth("100.0%");
		verticalLayout_3.setHeight("-1px");
		verticalLayout_3.setMargin(false);
		
		// toolbar_1
		toolbar_1 = new Toolbar();
		toolbar_1.setImmediate(false);
		toolbar_1.setWidth("-1px");
		toolbar_1.setHeight("-1px");
		verticalLayout_3.addComponent(toolbar_1);
		verticalLayout_3.setComponentAlignment(toolbar_1, new Alignment(20));
		
		return verticalLayout_3;
	}

}
