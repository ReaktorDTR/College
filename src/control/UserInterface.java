package control;

import database.LocalStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Reaktor on 11.12.2014.
 */
public class UserInterface {

    private static LocalStorage localStorage = new LocalStorage();

    public static void showMenu() {
        StorageManager storageManager = new StorageManager(localStorage);
        storageManager.
        baseManager.addGroup("Engineers");
        baseManager.addGroup("Mathematics");
        baseManager.addStudent("Serhii", "Dovhanuk", 1);
        baseManager.addStudent("Serhii","Kurdulo",1);
        baseManager.addStudent("Marc","Adamov",2);
        baseManager.addStudent("Marcy","De-Pase",2);
        baseManager.addStudent("Lina","Rosario",2);
        baseManager.addStudent("Ayanami","Rei",1);
        baseManager.addSubject("Math");
        baseManager.addSubject("History");
        baseManager.addSubject("Tech");
        GroupTableManager gm =  GroupManager(localStorage);
        while (true) {
            System.out.print("key : ");
            String inputData = inputFromKeyboard();
            if (inputData.equals("ag")) {
                gm.addGroup();
            } else if (inputData.equals("lg")) {
                baseManager.outTableGroups();
            } else if (inputData.equals("as")) {
                baseManager.addStudents();
            } else if (inputData.equals("ls")) {
                baseManager.outTableStudents();
            } else if (inputData.equals("asub")) {
                baseManager.addSubject();
            } else if (inputData.equals("lsub")) {
                baseManager.outTableSubjects();
            } else if (inputData.equals("artg")) {
                baseManager.addRatingToGroup();
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
