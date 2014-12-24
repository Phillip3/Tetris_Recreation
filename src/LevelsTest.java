import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class LevelsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
	//Levels.setNameAndScore("Bill", 100, 200, 300);
	int[] a = Levels.getScoreNumbLinesLevelForName("Bill");
	assertEquals(100, a[0]);
	assertEquals(200, a[1]);
	assertEquals(300, a[2]);
    }

}
