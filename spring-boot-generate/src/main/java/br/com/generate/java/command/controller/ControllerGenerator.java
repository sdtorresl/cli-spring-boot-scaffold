package br.com.generate.java.command.controller;

import java.io.IOException;

import br.com.generate.Layers;
import br.com.generate.ReadTemplateFile;

public class ControllerGenerator extends ReadTemplateFile {

	@Override
	public String getLayer() {
		return Layers.CONTROLLER;
	}

	@Override
	protected String operationGenerate(String javaStrings, String nameClass, String parameters) {
		return javaStrings.replace("${package}", getPackage() + ".controller")
				.replace("${package_model}", getPackage() + ".model")
				.replace("${package_service}", getPackage() + ".service")
				.replace("${className}", modelClassNameHolder.getClassName())
				.replace("${paramClassName}", modelClassNameHolder.getClassName().toLowerCase())
				.replace("${url_path}", modelClassNameHolder.getClassName().toLowerCase() + "s");
	}
	
	public static void main(String[] args) throws IOException {
		new ControllerGenerator().generate("User", null, "template-controller.txt");
	}

}
