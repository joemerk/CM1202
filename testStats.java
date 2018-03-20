class testStats{

	public static void main (String args[]) {
		Statistics stats = new Statistics(); //This statistics objects handles the stats for ALL quizes + questions

		stats.addQuiz(new int[] {1,4,1,4,1,3,3,1,2,3,1}); //This line adds the new quiz, arguments are correct answers. This would be all the CorrectAnswers values.
		stats.addQuiz(new int[] {1,4}); //Adding another quiz answers to the stats object. 
		//Quiz IDs will be automatically generated.
		//Aka quiz with ID: 0 and quiz with ID: 1 is now recognised in the stats object.


		//Statistics object is fairly dynamic in that it can handle any amount of questions per quiz.

		// This is all just dummy data to test the class.
		// In reality, one of these lines would only be executed after someone has taken the quiz.
		// The first argument is the quiz ID, second is an array of ints representing the choices made during the quiz.
		// Third argument is School.

		stats.appendQuizData(0, new int[]{1,4,3,4,1,3,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{1,3,3,1,1,1,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{3,4,3,4,1,1,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{3,4,3,4,1,1,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{1,4,2,3,1,2,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{3,4,3,3,1,2,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{2,1,2,1,1,2,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{1,4,3,3,1,2,1,2,3,2,1}, "Kings of Wessex");
		stats.appendQuizData(0, new int[]{3,4,1,4,1,3,1,2,3,2,1}, "Kings of Wessex");

		//Adding attempts to the second quiz (ID: 1)
		stats.appendQuizData(1, new int[]{3,1}, "Big Boy school");
		stats.appendQuizData(1, new int[]{3,2}, "Kings of Wessex");
		stats.appendQuizData(1, new int[]{4,4}, "Kings of Wessex");
		stats.appendQuizData(1, new int[]{1,2}, "Kings of Wessex");

		//Generate the interpretation of all attempts on QUIZ 1 (Quiz with ID: 1)
		stats.generateInterpretation(1);
		//stats.generateInterpretation(0);
	}
}