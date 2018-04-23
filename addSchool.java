import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

public class addSchool implements CallBack{

	public addSchool(LogIn logIn){
        
        logIn.getAdminPermissions(this);
		
    }
    public void callBack(){
        JTextField newSchool = new JTextField(10);

        String FileName = "Schools.txt";

        JLabel title = new JLabel("Add Schools");
    
        JFrame f = new JFrame();

        JLabel label1 = new JLabel();

        JLabel confirmAdd2 = new JLabel();

        JButton quitButton = new JButton("Quit");
        JButton addSchoolButton = new JButton("Add school");

        title.setBounds(250,25,400,80);
        quitButton.setBounds(350, 200, 150, 30);
        newSchool.setBounds(200,100,150,30);
        addSchoolButton.setBounds(100, 200, 150, 30);
        label1.setBounds(15,250,150,30);
        confirmAdd2.setBounds(120,250,60,30);

        label1.setVisible(true);

        f.add(title);
        f.add(label1);
        f.add(confirmAdd2);
        f.add(quitButton);
        f.add(newSchool);
        f.add(addSchoolButton);

        f.setSize(575, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
    
        quitButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                f.setVisible(false);
                f.dispose();
            }
        });

        addSchoolButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                label1.setText("School Added:");

                confirmAdd2.setText(newSchool.getText());

                BufferedWriter bw = null;
            
                  try {
                // APPEND MODE SET HERE
                    bw = new BufferedWriter(new FileWriter("Schools.txt", true));
                    bw.newLine();
                    bw.write(newSchool.getText());
                    bw.flush();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    } finally {                       // always close the file
                    if (bw != null) try {
                        bw.close();
                    } catch (IOException ioe2) {
                        // just ignore it
                    }
                } // end try/catch/finally


            }
        });      

    }


    public static void main(String[] args) throws IOException {
        new addSchool(new LogIn("Password123"));
    }
}