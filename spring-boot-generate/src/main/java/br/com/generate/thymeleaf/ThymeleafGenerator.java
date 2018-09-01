package br.com.generate.thymeleaf;

import br.com.generate.java.command.model.ModelClassNameHolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ThymeleafGenerator extends AbstractThymeleafGenerate {

	public ThymeleafGenerator(String className, String parameters) throws IOException {
		if (validateLayoutHtml(null)) {
			generateTemplateLayout();
		}
		ModelClassNameHolder modelClassNameHolder = new ModelClassNameHolder(className.split(":")); 
		generateIndexHtml(modelClassNameHolder.getClassName(), parameters, null);
		generateFormHtml(modelClassNameHolder.getClassName(), parameters, null);
		generateShowHtml(modelClassNameHolder.getClassName(), parameters, null);
	}

	public ThymeleafGenerator(String className, String parameters, String templateDir) throws IOException {
		if (validateLayoutHtml(templateDir)) {
			generateTemplateLayout(templateDir);
		}
		ModelClassNameHolder modelClassNameHolder = new ModelClassNameHolder(className.split(":")); 
		generateIndexHtml(modelClassNameHolder.getClassName(), parameters, templateDir);
		generateFormHtml(modelClassNameHolder.getClassName(), parameters, templateDir);
		generateShowHtml(modelClassNameHolder.getClassName(), parameters, templateDir);
	}

	public void generateTemplateLayout() throws IOException {
		String htmlString = IOUtils.toString(getClass().getResourceAsStream("/templates/template-layout.html"), null);
		File newHtmlFile = new File(getUserDir() + "/src/main/resources/templates/layout.html");
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		System.out.println("Create src/main/resources/templates/layout.html");
	}

	public void generateTemplateLayout(String templateDir) throws IOException {
		File layoutFile = new File(templateDir + "/template-layout.html");
		InputStream layoutInputStream = new FileInputStream(layoutFile);
		String htmlString = IOUtils.toString(layoutInputStream, null);

		File newHtmlFile = new File(getUserDir() + "/src/main/resources/templates/layout.html");
		FileUtils.writeStringToFile(newHtmlFile, htmlString);
		System.out.println("Create src/main/resources/templates/layout.html");
	}
	
	public void generateIndexHtml(String className, String parameters, String templateDir) throws IOException {
		String htmlString;
		if(templateDir != null) {
			File layoutFile = new File(templateDir + "/template-index.html");
			InputStream layoutInputStream = new FileInputStream(layoutFile);
			htmlString = IOUtils.toString(layoutInputStream, null);
		}
		else {
			htmlString = IOUtils.toString(getClass().getResourceAsStream("/templates/template-index.html"), null);
		}

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

	public void generateFormHtml(String className, String parameters, String templateDir) throws IOException {
		String htmlString;
		if(templateDir != null) {
			File layoutFile = new File(templateDir + "/template-form.html");
			InputStream layoutInputStream = new FileInputStream(layoutFile);
			htmlString = IOUtils.toString(layoutInputStream, null);
		}
		else {
			htmlString = IOUtils.toString(getClass().getResourceAsStream("/templates/template-form.html"), null);
		}

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
	
	public void generateShowHtml(String className, String parameters, String templateDir) throws IOException {
		String htmlString;
		if(templateDir != null) {
			File layoutFile = new File(templateDir + "/template-show.html");
			InputStream layoutInputStream = new FileInputStream(layoutFile);
			htmlString = IOUtils.toString(layoutInputStream, null);
		}
		else {
			htmlString = IOUtils.toString(getClass().getResourceAsStream("/templates/template-show.html"), null);
		}

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
	
	public boolean validateLayoutHtml(String templateDir) throws IOException {
		String pathFile = templateDir != null ? templateDir + "/layout.html": "/src/main/resources/templates/layout.html";
		File f = new File(pathFile);
		if(f.exists()) { 
			return false;
		} 
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		new ThymeleafGenerator("User", "name:String email:String");
	}
	
}
