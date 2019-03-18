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

            Scanner scanner = new Scanner(System.in);
            System.out.print("Player 1, please, sign in: ");
            String nameOne = scanner.nextLine();
            System.out.print("Player 2, please sign in: ");
            String nameTwo = scanner.nextLine();

            System.out.print("\n");

            System.out.print("How many past winners would you like to see?");
            System.out.print("\n");
            Integer pastWin = scanner.nextInt();
            scanner.close();
            System.out.print("\n");



        List<String > list =new ArrayList<String>();
        FileInputStream in = new FileInputStream("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/winnerfile");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine ="", tmp;
        while ((tmp = br.readLine()) != null){
            strLine =tmp+"\n"+strLine;
            list.add(tmp);
        }

        if(list.size()>pastWin){
            for (int i=list.size()-1; i>=(list.size()-pastWin); i--) {
                System.out.println(list.get(i));
            }
        }else{
            for (int i=0; i<pastWin; i++) {
                System.out.println(list.get(i));
            }

        }
        System.out.print("\n");


        try { Thread.sleep(2000); }


        catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }

        List<Card> cardDeck = new ArrayList<Card>(); //create an ArrayList "cardDeck"

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

                    cardDeck.add(new Card(q, r)); //create new card and add into the deck
                }
                y.close();

            }
            x.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //shuffle the deck
        Collections.shuffle(cardDeck, new Random());

        //creating 2 decks
        LinkedList<Card> deck1 = new LinkedList<Card>();
        LinkedList<Card> deck2 = new LinkedList<Card>();

        deck1.addAll(cardDeck.subList(0, 25));              //26 cards for player 1
        deck2.addAll(cardDeck.subList(26, cardDeck.size()));//26 cards for player 2

        while (true) {
            //each player gets one card face up
            Card p1Card = deck1.pop();
            Card p2Card = deck2.pop();

            //display the face up card
            System.out.print(nameOne);
            System.out.print(" plays ");
            System.out.print(p1Card.toString());
            System.out.print("\n");

            System.out.print(nameTwo);
            System.out.print(" plays ");
            System.out.print(p2Card.toString());
            System.out.print("\n");


            //rank comparision between two cards
            //if player 1 wins
            if (p1Card.getCard() > p2Card.getCard()) {
                deck1.addLast(p1Card);  //higher rank wins both cards and
                deck1.addLast(p2Card);  //places them at the bottom of his deck.

                System.out.print(nameOne);
                System.out.print(" wins the round ");
                System.out.print("\n");

            }
            //if player 2 wins
            else if (p1Card.getCard() < p2Card.getCard()) {
                deck2.addLast(p1Card);
                deck2.addLast(p2Card);

                System.out.print(nameTwo);
                System.out.print(" wins the round ");
                System.out.print("\n");
            } else { //war happens when both cards' rank matched
                System.out.println("This Means War!!!");

                //creating war cards
                List<Card> war1 = new ArrayList<Card>();
                List<Card> war2 = new ArrayList<Card>();

                //checking if players have enough (4)cards to stay in game
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

                    war1.add(deck1.pop());  //place additional card for war
                    war2.add(deck2.pop());
                }

                //only compare result when both players have enough cards for war
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
                        deck1.addAll(war1); //player1 get all 10 cards
                        deck1.addAll(war2);
                        System.out.print(nameOne);
                        System.out.print(" wins the war round (gets all 10 cards)");
                        System.out.print("\n");

                    }

                    //otherwise player 2 wins the war round
                    else {
                        deck2.addAll(war1); //player2 get all 10 cards
                        deck2.addAll(war2);
                        System.out.print(nameTwo);
                        System.out.print(" wins the war round (gets all 10 cards)");
                        System.out.print("\n");

                    }
                }

            }

            //game over either one player runs out of card(deck size is 0)
            if (deck1.size() == 0) {

                System.out.print("Game Over");
                System.out.print("\n");
                System.out.print(nameOne);
                System.out.print(" Wins the Game!");
                System.out.print("\n");


                FileWriter writer = new FileWriter("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/winnerfile", true);
                BufferedWriter buffer = new BufferedWriter(writer);

                buffer.write(nameOne + " wins the game");
                buffer.newLine();
                buffer.close();

                break;
            } else if (deck2.size() == 0) {

                System.out.print("Game Over");
                System.out.print("\n");
                System.out.print(nameTwo);
                System.out.print(" Wins the Game!");
                System.out.print("\n");


                //Scanner scanner2 = new Scanner(System.in);
                //System.out.print("Hey Winner any comments? ");
                //String comments2 = scanner2.nextLine();


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
