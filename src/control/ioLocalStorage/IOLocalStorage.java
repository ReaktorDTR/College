package control.ioLocalStorage;

import control.ioLocalStorage.ioType.IODatFile;
import control.ioLocalStorage.ioType.IOSQL;
import control.ioLocalStorage.ioType.IOXML;
import database.LocalStorage;

/**
 * Created by Reaktor on 13.12.2014.
 */
public class IOLocalStorage {
    IODatFile ioDatFile = new IODatFile();
    IOXML ioXML = new IOXML();
    IOSQL ioSQL = new IOSQL();

    public void saveToDatFile(LocalStorage localStorage, String outputFileName) {
        ioDatFile.output(localStorage, outputFileName);
    }

    public LocalStorage loadFromDatFile(String inputFileName) {
        return ioDatFile.input(inputFileName);
    }

    public void saveToXMLFile(LocalStorage localStorage, String outputFileName) {
        ioXML.output(localStorage, outputFileName);
    }

    public LocalStorage loadFromXMLFile(String inputFileName) {
        return ioXML.input(inputFileName);
    }

    public void saveToSQL(LocalStorage localStorage) {
        ioSQL.output(localStorage);
    }

    public LocalStorage loadFromSQL() {
        return ioSQL.input();
    }
}
