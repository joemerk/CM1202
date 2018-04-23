import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class School extends Frame {

    public Statistics stats;

    School(Statistics stats) throws IOException {

        this.stats = stats;

        String FileName = "Schools.txt";

        JLabel title = new JLabel("School Selection");

        BufferedReader schoolFile = new BufferedReader(new FileReader(FileName));
        List<String> schoolArray = new ArrayList<String>();

        String school; while(( school = schoolFile.readLine()) != null) {
            schoolArray.add(school);
        }
        schoolFile.close();

        String[] array = schoolArray.toArray(new String[schoolArray.size()]);
        JComboBox<String> c = new JComboBox<String>(array);
    
        JLabel l1 = new JLabel("Please select a school");
        JButton button = new JButton("Select");
    
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
    
        JFrame f = new JFrame();

        JButton quitButton = new JButton("Quit");

        JButton takeQuizButton = new JButton("Take Quiz");

        quitButton.setBounds(350, 200, 150, 30);
        takeQuizButton.setBounds(250, 300, 150, 30);

        l1.setBounds(15, 100, 150, 30);
        l2.setBounds(15, 200, 150, 30);
        l3.setBounds(150, 200, 150, 30);
        c.setBounds(160, 100, 75, 30);
        button.setBounds(350, 100, 150, 30);
        title.setBounds(250,25,400,80);
        c.setSize(180, c.getPreferredSize().height);
        takeQuizButton.setVisible(false);


        f.add(takeQuizButton);
        f.add(title);
        f.add(l1);
        f.add(c);
        f.add(button);
        f.add(quitButton);
        f.add(takeQuizButton);
        f.add(l2);
        f.add(l3);

        f.setSize(575, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
    
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                String schoolSelected = c.getSelectedItem().toString();
                l2.setText("Your chosen school is:");
                l3.setText(schoolSelected);
                takeQuizButton.setVisible(true);

            }
        });

        quitButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                f.setVisible(false);
                f.dispose();
            }
        });

        takeQuizButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                new Quiz(stats);
                f.setVisible(false);
                f.dispose();                    

            }
        });        

    }

   // public static void main(String[] args) throws IOException {
     //  new School();
    //}
}

