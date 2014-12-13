package userInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sdovhtc on 12/11/2014.
 */
public class KeyboardInput {
    public static String input() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputData = reader.readLine();
            return inputData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String inputValidQueryData(String inputQuery, String pattern) {
        while (true) {
            System.out.print("Input " + inputQuery + "(!e - Exit): ");
            String inputData = input();
            if (inputData.equals("!e")) {
                return "!e";
            } else if (Validation.validate(inputData, pattern)) {
                return inputData;
            } else {
                System.out.println("Bad " + inputQuery + "!");
            }
        }
    }

    public static int selectId(String inputQuery) {
        while (true) {
            System.out.print("Select " + inputQuery + " ID: ");
            String inputData = input();
            if (!inputData.equals("!e")) {
                if (Validation.validate(inputData, Validation.ID_PATTERN)) {
                    return Integer.parseInt(inputData);
                } else {
                    System.out.println("Bad " + inputQuery + " ID!");
                }
            } else {
                return -1;
            }
        }
    }
}
