import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class DayFour {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(validPassports("C:\\Users\\Rachel\\Desktop\\java_stuf\\Advent\\src\\main\\java" +
                "\\DayFour.txt"));
        System.out.println(updatedValidPassports("C:\\Users\\Rachel\\Desktop\\java_stuf\\Advent\\src\\main\\java" +
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

    // Checks each passport if it is valid (helper function)
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

    /*
    --- Part Two ---
    The line is moving more quickly now, but you overhear airport security talking about how passports with invalid
    data are getting through. Better add some data validation, quick!
    You can continue to ignore the cid field, but each other field has strict rules about what values are valid for
    automatic validation:

    byr (Birth Year) - four digits; at least 1920 and at most 2002.
    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    hgt (Height) - a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    pid (Passport ID) - a nine-digit number, including leading zeroes.
    cid (Country ID) - ignored, missing or not.
    Your job is to count the passports where all required fields are both present and valid according to the above
    rules. Here are some example values:

    Count the number of valid passports - those that have all required fields and valid values.
    Continue to treat cid as optional. In your batch file, how many passports are valid?
     */

    public static Integer updatedValidPassports(String path) throws FileNotFoundException {
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
            if (updatedCheckIfValid(toProcess) && checkIfValid(toProcess)) {
                totalPassports++;
            }
            toProcess = "";  // Reset to process next passport details
        }
        // For the last passport (otherwise will not count it even if valid due to the condition of the while loop)
        boolean valid = updatedCheckIfValid(toProcess);
        if (valid) {
            totalPassports++;
        }
        return totalPassports;
    }

    private static boolean updatedCheckIfValid(String toProcess) {
        Scanner sentence = new Scanner(toProcess);
        int fields = 0;
        check:
            while (sentence.hasNext()) {
                String fullField = sentence.next();
                String[] field = fullField.split(":");
                String category = field[0];
                String details = field[1];

                switch (category) {
                    case "byr": {
                        fields++;
                        // byr (Birth Year) - four digits; at least 1920 and at most 2002.
                        int num_details = Integer.parseInt(details);  // Also accessible by other cases.
                        if (num_details >= 1920 && num_details <= 2002) {
                            continue check;
                        }
                        return false;
                    }

                    case "iyr": {
                        fields++;
                        // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
                        int num_details = Integer.parseInt(details);
                        if (num_details >= 2010 && num_details <= 2020) {
                            continue check;
                        }
                        return false;
                    }

                    case "eyr": {
                        fields++;
                        // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
                        int num_details = Integer.parseInt(details);
                        if (num_details >= 2020 && num_details <= 2030) {
                            continue check;
                        }
                        return false;
                    }

                    case "hgt":
                        fields++;
                        // hgt (Height) - a number followed by either cm or in:
                        // If cm, the number must be at least 150 and at most 193.
                        // If in, the number must be at least 59 and at most 76.
                        int lastInd = details.length() - 2;
                        if (lastInd < 2) {  // There is no cm/in or number is less than two digits.
                            return false;
                        }
                        int units = Integer.parseInt(details.substring(0, lastInd));
                        if (details.endsWith("in")) {
                            if (units >= 59 && units <= 76) {
                                continue check;
                            }
                        } else if (details.endsWith("cm")) {
                            if (units >= 150 && units <= 193) {
                                continue check;
                            }
                        }
                        return false;

                    case "hcl":
                        fields++;
                        // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
                        if (details.charAt(0) == '#' && details.length() == 7) {  // 7 characters long counting '#'
                            if (checkHcl(details)) {
                                continue check;
                            }
                        }
                        return false;

                    case "ecl":
                        fields++;
                        // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
                        if (checkEcl(details)) {
                            continue check;
                        }
                        return false;

                    case "pid":
                        fields++;
                        // pid (Passport ID) - a nine-digit number, including leading zeroes.
                        if (details.length() == 9) {
                            continue check;
                        }
                        return false;

                    case "cid":
                        // cid (Country ID) - ignored, missing or not.
                        continue check;
                }
            }
        return true;
    }

    public static boolean checkHcl(String details) {
        String[] validDetails = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        for (int ind = 1; ind <= 6; ind++) {
            for (String detail: validDetails) {
                String stringDetail = Character.toString(details.charAt(ind));  // Or String.valueOf(details.charAt(ind))
                if (stringDetail.equals(detail)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkEcl(String details) {
        String[] validDetails = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
            for (String detail: validDetails) {
                if (details.equals(detail)) {
                    return true;
                }
            }
        return false;
    }
}
