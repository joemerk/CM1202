import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
public class Quiz extends Frame implements ActionListener{  
	JLabel QuestionText; JLabel ResultOfButtonPress;
  JButton RestartButton; JButton QuitButton;
	JButton[] AnswerButton = new JButton[4];
  int QuestionNumber = 0; String CorrectAnswer; int QuestionsAnsweredCorrectly = 0;
  boolean QuizFinished = false;
	Quiz(){  
    this.setTitle("Quiz");

    ResultOfButtonPress = new JLabel();
    ResultOfButtonPress.setBounds(100, 325, 400, 80);
    add(ResultOfButtonPress);

    QuestionText = new JLabel();
    QuestionText.setBounds(100, 50, 400, 80);
    add(QuestionText);

    RestartButton = new JButton();
    RestartButton.addActionListener(this);
    RestartButton.setText("Restart");
    RestartButton.setBounds(50, 400, 150, 30);
    add(RestartButton);

    QuitButton = new JButton();
    QuitButton.addActionListener(this);
    QuitButton.setText("Quit");
    QuitButton.setBounds(350, 400, 150, 30);
    add(QuitButton);

    for (int i=0; i<4; i++) {
      AnswerButton[i] = new JButton();
      AnswerButton[i].addActionListener(this);
    }
    AnswerButton[0].setBounds(25, 150, 200, 60);
    AnswerButton[1].setBounds(25, 250, 200, 60);
    AnswerButton[2].setBounds(325, 150, 200, 60);
    AnswerButton[3].setBounds(325, 250, 200, 60);
    for (int i=0; i<4; i++) {
      add(AnswerButton[i]);;
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
      AnswerButton[i].setText(PossibleAnswers[i]);
    }
    revalidate();
    repaint();
  }

  public void actionPerformed(ActionEvent evt) {
    String btnLabel = evt.getActionCommand();

    switch(btnLabel){
      case "Restart": QuestionsAnsweredCorrectly = 0;
                      QuestionNumber = 0;
                      Questions.resetListOfAnsweredQuestions();
                      ResultOfButtonPress.setText("Quiz Restarted");
                      if (QuizFinished == true){
                        for (int i=0; i<4; i++) {
                          AnswerButton[i].addActionListener(this);
                        }
                      }
                      QuizFinished = false;
                      break;
      case "Quit":    this.dispose();
                      break;
      default:        if (btnLabel.equals(CorrectAnswer)){
                        ResultOfButtonPress.setText("You got it right.");
                        QuestionsAnsweredCorrectly = QuestionsAnsweredCorrectly + 1;
                      } else {
                        ResultOfButtonPress.setText("Too bad, the correct answer was " + CorrectAnswer);
                      }
                      break;
    }

    if (QuestionNumber < Questions.SizeOfFile){
      moveToNextQuestion();
    }else{
      ResultOfButtonPress.setText("<html>" + ResultOfButtonPress.getText() + "<br/> Congratulations, you scored " + QuestionsAnsweredCorrectly + "/" + Questions.SizeOfFile + "</html>");
      QuizFinished = true;
      for (int i=0; i<4; i++) {
          AnswerButton[i].removeActionListener(this);
      }
    }
   }

  public static void main(String[] args) { 
  	new Quiz();
  }
}