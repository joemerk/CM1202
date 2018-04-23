import java.security.MessageDigest;
public class LogIn {
	private String adminPassword;
	private String hashPassword(){
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(stringToEncrypt.getBytes());
		String encryptedString = new String(messageDigest.digest());
		return encryptedString;
	}

}