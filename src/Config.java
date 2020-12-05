package aut.markus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Config {

    public String[] configDatas(){
        String[] config = new String[100];

        File file = new File("config.txt");

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            int count=0;
            while ((config[count] = in.readLine()) != null) {
                count++;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config[4] = commands(config);
        return config;
    }

    public String commands(String[] config){
        String commands = "";

        for(int i=4;i<config.length-1;i++){
            commands+=config[i]+"\n";
        }
        return commands;
    }
}
