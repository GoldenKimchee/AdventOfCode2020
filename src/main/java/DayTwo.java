import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
For example, suppose you have the following list:

1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc
Each line gives the password policy and then the password. The password policy indicates the lowest and highest number
of times a given letter must appear for the password to be valid. For example, 1-3 a means that the password must
contain a at least 1 time and at most 3 times.

In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b, but
needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the limits of their
respective policies.

How many passwords are valid according to their policies?
 */
public class DayTwo {
    public static void main(String[] args) throws FileNotFoundException {
        String[] toProcess = validPasswordWithFile();
        int answer = validPassword(toProcess);
        System.out.println(answer);
        System.out.println(newValidPassword());
    }

    public static String[] validPasswordWithFile() {
        try {
            Scanner console = new Scanner(new File("C:\\Users\\Rachel\\Desktop\\Advent\\src\\main\\java\\DayTwoCase.txt"));
            int lengthOfFile = 0;
            while (console.hasNextLine()) { //WARNING!
                console.nextLine();         //by doing this for the whole list, we run through till end of list
                lengthOfFile++;
            }
            String[] passwords = new String[lengthOfFile];
            int ind = 0;
            Scanner newConsole = new Scanner(new File("C:\\Users\\Rachel\\Desktop\\Advent\\src\\main\\java\\DayTwoCase.txt"));
            while (newConsole.hasNextLine()) {  //have to reset it for here, otherwise we will get null for the rest
                passwords[ind] = newConsole.nextLine();
                ind++;
            }
            return passwords;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
        }
        return null;
    }

    public static int validPassword(String[] passwords) {
        int toReturn = 0;
        for (int i = 0; i < passwords.length; i++) {
            Scanner stringReader = new Scanner(passwords[i]);
            String range = stringReader.next();
            String preMin = "";
            String preMax = ""; // "10-12"
            int skip = range.indexOf("-");
            for (int j = 0; j < skip; j++) {
                String item = range.substring(j, j + 1);
                preMin = preMin.concat(item);
            }
            for (int p = skip + 1; p < range.length(); p++) {
                String item = range.substring(p, p + 1);
                preMax = preMax.concat(item);
            }
            int max = Integer.parseInt(preMax);
            int min = Integer.parseInt(preMin);

            String toEdit = stringReader.next();
            char letter = toEdit.charAt(0);
            String password = stringReader.next();
            int occurances = 0;
            for (int eachIndex = 0; eachIndex < password.length(); eachIndex++) {
                char letterAt = password.charAt(eachIndex);
                if (letterAt == letter) {
                    occurances++;
                }
            }
            if (occurances <= max && occurances >= min) {
                toReturn++;
            }
        }
        return toReturn;
    }

    /*
    Each policy actually describes two positions in the password, where 1 means the first character,
    2 means the second character, and so on. (Be careful; Toboggan Corporate Policies have no concept of
    "index zero"!) Exactly one of these positions must contain the given letter. Other occurrences of the
    letter are irrelevant for the purposes of policy enforcement.

    Given the same example list from above:

    1-3 a: abcde is valid: position 1 contains a and position 3 does not.
    1-3 b: cdefg is invalid: neither position 1 nor position 3 contains b.
    2-9 c: ccccccccc is invalid: both position 2 and position 9 contain c.
    How many passwords are valid according to the new interpretation of the policies?
     */

    public static int newValidPassword() throws FileNotFoundException {
        try {
            Scanner console = new Scanner(new File("C:\\Users\\Rachel\\Desktop\\Advent\\src\\main\\java\\DayTwoCase.txt"));
            List<String> passwords = new ArrayList<>();
            while (console.hasNextLine()) {
                String toAdd = console.nextLine();
                passwords.add(toAdd);
            }
            int total = 0;
            for (String password: passwords) {
                Scanner readPassword = new Scanner(password);
                String conditions = readPassword.next();
                int dash = conditions.indexOf("-");
                String firstPosition = conditions.substring(0,dash);
                int firstPos = Integer.parseInt(firstPosition);
                String secondPosition = conditions.substring(dash+1);
                int secondPos = Integer.parseInt(secondPosition);

                String characterAndColon = readPassword.next();
                char letter = characterAndColon.charAt(0);
                String passwordToCheck = readPassword.next();

                boolean conditionMet = false;
                for (int i = 0; i < passwordToCheck.length(); i++) {
                    if (passwordToCheck.charAt(i) == letter) {
                        if (i + 1 == firstPos) {
                            if (conditionMet) {
                                break;
                            }
                            conditionMet = true;
                        } else if (i + 1 == secondPos) {
                            if (conditionMet) {
                                break;
                            }
                            conditionMet = true;
                        }
                    }
                    if (i == passwordToCheck.length() - 1) { //if at last iteration
                        if (conditionMet) {
                            total++;
                        }
                    }
                }
            }
            return total;
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }
}
