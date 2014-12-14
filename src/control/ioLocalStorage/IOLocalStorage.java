package control.ioLocalStorage;

import control.ioLocalStorage.ioType.IODatFile;
import control.ioLocalStorage.ioType.IOXML;
import database.LocalStorage;

/**
 * Created by Reaktor on 13.12.2014.
 */
public class IOLocalStorage {
    IODatFile ioDatFile = new IODatFile();
    IOXML ioxml = new IOXML();

    public void saveToDatFile(LocalStorage localStorage, String outputFileName) {
        ioDatFile.output(localStorage, outputFileName);
    }

    public LocalStorage loadFromDatFile(String inputFileName) {
        return ioDatFile.input(inputFileName);
    }

    public void saveToXMLFile(LocalStorage localStorage, String outputFileName) {
        ioxml.output(localStorage, outputFileName);
    }

    public LocalStorage loadFromXMLFile(String inputFileName) {
        return ioxml.input(inputFileName);
    }
}
