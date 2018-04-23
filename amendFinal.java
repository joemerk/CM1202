import java.security.MessageDigest;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class amendFinal extends Frame implements ActionListener{
	JLabel title;
	JButton addBtn, delBtn, cancelBtn, inputBtn;
	JTextField textField;
	JFrame frame;
	static int FileSize = 0;
	static ArrayList<String> Questions = new ArrayList<String>();

	public amendFinal(){
		JFrame frame = new JFrame();

		addBtn = new JButton();
		delBtn = new JButton();
		addBtn.setText("Add a question");
		delBtn.setText("Delete a question");
		addBtn.setBounds(25,150,200,60);
		delBtn.setBounds(25,350,200,60);

		addBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		frame.getContentPane().setLayout(null);
		frame.add(textField);
		frame.add(inputBtn);
		frame.add(cancelBtn);

		frame.setSize(500,500);
		frame.setVisible(true);
	}

	public void addQuestionG(){
		JFrame frame = new JFrame();

		textField = new JTextField("Enter the question you want to add",20);
		inputBtn = new JButton();
		cancelBtn = new JButton();
		inputBtn.setText("Enter");
		cancelBtn.setText("Cancel");
		cancelBtn.setBounds(25,150,200,60);
		textField.setBounds(25,250,200,60);
		inputBtn.setBounds(25,350,200,60);

		inputBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		frame.getContentPane().setLayout(null);
		frame.add(textField);
		frame.add(inputBtn);
		frame.add(cancelBtn);

		frame.setSize(500,500);
		frame.setVisible(true);
	}

	public static void readFile(){
		try{
			FileInputStream fis = new FileInputStream("Questions.txt");
		    BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
		    String s;
		    int counter = 1;
		    while((s = buffer.readLine()) != null){
		    	counter ++;
		    }
		    //Questions[counter];
		    //counter = 0;
		    buffer.close();
		    FileSize = counter;
		}catch (Exception e){
			e.printStackTrace();
			FileSize = 0;
		}
		
	}

	public void deleteQuestionG(){
		JFrame frame = new JFrame();

		textField = new JTextField("Enter the row you want to delete",20);
		inputBtn = new JButton();
		cancelBtn = new JButton();
		inputBtn.setText("Enter");
		cancelBtn.setText("Cancel");
		cancelBtn.setBounds(25,150,200,60);
		textField.setBounds(25,250,200,60);
		inputBtn.setBounds(25,350,200,60);

		inputBtn.addActionListener(this);
		cancelBtn.addActionListener(this);

		frame.getContentPane().setLayout(null);
		frame.add(textField);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent evt){
		String btnLabel = evt.getActionCommand();

		switch(btnLabel){
			case "Add a question":
					readFile();
					addQuestionG();
					//FileSize = FileSize + 1;
					String dataToBeWritten = textField.getText();
					Questions.add(dataToBeWritten);
					frame.dispose();
					break;
			case "Delete a question":
					readFile();
					deleteQuestionG();
					FileSize = FileSize - 1;
					break;
		}
	}
}