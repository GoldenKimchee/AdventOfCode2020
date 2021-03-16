import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(twoProduct());
        System.out.println(threeProduct());
    }
    /*
    --- Day 1: Report Repair ---
    After saving Christmas five years in a row, you've decided to take a vacation at a nice resort on a tropical island.
    Surely, Christmas will go on without you.

    Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input); apparently,
    something isn't quite adding up.

    Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.

    For example, suppose your expense report contained the following:

            1721
            979
            366
            299
            675
            1456

    In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces
    1721 * 299 = 514579, so the correct answer is 514579.

    Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you
    multiply them together?

     */

    public static Integer twoProduct() {
        // First, lets use a stream to sort them from least to greatest.
        List<Integer> list = new ArrayList<>();
        try {
            Scanner fileProcessor = new Scanner(new File("C:\\Users\\Rachel\\Desktop\\Advent\\src\\main\\java\\DayOneCase.txt"));
            while (fileProcessor.hasNext()) {
                Integer toAppend = Integer.parseInt(fileProcessor.next());
                list.add(toAppend);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + e);
            return null;
        }

        Integer[] arrayList = new Integer[list.size()];
        list.toArray(arrayList);
        Arrays.sort(arrayList);

        // Go one by one number, comparing to the rest of the numbers to see what adds to 2020
        for (int i = 0; i < arrayList.length; i++) {
            // calculate what the other number should equal to give 2020 sum
            Integer otherNumberTarget = 2020 - arrayList[i];
            if (otherNumberTarget < 0) { // if the number we are at is already greater than 2020
                continue; //skip this iteration
            }

            // Basically do binary search here to try find the number that would add to 2020
            Integer otherNumber = findOtherNumber(arrayList, otherNumberTarget, i);
            if (otherNumber != null) {
                return arrayList[otherNumber] * arrayList[i];
            }
        }
        return null;
    }

    public static Integer twoProduct(Integer[] list) {
        // First, lets use a stream to sort them from least to greatest.
        Arrays.sort(list);

        // Go one by one number, comparing to the rest of the numbers to see what adds to 2020
        for (int i = 0; i < list.length; i++) {

            // calculate what the other number should equal to give 2020 sum
            Integer otherNumberTarget = 2020 - list[i];
            if (otherNumberTarget < 0) { // if the number we are at is already greater than 2020
                continue; //skip this iteration
            }

            // Basically do binary search here to try find the number that would add to 2020
            Integer otherNumber = findOtherNumber(list, otherNumberTarget, i);
            if (otherNumber != null) {
                return list[otherNumber] * list[i];
            }
        }
        return null;
    }

    // basically binary search
    public static Integer findOtherNumber(Integer[] list, Integer target, int index) {
        int high = list.length - 1;
        int low = 0;

        while (low <= high) {
            int mid = (high + low) / 2;
            if (list[mid].equals(target) && mid != index) { //list[mid] == target does not work;
                                                            //Integer is not int and operator == won't work, only on primitive types
                return mid;
            } else if (list[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return null;
    }

        /*
    --- Part Two ---
    The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they had left over
    from a past vacation. They offer you a second one if you can find three numbers in your expense report that meet
    the same criteria.
    Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them together
    produces the answer, 241861950.
    In your expense report, what is the product of the three entries that sum to 2020?
     */

    public static Integer threeProduct(Integer[] list) {
        //Go thru each number
        for (int firstNum = 0; firstNum < list.length; firstNum++) {
            Integer first = list[firstNum];
            for (int secondNum = 0; secondNum < list.length; secondNum++) {
                if (secondNum == firstNum) {
                    continue;
                }
                Integer second = list[secondNum];
                Integer sum = first + second;
                Integer remainder = 2020 - sum;
                if (remainder < 0) {
                    continue;
                }
                for (int thirdNum = 0; thirdNum < list.length; thirdNum++) {
                    Integer third = list[thirdNum];
                    if (third.equals(remainder)) {
                        return first * second * third;
                    }
                }
            }
        }
        return null;
    }

    public static Integer threeProduct() {
        List<Integer> list = new ArrayList<>();
        try {
            Scanner fileProcessor = new Scanner(new File("C:\\Users\\Rachel\\Desktop\\Advent\\src\\main\\java\\DayOneCase.txt"));
            while (fileProcessor.hasNext()) {
                Integer toAppend = Integer.parseInt(fileProcessor.next());
                list.add(toAppend);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file: " + e);
            return null;
        }

        Integer[] arrayList = new Integer[list.size()];
        list.toArray(arrayList);
        Integer num = threeProduct(arrayList);
        return num;
    }
}
