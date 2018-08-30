package br.com.generate.java.command.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author root
 */
public class ModelClassNameHolderTest {
	
	@Test
	public void testInstanceIsCreatedFromClassName() {
		final ModelClassNameHolder modelClassNameHolder = new ModelClassNameHolder(new String[] { "User" });
		
		assertEquals("User", modelClassNameHolder.getClassName());
		assertEquals("users", modelClassNameHolder.getDbTableName());
	}
	
	@Test
	public void testInstanceIsCreatedFromClassNameAndDbTableName() {
		final ModelClassNameHolder modelClassNameHolder = new ModelClassNameHolder(new String[] { "user_table", "User" });
		
		assertEquals("User", modelClassNameHolder.getClassName());
		assertEquals("user_table", modelClassNameHolder.getDbTableName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsThrownIfParamsHaveInvalidLength() {
		new ModelClassNameHolder(new String[] { "user_table", "User", "String" });
	}
	
	
}
