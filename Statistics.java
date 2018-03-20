import java.util.Vector;

class Statistics {

	private static Vector<Vector<Integer>> correctChoices = new Vector<Vector<Integer>>(); //Catagorized by Question ID (0,1,2,3.. etc)
	private static Vector<Integer> quizLengths = new Vector<Integer>(); 

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


	public Statistics() { //??
	}

	public void addQuiz(int[] answers) {
		Vector<Integer> vAnswers = new Vector<Integer>();
		for (int a : answers) {vAnswers.add(a);}
		correctChoices.add(vAnswers);
		quizLengths.add(vAnswers.size());

	}

	public void appendQuizData(int quizID, int[] answers, String school){
		QuizData qd = new QuizData(quizID, answers, school);
		dataSet.add(qd);
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

	public void generateInterpretation(int quizID) {

		Vector<Vector<Answer>> allData = new Vector<Vector<Answer>>(); //QuestionData		
		int vIndex = 1;

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
		

		System.out.println("Quiz ID - " + quizID);
		for (int i = 0; i <= quizLengths.get(quizID)-1; i++) {
			System.out.println("Question " + (i+1) + " -- Total answers: " + qTotals.get(i) + "; Correct Answers: " + qCorrects.get(i) + "; Most common answer: " + qModes.get(i));
		}
		
		System.out.println("Total Quiz Accuracy: " + correctPerc*100 + "%");
		System.out.println("Question with highest accuracy: Question " + maxIndex);
		System.out.println("Question with lowest accuracy: Question " + minIndex) ;

	}
}