package util;

import adapter.BuildAuto;

import java.io.*;

class FileIO {
    public BuildAuto deserializeAutomotive(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        return (BuildAuto) in.readObject();
    }

    public void serializeAutomotive(BuildAuto buildAuto, String fileName) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(buildAuto);
        out.close();
    }
}