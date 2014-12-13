package control.ioLocalStorage.ioType;

import database.LocalStorage;

/**
 * Created by Reaktor on 13.12.2014.
 */
public interface IOStorage {
    public void output(LocalStorage localStorage, String outputFileName);

    public LocalStorage input(String inputFileName);
}
