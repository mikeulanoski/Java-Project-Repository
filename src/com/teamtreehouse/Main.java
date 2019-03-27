//Final Build 3/27/19
package com.teamtreehouse;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.BufferedReader;


public class Main {

    public static void main(String[] args) throws Exception {

// This section requires the two players to sign into the game.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player 1, please, sign in: ");
        String nameOne = scanner.nextLine();
        System.out.print("Player 2, please sign in: ");
        String nameTwo = scanner.nextLine();

        System.out.print("\n");

// This section asks the users how many names of past winners they would like to see.
        System.out.print("How many past winners would you like to see?");
        System.out.print("\n");
        Integer pastWin = scanner.nextInt();
        scanner.close();

        System.out.print("\n");

        List<String> list = new ArrayList<String>();

// Input from the winners file
        FileInputStream in = new FileInputStream("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/winnerfile");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine = "", tmp;
        while ((tmp = br.readLine()) != null) {
            strLine = tmp + "\n" + strLine;
            list.add(tmp);
        }

        if (list.size() > pastWin) {
            for (int i = list.size() - 1; i >= (list.size() - pastWin); i--) {
                System.out.println(list.get(i));
            }
        } else {
            for (int i = 0; i < pastWin; i++) {
                System.out.println(list.get(i));
            }
        }

        System.out.print("\n");

// Show the list of past winners for 2000 milliseconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

//create an ArrayList called "cardDeck"
        List<Card> cardDeck = new ArrayList<Card>();

        try {

// Input from xfile
// Create card suits (4 suits)

            Scanner x = new Scanner(new File("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/xfile"));


            while (x.hasNext()) {

                int q = Integer.parseInt(x.next());

//Input from yfile
// Create card ranks (13 ranks)
                Scanner y = new Scanner(new File("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/yfile"));

                while (y.hasNext()) {

                    int r = Integer.parseInt(y.next());

//create new card and add into the deck
                    cardDeck.add(new Card(q, r));
                }
                y.close();

            }
            x.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//shuffle the deck of cards
        Collections.shuffle(cardDeck, new Random());

//creating 2 war decks, one for each player
        LinkedList<Card> deck1 = new LinkedList<Card>();
        LinkedList<Card> deck2 = new LinkedList<Card>();

//26 cards deal for player 1
        deck1.addAll(cardDeck.subList(0, 25));

//26 cards deal for player 2
        deck2.addAll(cardDeck.subList(26, cardDeck.size()));

        while (true) {

//each player plays one card
            Card p1Card = deck1.pop();
            Card p2Card = deck2.pop();

//display the cards played
            System.out.print(nameOne);
            System.out.print(" plays ");
            System.out.print(p1Card.toString());
            System.out.print("\n");

            System.out.print(nameTwo);
            System.out.print(" plays ");
            System.out.print(p2Card.toString());
            System.out.print("\n");

//rank comparision between the two cards played

//if player 1 wins, print the winner of the hand
            if (p1Card.getCard() > p2Card.getCard()) {

//higher rank wins both cards and places them at the bottom of his/hers deck.
                deck1.addLast(p1Card);
                deck1.addLast(p2Card);

                System.out.print(nameOne);
                System.out.print(" wins the round ");
                System.out.print("\n");
            }

//if player 2 wins, print the winner of the hand
            else if (p1Card.getCard() < p2Card.getCard()) {

//higher rank wins both cards and places them at the bottom of his/hers deck.
                deck2.addLast(p1Card);
                deck2.addLast(p2Card);

                System.out.print(nameTwo);
                System.out.print(" wins the round ");
                System.out.print("\n");
            }

//war happens when the ranks of both cards match.
            else {
                System.out.println("This Means War!!!");

//creating the war cards
                List<Card> war1 = new ArrayList<Card>();
                List<Card> war2 = new ArrayList<Card>();

//checking if both players have enough (4)cards to stay in the game
                for (int x = 0; x < 3; x++) {

//If either player runs out of cards, the is game over.
                    if (deck1.size() == 0 || deck2.size() == 0) {
                        break;
                    }

                    System.out.print("War card for ");
                    System.out.print(nameOne);
                    System.out.print("\n");
                    System.out.print("War card for ");
                    System.out.print(nameTwo);
                    System.out.print("\n");

//place additional card for war
                    war1.add(deck1.pop());
                    war2.add(deck2.pop());
                }

//compare result when both players have enough cards for war
                if (war1.size() == 3 && war2.size() == 3) {

//display the war cards from each player
                    System.out.print("War card for ");
                    System.out.print(nameOne);
                    System.out.print(" is the ");
                    System.out.print(war1.get(0).toString());
                    System.out.print("\n");
                    System.out.print("War card for ");
                    System.out.print(nameTwo);
                    System.out.print(" is the ");
                    System.out.print(war2.get(0).toString());
                    System.out.print("\n");

//if player 1 wins the war round
                    if (war1.get(2).getCard() > war2.get(2).getCard()) {

//player1 get all 10 cards
                        deck1.addAll(war1);
                        deck1.addAll(war2);
                        System.out.print(nameOne);
                        System.out.print(" wins the war round (gets all 10 cards)");
                        System.out.print("\n");
                    }

//otherwise player 2 wins the war round
                    else {
//player2 get all 10 cards
                        deck2.addAll(war1);
                        deck2.addAll(war2);
                        System.out.print(nameTwo);
                        System.out.print(" wins the war round (gets all 10 cards)");
                        System.out.print("\n");
                    }
                }
            }

//game over when either player runs out of cards(deck size is 0)
            if (deck1.size() == 0) {

//game over player one
                System.out.print("Game Over");
                System.out.print("\n");
                System.out.print(nameOne);
                System.out.print(" Wins the Game!");
                System.out.print("\n");

//output/write the winners name to the winnerfile
                FileWriter writer = new FileWriter("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/winnerfile", true);
                BufferedWriter buffer = new BufferedWriter(writer);
                buffer.write(nameOne + " wins the game");
                buffer.newLine();
                buffer.close();
                break;
            } else if (deck2.size() == 0) {

//game over player two
                System.out.print("Game Over");
                System.out.print("\n");
                System.out.print(nameTwo);
                System.out.print(" Wins the Game!");
                System.out.print("\n");

//write the winners name to the winnerfile
                FileWriter writer = new FileWriter("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/winnerfile", true);
                BufferedWriter buffer = new BufferedWriter(writer);
                buffer.write(nameTwo + "wins the game ");
                buffer.newLine();
                buffer.close();

                break;
            }
        }
    }
}
