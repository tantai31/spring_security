package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Helper {
	public static byte[] sha256(String plaintext){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(plaintext.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return null;
        }
    }
//	public static String bcrypt(String plaintext) {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder.encode(plaintext);
//	}
	public static String bcrypt(String password) {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
		return hash;
	}
//	public static boolean checkBcrypt(String pass) {
//		boolean value = BCrypt.checkpw(pass, bcrypt(pass));
//		return value;
//	}
	public static long randomLong() {
		Random rand = new Random();
		return Math.abs(rand.nextLong());
	}
	public static String randomString(int n){
        final String pattern = "ABCDEFGHIGKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz012345678";
        final Random rnd = new Random();
        int len = pattern.length();
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(pattern.charAt(rnd.nextInt(len)));
        }
        return sb.toString();
    }
	public static void main(String[] args) {
		String user = "asdsdsadasda";
		String hash = BCrypt.hashpw(user, BCrypt.gensalt(12));
		System.out.println("Hash :"+hash);
		boolean value = BCrypt.checkpw(user, hash);
		System.out.println(value);
	}
}
