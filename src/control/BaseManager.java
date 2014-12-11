package control;

import database.LocalStorage;
import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;

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

    public void addGroup(String groupName) {
        Group newGroup = new Group();
        newGroup.setGroupName(groupName);
        if (!localStorage.getGroupsTable().containsValue(newGroup)) {
            idGroup = idGroup + 1;
            addGroup(idGroup, newGroup);
        }
    }

    public void addGroups() {
        System.out.println("Input groups :");
        while (true) {
            String inputQuery = "group name";
            String groupName = inputFromKeyboard(inputQuery, Validation.GROUP_PATTERN);
            if (groupName.equals("!e")) break;
            addGroup(groupName);
            System.out.println("Group added");
        }
    }

    private void addStudent(int idStudent, Student student) {
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
            int idGroup = selectIdGroup();
            System.out.println("Input students: ");
            while (true) {
                String inputQuery = "student first name";
                String firstName = inputFromKeyboard(inputQuery, Validation.FIRST_NAME_PATTERN);
                if (firstName.equals("!e")) break;

                inputQuery = "student last name";
                String lastName = inputFromKeyboard(inputQuery, Validation.LAST_NAME_PATTERN);
                if (lastName.equals("!e")) break;
                addStudent(firstName, lastName, idGroup);
                System.out.println("Student added");
            }
        } else {
            System.out.println("Add some groups");
            addGroups();
            addStudents();
        }
    }

    private void addSubject(int idSubject, Subject subject) {
        localStorage.getSubjectsTable().put(idSubject, subject);
    }

    public void addSubject(String subjectName) {
        Subject newSubject = new Subject();
        newSubject.setSubjectName(subjectName);
        if (!localStorage.getSubjectsTable().containsValue(newSubject)) {
            idSubject = idSubject + 1;
            addSubject(idSubject, newSubject);
        }
    }

    public void addSubject() {
        System.out.println("Input subjects :");
        while (true) {
            String inputQuery = "subject name";
            String SubjectName = inputFromKeyboard(inputQuery, Validation.SUBJECT_PATTERN);
            if (SubjectName.equals("!e")) break;
            addSubject(SubjectName);
            System.out.println("Subject added");
        }
    }

    private void addRating(int idRating, Rating rating) {
        localStorage.getRatingsTable().put(idRating, rating);
    }

    public void addRating(int idStudent, int idSubject, int mark) {
        Rating newRating = new Rating();
        newRating.setIdStudent(idStudent);
        newRating.setIdSubject(idSubject);
        newRating.setMark(mark);
        if (!localStorage.getRatingsTable().containsValue(newRating)) {
            idRating = idRating + 1;
            addRating(idRating, newRating);
        }
    }

    public void addRatingToGroup() {
        if (!localStorage.getGroupsTable().isEmpty()) {
            int idGroup = selectIdGroup();
            if (!isGroupEmpty(idGroup)) {
                for (Entry<Integer, Student> entry : localStorage.getStudentsTable().entrySet()) {
                    Integer key = entry.getKey();
                    Student value = entry.getValue();
                    if (value.getIdGroup() == idGroup) {
                        System.out.println("ID=" + key + " Student='" + value.getFirstName() + " " + value.getLastName() + "\' Group=" + localStorage.getGroupsTable().get(idGroup));
                    }
                }

                System.out.println("Input students: ");
                while (true) {
                    String inputQuery = "student first name";
                    String firstName = inputFromKeyboard(inputQuery, Validation.FIRST_NAME_PATTERN);
                    if (firstName.equals("!e")) break;

                    inputQuery = "student last name";
                    String lastName = inputFromKeyboard(inputQuery, Validation.LAST_NAME_PATTERN);
                    if (lastName.equals("!e")) break;
                    addStudent(firstName, lastName, idGroup);
                    System.out.println("Student added");
                }
            } else {
                System.out.println("Add some students");
                addStudents();
                addRatingToGroup();
            }
        } else {
            System.out.println("Add some groups");
            addGroups();
            addStudents();
        }
    }

    public int selectIdGroup() {
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

    public int selectIdStudent(int idGroup) {
        String inputQuery = "student";
        while (true) {
            outTableStudents(idGroup);
            int selectedId = selectId(inputQuery);
            if (localStorage.getStudentsTable().containsKey(selectedId) && localStorage.getStudentsTable().get(idGroup).getIdGroup() == idGroup) {
                return selectedId;
            } else {
                System.out.println("Student ID=" + selectedId + ", not found.");
            }
        }
    }

    public int selectIdStudent() {
        int idGroup = selectIdGroup();
        return selectIdStudent(idGroup);
    }

    public int selectIdSubject() {
        String inputQuery = "subject";
        while (true) {
            outTableSubjects();
            int selectedId = selectId(inputQuery);
            if (localStorage.getSubjectsTable().containsKey(selectedId)) {
                return selectedId;
            } else {
                System.out.println("Subject ID=" + selectedId + ", not found.");
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

    public void outTableStudents() {
        for (Entry<Integer, Group> entry : localStorage.getGroupsTable().entrySet()) {
            Integer key = entry.getKey();
            Group value = entry.getValue();
            outTableStudents(key);
        }
    }

    public void outTableStudents(int idGroup) {
        for (Entry<Integer, Student> entry : localStorage.getStudentsTable().entrySet()) {
            Integer key = entry.getKey();
            Student value = entry.getValue();
            if (value.getIdGroup() == idGroup) {
                System.out.println("ID=" + key + " Student='" + value.getFirstName() + " " + value.getLastName() + "\' Group=" + localStorage.getGroupsTable().get(idGroup));
            }
        }
    }

    public void outTableSubjects() {
        for (Entry<Integer, Subject> entry : localStorage.getSubjectsTable().entrySet()) {
            Integer key = entry.getKey();
            Subject value = entry.getValue();
            System.out.println("ID=" + key + " Subject=" + value);
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

    private boolean isGroupEmpty(int idGroup) {
        for (Entry<Integer, Student> entry : localStorage.getStudentsTable().entrySet()) {
            Integer key = entry.getKey();
            Student value = entry.getValue();
            if (value.getIdGroup() == idGroup) {
                return false;
            }
        }
        return true;
    }
}
