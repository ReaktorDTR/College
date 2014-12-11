package control;

import database.LocalStorage;
import entity.Student;

/**
 * Created by sdovhtc on 12/11/2014.
 */
public class StudentTableManager {
    private LocalStorage localStorage;
    GroupTableManager groupTableManager = new GroupTableManager(localStorage);

    private void addStudent(Student student) {
        localStorage.getStudentsTable().add(student);
    }

    public void addStudent(String firstName, String lastName, int idGroup) {
        Student newStudent = new Student();
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setIdGroup(idGroup);
        if (!localStorage.getStudentsTable().contains(newStudent)) {
            localStorage.incrementIdStudent();
            newStudent.setIdStudent(localStorage.getIdStudent());
            addStudent(newStudent);
        }
    }

    public void addStudents() {
        if (!localStorage.getGroupsTable().isEmpty()) {
            int idGroup = groupTableManager.selectIdGroup();
            System.out.println("Input students: ");
            while (true) {
                String inputQuery = "student first name";
                String firstName = KeyboardInput.inputValidQueryData(inputQuery, Validation.FIRST_NAME_PATTERN);
                if (firstName.equals("!e")) break;

                inputQuery = "student last name";
                String lastName = KeyboardInput.inputValidQueryData(inputQuery, Validation.LAST_NAME_PATTERN);
                if (lastName.equals("!e")) break;
                addStudent(firstName, lastName, idGroup);
                System.out.println("Student added");
            }
        } else {
            System.out.println("Add some groups");
            groupTableManager.addGroups();
            addStudents();
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
}
