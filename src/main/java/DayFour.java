import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayFour {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(validPassports("C:\\Users\\Rachel\\Desktop\\java_stuf\\Advent\\src\\main\\java" +
                "\\DayFour.txt"));
    }

    /*
    --- Day 4: Passport Processing ---
    The expected fields are as follows:

    byr (Birth Year)
    iyr (Issue Year)
    eyr (Expiration Year)
    hgt (Height)
    hcl (Hair Color)
    ecl (Eye Color)
    pid (Passport ID)
    cid (Country ID)

    Passport data is validated in batch files (your puzzle input). Each passport is represented as a sequence of
    key:value pairs separated by spaces or newlines. Passports are separated by blank lines.
    Here is an example batch file containing four passports:

    ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
    byr:1937 iyr:2017 cid:147 hgt:183cm

    iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
    hcl:#cfa07d byr:1929

    hcl:#ae17e1 iyr:2013
    eyr:2024
    ecl:brn pid:760753108 byr:1931
    hgt:179cm

    hcl:#cfa07d eyr:2025 pid:166559648
    iyr:2011 ecl:brn hgt:59in

    The first passport is valid - all eight fields are present.
    The second passport is invalid - it is missing hgt (the Height field).
    The third passport is interesting; the only missing field is cid, so it looks like data from North Pole
    Credentials, not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing
    cid fields. Treat this "passport" as valid.
    The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not,
    so this passport is invalid.
    According to the above rules, your improved system would report 2 valid passports.
    Count the number of valid passports - those that have all required fields. Treat cid as optional.
    In your batch file, how many passports are valid?
     */

    public static Integer validPassports(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new File(path));
        Integer totalPassports = 0;
        String toProcess = "";  // hold passport's info to later process if valid
        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (line.length() != 0) {  // If not blank line, continue to add the line
                toProcess = toProcess.concat(line + " ");  // Strings are immutable, so concat returns a new string!!
                continue;
            }
            // Once encounter a blank line, that is one passport
            boolean valid = checkIfValid(toProcess);
            if (valid) {
                totalPassports++;
            }
            toProcess = "";  // Reset to process next passport details
        }
        // For the last passport (otherwise will not count it even if valid due to the condition of the while loop)
        boolean valid = checkIfValid(toProcess);
        if (valid) {
            totalPassports++;
        }
        return totalPassports;
    }

    public static boolean checkIfValid(String toProcess) {
        String[] fields = new String[8];  // Each passport has a maximum of 8 fields
        int ind = 0;
        Scanner passportDetails = new Scanner(toProcess);
        while (passportDetails.hasNext()) {
            String token = passportDetails.next();
            String field = token.substring(0, token.indexOf(":"));
            fields[ind] = field;
            ind++;
        }  // Now all fields that were present are added to the fields array

        if (ind < 7) {  // If two or more fields are missing, automatically invalid
            return false;
        } else if (ind == 7) {  // If only one field missing, check if its cid
            for (int i = 0; i <= 6; i++) {
                if (fields[i].equals("cid")) {  // If any of the fields equal cid, that means it is missing a
                    return false;               // non-cid field; this means it is not valid!
                }
            }
        }
        assert ind == 8; // Or if only missing cid field = viable passport
        return true;
    }
}
