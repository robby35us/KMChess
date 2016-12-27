package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import testComponents.TestSuiteComponents;
import testConstraints.TestSuiteConstraints;
import testIntegration.TestSuiteIntegration;

@RunWith(Suite.class)
@SuiteClasses({TestSuiteComponents.class, 
			   TestSuiteConstraints.class,
			   TestSuiteIntegration.class})
public class Test{

	public static final boolean SHOW_DISPLAY = false;

	public void runTestSuite(){
		
	}
}