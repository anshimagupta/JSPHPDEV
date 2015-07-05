package util;

import java.io.*;

import model.Automotive;

public class ReadFile {
    public Automotive buildAutomotiveObject(String fileName, Automotive automotive) {
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;

            int opSetIndex = 0;
            while (!eof) {
                String line = buff.readLine();

                if (line == null) {
                    eof = true;
                } else {
                    String[] opSet = line.split(":");
                    String opSetName = opSet[0];
                    String[] option = opSet[1].split(";");
                    int optionSize = option.length;
                    automotive.setOpSetValues(opSetIndex, opSetName, optionSize);

                    for (int optIndex = 0; optIndex < optionSize; optIndex++) {
                        String[] opSetSplit = option[optIndex].split(",");
                        String optName = opSetSplit[0];
                        float optPrice = Float.parseFloat(opSetSplit[1]);
                        automotive.setOptionValues(opSetIndex, optIndex, optName, optPrice);
                    }
                    opSetIndex++;
                }
            }
            buff.close();
        } catch (IOException e) {
            System.out.println("Error -- " + e.toString());
        }
        return automotive;
    }
}