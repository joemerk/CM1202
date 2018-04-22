import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.io*;
//import java.util.Vector;

public class Menu extends Frame implements ActionListener{
	Statistics stats = new Statistics();
	Quiz quiz;
	QuizPresets qp;

	JLabel title;
	JButton setupQuiz, takeQuiz, statistics, QuitButton, setSchool;

	Menu() {

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
		setSchool.setText("Select School");
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

	    setSize(575, 500);
		setLayout(null);
		setVisible(true);
		setResizable(false);

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
			case "Select School":
					try {
  					new School();
					}
					catch(Exception E){};
					break;
			case "View statistics":
					new showStats(stats);
					break;
			case "Quit":
					this.dispose();
					break;
		}
	}

	public static void main(String[] args){
		new Menu();
	}
}