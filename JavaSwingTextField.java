import javax.swing.*;


public class JavaSwingTextField {

    private JTextField myTextField;
    private JLabel title;
    private JButton inputButton, cancelButton;

    public JavaSwingTextField() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
      

        
        myTextField = new JTextField("Start",20);
        inputButton = new JButton();
        cancelButton = new JButton();
        inputButton.setText("Enter");
        cancelButton.setText("Cancel");
        cancelButton.setBounds(25, 150, 200, 60);
        myTextField.setBounds(25, 250, 200, 60);
        inputButton.setBounds(25, 350, 200, 60);
        // Add the label to the JFrame

        frame.getContentPane().setLayout(null);
        frame.add(myTextField);
        frame.add(inputButton);
        frame.add(cancelButton);

        //pack frame to component preferred sizes
        //frame.pack();

        frame.setSize(500,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Create UI on EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JavaSwingTextField();
            }
        });
    }
}