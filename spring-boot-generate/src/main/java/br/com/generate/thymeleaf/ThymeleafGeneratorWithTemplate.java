package br.com.generate.thymeleaf;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class ThymeleafGeneratorWithTemplate extends ThymeleafGenerator {

	public ThymeleafGeneratorWithTemplate(String className, String parameters, String templateDir) throws IOException {
        super(className, parameters, templateDir);
	}
    
    @Override
    protected String getHtmlString(String resource) throws IOException {
        String templateDir = super.getTemplateDir();
        File layoutFile = new File(templateDir + File.separator + resource);
		InputStream layoutInputStream = new FileInputStream(layoutFile);
        String htmlString = IOUtils.toString(layoutInputStream, null);
        return htmlString;
    }
}
