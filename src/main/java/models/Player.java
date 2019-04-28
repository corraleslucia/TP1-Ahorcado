package models;

import Hangman.Hangman;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;
import static java.util.Arrays.asList;

public class Player extends Thread {

    private static List<Character> ABECEDARY = new ArrayList<>(asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
    private int trys = 10;
    private String name;
    private Hangman hangman;

    public Player() {
    }

    public Player(String name, Hangman hangman) {
        this.name = name;
        this.hangman = hangman;
    }


    public int getTrys() {
        return trys;
    }

    public void restTry() {
        trys--;
    }


    public String getPlayerName() {
        return name;
    }

    //Returns a random char from the List.
    public synchronized char chooseLetter() throws Exception {
        if (!ABECEDARY.isEmpty()) {
            return ABECEDARY.remove((int) (random() * ABECEDARY.size()));
        }
        throw new Exception("No hay mas letras.");
    }

    public void run() {
        while (!hangman.isWinner() && trys > 0) {
            char letter = 0;
            try {
                letter = chooseLetter();
            } catch (Exception e) {
            }
            try {
                hangman.guessWord(this, letter);
            } catch (Exception e) {
            }
        }
        if (trys == 0) {
            System.out.println("----------------------------");
            System.out.println("Lo siento, no tienes mas intentos. Has perdido :'(");
            System.out.println("----------------------------");
        }
    }
}
