package google.com.lab2.fragments.main_screen;

import java.util.Random;

/**
 * Created by Sergey on 13.11.2016.
 */

public class GameNumber {

    public static final int TOOOO_DIFF = 40;
    public static final int TOOO_DIFF = 20;
    public static final int TOO_DIFF = 5;


    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 100;

    public static final int CORRECT_NUMBER_MESSAGE = 0;
    public static final int TOOOO_BIG_MESSAGE = 1;
    public static final int TOOO_BIG_MESSAGE = 2;
    public static final int TOO_BIG_MESSAGE = 3;
    public static final int ALMOST_DONE_MESSAGE = 4;
    public static final int TOO_SMALL_MESSAGE = 5;
    public static final int TOOO_SMALL_MESSAGE = 6;
    public static final int TOOOO_SMALL_MESSAGE = 7;
    public static final int NOT_NUMBER_ERROR_MESSAGE = 8;
    public static final int INVALID_NUMBER_ERROR_MESSAGE = 9;

    private int correctNumber;

    public GameNumber(){
        correctNumber = generateNumber();
    }

    public int getMessageNumber(String inputNumber){
        int number;
        try {
            number = Integer.parseInt(inputNumber);
        } catch(NumberFormatException e) {
            return NOT_NUMBER_ERROR_MESSAGE;
        }
        if(number < MIN_VALUE || number > MAX_VALUE){
            return INVALID_NUMBER_ERROR_MESSAGE;
        }
        if(number == correctNumber){
            return CORRECT_NUMBER_MESSAGE;
        }else {
            return handleDiff(number - correctNumber);
        }
    }

    private int handleDiff(int diff) {
        boolean isBig = diff > 0;
        diff = Math.abs(diff);
        if(diff > TOOOO_DIFF){
            return isBig ? TOOOO_BIG_MESSAGE : TOOOO_SMALL_MESSAGE;
        }else if(diff > TOOO_DIFF){
            return isBig ? TOOO_BIG_MESSAGE : TOOO_SMALL_MESSAGE;
        }else if(diff > TOO_DIFF) {
            return isBig ? TOO_BIG_MESSAGE : TOO_SMALL_MESSAGE;
        }else{
            return ALMOST_DONE_MESSAGE;
        }
    }

    public static int generateNumber() {
        return new Random().nextInt(MAX_VALUE);
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public void setCorrectNumber(int correctNumber) {
        this.correctNumber = correctNumber;
    }
}
