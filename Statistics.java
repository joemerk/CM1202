import java.util.Vector;
import java.util.Scanner;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.io.*;

class Statistics {

	private static Vector<Vector<Integer>> correctChoices = new Vector<Vector<Integer>>(); //Catagorized by Question ID (0,1,2,3.. etc)
	private static Vector<Vector<String>> correctStrings = new Vector<Vector<String>>();
	private static Vector<Integer> quizLengths = new Vector<Integer>(); 
	private static Vector<String> themes;
	private Vector<QuizData> dataSet = new Vector<QuizData>();

	public class Answer { //'struct' for answers, easier than 2d arrays/vectors
		public int choice = 1;
		public int correct = 0; 
		public Answer(int choice, int correct){this.choice = choice; this.correct = correct;}
		}

	private class QuizData {
		private int quizID;
		Vector<Answer> answers = new Vector<Answer>();
		private String school;
		private int quizLength;
		private int correctAnswers = 0;

		public QuizData(int quizID, int[] answersInput, String school) { // Constructor
			this.quizID = quizID;
			this.quizLength = 0;
			this.school = school;
			int aIndex = 0;

			for (int a : answersInput) {
				if (a == correctChoices.get(quizID).get(aIndex)) { 
					answers.add(new Answer(a, 1));
					correctAnswers++;
				} 
				else {
					answers.add(new Answer(a, 0));
				}
				aIndex++;
				quizLength++;
			}
		} //End of constructor

		public int getQuizID() { return quizID; }
		public Vector<Answer> getAnswers() { return answers; }
		public int getQuizLength() { return quizLength ; }
		public String getSchool() { return school; }
		public int getCorrectAnswers() { return correctAnswers; }
	} 


	public Statistics() {
		themes = getThemes();
		getQuizAnswers();
	}

	public void addQuiz(int[] answers) {
		Vector<Integer> vAnswers = new Vector<Integer>();
		for (int a : answers) {vAnswers.add(a);}
		correctChoices.add(vAnswers);
		quizLengths.add(vAnswers.size());

	}

	public void appendQuizData(String quizTheme, int[] answers, String school){
		for (int i = 0; i < themes.size(); i ++){
			if (themes.get(i).contains(quizTheme)) {
				QuizData qd = new QuizData(i, answers, school);
				dataSet.add(qd);
			}
		}
	}

	public Vector<Answer> retrieveStaticstics(int quizID, int questionNum) {
		Vector<Answer> finalData = new Vector<Answer>();

		for (QuizData qd : dataSet){
			if (qd.getQuizID() == quizID) {
				//System.out.println("getting answers for question num " + questionNum );
				finalData.add(qd.getAnswers().get(questionNum));
			}
		}
		return finalData;
	}

	public Vector<Answer> retrieveStaticstics(int quizID, int questionNum, String school) {
		Vector<Answer> finalData = new Vector<Answer>();
			for (QuizData qd : dataSet){
				if (qd.getQuizID() == quizID && qd.getSchool() == school) {
					finalData.add(qd.getAnswers().get(questionNum));
				}
			}
			return finalData;
	}

	public Vector<Answer> retrieveStaticstics(int quizID) {
		Vector<Answer> finalData = new Vector<Answer>();
		for (QuizData qd : dataSet){
			if (qd.getQuizID() == quizID) {
				finalData.addAll(qd.getAnswers());
			}
		}
		return finalData;
	}

