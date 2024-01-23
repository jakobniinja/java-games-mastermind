package com.jakobniinja;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class WordMastermind extends MyWindow {

  private static final int NUMBER_OF_WORDS = 1;

  private static final String FILE_NAME = "wordMastermind.txt";

  private String word;

  private String clue;

  public WordMastermind() {
    String[] words = new String[NUMBER_OF_WORDS];
    Random random = new Random();

    try {

      // read a list of words from the file
      BufferedReader in = new BufferedReader(new FileReader(new File(FILE_NAME)));
      for (int i = 0; i < NUMBER_OF_WORDS; i++) {
        words[i] = in.readLine();
      }
      in.close();
      boolean repeat = true;
      while (repeat) {

        int pick = random.nextInt(NUMBER_OF_WORDS);
        word = words[pick];
        word = word.toUpperCase();

        // Prompt user for a guess
        print("I'm thinking of a 4 letter word.");
        print(" I will give you clues:");
        print(" An \"O\" means you guessed the correct letter in the correct position.");
        print(" An \"X\" means you guessed the correct letter but in the wrong position.");
        String guess = promptForString("Guess my word").toUpperCase();
        int count = 1;
        String originalWord = word;
        boolean solved = false;
        // Repeat while not solved
        while (!solved) {
          if (guess.length() == 4) {

            // reset word and clue for each guess
            word = originalWord;
            clue = "----";
            // set the clue
            findRightPlaceLetters(guess);
            findWrongPlaceLetters(guess);
            // did he guess it?
            if (guess.equals(originalWord)) {
              solved = true;
            } else {
              guess = promptForString(clue).toUpperCase();
              count++;
            }
          } else {
            guess = promptForString("Your guess must contain 4 letters.").toUpperCase();
          }
        }

        // congratulate the user
        print("OOOO");
        print("You guessed it in " + count + " tries!");
        repeat = promptForYesNo("Do you want to play again?");
        print();
      }

      System.exit(0);
    } catch (FileNotFoundException e) {
      print("Could not find file " + FILE_NAME);
    } catch (IOException e) {
      print("Could not read file " + FILE_NAME);
    }
  }

  private void findRightPlaceLetters(String guess) {
    // loop through all the letters of the guess
    for (int i = 0; i < guess.length(); i++) {
      // get the letter in that position of the guess and word
      String guessLetter = guess.substring(i, i + 1);
      String wordLetter = word.substring(i, i + 1);
      // if guess letter is same as word letter, set clue to O and word to -
      if (guessLetter.equals(wordLetter)) {
        clue = MyStringMethods.replaceStringAt(clue, i, "O");
        word = MyStringMethods.replaceStringAt(word, i, "-");
      }
    }
  }

  private void findWrongPlaceLetters(String guess) {
    // loop through all the letters of the guess
    for (int i = 0; i < guess.length(); i++) {
      // get the guess letter at position i
      String letter = guess.substring(i, i + 1);
      // if letter is in word (indexOf > -1)
      int letterLoc = word.indexOf(letter);
      if (letterLoc > -1) {
        // replace letter in word with -
        word = MyStringMethods.replaceStringAt(word, letterLoc, "-");
        // set clue at the loop index to X if it is still -
        String clueLetter = clue.substring(i, i + 1);
        if (clueLetter.equals("-")) {
          clue = MyStringMethods.replaceStringAt(clue, i, "X");
        }
      }
    }
  }

  public static void main(String[] args) {
    new WordMastermind();
  }
}
