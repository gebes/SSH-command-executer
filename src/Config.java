package at.markus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Config {
    //private Encryption encryption = new Encryption();
    private Scanner scan = new Scanner(System.in);

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
        config[4] = commands(config, config[1]);

        return config;
    }

    public String commands(String[] config, String password){
        String commands = "";

        for(int i=4;i<config.length-1;i++){
            if(config[i]!=null) {
                if (config[i].contains("?pw?")) {
                    String containPassword = config[i].replace("?pw?", password);
                    commands += containPassword + "\n";
                } else if(config[i].contains("?date?"))  {
                    String containDate=config[i].replace("?date?", getCurrentTimeStamp());
                    commands += containDate + "\n";
                } else if(config[i].contains("?input?"))  {
                    String containInput = config[i].replace("?input?", input());
                    commands += containInput + "\n";
                }else{
                    commands += config[i] + "\n";
                }
            }
        }
        return commands;

    }
    public  String input() {
        System.out.print("Input: ");
        String input = scan.nextLine();
        return input;
    }
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy:MM:dd_HH:mm");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
