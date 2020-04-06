package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;



public class ReadCSVasList {

    private List<ClassInfo> records = null;
    private String fPath;

    private static final String SEMICOLON_DELIMITER = ";";

    public ReadCSVasList(String fPath, List<ClassInfo> records){
        this.records = records;
        this.fPath = fPath;
    }

    public void FillArray(){
        try (BufferedReader br = new BufferedReader(new FileReader(fPath))) {
            String line;
            boolean firstTry = true;
            while ((line = br.readLine()) != null) {
                String[] v = line.split(SEMICOLON_DELIMITER);
                if(!firstTry){
                    ClassInfo currClass = new ClassInfo(v[0],v[1],Integer.valueOf(v[2].trim().substring(0,3)),v[3],Integer.valueOf(v[4].trim()),v[5],Integer.valueOf(v[6].trim()),
                            v[7],Integer.valueOf(v[8].trim()),Integer.valueOf(v[9].trim()),v[10],v[11],v[12],v[13],v[14],v[15],v[16],v[17],v[18],
                            Integer.valueOf(v[19].trim()),v[20],v[21],Integer.valueOf(v[22].trim()),v[23],v[24],v[25],v[26],v[27],v[28],v[29],
                            Integer.valueOf(v[30].trim()),Integer.valueOf(v[31].trim()),Integer.valueOf(v[32].trim()),v[33],Integer.valueOf(v[34].trim()));
                    records.add(currClass);
                }
                else
                    firstTry=false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
