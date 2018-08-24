/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generate.java.command.model;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Nicolás Arias
 */
public class ModelParamsGeneratorFactory {
	
	public ModelParamsGenerator getModelParamsGenerator(String [] typeAndNameVars) {
		if (typeAndNameVars == null || (typeAndNameVars.length != 2 && typeAndNameVars.length != 3)) {
			throw new IllegalArgumentException("Received parameters are not valid");
		} else if(typeAndNameVars.length == 2) {
			return new ModelParamsGenerator(typeAndNameVars[0], typeAndNameVars[1], typeAndNameVars[0]);
		} else {
			return new ModelParamsGenerator(typeAndNameVars[0], typeAndNameVars[2], typeAndNameVars[1]);
		}
		
	}
}
