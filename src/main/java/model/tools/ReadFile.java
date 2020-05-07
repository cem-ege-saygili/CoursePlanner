package model.tools;

import java.io.*;

public class ReadFile{

    String fileLocation;

    public ReadFile(String fileLocation){

        this.fileLocation = fileLocation;

    }

    public String export2String(){
        String fileAsString = "";
        try {
            InputStream is = new FileInputStream(fileLocation);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            fileAsString = sb.toString();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return fileAsString;
    }

}
