package userInterface;

import control.StorageManager;
import database.LocalStorage;

/**
 * Created by Reaktor on 11.12.2014.
 */
public class UserInterface {

    private static LocalStorage localStorage = new LocalStorage();

    public static void showMenu() {
        StorageManager storageManager = new StorageManager(localStorage);
        storageManager.groupTableManager.addGroup("Engineers");
        storageManager.groupTableManager.addGroup("Mathematics");
        storageManager.studentTableManager.addStudent("Serhii", "Dovhanuk", 1);
        storageManager.studentTableManager.addStudent("Serhii", "Kurdulo", 1);
        storageManager.studentTableManager.addStudent("Marc", "Adamov", 2);
        storageManager.studentTableManager.addStudent("Marcy", "De-Pase", 2);
        storageManager.studentTableManager.addStudent("Lina", "Rosario", 2);
        storageManager.studentTableManager.addStudent("Ayanami", "Rei", 1);
        storageManager.subjectTableManager.addSubject("Math");
        storageManager.subjectTableManager.addSubject("History");
        storageManager.subjectTableManager.addSubject("Tech");
        while (true) {
            System.out.print("key : ");
            String inputData = KeyboardInput.input();
            if (inputData.equals("ag")) {
                storageManager.groupTableManager.addGroups();
            } else if (inputData.equals("lg")) {
                storageManager.groupTableManager.outTableGroups();
            } else if (inputData.equals("as")) {
                storageManager.studentTableManager.addStudents();
            } else if (inputData.equals("ls")) {
                storageManager.studentTableManager.outTableStudents();
            } else if (inputData.equals("asub")) {
                storageManager.subjectTableManager.addSubjects();
            } else if (inputData.equals("lsub")) {
                storageManager.subjectTableManager.outTableSubjects();
            } else if (inputData.equals("artg")) {
                storageManager.ratingTableManager.addRatingsToGroup();
            } else if (inputData.equals("e")) {
                break;
            }
        }
    }

}