	public String generateInterpretation(int quizID) {
		String interpretation = "<html>";
		Vector<Vector<Answer>> allData = new Vector<Vector<Answer>>(); //QuestionData		
		int vIndex = 1;

		//System.out.println(quizID + " quizlength: " + quizLengths.size());
		//try {
		if (quizLengths.size()-1 < quizID) {
			return ("No quiz data found for this theme.");
		}

		for (int i = 0; i < quizLengths.get(quizID); i++) {
			//System.out.println(quizID);
			allData.insertElementAt(retrieveStaticstics(quizID, i), i);
			//System.out.println("Adding vector<Answer> to allData ::: " + retrieveStaticstics(quizID, i).size() );
		}

		Vector<Integer> qTotals = new Vector<Integer>();
		qTotals.setSize(quizLengths.get(quizID));	
		vIndex = 0;

		for (Vector<Answer> qData : allData) {	
			qTotals.insertElementAt(qData.size(), vIndex);
			vIndex++;
		}

		Vector<Integer> qCorrects = new Vector<Integer>();
		qCorrects.setSize(quizLengths.get(quizID));

		vIndex = 0;
		for (Vector<Answer> qData : allData) {
			qCorrects.insertElementAt(0, vIndex);
			vIndex++;
		}


		Vector<Integer> qModes = new Vector<Integer>();
		qModes.setSize(quizLengths.get(quizID));
		vIndex = 0;
		for (Vector<Answer> qData : allData) {
			qModes.insertElementAt(0, vIndex);
			vIndex++;
		}

		int[] count = {0, 0, 0, 0};
		vIndex = 0;
		int tMax = -1;
		for (Vector<Answer> question : allData) {
			for (Answer a : question) {
					if (a.correct == 1) {
						qCorrects.set(vIndex, qCorrects.get(vIndex) + 1); 
					}
					count[a.choice-1]++; //THIS IS BECAUSE CHOICES ARE 1-4
					if (count[a.choice-1] > tMax) {
						tMax = count[a.choice-1];
						qModes.set(vIndex, a.choice);
					}
			}
			count = new int[]{0,0,0,0};
			tMax = -1;
			vIndex++;
		}

		float correctPerc = 0f;
		int qIndex = 0;
		for (Integer q: qCorrects) {
			if (q != null) {
				correctPerc += ((float)q/(float)(qTotals.get(qIndex)));
			}
			qIndex++;
		}

		correctPerc = correctPerc/quizLengths.get(quizID);

		float max = 0;
		float maxIndex = 1;
		qIndex = 1;
		for (Integer q: qCorrects) {
			if (q != null) {
				if (q > max) {max = q; maxIndex = qIndex;}
				qIndex++;
			}
		}

		float min = 999;
		float minIndex = 1;
		qIndex = 1;
		for (Integer q: qCorrects) {
			if (q != null) {
				if (q < min) {min = q; minIndex = qIndex;}
				qIndex++;
			}
		}
		

		interpretation += ("Quiz ID - " + quizID + "<br>");
		for (int i = 0; i <= quizLengths.get(quizID)-1; i++) {
			interpretation += ("Question " + (i+1) + " -- Total answers: " + qTotals.get(i) + "; Correct Answers: " + qCorrects.get(i) + "; Most common answer: " + qModes.get(i) + "<br>");
		}
		
		interpretation += ("Total Quiz Accuracy: " + correctPerc*100 + "%" + "<br>");
		interpretation += ("Question with highest accuracy: Question " + maxIndex + "<br>");
		interpretation +=("Question with lowest accuracy: Question " + minIndex + "<br>") ;
		interpretation += ("</html>");

		return interpretation;

	}

	public static Vector<String> getThemes(){
		Vector<String> themes = new Vector<String>();

		try{
			String filename = "Questions.txt";
			File fileIn = new File(filename);
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
            System.out.println( "Problem reading file... ");
           // throw e;  // re-raise exception
        }
        Set<String> temp = new HashSet<String>();
        temp.addAll(themes);
        themes.clear();
        themes.addAll(temp);

        return themes;
	}

	public void getQuizAnswers( ){

      	String fileName = "Questions.txt";
      	String currentLine;
      	String[] question = {};
      	int line;

      	Vector<Vector<Integer>> _questionA = new Vector<Vector<Integer>>();
      	Vector<Vector<String>> _questionS = new Vector<Vector<String>>();

      	for (int q = 0; q < themes.size(); q++){
      		_questionA.add(new Vector<Integer>());
      		_questionS.add(new Vector<String>());
      	}

          try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            while((currentLine = br.readLine()) != null){
                question = currentLine.split(",");
               // System.out.println(question[0]); gets to here
                for (int i = 0; i < themes.size(); i++) {
                	if (question[6].contains(themes.get(i))) {
                		for (int j = 1; j <= 4; j++){
                			if (question[j].contains(question[5])) {
                				_questionA.get(i).add(j);
                				_questionS.get(i).add(question[5]);
                				//System.out.println("j:" + j);
                			}
                		}
                	}
                }
            }

            	for (Vector<Integer> vi : _questionA) {
            		int[] ta = new int[vi.size()];
            		int count = 0;
            		for (Integer k : vi) {
            			ta[count] = k;
            			count++;
            			//System.out.print(k);
            		}
            		addQuiz(ta);
            	}
            	
            	correctStrings = _questionS;

            	/*System.out.println();
            	for (String s : correctStrings.get(0)) {
            		System.out.println(s);
            	}
            	System.out.println();
            	for (String s : correctStrings.get(1)) {
            		System.out.println(s);
            	} */

            br.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
  }

  public static int getQuestionIDbyAnswer(String answer){
  		for (Vector<String> vs : correctStrings){
  			for (String aString : vs) {
  				if (aString.equals(answer)) {
  					return vs.indexOf(aString);
  				}
  			}
  		}
  		return -1;
  	}

  public static int getQuizLengthbyTheme(String theme){
  		for (int i = 0; i < themes.size(); i++){
  			if (themes.get(i).equals(theme)){
  				return quizLengths.get(i);
  			}
  		}
  		return -1;
  }

}