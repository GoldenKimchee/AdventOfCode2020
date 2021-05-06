import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayFive {
    public static void main(String[] args) throws FileNotFoundException{
        // Provide fully qualified path  OR  Path starting from project folder
        String[] data = processFile("src\\main\\java\\DayFive.txt");
        System.out.println(binaryParition(data));
    }

    public static String[] processFile(String path) throws FileNotFoundException {
        List<String> dataToProcess = new ArrayList<>();
        int ind = 0;
        try {
            Scanner reader = new Scanner(new File(path));
            while (reader.hasNext()) {
                dataToProcess.add(reader.next());
            }
        } catch(FileNotFoundException error) {
            System.out.println("File not found: " + error);
        }
        String[] data= new String[dataToProcess.size()];
        dataToProcess.toArray(data);
        return data;
    }
    /*
    --- Day 5: Binary Boarding ---
    A seat might be specified like FBFBBFFRLR, where F means "front", B means "back", L means "left", and R means "right".
    The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane
    (numbered 0 through 127). Each letter tells you which half of a region the given seat is in. Start with the whole
    list of rows; the first letter indicates whether the seat is in the front (0 through 63) or the back (64 through 127).
    The next letter indicates which half of that region the seat is in, and so on until you're left with exactly one row.

    For example, consider just the first seven characters of FBFBBFFRLR:

    Start by considering the whole range, rows 0 through 127.
    F means to take the lower half, keeping rows 0 through 63.
    B means to take the upper half, keeping rows 32 through 63.
    F means to take the lower half, keeping rows 32 through 47.
    B means to take the upper half, keeping rows 40 through 47.
    B keeps rows 44 through 47.
    F keeps rows 44 through 45.
    The final F keeps the lower of the two, row 44.
    The last three characters will be either L or R; these specify exactly one of the 8 columns of seats on the
    plane (numbered 0 through 7). The same process as above proceeds again, this time with only three steps.
    L means to keep the lower half, while R means to keep the upper half.

    For example, consider just the last 3 characters of FBFBBFFRLR:

    Start by considering the whole range, columns 0 through 7.
    R means to take the upper half, keeping columns 4 through 7.
    L means to take the lower half, keeping columns 4 through 5.
    The final R keeps the upper of the two, column 5.
    So, decoding FBFBBFFRLR reveals that it is the seat at row 44, column 5.

    Every seat also has a unique seat ID: multiply the row by 8, then add the column. In this example, the seat
    has ID 44 * 8 + 5 = 357.

    Here are some other boarding passes:

    BFFFBBFRRR: row 70, column 7, seat ID 567.
    FFFBBBFRRR: row 14, column 7, seat ID 119.
    BBFFBBFRLL: row 102, column 4, seat ID 820.
    As a sanity check, look through your list of boarding passes. What is the highest seat ID on a boarding pass?
     */

    public static int binaryParition(String[] data) {
        int highestSeatID = 0;
        // Each seat has 10 letters
        for (int seat = 0; seat < data.length; seat++) {
            int row = findRowOrColumn(data[seat].substring(0, 7), 127);
            int column = findRowOrColumn(data[seat].substring(7,10), 7);
            int seatID = row * 8 + column;
            if (seatID > highestSeatID) {
                highestSeatID = seatID;
            }
        }
        return highestSeatID;
    }

    // Helper functions to process the two parts
    // Works for . . . Rows 0-127 (128 rows) or Columns 0-7 (8 rows)
    public static int findRowOrColumn(String nums, int end) {
        int start = 0;

        for (int ind = 0; ind < nums.length(); ind++) {  // For all letters except the last one
            char currentChar = nums.charAt(ind);
            // If do not type wrap in float, it (e.g.) turns 1.5 into 1.0
            float actualRange = (float) (end - start) / 2;  // Rounds down if int, so need float; not sure to round up or down YET
            actualRange = Math.abs(actualRange);
            int range = (int) Math.ceil(actualRange);
            if (actualRange == 0.5) {  // If on the final iteration
                if (currentChar == 'F' || currentChar == 'L') {
                    return start;
                } else if (currentChar == 'B' || currentChar == 'R') {
                    return end;
                }
            }
            // Otherwise check for the first 6 letters
            if (currentChar == 'F' || currentChar == 'L') { // Front/Left = lower half
                end -= range;
            } else if (currentChar == 'B' || currentChar == 'R') {  // Back/Right = higher half
                start += range;
            }
        }
        return 2;  // Should never be returned
    }
}
