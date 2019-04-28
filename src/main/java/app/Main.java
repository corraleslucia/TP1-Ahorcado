package app;

import Hangman.Hangman;
import databases.Jdbc;
import models.Player;

public class Main {

    public static void main(String[] args) {
        Jdbc jdbc = new Jdbc();
        Hangman hangman = new Hangman();
        Player player1 = new Player("Lucia", hangman);
        Player player2 = new Player("Mariano", hangman);

        hangman.setWord(jdbc.getWord());

        player1.start();
        player2.start();

        try {
            player1.join();
            player2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        jdbc.insertWinner(hangman.getWinnersName(), hangman.getWord());
        System.out.println("**************************************************");
        System.out.println(">>>> GANADOR DEL AHORCADO: " + hangman.getWinnersName());
        System.out.println("**************************************************");
    }
}
