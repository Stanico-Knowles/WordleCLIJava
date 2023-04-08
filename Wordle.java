import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
    public static String solution(String wordOfTheDay) {
        System.out.println("Welcome to my Wordle Game!");

        String[] wordOfTheDayToArray = wordOfTheDay.split("");
        String[] responses = {"Fantastic!", "You Guessed It!", "Smarty Pants!"};

        int counter = 0;

        while (counter < 6) {
            StringBuffer hint = new StringBuffer("| ");
            Scanner input = new Scanner(System.in);
            String guess;
            System.out.println("Enter your guess: ");
            guess = input.nextLine();
            String[] guessToArray = guess.split("");
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
                System.out.println("Wrong!");
                counter+=1;
                System.out.println(hint);
                if (counter == 6) {
                    input.close();
                }
            } 
        }
        return "Aww man. Sorry you did not guess the word today.";
    }

    public static void main(String[] args) {
        System.out.println(solution("hello"));
    }
}