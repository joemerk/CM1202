import java.security.MessageDigest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface CallBack {                   
    void callBack();
}

class LogIn extends Frame implements ActionListener, CallBack{
	JLabel title;
	JButton inputButton, cancelButton;
	JTextField textField;
	CallBack callBack;
	JFrame frame;
	private String adminPassword;
	public LogIn(){
		
		getNewPassword();
	}
	public LogIn(String password){
		adminPassword = password;
		
	}
	private boolean verifyPassword(String password){
		return hashPassword(password).equals(adminPassword);
	}
	public void getAdminPermissions(CallBack callBackObj){
		callBack = callBackObj;
        frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        textField = new JTextField("Enter admin password",20);
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

        frame.getContentPane().setLayout(null);
        frame.add(textField);
        frame.add(inputButton);
        frame.add(cancelButton);
       
        //addWindowListener(this);

        //pack frame to component preferred sizes
        //frame.pack();

        frame.setSize(500,500);
        frame.setVisible(true);
        bringToFront();
	}
	public void getNewPassword(){
        frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textField = new JTextField("Enter new password",20);
        inputButton = new JButton();
        cancelButton = new JButton();
        inputButton.setText("Set");
        cancelButton.setText("Cancel");
        cancelButton.setBounds(25, 150, 200, 60);
        textField.setBounds(25, 250, 200, 60);
        inputButton.setBounds(25, 350, 200, 60);
        // Add the label to the JFrame
        inputButton.addActionListener(this);
		cancelButton.addActionListener(this);

        frame.getContentPane().setLayout(null);

        frame.add(textField);
        frame.add(inputButton);
        frame.add(cancelButton);
        
        //addWindowListener(this);

        //pack frame to component preferred sizes
        //frame.pack();

        frame.setSize(500,500);
        frame.setVisible(true);
        bringToFront();
	}
	public void actionPerformed(ActionEvent evt) {
		String btnLabel = evt.getActionCommand();	
		//this.dispose();
		switch(btnLabel){
			case "Enter":
			    	frame.dispose();
			    	call(callBack,textField.getText());
			    	
					break;
			case "Cancel":
					frame.dispose();
					break;
			case "Set":
					
					frame.dispose();
					changePassword(textField.getText());
					
					break;
		}
	}
	public void call(CallBack callBack,String password){
		if (verifyPassword(password)){
			callBack.callBack();
		}
	}
	public void changePassword(String password){
		adminPassword = hashPassword(password);
		System.out.println(adminPassword);
	}
	private String hashPassword(String password){
		try{
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			String encryptedString = new String(messageDigest.digest());
			return encryptedString;
		}catch (Exception e) {
			System.out.println("error " + e);
			return "";
		}
		//return password;
		
	}
	public void callBack(){
		getNewPassword();
	}
	public void bringToFront(){
		frame.toFront();
        frame.repaint();
    }


}
