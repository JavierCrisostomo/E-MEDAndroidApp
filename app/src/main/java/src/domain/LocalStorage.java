package src.domain;

public class LocalStorage {
    private static String userKey;

    public static String getUserKey() {
        return userKey;
    }

    public static void setUserKey(String userKey) {
        LocalStorage.userKey = userKey;
    }
}
