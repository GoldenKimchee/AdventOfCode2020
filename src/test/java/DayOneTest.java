import org.junit.Test;
import org.junit.Assert;

public class DayOneTest {
    @Test
    public void testDay1() {
        Integer[][] testCases= {
                {1721, 979, 366, 299, 675, 1456},
                {2020, 0}
        };

        Integer[] expected = {
                514579,
                0
        };

        for (int testCase = 0; testCase < testCases.length; testCase++) {
            Integer actual = DayOne.twoProduct(testCases[testCase]);
            try {
                Assert.assertEquals(expected[testCase], actual);
            } catch (AssertionError e) {
                System.out.println("The expected and actual don't match: " + e);
                Assert.fail();
            }
        }
        System.out.println("testDay_1 test passes.");
    }

    @Test
    public void testDay1_part2() {
        Integer[][] testCases = {
                {1721, 979, 366, 299, 675, 1456}
        };

        Integer[] expected = {
                241861950
        };

        for (int testCase = 0; testCase < testCases.length; testCase++) {
            Integer actual = DayOne.threeProduct(testCases[testCase]);
            try {
                Assert.assertEquals(actual, expected[testCase]);
            } catch (AssertionError e) {
                System.out.println("The expected and actual don't match: " + e);
                Assert.fail();
            }

        }

    }

}
