package testIntegration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({MateTest.class, MoveDecoratorTest.class, PawnTest.class})
public class TestSuiteIntegration {

}
