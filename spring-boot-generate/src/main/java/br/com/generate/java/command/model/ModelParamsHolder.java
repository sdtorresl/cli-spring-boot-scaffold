/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.generate.java.command.model;

import org.springframework.lang.NonNull;

/**
 * Holds the parameters defined by the user to generate the model class
 *
 * @author Nicolás Arias
 */
public class ModelParamsHolder {

	private final String dbColumnName;
	private final String fieldName;
	private final String fieldType;

	/**
	 * Creates an instance this class from String varargs containing a field type, 
	 * a field name and, optionally, the corresponding database column name. If
	 * the varargs have a length different than 2 or 3 an exception is thrown
	 * 
	 * @param params varargs containing the field information
	 * @throws IllegalArgumentException if the varargs have a length different than
	 * 2 or 3
	 */
	public ModelParamsHolder(@NonNull String... params) {
		switch (params.length) {
			case 2:
				dbColumnName = params[0];
				fieldName = params[0];
				fieldType = params[1];
				break;
			case 3:
				dbColumnName = params[0];
				fieldName = params[1];
				fieldType = params[2];
				break;
			default:
				throw new IllegalArgumentException("Params must have a length of 2 or 3.");
		}
	}
	
	public String getDbColumnName() {
		return dbColumnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}
}
