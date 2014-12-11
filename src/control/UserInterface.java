package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Reaktor on 11.12.2014.
 */
public class UserInterface {
    public static void showMenu() {
        BaseManager baseManager = new BaseManager();
        while (true) {
            System.out.print("key : ");
            String inputData = inputFromKeyboard();
            if (inputData.equals("AG")) {
                baseManager.addGroups();
            } else if (inputData.equals("LG")) {
                baseManager.outTableGroups();
            } else if (inputData.equals("AS")) {
                baseManager.addStudents();
            }  else if (inputData.equals("E")) {
                break;
            }
        }
    }

    private static String inputFromKeyboard() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputData = reader.readLine();
            return inputData;
        } catch (IOException e) {
            System.out.println("Bad data");
            e.printStackTrace();
        }
        return "";
    }
}
