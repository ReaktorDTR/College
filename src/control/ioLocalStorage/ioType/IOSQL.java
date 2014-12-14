package control.ioLocalStorage.ioType;

import database.LocalStorage;
import database.SQLUtil.ConnectionConfiguration;
import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Reaktor on 14.12.2014.
 */
public class IOSQL{

    public void output(LocalStorage localStorage) {
        try (Connection connection = new ConnectionConfiguration().getConnection(); Statement statement = connection.createStatement();) {
            statement.addBatch("DELETE FROM groups");
            statement.addBatch("DELETE FROM students");
            statement.addBatch("DELETE FROM subjects");
            statement.addBatch("DELETE FROM ratings");
            for (Group group : localStorage.getGroupsTable()) {
                statement.addBatch(queryAddGroups(group));
            }
            for (Student student : localStorage.getStudentsTable()) {
                statement.addBatch(queryAddStudents(student));
            }
            for (Subject subject : localStorage.getSubjectsTable()) {
                statement.addBatch(queryAddSubjects(subject));
            }
            for (Rating rating : localStorage.getRatingsTable()) {
                statement.addBatch(queryAddRatings(rating));
            }
            statement.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String queryAddStudents(Student student) {
        String query = "INSERT INTO students(idStudent, firstName, lastName, idGroup) VALUES" +
                "(" + student.getIdStudent() +
                ",'" + student.getFirstName() +
                "','" + student.getLastName() +
                "'," + student.getIdGroup() +
                ")";
        return query;
    }

    public String queryAddGroups(Group group) {
        String query = "INSERT INTO groups(idGroup, groupName) VALUES" +
                "(" + group.getIdGroup() +
                ",'" + group.getGroupName() +
                "')";
        return query;
    }

    public String queryAddSubjects(Subject subject) {
        String query = "INSERT INTO subjects(idSubject, subjectName) VALUES" +
                "(" + subject.getIdSubject() +
                ",'" + subject.getSubjectName() +
                "')";
        return query;
    }

    public String queryAddRatings(Rating rating) {
        String query = "INSERT INTO ratings(idRating, idStudent, idSubject, mark) VALUES" +
                "(" + rating.getIdRating() +
                "," + rating.getIdStudent() +
                "," + rating.getIdSubject() +
                "," + rating.getMark() +
                ")";
        return query;
    }

    public LocalStorage input() {
        LocalStorage localStorage = new LocalStorage();

        try (Connection connection = new ConnectionConfiguration().getConnection(); Statement statement = connection.createStatement();) {
            String query = "SELECT * FROM groups";
            ResultSet resultSet = statement.executeQuery(query);
            int idGroupCounter = 0;
            while (resultSet.next()) {
                Group group = new Group();
                group.setIdGroup(resultSet.getInt("idGroup"));
                group.setGroupName(resultSet.getString("groupName"));
                localStorage.getGroupsTable().add(group);
                if (idGroupCounter < group.getIdGroup()) {
                    idGroupCounter = group.getIdGroup();
                }
            }
            localStorage.setIdGroupCounter(idGroupCounter);

            query = "SELECT * FROM students";
            resultSet = statement.executeQuery(query);
            int idStudentCounter = 0;
            while (resultSet.next()) {
                Student student = new Student();
                student.setIdStudent(resultSet.getInt("idStudent"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setIdGroup(resultSet.getInt("idGroup"));
                localStorage.getStudentsTable().add(student);
                if (idStudentCounter < student.getIdStudent()) {
                    idStudentCounter = student.getIdStudent();
                }
            }
            localStorage.setIdStudentCounter(idStudentCounter);

            query = "SELECT * FROM subjects";
            resultSet = statement.executeQuery(query);
            int idSubjectCounter = 0;
            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setIdSubject(resultSet.getInt("idSubject"));
                subject.setSubjectName(resultSet.getString("subjectName"));
                localStorage.getSubjectsTable().add(subject);
                if (idSubjectCounter < subject.getIdSubject()) {
                    idSubjectCounter = subject.getIdSubject();
                }
            }
            localStorage.setIdSubjectCounter(idSubjectCounter);

            query = "SELECT * FROM ratings";
            resultSet = statement.executeQuery(query);
            int idRatingCounter = 0;
            while (resultSet.next()) {
                Rating rating = new Rating();
                rating.setIdRating(resultSet.getInt("idRating"));
                rating.setIdStudent(resultSet.getInt("idStudent"));
                rating.setIdSubject(resultSet.getInt("idSubject"));
                rating.setMark(resultSet.getInt("mark"));
                localStorage.getRatingsTable().add(rating);
                if (idRatingCounter < rating.getIdRating()) {
                    idRatingCounter = rating.getIdRating();
                }
            }
            localStorage.setIdRatingCounter(idRatingCounter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localStorage;
    }
}
