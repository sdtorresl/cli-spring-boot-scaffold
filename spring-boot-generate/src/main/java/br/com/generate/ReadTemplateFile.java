package br.com.generate;

import br.com.generate.java.command.model.ModelClassNameHolder;
import java.io.IOException;

/**
 * @author NetoDevel
 */
public abstract class ReadTemplateFile extends AbstractGenerate {

	protected ModelClassNameHolder modelClassNameHolder;
	
	protected abstract String operationGenerate(String javaStrings, String nameClass, String parameters);

	private GenerateValidator validatorGenerate = new GenerateValidator();
	
	@Override
	public boolean generate(String nameClass, String parameters, String fileNameTemplate) throws IOException {
		if (validatorGenerate.validate(nameClass, parameters, getLayer())) {
			modelClassNameHolder = new ModelClassNameHolder(nameClass.split(":"));
			String javaStrings = readTemplateFile(fileNameTemplate);
			String newStrings = operationGenerate(javaStrings, nameClass, parameters);
			outPutFile(newStrings, modelClassNameHolder.getClassName());
			return true;
		}
		return false;
	}

}
