package control.ioLocalStorage.ioType;

import database.LocalStorage;

import java.io.*;

/**
 * Created by Reaktor on 13.12.2014.
 */
public class IODatFile implements IOStorage {

    public void output(LocalStorage localStorage, String outputFileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFileName))) {
            outputStream.writeObject(localStorage);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalStorage input (String inputFileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputFileName))) {
            return (LocalStorage) inputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
