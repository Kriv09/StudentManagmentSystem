package om.school.studentmanagement.Utils;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilsFunctions {
    public static String getOrDefault(String value, String defaultValue) {
        return isNullOrEmpty(value) ? defaultValue : value;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    @org.jetbrains.annotations.NotNull
    public static String hashPassword(String password) {
        return isNullOrEmpty(password) ? "" : getHashOfPassword(password);
    }

    public static @NotNull String getHashOfPassword(@NotNull String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found.", e);
        }
    }

    public static boolean compareHashPasswords(@NotNull String password1, @NotNull String password2) {
        return hashPassword(password1).equals(hashPassword(password2));
    }
}
