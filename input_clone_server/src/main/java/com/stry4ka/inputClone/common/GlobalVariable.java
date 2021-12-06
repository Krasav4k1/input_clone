package com.stry4ka.inputClone.common;

import com.stry4ka.inputClone.utils.DialogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class GlobalVariable {
    public static boolean thisDevice = true;
    public static String host;
    public static int port;

    public static final String CONFIG_FILE_PATH = "inputCloseConfig.txt";

    public static boolean isThisDevice() {
        return thisDevice;
    }

    public static boolean changeDevice() {
        GlobalVariable.thisDevice = !GlobalVariable.thisDevice;
        return GlobalVariable.thisDevice;
    }

    public static String getHost() {
        if (GlobalVariable.host == null) {
            String hostFromFile = getHostFromFile();
            GlobalVariable.host = DialogUtil.showInputDialog("IP of other device 'x.x.x.x'", hostFromFile == null ? "" : hostFromFile);
            writeHostToFile(GlobalVariable.host);
        }
        return GlobalVariable.host;
    }

    public static String getCurrentJarFilePath() {
        File f = new File(System.getProperty("java.class.path"));
        File dir = f.getAbsoluteFile().getParentFile();
        return dir.toString() + File.separator;
    }

    private static String getHostFromFile() {
        File f = new File(getCurrentJarFilePath() + CONFIG_FILE_PATH);
        if (f.exists()) {
            String row = null;
            try {
                Scanner myReader = new Scanner(f);
                while (myReader.hasNextLine()) {
                    row = myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return row;
        }
        return null;
    }

    private static void writeHostToFile(String fileData) {
        try {
            Files.write(Paths.get(getCurrentJarFilePath() + CONFIG_FILE_PATH), fileData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
