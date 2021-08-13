package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaUtils {

    public static <T> T convertJsonFileToJavaObject(String jsonFilePath, Class<T> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        Object obj = new Object();
        try (Reader reader = new FileReader(jsonFilePath)) {
            // Convert JSON File to Java Object
            obj = gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazz.cast(obj);
    }

    public static <T> T convertJsonStringToJavaObject(String jsonString, Class<T> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        Object obj = new Object();
        try {
            // Convert JSON File to Java Object
            obj = gson.fromJson(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz.cast(obj);
    }

    public static <T> void convertToJsonFile(T obj, String jsonFilePath) {
        //pretty print
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        // Java objects to String
        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> String convertToJsonString(T obj) {
        //pretty print
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            // Java objects to String
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getCurrentDate(String format) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        // System.out.println("Using System Property: " + os);
        return os;
    }



    public static void main(String[] args) {

        System.out.println(getOperatingSystem());
    }
}
