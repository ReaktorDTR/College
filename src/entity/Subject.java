package entity;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class Subject {
    private int idSubject;
    private String subjectName;

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (subjectName != null ? !subjectName.equals(subject.subjectName) : subject.subjectName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return subjectName != null ? subjectName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ID=" + idSubject + "  Subject=" + subjectName;
    }
}
