import java.security.MessageDigest;
public class LogIn {
	private String adminPassword;
	private String hashPassword(String password){
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes());
		String encryptedString = new String(messageDigest.digest());
		return encryptedString;
	}
	public String changePassword(){
		if (adminPassword == null){
			adminPassword = hashPassword(GetNewPassword());
			return "Success";
		}else if (verifyPassword()){
			adminPassword = hashPassword(GetNewPassword());
			return "Success";
		}else{
			return "Incorrect password";
		}
	}
	//private String GetNewPassword(){
		//get password
	//	return;
	//}
	private boolean verifyPassword(String password){
		return hashPassword(password).equals(adminPassword);
	}
}