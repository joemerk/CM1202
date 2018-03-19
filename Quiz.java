import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
public class Quiz extends Frame implements ActionListener{  
	JLabel QuestionText; JLabel ResultOfButtonPress;
	JButton[] Buttons = new JButton[4];
  int QuestionNumber = 0; String CorrectAnswer; int QuestionsAnsweredCorrectly = 0;
	Quiz(){  
    ResultOfButtonPress = new JLabel();
    ResultOfButtonPress.setBounds(100, 400, 400, 20);
    add(ResultOfButtonPress);

    QuestionText = new JLabel();
    QuestionText.setBounds(100, 50, 400, 80);
    add(QuestionText);

    for (int i=0; i<4; i++) {
      Buttons[i] = new JButton();
      Buttons[i].addActionListener(this);
    }
    Buttons[0].setBounds(25, 175, 200, 60);
    Buttons[1].setBounds(25, 275, 200, 60);
    Buttons[2].setBounds(325, 175, 200, 60);
    Buttons[3].setBounds(325, 275, 200, 60);
    for (int i=0; i<4; i++) {
      add(Buttons[i]);;
    }
 
    moveToNextQuestion();

	  setSize(575,500); 
	  setLayout(null);
	  setVisible(true);
	  setResizable(false);
	}  

  public void moveToNextQuestion(){
    String[] CurrentQuestion;
    CurrentQuestion = Questions.getRandomUnansweredQuestion();


    QuestionNumber = QuestionNumber + 1;  
    QuestionText.setText("<html>Question Number: " + QuestionNumber + "<br/>" + CurrentQuestion[0] + "</html>");
    String FirstAnswer = CurrentQuestion[1];
    String SecondAnswer = CurrentQuestion[2];
    String ThirdAnswer = CurrentQuestion[3];
    String FourthAnswer = CurrentQuestion[4];
    CorrectAnswer = CurrentQuestion[5];
    String[] PossibleAnswers = {FirstAnswer, SecondAnswer, ThirdAnswer, FourthAnswer};

    for (int i=0; i<4; i++) {
      Buttons[i].setText(PossibleAnswers[i]);
    }
    revalidate();
    repaint();
  }

  public void actionPerformed(ActionEvent evt) {
    String btnLabel = evt.getActionCommand();

    if (btnLabel.equals(CorrectAnswer)){
        ResultOfButtonPress.setText("You got it right.");
        QuestionsAnsweredCorrectly = QuestionsAnsweredCorrectly + 1;
    } else {
    	ResultOfButtonPress.setText("Too bad, the correct answer was " + CorrectAnswer);
    }
    if (QuestionNumber < Questions.SizeOfFile){
      moveToNextQuestion();
    }else{
      ResultOfButtonPress.setText("Congratulations, you scored " + QuestionsAnsweredCorrectly + "/10");
      for (int i=0; i<4; i++) {
          Buttons[i].removeActionListener(this);
      }
    }
   }

  public static void main(String[] args) { 
  	new Quiz();
  }
}