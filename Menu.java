import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.io*;
//import java.util.Vector;

public class Menu extends Frame implements ActionListener, CallBack{
	Statistics stats = new Statistics();
	Quiz quiz;
	QuizPresets qp;
	LogIn adminLogIn;

	JLabel title;
	JButton setupQuiz, takeQuiz, statistics, QuitButton, setSchool, changePassword;

	Menu() {
		adminLogIn = new LogIn();

		title = new JLabel();
		title.setBounds(100,50,400,80);
		add(title);
		title.setText("Quiz Menu");

		setupQuiz = new JButton();
		takeQuiz = new JButton();
		statistics = new JButton();
		setSchool = new JButton();
		QuitButton = new JButton();

		setupQuiz.addActionListener(this);
		takeQuiz.addActionListener(this);
		statistics.addActionListener(this);
	  	QuitButton.addActionListener(this);
		setSchool.addActionListener(this);

		setupQuiz.setText("Select Theme");
		takeQuiz.setText("Take Quiz");
		setSchool.setText("Add School");
		statistics.setText("View statistics");
		QuitButton.setText("Quit");

		takeQuiz.setBounds(25, 150, 200, 60);
	    setupQuiz.setBounds(25, 250, 200, 60);
	    setSchool.setBounds(325, 150, 200, 60);
	    statistics.setBounds(325, 250, 200, 60);
	    QuitButton.setBounds(350, 400, 150, 30);

	    add(takeQuiz);
	    add(setupQuiz);
	    add(setSchool);
	    add(statistics);
	    add(QuitButton);

	    changePassword = new JButton();
	    changePassword.addActionListener(this);
	    changePassword.setText("Change admin password");
	    changePassword.setBounds(50, 400, 150, 30);
	    add(changePassword);

	    setSize(575, 500);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		adminLogIn.bringToFront();
	}

	public void actionPerformed(ActionEvent evt) {
		String btnLabel = evt.getActionCommand();	

		switch(btnLabel){
			case "Select Theme":
			    	try {
					new QuizPresets();
					}
					catch(Exception E){};
					break;
			case "Take Quiz":
    				new Quiz(stats);
					break;
			case "Add School":
					try {
						System.out.println("sdas");
  						new addSchool(adminLogIn);
					}
					catch(Exception E){

					};
					break;
			case "View statistics":
					
					new showStats(stats,adminLogIn);
					
					
					break;
			case "Change admin password":
					adminLogIn.getAdminPermissions(adminLogIn);
					break;
			case "Quit":
					adminLogIn.getAdminPermissions(this);
					break;
		}
	}

	public static void main(String[] args){
		new Menu();
	}
	public void callBack(){
		this.dispose();
	}
}
