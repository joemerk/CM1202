import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;


public class School {

    public static void main(String[] args) throws IOException {

        String FileName = "Schools.txt";

        BufferedReader schoolFile = new BufferedReader(new FileReader(FileName));
        List<String> schoolArray = new ArrayList<String>();

        String school; while(( school = schoolFile.readLine()) != null) {
            schoolArray.add(school);
        }
        schoolFile.close();

        String[] array = schoolArray.toArray(new String[schoolArray.size()]);
        JComboBox c = new JComboBox(array);
    
        JLabel l1 = new JLabel("Please select a school");
        JButton button = new JButton("Select");
    
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
    
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(350, 200, 150, 30);
        
    
        JPanel p = new JPanel();
    
        p.add(l1);
        p.add(c);
        p.add(button);
        p.add(quitButton);
        p.add(l2);
        p.add(l3);
    
        f.add(p);
    
    
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
    
}

