package userInterface;

import control.StorageManager;
import control.ioLocalStorage.IOLocalStorage;
import database.LocalStorage;

/**
 * Created by Reaktor on 11.12.2014.
 */
public class UserInterface {

    public static void showMenu() {
        LocalStorage localStorage = new LocalStorage();
        IOLocalStorage ioLocalStorage = new IOLocalStorage();
        StorageManager storageManager = new StorageManager(localStorage);
/*
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
        storageManager.ratingTableManager.addRating(1, 2, 3);
        storageManager.ratingTableManager.addRating(2, 2, 5);
        storageManager.ratingTableManager.addRating(2, 2, 5);
        storageManager.ratingTableManager.addRating(2, 2, 4);
        storageManager.ratingTableManager.addRating(2, 1, 5);
        storageManager.ratingTableManager.addRating(4, 3, 2);
*/
        //ioLocalStorage.saveToSQL(localStorage);

        while (true) {
            System.out.print("key: ");
            String inputData = KeyboardInput.input();
            if (inputData.equals("ag")) {
                storageManager.groupTableManager.addGroups();
            } else if (inputData.equals("lg")) {
                storageManager.groupTableManager.outTableGroups();
            } else if (inputData.equals("rg")) {
                storageManager.ratingTableManager.removeGroup();
            } else if (inputData.equals("ug")) {
                storageManager.groupTableManager.updateGroup();
            } else if (inputData.equals("as")) {
                storageManager.studentTableManager.addStudents();
            } else if (inputData.equals("ls")) {
                storageManager.studentTableManager.outTableStudents();
            } else if (inputData.equals("lsog")) {
                storageManager.studentTableManager.outTableStudents(storageManager.groupTableManager.selectIdGroup());
            } else if (inputData.equals("rs")) {
                storageManager.ratingTableManager.removeStudent();
            } else if (inputData.equals("us")) {
                storageManager.studentTableManager.updateStudent();
            } else if (inputData.equals("asub")) {
                storageManager.subjectTableManager.addSubjects();
            } else if (inputData.equals("lsub")) {
                storageManager.subjectTableManager.outTableSubjects();
            } else if (inputData.equals("rsub")) {
                storageManager.ratingTableManager.removeSubject();
            } else if (inputData.equals("usub")) {
                storageManager.subjectTableManager.updateSubject();
            } else if (inputData.equals("arg")) {
                storageManager.ratingTableManager.addRatingsToGroup();
            } else if (inputData.equals("ars")) {
                storageManager.ratingTableManager.addRatingsToStudent();
            } else if (inputData.equals("lr")) {
                storageManager.ratingTableManager.outTableRating();
            } else if (inputData.equals("lrog")) {
                storageManager.ratingTableManager.outTableRating(storageManager.groupTableManager.getGroupById(storageManager.groupTableManager.selectIdGroup()));
            } else if (inputData.equals("lros")) {
                storageManager.ratingTableManager.outTableRating(storageManager.studentTableManager.getStudentById(storageManager.studentTableManager.selectIdStudent()));
            } else if (inputData.equals("rrs")) {
                storageManager.ratingTableManager.removeRating();
            } else if (inputData.equals("clr")) {
                localStorage = new LocalStorage();
                storageManager = new StorageManager(localStorage);
            } else if (inputData.equals("sdat")) {
                ioLocalStorage.saveToDatFile(localStorage, "database.dat");
            } else if (inputData.equals("ldat")) {
                localStorage = ioLocalStorage.loadFromDatFile("database.dat");
                storageManager.setLocalStorage(localStorage);
            } else if (inputData.equals("sxml")) {
                ioLocalStorage.saveToXMLFile(localStorage, "database.xml");
            } else if (inputData.equals("lxml")) {
                localStorage = ioLocalStorage.loadFromXMLFile("database.xml");
                storageManager.setLocalStorage(localStorage);
            } else if (inputData.equals("ssql")) {
                ioLocalStorage.saveToSQL(localStorage);
            } else if (inputData.equals("lsql")) {
                localStorage = ioLocalStorage.loadFromSQL();
                storageManager.setLocalStorage(localStorage);
            } else if (inputData.equals("h")) {
                menu();
            } else if (inputData.equals("!e")) {
                break;
            }
        }
    }

    private static void menu() {
        System.out.println("key : ag    : Add groups.");
        System.out.println("key : lg    : List groups.");
        System.out.println("key : rg    : Remove group.");
        System.out.println("key : ug    : Update group.");
        System.out.println("key : as    : Add students to group.");
        System.out.println("key : ls    : List all students.");
        System.out.println("key : lsog  : List students of group.");
        System.out.println("key : us    : Update student.");
        System.out.println("key : rs    : Remove student.");
        System.out.println("key : asub  : Add subjects.");
        System.out.println("key : lsub  : List subjects.");
        System.out.println("key : rsub  : Remove subject.");
        System.out.println("key : usub  : Update subject.");
        System.out.println("key : arg   : Add rating to group.");
        System.out.println("key : ars   : Add rating to student.");
        System.out.println("key : lr    : List ratings.");
        System.out.println("key : lrog  : List ratings of group.");
        System.out.println("key : lros  : List ratings of student.");
        System.out.println("key : rrs   : Remove ratings of student.");
        System.out.println("key : sdat  : Save database to *.dat file.");
        System.out.println("key : ldat  : Load database from *.dat file.");
        System.out.println("key : sxml  : Save database to *.xml file.");
        System.out.println("key : lxml  : Load database from *.xml file.");
        System.out.println("key : ssql  : Save database to SQL.");
        System.out.println("key : lsql  : Load database from SQL.");
        System.out.println("key : clr   : Clear database.");
        System.out.println("key : h     : Help.");
        System.out.println("key : !e    : Exit.");
    }
}
