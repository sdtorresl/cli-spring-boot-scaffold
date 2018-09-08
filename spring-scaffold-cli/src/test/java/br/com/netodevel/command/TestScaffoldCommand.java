package br.com.netodevel.command;

import org.junit.Ignore;
import org.junit.Test;

import br.com.netodevel.scaffold.ScaffoldCommand;
import br.com.netodevel.scaffold.ScaffoldHandler;

/**
 * @author NetoDevel
 * @since 0.0.1
 */
public class TestScaffoldCommand {

	@Test
	@Ignore
	public void testRunScaffoldCommand() {
		// TODO Delete folder after run the test
		try {
			new ScaffoldCommand("scaffold", "generate scaffold", new ScaffoldHandler()).run("-n UserModel", "-p name:String email:String idade:Integer dateCreated:Date admin:Boolean");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRunScaffoldCommandWithTemplate() {
		try {
			new ScaffoldCommand("scaffold", "generate scaffold", new ScaffoldHandler()).run("-n UserModel", "-p name:String email:String idade:Integer dateCreated:Date admin:Boolean", "-t /home/sdtorresl/Programming/Java/cli-spring-boot-scaffold/custom-templates");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
