package br.com.generate.java.command.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link ModelParamsHolder}
 *
 * @author Nicolás Arias
 */
public class ModelParamsHolderTest {

	public ModelParamsHolderTest() {
	}

	@Test
	public void testInstanceIsCreatedFromTypeAndFieldName() {
		ModelParamsHolder modelParamsHolder = new ModelParamsHolder(new String[]{"name", "String"});

		assertEquals("name", modelParamsHolder.getDbColumnName());
		assertEquals("name", modelParamsHolder.getFieldName());
		assertEquals("String", modelParamsHolder.getFieldType());
	}

	@Test
	public void testInstanceIsCreatedFromDbColumnNameTypeAndFieldName() {
		ModelParamsHolder modelParamsHolder = new ModelParamsHolder(new String[]{"name_col", "name", "String"});

		assertEquals("name_col", modelParamsHolder.getDbColumnName());
		assertEquals("name", modelParamsHolder.getFieldName());
		assertEquals("String", modelParamsHolder.getFieldType());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsTrownIfParametersHaveAnInvalidLength() {
		new ModelParamsHolder(new String[]{"name_col"});
	}

}
