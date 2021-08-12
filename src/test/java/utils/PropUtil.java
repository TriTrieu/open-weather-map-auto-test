package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropUtil {
    public static Properties configProp;

    public static Properties loadConfigProb() {
        try {
            configProp = new Properties();
            String filePath = "src/test/resources/config.properties";
            InputStream input = new FileInputStream(new File(filePath));
            configProp.load(new InputStreamReader(input, Charset.forName("UTF-8")));
            configProp.load(input);
        } catch (Exception e) {
            System.out.println("Unable to load Config.properties file. ERROR: " + e.getMessage());
        }
        return configProp;
    }

    public static Properties loadConfigProb(String path) {
        Properties prop = new Properties();
        try {
            prop = new Properties();
            InputStream input = new FileInputStream(new File(path));
            prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));

            prop.load(input);
        } catch (Exception e) {
            System.out.println("Unable to load Config.properties file. ERROR: " + e.getMessage());
        }
        return prop;
    }



}
