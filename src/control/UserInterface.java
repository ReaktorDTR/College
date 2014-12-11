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
        baseManager.addGroup("Root");
        baseManager.addGroup("User");
        baseManager.addStudent("Serhii","Dovhanuk",1);
        baseManager.addStudent("Serhii","Kurdulo",1);
        baseManager.addStudent("Marc","Adamov",2);
        baseManager.addStudent("Marcy","De-Pase",2);
        baseManager.addStudent("Lina","Rosario",2);
        baseManager.addStudent("Ayanami","Rei",1);
        while (true) {
            System.out.print("key : ");
            String inputData = inputFromKeyboard();
            if (inputData.equals("ag")) {
                baseManager.addGroups();
            } else if (inputData.equals("lg")) {
                baseManager.outTableGroups();
            } else if (inputData.equals("as")) {
                baseManager.addStudents();
            } else if (inputData.equals("ls")) {
                baseManager.outTableStudents();
            } else if (inputData.equals("ar")) {
                baseManager.selectIdStudent(baseManager.selectIdGroup());
            }  else if (inputData.equals("e")) {
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
