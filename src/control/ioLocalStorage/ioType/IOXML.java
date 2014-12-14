package control.ioLocalStorage.ioType;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import database.LocalStorage;
import entity.Group;
import entity.Rating;
import entity.Student;
import entity.Subject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Reaktor on 13.12.2014.
 */
public class IOXML {

    public void output(LocalStorage localStorage, String outputFileName) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("LocalStorage", LocalStorage.class);
        xstream.alias("Group", Group.class);
        xstream.alias("Rating", Rating.class);
        xstream.alias("Student", Student.class);
        xstream.alias("Subject", Subject.class);
        xstream.setMode(XStream.NO_REFERENCES);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFileName);
            xstream.toXML(localStorage, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public LocalStorage input(String inputFileName) {
        LocalStorage localStorage = new LocalStorage();
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("LocalStorage", LocalStorage.class);
        xstream.alias("Group", Group.class);
        xstream.alias("Rating", Rating.class);
        xstream.alias("Student", Student.class);
        xstream.alias("Subject", Subject.class);
        xstream.setMode(XStream.NO_REFERENCES);
        try {
            FileInputStream fileInputStream = new FileInputStream(inputFileName);
            xstream.fromXML(fileInputStream, localStorage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return localStorage;
    }
}
