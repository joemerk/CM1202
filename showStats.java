import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.Color;

public class showStats extends Frame implements ActionListener, CallBack{
	Statistics stats;

	Vector<String> themes;

	JLabel title;
	JLabel statText;
	JButton[] themeButton;
	JButton QuitButton;

	public void callBack(){
		themes = Statistics.getThemes();
		themeButton = new JButton[themes.size()];

		//stats.appendQuizData("History", new int[]{4,2,2,3,4}, "Kings of Wessex");
		//stats.appendQuizData("History", new int[]{1,3,3,1,1}, "Kings of Wessex");
		//stats.appendQuizData("History", new int[]{3,4,3,4,1}, "Kings of Wessex");

		title = new JLabel();
		title.setBounds(100,50,400,80);
		add(title);
		title.setText("Statistics - Filter by Theme");

		statText = new JLabel();
		statText.setBounds(50,275,560,250);
		statText.setVerticalAlignment(JLabel.TOP);
		//statText.setOpaque(true);
		//statText.setBackground(Color.red);
		add(statText);
		statText.setText("");

        for (int i = 0; i < themes.size(); i++) {
        	themeButton[i] = new JButton();
        	themeButton[i].setText(themes.get(i));
        	themeButton[i].addActionListener(this);
        	themeButton[i].setBounds(100, 125+70*i, 200, 60);
        	add(themeButton[i]);
        }

		QuitButton = new JButton();
	  	QuitButton.addActionListener(this);
		QuitButton.setText("Quit");
	    QuitButton.setBounds(350, 400, 150, 30);

	    add(QuitButton);

	    setSize(575, 500);
		setLayout(null);
		setVisible(true);
		setResizable(false);
	}
	showStats(Statistics _stats,LogIn logIn) {
		stats = _stats;
		logIn.getAdminPermissions(this);

	}

	public void actionPerformed(ActionEvent evt) {
		String btnLabel = evt.getActionCommand();	

		if (btnLabel == "Quit"){
				this.dispose();
			}
		else {
			for (int i = 0; i < themes.size(); i ++){
				if (btnLabel == themes.get(i)) {
					add(statText);
					statText.setText(stats.generateInterpretation(i));
				}

			}
		}

	}

}