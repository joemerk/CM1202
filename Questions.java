import java.util.Random;
import java.io.*;
import java.util.ArrayList;

class Questions {
	static ArrayList<Integer> ListOfAnsweredQuestions = new ArrayList<>();
	static int SizeOfFile = 10;

	public Questions( String title, String barcode) {
	}

	public static void AddQuestion( String[] QuestionDetails ) {
      String FileName = "Questions.txt";
      String DataToBeWritten;

      try{
      BufferedWriter bw = new BufferedWriter(new FileWriter(FileName, true));
        for (int i = 0; i < QuestionDetails.length; ++i) {
          DataToBeWritten = QuestionDetails[i];
          bw.write(DataToBeWritten + ",");   
          bw.flush();
        }
        bw.newLine();
        bw.flush();
        bw.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        SizeOfFile = SizeOfFile + 1;
    }

     public static String[] getRandomUnansweredQuestion( ){
      	String FileName = "Questions.txt";
      	String CurrentLineToBeRead;
      	String[] QuestionReturned = {};
      	int LineNumber;

      	Random Random = new Random();
  		do{
  		LineNumber = Random.nextInt(SizeOfFile) + 1;
  		}while(ListOfAnsweredQuestions.contains(LineNumber) != false);

       try {
            FileInputStream fis = new FileInputStream(FileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            int Count = 1;
            while((CurrentLineToBeRead = br.readLine()) != null){
              if (Count == LineNumber){
                QuestionReturned = CurrentLineToBeRead.split(",");
              }
              Count = Count + 1;
            }

            br.close();
      } catch (Exception e) {
          e.printStackTrace();
        }

        ListOfAnsweredQuestions.add(LineNumber);
        return QuestionReturned;
  }

  public static void resetListOfAnsweredQuestions( ){
    ListOfAnsweredQuestions.clear();
  }
}