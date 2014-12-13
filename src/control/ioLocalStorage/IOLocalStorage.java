package control.ioLocalStorage;

import control.ioLocalStorage.ioType.IODatFile;
import database.LocalStorage;

/**
 * Created by Reaktor on 13.12.2014.
 */
public class IOLocalStorage {
    IODatFile ioDatFile = new IODatFile();

    public void saveToDatFile(LocalStorage localStorage, String outputFileName) {
        ioDatFile.output(localStorage, outputFileName);
    }

    public LocalStorage loadFromDatFile(String inputFileName) {
        return ioDatFile.input(inputFileName);
    }

}
