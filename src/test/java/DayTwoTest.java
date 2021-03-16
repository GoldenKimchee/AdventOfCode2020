import org.junit.Assert;
import org.junit.Test;

public class DayTwoTest {
    @Test
    public void testDayTwo() {
        String[][] testCases = {
                {"1-3 a: abcde",
                "1-3 b: cdefg",
                "2-9 c: ccccccccc"},
                {}
        };

        int[] expected = {
                2,
                0
        };

        for (int testCase = 0; testCase < testCases.length; testCase++) {
            int actual = DayTwo.validPassword(testCases[testCase]);
            try {
                Assert.assertEquals(actual, expected[testCase]);
            } catch (AssertionError error) {
                System.out.println("The expected and actual do not match: " + error);
                Assert.fail();
            }
        }
    }
}
