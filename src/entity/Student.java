package entity;

import java.io.Serializable;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class Student implements Serializable {
    private int idStudent;
    private String firstName;
    private String lastName;
    private int idGroup;

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (idGroup != student.idGroup) return false;
        if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) return false;
        if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + idGroup;
        return result;
    }

    @Override
    public String toString() {
        return "ID=" + idStudent + " Student='" + firstName + " " + lastName + "'";
    }
}
