package helpers;

import java.util.Base64;

public class EncodeString {
    public static String getBasicAuth(String userName, String password){
        String encoding = Base64.getEncoder().encodeToString((userName + ":" + password).getBytes());
        return "Basic " + encoding;
    }
}
