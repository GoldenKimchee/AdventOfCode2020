import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) throws FileNotFoundException{
        String[] testCase = processFile("C:\\Users\\Rachel\\Desktop\\java_stuf\\Advent\\src\\main\\java\\DayThreeCase.txt");
        System.out.println(findTrees(testCase));
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

    public static Integer findTrees(String[] map) throws FileNotFoundException {
        int rowLength = map[0].length();
        // Starting positions
        int index = 0;
        int row = 0;
        int trees = 0;

        while (row < map.length - 1) {
            index += 3;
            index = index % rowLength;
            row++;
            char plot = map[row].charAt(index);
            if (plot == '#') {
                trees++;
            }
        }
        return trees;
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
