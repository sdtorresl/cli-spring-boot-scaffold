package br.com.generate.java.command.model;

/**
 * Holder class to provide the model class name and database table name based on
 * user input.
 *
 * @author Nicolás Arias
 */
public class ModelClassNameHolder {

	private final String className;

	private final String dbTableName;

	/**
	 * Creates an instance of this class from an String array with the class-name-related
	 * params, which can have a length of 1 (class name and db table name are the same) or 
	 * 2 (db table name is specified).
	 * 
	 * @param classNameParams the class-name-related params entered by the user.
	 */
	public ModelClassNameHolder(String[] classNameParams) {
		switch (classNameParams.length) {
			case 1:
				className = classNameParams[0];
				dbTableName = classNameParams[0].toLowerCase() + "s";
				break;
			case 2:
				className = classNameParams[1];
				dbTableName = classNameParams[0];
				break;
			default:
				throw new IllegalArgumentException("Class name params must have a length of 1 or 2.");
		}
	}

	public String getClassName() {
		return className;
	}

	public String getDbTableName() {
		return dbTableName;
	}
}
