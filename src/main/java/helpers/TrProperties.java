package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TrProperties {
    public String userName;
    public String password;
    public String inProgressStatus;
    public String doneStatus;
    public String baseUrl;

    private TrProperties() {}

    public static TrProperties getProperties() {
        FileInputStream fis;
        Properties property = new Properties();
        TrProperties properties = new TrProperties();
        try {
            String userDir = System.getenv("HOME");
            fis = new FileInputStream(userDir + "/test_rail.properties");
            property.load(fis);
            properties.userName = property.getProperty("tr.email");
            properties.password = property.getProperty("tr.password");
            properties.inProgressStatus = property.getProperty("tr.status.inProgress");
            properties.doneStatus = property.getProperty("tr.status.Done");
            properties.baseUrl = property.getProperty("tr.baseurl");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        return properties;
    }

}
