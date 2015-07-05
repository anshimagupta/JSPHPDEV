package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Automotive;

public class FileIO {
    public void serializeAuto(Automotive automobile, String fileName) throws FileNotFoundException, IOException{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(automobile);
        out.close();
    }

    public Automotive deserializeAuto(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        Automotive automobile = (Automotive) in.readObject();
        return automobile;
    }
}