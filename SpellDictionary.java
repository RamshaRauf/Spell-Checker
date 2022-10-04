import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;

/**
 *  Class to store a spelling dictionary and methods to check if a word is in a dictionary or not and also has a method to suggest words if they are not spelled correctly
 *  @author Ramsha Rauf
 *  @version Spring 2022
 */
public class SpellDictionary {

  /** Stores the dictionary words */
  private HashSet<String> words;

  /** stores the alphabets(A-z) */
  public static String[] alphabets = new String[] {
      "a",
      "b",
      "c",
      "d",
      "e",
      "f",
      "g",
      "h",
      "i",
      "j",
      "k",
      "l",
      "m",
      "n",
      "o",
      "p",
      "q",
      "r",
      "s",
      "t",
      "u",
      "v",
      "w",
      "x",
      "y",
      "z"
    };

  /** 
   *  Constructor sets up the set of words by scanning the file and storing it in a hashSet
   *  @param filename The word list file
   */
  public SpellDictionary(String filename) { 
    this.words = new HashSet<String>();
    try {
      Scanner input = new Scanner(new File(filename));
      while (input.hasNextLine()) {
        String word = input.nextLine();
        words.add(word);
      }
    } catch (FileNotFoundException e) {

    }
  }

  /** 
   *  checks whether the dictionary contains word for a particular spelling
   *  @param Spelling of a word 
   *  @return T/F
   */
  public boolean containsWord(String Spelling){
    if (words.contains(Spelling)){
      return true;
    }else{
      return false;
    }

  }

  /**
   *  checks whether the dictionary contains word for a particular spelling
   *  @param incorrect spelling 
   *  @return ArrayList<String> of correctly spelled words that are exactly one edit away
   */
  public ArrayList<String> nearMisses(String incorrect){
    ArrayList<String> suggested_words = new ArrayList<String>();
    String [] incorrect_word = incorrect.split("");

    //Deletation - deletes each word and adds a word to the suggest word string array if a valid word was created
    for (int i = 0; i < incorrect.length(); i++){

      List<String> modified = new ArrayList<String>(Arrays.asList(incorrect_word));
      modified.remove(incorrect_word[i]);
      String modified_string = String.join("",modified);
      if (words.contains(modified_string)){
        suggested_words.add(modified_string);
      }
      //System.out.println(suggested_words);

    }

    //Insertion- inserts an alphabet at every point of the word and adds a word to the suggest word string array if a valid word was created
    for (int i = 0; i < alphabets.length; i++){

      for (int j = 0; j < (incorrect.length()+1); j++){

        List<String> modified = new ArrayList<String>(Arrays.asList(incorrect_word));
        modified.add(j,alphabets[i]);
        String modified_string = String.join("",modified);
        if (words.contains(modified_string)){
          suggested_words.add(modified_string);
        }

      }

    }

    //Substitution- replaces each character with an alphabet and adds a word to the suggest word string array if a valid word was created
    for (int i = 0; i < alphabets.length; i++){

      for (int j = 0; j < incorrect.length(); j++){

        List<String> modified = new ArrayList<String>(Arrays.asList(incorrect_word));
        modified.set(j, alphabets[i]);
        String modified_string = String.join("",modified);
        if (words.contains(modified_string)){
          suggested_words.add(modified_string);
        }

      }

    }

     //Transpositions- swaps two adjacent characters and adds a word to the suggest word string array if a valid word was created
    for (int j = 0; j < (incorrect.length()-1); j++){
       
      List<String> modified = new ArrayList<String>(Arrays.asList(incorrect_word));
      Collections.swap(modified, j, j+1);
      String modified_string = String.join("",modified);
      if (words.contains(modified_string)){
        suggested_words.add(modified_string);
      }

    }

     //Splits- divides the word into two legal words and adds two words to the suggest word string array if both words are valid 
    for (int j = 0; j < (incorrect.length()-1); j++){

      List<String> modified = new ArrayList<String>(Arrays.asList(incorrect_word));
      modified.add(j," ");
      String modified_string = String.join("",modified);
      String[] two_words = modified_string.split(" ");
      String first_word = two_words[0];
      String second_word = two_words[1];

      if (words.contains(first_word) &&  words.contains(second_word)){
        suggested_words.add(modified_string);
       }

    }
   
    //removes duplicates 
    ArrayList<String> no_duplicates = new ArrayList<String>();

    for (String element:suggested_words){

      if(!no_duplicates.contains(element)){
        no_duplicates.add(element);
      }

    }
    suggested_words = no_duplicates;
    return suggested_words;
  }

}