package entity;

/**
 * Created by Reaktor on 10.12.2014.
 */
public class Rating {
    private int idRating;
    private int idStudent;
    private int idSubject;
    private int mark;

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (idStudent != rating.idStudent) return false;
        if (idSubject != rating.idSubject) return false;
        if (mark != rating.mark) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStudent;
        result = 31 * result + idSubject;
        result = 31 * result + mark;
        return result;
    }
}
