package br.com.generate.thymeleaf;

import br.com.generate.java.command.model.ModelClassNameHolder;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ThymeleafGenerator extends AbstractThymeleafGenerate {

    private String templateDir;

	public ThymeleafGenerator(String className, String parameters) throws IOException {
		this.templateDir = "/templates/";

		generateTemplates(className, parameters);
	}

	public ThymeleafGenerator(String className, String parameters, String templateDir) throws IOException {
		this.templateDir = templateDir;
		
		generateTemplates(className, parameters);
	}

	private void generateTemplates(String className, String parameters) throws IOException {
		if (validateLayoutHtml()) {
			generateTemplateLayout();
		}
		ModelClassNameHolder modelClassNameHolder = new ModelClassNameHolder(className.split(":")); 
		generateIndexHtml(modelClassNameHolder.getClassName(), parameters);
		generateFormHtml(modelClassNameHolder.getClassName(), parameters);
		generateShowHtml(modelClassNameHolder.getClassName(), parameters);
	}

	public void generateTemplateLayout() throws IOException {		
		String htmlString = getHtmlString("template-layout.html");

		File newHtmlFile = new File(getUserDir() + "/src/main/resources/templates/layout.html");
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		System.out.println("Create src/main/resources/templates/layout.html");
	}
	
	public void generateIndexHtml(String className, String parameters) throws IOException {
		String htmlString = getHtmlString("template-index.html");

		String template = "layout";
		String classNameParam = className;
		String paramClassName = className.toLowerCase();
		String pathUrl = "/" + className.toLowerCase() + "s";
		String thAttributes = generateThParameters(parameters);
		String tdAttributes = generateTdParameters(className, parameters);
		String eachParam = "list" + className;
		
		htmlString = htmlString.replace("${template}", template);
		htmlString = htmlString.replace("${className}", classNameParam);
		htmlString = htmlString.replace("paramClassName", paramClassName);
		htmlString = htmlString.replace("eachParam", eachParam);
		htmlString = htmlString.replace("url_path", pathUrl);
		htmlString = htmlString.replace("${th_attributes}", thAttributes);
		htmlString = htmlString.replace("${td_attributes}", tdAttributes);
		
		File newHtmlFile = new File(getUserDir() + "/src/main/resources/templates/" + className.toLowerCase() + "/index.html");
		
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		System.out.println("Create src/main/resources/templates/" + className.toLowerCase() + "/index.html");
	}

	public void generateFormHtml(String className, String parameters) throws IOException {
		String htmlString = getHtmlString("template-form.html");

		String template = "layout";
		String paramClassName = className.toLowerCase();
		String pathUrl = "/" + className.toLowerCase() + "s";
		String inputParameters = generateInputParameters(parameters);
		
		htmlString = htmlString.replace("${template}", template);
		htmlString = htmlString.replace("${className}", className);
		htmlString = htmlString.replace("paramClassName", paramClassName);
		htmlString = htmlString.replace("url_path", pathUrl);
		htmlString = htmlString.replace("${input_parameters}", inputParameters);
		
		File newHtmlFile = new File(getUserDir() + "/src/main/resources/templates/" + className.toLowerCase() + "/form.html");
		
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		System.out.println("Create src/main/resources/templates/" + className.toLowerCase() + "/form.html");
	}
	
	public void generateShowHtml(String className, String parameters) throws IOException {
		String htmlString = getHtmlString("template-show.html");
		
		String template = "layout";
		String paramClassName = className.toLowerCase();
		String pathUrl = "/" + className.toLowerCase() + "s";
		String showAttributes = generateShowParameters(paramClassName, parameters);
		
		htmlString = htmlString.replace("${template}", template);
		htmlString = htmlString.replace("${className}", className);
		htmlString = htmlString.replace("paramClassName", paramClassName);
		htmlString = htmlString.replace("/path_url", pathUrl);
		htmlString = htmlString.replace("${showAttributes}", showAttributes);
		
		File newHtmlFile = new File(getUserDir() + "/src/main/resources/templates/" + className.toLowerCase() + "/show.html");
		
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		System.out.println("Create src/main/resources/templates/" + className.toLowerCase() + "/show.html");
	}

	protected String getHtmlString(String resource) throws IOException {
		resource = this.templateDir + File.separator + resource;
		String htmlString;
		htmlString = IOUtils.toString(getClass().getResourceAsStream(resource), null);
		return htmlString;
	}
	
	public boolean validateLayoutHtml() throws IOException {
		File file = new File("layout.html");
		return checkIfFileExists(file);
	}

	public boolean validateLayoutHtml(String templateDir) throws IOException {
		File file = new File(templateDir);
		return checkIfFileExists(file);
	}
	
	private boolean checkIfFileExists(File file) {
		if(file.exists()) { 
			return false;
		} 
		return true;
	}

	public static void main(String[] args) throws IOException {
		new ThymeleafGenerator("User", "name:String email:String");
	}
	
	protected String getTemplateDir() {
		return this.templateDir;
	}
}
