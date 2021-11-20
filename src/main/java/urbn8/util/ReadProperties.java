package urbn8.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static ReadProperties instance;

    private ReadProperties(){}

    public static ReadProperties getInstance(){
        if(instance == null)
            instance = new ReadProperties();
        return instance;
    }

    public String readPro(String key){
        String value = null;
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/environment.properties"));
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
