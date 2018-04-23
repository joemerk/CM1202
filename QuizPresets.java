import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

public class QuizPresets extends Frame implements ActionListener{

    JButton QuitButton;

	static String chosenTheme = "History";
	QuizPresets() throws Exception{
		//JFrame f = new JFrame();
		ArrayList<String> themes = new ArrayList<String>();
		String filename = "Questions.txt";
		File fileIn = new File(filename);

        QuitButton = new JButton();
        QuitButton.addActionListener(this);
        QuitButton.setText("Quit");
        QuitButton.setBounds(350, 400, 150, 30);
        add(QuitButton);

		try{
			Scanner in = new Scanner(fileIn);

			while (in.hasNextLine()) {
				String line = in.nextLine();

				String[] parts = line.split(",");
				String theme = parts[6];

				themes.add(theme);
			}
			in.close();
		}
		catch( Exception e ) {
            System.out.println( "Problem reading file: " + filename +e );
            throw e;  // re-raise exception
        }

        Set<String> temp = new HashSet<String>();
        temp.addAll(themes);
        themes.clear();
        themes.addAll(temp);

        JLabel selectTheme = new JLabel();
        selectTheme.setBounds(100, 50, 400, 80);
        add(selectTheme);
        selectTheme.setText("Please select a theme from the list below:");

        JButton[] b = new JButton[themes.size()];

        for (int i = 0; i < themes.size(); i++) {
        	b[i] = new JButton();
        	b[i].setText(themes.get(i));
        	b[i].addActionListener(this);
        	int start = 175;
        	b[i].setBounds(100, 125+70*i, 200, 60);
        	add(b[i]);
        }
    	setSize(575,500); 
	  	setLayout(null);
	 	setVisible(true);
	  	setResizable(false);

    }
    public void actionPerformed(ActionEvent evt){

    	String btnLabel = evt.getActionCommand();
        if (btnLabel.equals("Quit")){
            
        }
        else{
    	chosenTheme = btnLabel;
        }
        this.dispose();
    }

    public static String getChoice(){
        return chosenTheme;
    }

	//public static void main(String[] args) throws Exception {
	//	new QuizPresets();
	//}
}