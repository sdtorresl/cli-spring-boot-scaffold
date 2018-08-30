package br.com.generate.java.command.service;

import java.io.IOException;

import br.com.generate.Layers;
import br.com.generate.ReadTemplateFile;

public class ServiceGenerator extends ReadTemplateFile {

	@Override
	public String getLayer() {
		return Layers.SERVICE;
	}

	@Override
	protected String operationGenerate(String javaStrings, String nameClass, String parameters) {
		return javaStrings.replace("${package}", getPackage() + ".service")
				.replace("${package_model}", getPackage() + ".model")
				.replace("${package_repository}", getPackage() + ".repository")
				.replace("${className}", modelClassNameHolder.getClassName())
				.replace("${paramClassName}", modelClassNameHolder.getClassName().toLowerCase());

	}

	public static void main(String[] args) throws IOException {
		new ServiceGenerator().generate("User", null, "template-service.txt");
	}
	
}

