package br.com.generate.java.command.model;

import java.io.IOException;

import br.com.generate.Layers;
import br.com.generate.ReadTemplateFile;
import br.com.generate.migrate.Migrations;
import br.com.util.ModelGenerateUtils;

/**
 * @author NetoDevel
 */
public class ModelGenerator extends ReadTemplateFile {
	
	private final ModelParamsGeneratorFactory modelParamsGeneratorFactory;

	public ModelGenerator() {
		modelParamsGeneratorFactory = new ModelParamsGeneratorFactory();
	}
	
	@Override
	public String getLayer() {
		return Layers.MODEL;
	}

	@Override
	protected String operationGenerate(String javaStrings, String nameClass, String parameters) {
		return javaStrings.replace("${package}", getPackage() + ".model")
				.replace("${imports}", ModelGenerateUtils.generateImports(parameters))
				.replace("${className}", nameClass)
				.replace("${name_table}", nameClass.toLowerCase() + "s")
				.replace("${parameters}", generateParams(parameters))
				.replace("${getters}", ModelGenerateUtils.generateGettersAndSetters(parameters));
	}
	
	public String generateParams(String params) {
		String[] variablesSplits = params.split(" ");
		StringBuilder modelParameters = new StringBuilder();
		
		for (int i = 0; i < variablesSplits.length; i++) {

			String [] typeAndNameVars = variablesSplits[i].split(":");
			
			final ModelParamsGenerator modelParamsGenerator = modelParamsGeneratorFactory.getModelParamsGenerator(typeAndNameVars);
			modelParameters.append(modelParamsGenerator.buildCompleteField());
		}
		return modelParameters.toString();
	}

	public static void main(String[] args) throws IOException  {
		new ModelGenerator().generate("User", "name:String mail:String age:Integer dataCreated:Date", "template-model.txt");
		Migrations migrations = new Migrations();
		try {
			migrations.create("User", "name:String mail:String age:Integer dataCreated:Date");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
