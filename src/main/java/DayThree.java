import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) throws FileNotFoundException{
        String[] testCase = processFile("C:\\Users\\Rachel\\Desktop\\java_stuf\\Advent\\src\\main\\java\\DayThreeCase.txt");
        System.out.println(findTrees(testCase, 3, 1));
        travelCombos(testCase);
    }
    /*
    --- Day 3: Toboggan Trajectory ---
    With the toboggan login problems resolved, you set off toward the airport. While travel by toboggan might be easy,
    it's certainly not safe: there's very minimal steering and the area is covered in trees. You'll need to see which
    angles will take you near the fewest trees.

    Due to the local geology, trees in this area only grow on exact integer coordinates in a grid.
    You make a map (your puzzle input) of the open squares (.) and trees (#) you can see.

    These aren't the only trees, though; due to something you read about once involving arboreal genetics and
    biome stability, the same pattern repeats to the right many times:
    You start on the open square (.) in the top-left corner and need to reach the bottom (below the
    bottom-most row on your map).

    From your starting position at the top-left, check the position that is right 3 and down 1. Then, check
    the position that is right 3 and down 1 from there, and so on until you go past the bottom of the map.
    */
    public static Integer findTrees(String[] map, int right, int down) {
        int rowLength = map[0].length();
        // Starting positions
        int index = 0;
        int row = 0;
        int trees = 0;

        char plot = map[row].charAt(index);
        if (plot == '#') {
            trees++;
        }

        while (row < map.length - 1) {
            index += right;
            index = index % rowLength;
            row += down;
            plot = map[row].charAt(index);
            if (plot == '#') {
                trees++;
            }
        }
        return trees;
    }

        /*
        --- Part Two ---
    Time to check the rest of the slopes - you need to minimize the probability of a sudden arboreal stop, after all.

    Determine the number of trees you would encounter if, for each of the following slopes, you start at the top-left
    corner and traverse the map all the way to the bottom:

    Right 1, down 1.
    Right 3, down 1. (This is the slope you already checked.)
    Right 5, down 1.
    Right 7, down 1.
    Right 1, down 2.
    In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s) respectively; multiplied together,
    these produce the answer 336.

    What do you get if you multiply together the number of trees encountered on each of the listed slopes?
     */

    // This HAS to be a long. The answer is too big, will give a wrong answer if you set it as a int instead of long.
    public static long travelCombos(String[] testCase) throws FileNotFoundException {
        System.out.println("_Part_Two__________________");
        int[][] cases = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        long total = 0;

        for (int per_case = 0; per_case < cases.length; per_case++) {
            System.out.printf("Right: %d    Down: %d\n", cases[per_case][0], cases[per_case][1]);
            Integer result = findTrees(testCase, cases[per_case][0], cases[per_case][1]);
            System.out.println("Result: " + result);
            if (per_case == 0) { // if its the first case, we want to add. If multiplied, we would end up with 0
                total += result;
                System.out.println("Total so far: " + total);
                System.out.println("__________________________");
                continue;
            }
            total = total * result;
            System.out.println("Total so far: " + total);
            System.out.println("__________________________");
        }
        return total;
    }

    public static String[] processFile(String path) throws FileNotFoundException {
        List<String> fileData = new ArrayList<>();
        String[] results = null;
        try {
            Scanner console = new Scanner(new File(path));
            while (console.hasNextLine()) {
                String row = console.nextLine();
                fileData.add(row);
            }
            results = new String[fileData.size()];
            fileData.toArray(results);
        } catch (FileNotFoundException error) {
            System.out.println("File not found: " + error);
        } catch (Exception error) {
            System.out.println("Something bad happened: " + error);
        }
        return results;
    }
}
