import java.security.MessageDigest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface CallBack {                   
    void callBack();
}

class LogIn extends Frame implements ActionListener{
	JLabel title;
	JButton inputButton, cancelButton;
	JTextField textField;
	CallBack callBack;
	private String adminPassword;
	public LogIn(){
		adminPassword = hashPassword("ABC");
	}
	private boolean verifyPassword(String password){
		return hashPassword(password).equals(adminPassword);
	}
	public void getAdminPermissions(CallBack callBackObj){
		callBack = callBackObj;
        //JFrame frame = new JFrame();
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField("Start",20);
        inputButton = new JButton();
        cancelButton = new JButton();
        inputButton.setText("Enter");
        cancelButton.setText("Cancel");
        cancelButton.setBounds(25, 150, 200, 60);
        textField.setBounds(25, 250, 200, 60);
        inputButton.setBounds(25, 350, 200, 60);
        // Add the label to the JFrame
        inputButton.addActionListener(this);
		cancelButton.addActionListener(this);

        setLayout(null);
        add(textField);
        add(inputButton);
        add(cancelButton);
        //addWindowListener(this);

        //pack frame to component preferred sizes
        //frame.pack();

        setSize(500,500);
        setVisible(true);
	}
	public void actionPerformed(ActionEvent evt) {
		String btnLabel = evt.getActionCommand();	
		//this.dispose();
		switch(btnLabel){
			case "Enter":
			    	System.out.println("Enter");
			    	call(callBack,textField.getText());
			    	this.dispose();
					break;
			case "Cancel":
					this.dispose();
					break;
		}
	}
	public void call(CallBack callBack,String password){
		if (verifyPassword(password)){
			callBack.callBack();
		}
	}
	private String hashPassword(String password){
		//try{
		//	MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		//	messageDigest.update(password.getBytes());
		//	String encryptedString = new String(messageDigest.digest());
		//	return encryptedString;
		//}catch (Exception e) {
		//	System.out.println("error " + e.getMessage());
		//	return "";
		//}
		return password;
		
	}
	//public void changePassword(){
	//	
	//		if (getAdminPermissions()){
	//			input.getNewPasswordInput();
	//			adminPassword = hashPassword(tempInput);
	//		}
	//		else{
	//			JOptionPane.showMessageDialog(null, "infoMessage", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
	//		}
	//
	//}



}

//class ChangePassword implements CallBack{
//	LogIn logIn;
//	public ChangePassword(LogIn logIn){
//		this.logIn = logIn;
//	}
//	callBack(){
//		GetAdminPermission password = new GetAdminPermission();
//		if(password.valid){
//			input = new GetPasswordInput(this);
//			
//		}
//	}
//}
//class GetAdminPermission implements CallBack

//public class LogIn implements CallBack{
//	private String adminPassword;
//	private String tempInput;
//	GetPasswordInput input;
//	public LogIn(){
//		input = new GetPasswordInput(this);
//		adminPassword = hashPassword("abc");
//		System.out.println(adminPassword);
//		
//	}
//	private String hashPassword(String password){
//		//try{
//		//	MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//		//	messageDigest.update(password.getBytes());
//		//	String encryptedString = new String(messageDigest.digest());
//		//	return encryptedString;
//		//}catch (Exception e) {
//		//	System.out.println("error " + e.getMessage());
//		//	return "";
//		//}
//		return password;
//		
//	}
//	//public void changePassword(){
//	//	
//	//		if (getAdminPermissions()){
//	//			input.getNewPasswordInput();
//	//			adminPassword = hashPassword(tempInput);
//	//		}
//	//		else{
//	//			JOptionPane.showMessageDialog(null, "infoMessage", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE);
//	//		}
//	//
//	//}
//	
//	private boolean verifyPassword(String password){
//		return hashPassword(password).equals(adminPassword);
//	}
//	public boolean getAdminPermissions(){
//		input.getNewPasswordInput();
//		return verifyPassword(tempInput);
//	}	
//}
//