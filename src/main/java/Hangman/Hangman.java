package Hangman;

import models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

public class Hangman {
    private List<Character> word;
    private List<Character> guessedWord;
    private boolean isEmpy;
    private boolean isWinner;
    private Player winner;

    public Hangman() {
        isEmpy = true;
        isWinner = false;
        word = new ArrayList<Character>();
        guessedWord = new ArrayList<Character>();
        winner = new Player();
    }

    private boolean isLetterInWord(Character c) {
        boolean isLetter = word.contains(c);
        if (isLetter) {
            getLetterIndexes(c).forEach(i -> guessedWord.set(i, c));
        }
        return isLetter;
    }

    private List<Integer> getLetterIndexes(Character c) {
        return IntStream.range(0, word.size())
                .filter(i -> word.get(i).equals(c))
                .boxed()
                .collect(Collectors.toList());
    }

    public String getWinnersName() {
        Optional<String> name = ofNullable(winner.getPlayerName());
        return name.orElse("SIN GANADOR");
    }

    public String getGuessedWord() {
        return guessedWord.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public String getWord() {
        return word.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public void setWord(String word) {
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            this.word.add(wordArray[i]);
        }
        guessedWord = new ArrayList<>();
        this.word.forEach(c -> guessedWord.add('-'));
    }

    public synchronized boolean guessWord(Player player, Character ch) throws Exception {
        boolean guessedLetter = false;
        while (!isEmpy) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Game: error in guessWord: " + e.getMessage());
            }
        }
        if (!isWinner) {
            System.out.println("----------------------------");
            System.out.println(format(">>> Jugador: %s\n\tIntentos disponibles: %d", player.getPlayerName(), player.getTrys()));
            System.out.println("Word : " + getGuessedWord());
            System.out.println(format("Probará con la letra: " + ch));
            guessedLetter = isLetterInWord(ch);
            if (guessedLetter) {
                System.out.println("================================");
                System.out.println("Adivinaste una letra! :D");
                System.out.println("Word : " + getGuessedWord());
                if (!guessedWord.contains('-')) {
                    isWinner = true;
                    System.out.println("----------------------------");
                    System.out.println("TENEMOS UN GANADOR!");
                    System.out.println("----------------------------\n");
                    winner = player;
                }
            } else {
                player.restTry();
                System.out.println("================================");
                System.out.println("Lo siento, esa letra no está en la palabra ");
            }
            isEmpy = true;
            notifyAll();
            return guessedLetter;
        }
        throw new Exception("Ya hay ganador. Perdiste :'(");
    }

    public boolean isWinner() {
        return isWinner;
    }


}
