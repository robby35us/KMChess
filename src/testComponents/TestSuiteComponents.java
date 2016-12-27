package testComponents;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({BoardTest.class, KingTest.class, PieceTest.class, PlayerTest.class, PlayerSetTest.class, SpaceTest.class})
public class TestSuiteComponents {

}
