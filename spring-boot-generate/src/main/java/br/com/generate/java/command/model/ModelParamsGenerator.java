package br.com.generate.java.command.model;

/**
 * Base class used to generate parameters inside a model class
 * 
 * @author Nicolás Arias
 */
public class ModelParamsGenerator {
	
	private static final String LINE_BREAK = "\n";

	private final String dbColumnName;
	private final String fieldType;
	private final String fieldName;

	public ModelParamsGenerator(String dbColumnName, String fieldType, String fieldName) {
		this.dbColumnName = dbColumnName;
		this.fieldType = fieldType;
		this.fieldName = fieldName;
	}
	
	protected StringBuilder getDbColumn() {
		return new StringBuilder().append("\t@Column(name = \"")
				.append(dbColumnName)
				.append("\")");
	}

	protected StringBuilder getFieldNameAndType() {
		return new StringBuilder()
				.append("\tprivate ")
				.append(fieldType)
				.append(" ")
				.append(fieldName)
				.append(";");
	}
	
	public String buildCompleteField() {
		return new StringBuilder().append(LINE_BREAK)
				.append(getDbColumn())
				.append(LINE_BREAK)
				.append(getFieldNameAndType())
				.append(LINE_BREAK)
				.toString();
	}
}
