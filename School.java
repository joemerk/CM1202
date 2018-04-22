import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class School extends Frame {

    School() throws IOException {

        String FileName = "Schools.txt";

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

        quitButton.setBounds(350, 200, 150, 30);

        l1.setBounds(15, 100, 150, 30);
        l2.setBounds(15, 200, 150, 30);
        l3.setBounds(200, 200, 150, 30);
        c.setBounds(250, 100, 75, 30);
        button.setBounds(350, 100, 150, 30);

    
        f.add(l1);
        f.add(c);
        f.add(button);
        f.add(quitButton);
        f.add(l2);
        f.add(l3);

        f.setSize(575, 500);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
    
        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                String schoolSelected = c.getSelectedItem().toString();
                l2.setText("Your chosen school is");
                l3.setText(schoolSelected);
            }
        });

        quitButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                System.exit(0);
            }
        });      

    }

    public static void main(String[] args) throws IOException {
        new School();
    }
}

