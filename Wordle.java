import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Wordle {
    static String wordOfTheDay = getRandomWordFromTxt();

    static String solution() {
        System.out.println("Welcome to my Wordle Game!");
        String[] responses = {"Fantastic!", "You Guessed It!", "Smarty Pants!"};

        int counter = 0;

        while (counter < 6) {
            Scanner input = new Scanner(System.in);
            String guess;
            System.out.println("Enter your guess: ");
            guess = input.nextLine();
            if (guess.length() == 5) {
                if (guess.equals(wordOfTheDay)) {
                    if (counter == 0) {
                        input.close();
                        return "You are a freaking genius. First try? Not fair!";
                    }
                    if (counter == 5) {
                        input.close();
                        return "Whewwww!";
                    }
                    input.close();
                    return responses[new Random().nextInt(responses.length)];
                } else {
                    String hint = getHint(guess);
                    System.out.println("Wrong!");
                    counter+=1;
                    System.out.println(hint);
                    if (counter == 6) {
                        input.close();
                    }
                }
            } else {
                System.out.println("Please enter a five letter word.");
            }
             
        }
        return String.format("Aww man. Sorry you did not guess the word today. It was %s.", wordOfTheDay);
    }

    static String getRandomWordFromTxt() {
        List<String> words = new ArrayList<String>();
        try {
            File wordsFile = new File("./files/words.txt");
            Scanner fileReader = new Scanner(wordsFile);
            while (fileReader.hasNextLine()) {
              String data = fileReader.nextLine();
              words.add(data);
            }
            fileReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        return words.get(new Random().nextInt(words.size()));
    }

    static String getHint(String guess) {
        StringBuffer hint = new StringBuffer("| ");
        String[] guessToArray = guess.split("");
        String[] wordOfTheDayToArray = wordOfTheDay.split("");

        for (int i = 0; i < guessToArray.length; i++) {
            if (Arrays.asList(wordOfTheDayToArray).contains(guessToArray[i]) && guessToArray[i].equals(wordOfTheDayToArray[i])) {
                hint.append(String.format("%s - correct ", guessToArray[i]));
            }
            if (Arrays.asList(wordOfTheDayToArray).contains(guessToArray[i]) && !guessToArray[i].equals(wordOfTheDayToArray[i])) {
                hint.append(String.format("%s - present ", guessToArray[i]));
            }
            if (!Arrays.asList(wordOfTheDayToArray).contains(guessToArray[i]) && !guessToArray[i].equals(wordOfTheDayToArray[i])) {
                hint.append(String.format("%s - absent ", guessToArray[i]));
            }
        }
        return new String(hint);
    }

    public static void main(String[] args) {
        System.out.println(solution());
    }
}