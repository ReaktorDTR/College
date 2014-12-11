package control;

import database.LocalStorage;
import entity.Group;
import entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class BaseManager {
    private LocalStorage localStorage = new LocalStorage();
    private int idGroup = 0;
    private int idStudent = 0;
    private int idSubject = 0;
    private int idRating = 0;

    private void addGroup(int idGroup, Group group) {
        localStorage.getGroupsTable().put(idGroup, group);
    }

    private void addGroup(String groupName) {
        Group newGroup = new Group();
        newGroup.setGroupName(groupName);
        if (!localStorage.getGroupsTable().containsValue(newGroup)) {
            idGroup = idGroup + 1;
            addGroup(idGroup, newGroup);
        }
    }

    public void addGroups() {
        System.out.println("Input groups");
        while (true) {
            String inputQuery = "group name";
            String groupName = inputFromKeyboard(inputQuery, Validation.GROUP_PATTERN);
            if (groupName.equals("!e")) break;
            addGroup(groupName);
            System.out.println("Group added");
        }
    }

    public void addStudent(int idStudent, Student student) {
        localStorage.getStudentsTable().put(idStudent, student);
    }

    public void addStudent(String firstName, String lastName, int idGroup) {
        Student newStudent = new Student();
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setIdGroup(idGroup);
        if (!localStorage.getStudentsTable().containsValue(newStudent)) {
            idStudent = idStudent + 1;
            addStudent(idStudent, newStudent);
        }
    }

    public void addStudents() {
        if (!localStorage.getGroupsTable().isEmpty()) {
            int idGroup = selectGroupId();
            System.out.println("Input students (:e - Exit)");
            while (true) {
                System.out.print("Input firs name and last name");
                String firstNameAndLastName = inputFromKeyboard();
/*
                int idGroup = Integer.parseInt();
                if (groupName.equals(":e")) break;
                if (Validation.validateGroupName(groupName)) {
                    addGroup(groupName);
                    System.out.println("Group added");
                } else {
                    System.out.println("Bad group name");
                }*/
            }
        } else {
            System.out.println("Add some groups");
            addGroups();
        }
    }

    public int selectGroupId() {
        String inputQuery = "group";
        while (true) {
            outTableGroups();
            int selectedId = selectId(inputQuery);
            if (localStorage.getGroupsTable().containsKey(selectedId)) {
                return selectedId;
            } else {
                System.out.println("Group ID=" + selectedId + ", not found.");
            }
        }
    }

    public void outTableGroups() {
        for (Entry<Integer, Group> entry : localStorage.getGroupsTable().entrySet()) {
            Integer key = entry.getKey();
            Group value = entry.getValue();
            System.out.println("ID=" + key + " Group=" + value);
        }
    }

    public static String inputFromKeyboard() {
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

    public static String inputFromKeyboard(String inputQuery, String pattern) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("Input " + inputQuery + "(!e - Exit): ");
                String inputData = reader.readLine();
                if (inputData.equals("!e")) {
                    return "!e";
                } else if (Validation.validate(inputData, pattern)) {
                    return inputData;
                } else {
                    System.out.println("Bad " + inputQuery + "!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int selectId(String inputQuery) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("Select " + inputQuery + " ID: ");
                String inputData = reader.readLine();
                if (Validation.validate(inputData, Validation.ID_PATTERN)) {
                    return Integer.parseInt(inputData);
                } else {
                    System.out.println("Bad " + inputQuery + " ID!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
