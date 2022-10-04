import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *  Class takes in user input and checks the spelling by calling SpellingDictionary
 *  @author Ramsha Rauf
 *  @version Spring 2022
 */
public class SpellChecker {
  /**
  *  takes in a correct word and prints it to indicate that the word is spelled correctly
  *  @param String correct word
  */
  public static void print_correct(String word){
      System.out.print("\n'"+ word + "'" + " is spelled correctly.");
  }
  
  /**
  *  takes in an incorrect word and prints suggestions to use
  *  @param String incorrect word
  */
  public static void print_incorrect(String word){
    SpellDictionary dictionary = new SpellDictionary("words.txt");
     System.out.print("\nNot found: " + word);
          System.out.print("\nSuggestions: ");
          ArrayList<String> suggestions = dictionary.nearMisses(word);

          for(int i = 0; i < suggestions.size(); i++){

          System.out.print(suggestions.get(i)+ " ");
        }

  }

  /**
  *  takes in user input and checks the spelling of the words and prints if they are correct or not and also provides suggestions and if a file is sent through then it read every line and stores it and removes any duplicates then checks if the word is correct or not.
   *  @param string[]
  */
  public static void main(String[]args) {
    SpellDictionary dictionary = new SpellDictionary("words.txt");

    if (args.length == 0){
      Scanner input = new Scanner(System.in);
      String file_content = "";

      while(input.hasNextLine()){
        file_content += input.nextLine();
      }
      

      String[] file = file_content.split(" ");

      //removes duplicates
      ArrayList<String> no_duplicates = new ArrayList<String>();
      for(int i=0; i < file.length; i++){
        if( !no_duplicates.contains(file[i]) ){
          no_duplicates.add(file[i]);
        }
      }
      file = no_duplicates.toArray( new String[no_duplicates.size()] );
      

      for (String word:file){
        word = word.toLowerCase();
        word = word.replaceAll("\\p{Punct}","");

        if (!dictionary.containsWord(word)){
          print_incorrect(word);
        }
      }

    }else{
      for (String word:args){
        word = word.replaceAll("\\p{Punct}","");
        word = word.toLowerCase();
        if (dictionary.containsWord(word)){
          print_correct(word);
        }else{
          print_incorrect(word);
        }
      }
    }
  }
}