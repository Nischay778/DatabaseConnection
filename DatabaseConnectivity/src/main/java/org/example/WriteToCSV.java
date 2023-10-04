package org.example;

import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVWriter;

public class WriteToCSV {
    public void WriteToCSV(String[] record) {
        String csvFilePath = "data.csv";
        try {
            FileWriter fileWriter = new FileWriter(csvFilePath, true);
            try{
                CSVWriter csvWriter = new CSVWriter(fileWriter);
                try {
                    csvWriter.writeNext(record);
                }
                catch (Throwable var9) {
                    try {
                        csvWriter.close();
                    }
                    catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                    throw var9;
                }
                csvWriter.close();
            } 
            catch (Throwable var10) {
                try {
                    fileWriter.close();
                }
                catch (Throwable var7) {
                    var10.addSuppressed(var7);
                }
                throw var10;
            }
            fileWriter.close();
        }
        catch (IOException var11) {
            var11.printStackTrace();
        }
    }
}
