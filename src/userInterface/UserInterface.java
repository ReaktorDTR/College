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
        storageManager.ratingTableManager.addRating(1,2,3);
        storageManager.ratingTableManager.addRating(2,2,5);
        storageManager.ratingTableManager.addRating(2,2,5);
        storageManager.ratingTableManager.addRating(2,2,4);
        storageManager.ratingTableManager.addRating(2,1,5);
        storageManager.ratingTableManager.addRating(4,3,2);
        while (true) {
            System.out.print("key: ");
            String inputData = KeyboardInput.input();
            if (inputData.equals("ag")) {
                storageManager.groupTableManager.addGroups();
            } else if (inputData.equals("lg")) {
                storageManager.groupTableManager.outTableGroups();
            } else if (inputData.equals("rg")) {
                storageManager.ratingTableManager.removeGroup();;
            } else if (inputData.equals("as")) {
                storageManager.studentTableManager.addStudents();
            } else if (inputData.equals("ls")) {
                storageManager.studentTableManager.outTableStudents();
            } else if (inputData.equals("lsbg")) {
                storageManager.studentTableManager.outTableStudents(storageManager.groupTableManager.selectIdGroup());
            } else if (inputData.equals("rs")) {
                storageManager.ratingTableManager.removeStudent();
            } else if (inputData.equals("asub")) {
                storageManager.subjectTableManager.addSubjects();
            } else if (inputData.equals("lsub")) {
                storageManager.subjectTableManager.outTableSubjects();
            } else if (inputData.equals("rsub")) {
                storageManager.ratingTableManager.removeSubject();
            } else if (inputData.equals("arg")) {
                storageManager.ratingTableManager.addRatingsToGroup();
            } else if (inputData.equals("ars")) {
                storageManager.ratingTableManager.addRatingsToStudent();
            } else if (inputData.equals("lr")) {
                storageManager.ratingTableManager.outTableRating();
            } else if (inputData.equals("rrs")) {
                storageManager.ratingTableManager.removeRating();
            } else if (inputData.equals("!e")) {
                break;
            }
        }
    }

}
