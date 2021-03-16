import org.junit.Assert;
import org.junit.Test;
import java.io.FileNotFoundException;

public class DayThreeTest {

    public static String[][] generateTestCases() {
        String[] t1 = {"..##.........##.........##.........##.........##.........##.......",
                       "#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..",
                       ".#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.",
                       "..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#",
                       ".#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.",
                       "..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....",
                       ".#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#",
                       ".#........#.#........#.#........#.#........#.#........#.#........#",
                       "#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...",
                       "#...##....##...##....##...##....##...##....##...##....##...##....#",
                       ".#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#"};
        String[] t2 = {""};
        String[] t3 = {"..#..#",
                       ".#.#.#",
                       "#....#"};

        String[][] testCases = {t1, t2, t3};

        return testCases;
    }

    @Test
    public void testDay3() throws FileNotFoundException {
        Integer[] expectedCases = {7, 0, 2};
        String[][] testCases = generateTestCases();

        for (int i = 0; i < testCases.length; i++) {
            Integer actual = DayThree.findTrees(testCases[i]);
            Integer expected = expectedCases[i];
            try {
                Assert.assertEquals(actual, expected);
            } catch (AssertionError assertError) {
                System.out.println("Actual and expected do not match: " + assertError);
                Assert.fail();
            }
        }

        System.out.println("All tests passed.");
    }
}
