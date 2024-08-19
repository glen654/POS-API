package lk.ijse.posapi.util;

import java.util.UUID;

public class UtilProcess {
    public static String generateCustomerId(){
        return UUID.randomUUID().toString();
    }

    public static String generateItemId(){
        return UUID.randomUUID().toString();
    }
}
