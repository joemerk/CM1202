import java.security.MessageDigest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogIn extends Frame implements ActionListener{
	private String adminPassword;
	JLabel title;
	JButton inputButton, cancelButton;
	JTextField textField;
	String tempInput;
	private String hashPassword(String password){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			String encryptedString = new String(messageDigest.digest());
			return encryptedString;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "";
		}
		
	}
	public String changePassword(){
		if (adminPassword == null){
			passwordInput();
			adminPassword = hashPassword(tempInput);
			return "Success";
		}else{
			if (getAdminPermissions()){
				passwordInput();
				adminPassword = hashPassword(tempInput);
				return "Success";
			}
			else{
				return "Incorrect password";
			}
	
		}
	}
	
	private boolean verifyPassword(String password){
		return hashPassword(password).equals(adminPassword);
	}
	public boolean getAdminPermissions(){
		passwordInput();
		return verifyPassword(tempInput);
	}
	public void passwordInput(){

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
			    	tempInput = textField.getText();
			    	System.out.println(tempInput);
			    	this.dispose();
					break;
			case "Cancel":
					this.dispose();
					break;
		}
	}
}