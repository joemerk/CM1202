import java.util.Random;
import java.io.*;
import java.util.ArrayList;

class Questions {
	static ArrayList<Integer> ListOfAnsweredQuestions = new ArrayList<>();
	static int SizeOfFile = 0;

	public Questions( String title, String barcode) {
	}

  public static void SetSizeOfFile(){
    String FileName = "Questions.txt";
    int Count = 1;
    String CurrentLineToBeRead;
    try {
      FileInputStream fis = new FileInputStream(FileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fis));

      while((CurrentLineToBeRead = br.readLine()) != null){
        Count = Count + 1;
      }
        br.close();
    }catch (Exception e) {
      e.printStackTrace();
    }

    //System.out.println("Count is " + Count);
    SizeOfFile = Count;
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

    public static void deleteQuestion (String[] questionDel) {
      String FileName = "Questions.txt";
      int rowToBeDeleted = 0;

      try{
        //rowToBeDeleted = rowToBeDeleted - 1;                                  //this is commented otherwise the program wouldn't run but it needs to be uncommented when
        BufferedWriter writer = new BufferedWriter(new FileWriter(FileName));   //it gets combined with the admin thingy
        for (int i = 0; i < questionDel.length; i++){
          if (rowToBeDeleted == i){
            i++;
          }
          if (i<questionDel.length){
            writer.write(questionDel[i] + ",");
            writer.flush();
            writer.close();
          }
        }
      } catch (Exception e){
        e.printStackTrace();
      }
      SizeOfFile = SizeOfFile - 1;
    }

     public static String[] getRandomUnansweredQuestion( ){
      	String FileName = "Questions.txt";
      	String CurrentLineToBeRead;
      	String[] QuestionReturned = new String[] {"","","","","","",""}; //had to predeclare these values because of indexing issue
      	int LineNumber;
        boolean endOfQuestions = false;

        do{
          //QuestionReturned = new String[] {}; 
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
        } while(QuestionReturned[6].equals(QuizPresets.getChoice()) == false);

        ListOfAnsweredQuestions.add(LineNumber);
        return QuestionReturned;
  }

  public static void resetListOfAnsweredQuestions( ){
    ListOfAnsweredQuestions.clear();
  }
}
