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
//import java.io.IOException;




public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player 1, what is your name: ");
        String nameOne = scanner.nextLine();
        System.out.print("Player 2, what is your name: ");
        String nameTwo = scanner.nextLine();

        List<Card> cardDeck = new ArrayList<Card>(); //create an ArrayList "cardDeck"

        // Input from xfile

        //for (int a = 0; a < 4; a++) {
            try {
                Scanner x = new Scanner(new File("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/xfile"));
                System.out.println(x);
                while (x.hasNextLine()) {
                    //System.out.println(x.nextLine());

                    int q = Integer.parseInt(x.nextLine());

                    for (int y = 2; y < 15; y++) {     //2-14 for rank (13 ranks)
                        cardDeck.add(new Card(q, y)); //create new card and add into the deck
                    }
                }

                x.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            //for(int x=0; x<4; x++){          //0-3 for suit (4 suits)
           // for (int y = 2; y < 15; y++) {     //2-14 for rank (13 ranks)
             //   cardDeck.add(new Card(x, y)); //create new card and add into the deck
                //System.out.println(cardDeck);
            //} //end rank
        //}//end suit


        Collections.shuffle(cardDeck, new Random()); //shuffle the deck randomly
        //System.out.println(cardDeck);

        //creating 2 decks, each for player1/player2
        LinkedList<Card> deck1 = new LinkedList<Card>();
        LinkedList<Card> deck2 = new LinkedList<Card>();

        deck1.addAll(cardDeck.subList(0, 25));              //26 cards for p1
        deck2.addAll(cardDeck.subList(26, cardDeck.size()));//26 cards for p2

        while(true){
            Card p1Card = deck1.pop();  //each player place one card face up
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
            if(p1Card.getCard() > p2Card.getCard()){//if player 1 win
                deck1.addLast(p1Card);  //higher rank wins both cards and
                deck1.addLast(p2Card);  //places them at the bottom of his deck.

                System.out.print(nameOne);
                System.out.print(" wins the round ");
                System.out.print("\n");

            }//end if

            else if(p1Card.getCard() < p2Card.getCard()){//if player 2 win
                deck2.addLast(p1Card);
                deck2.addLast(p2Card);

                System.out.print(nameTwo);
                System.out.print(" wins the round ");
                System.out.print("\n");

            }//end else if

            else { //war happens when both cards' rank matched
                System.out.println("This Means War!!!");

                //creating war cards
                List<Card> war1 = new ArrayList<Card>();
                List<Card> war2 = new ArrayList<Card>();

                //checking do players have enough (4)cards to stay in game
                for(int x=0; x<3; x++){
                    //either one player runs out of card is game over
                    if(deck1.size() == 0 || deck2.size() == 0 ){
                        break;
                    }//end if

                    System.out.print("War card for ");
                    System.out.print(nameOne);
                    System.out.print("\n");
                    System.out.print("War card for ");
                    System.out.print(nameTwo);
                    System.out.print("\n");

                    //System.out.println("War card for player1 is xx\nWar card for player2 is xx");

                    war1.add(deck1.pop());  //place additional card for war
                    war2.add(deck2.pop());
                }//end for

                //only compare result when both players have enough cards for war
                if(war1.size() == 3 && war2.size() == 3 ){
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
                    if(war1.get(2).getCard() > war2.get(2).getCard()){
                        deck1.addAll(war1); //player1 get all 10 cards
                        deck1.addAll(war2);
                        System.out.print(nameOne);
                        System.out.print(" wins the war round (gets all 10 cards)");
                        System.out.print("\n");

                    }//end if
                    //otherwise player 2 wins the war round
                    else{
                        deck2.addAll(war1); //player2 get all 10 cards
                        deck2.addAll(war2);
                        System.out.print(nameTwo);
                        System.out.print(" wins the war round (gets all 10 cards)");
                        System.out.print("\n");

                    }//end else
                }//end if

            }//end war round else

            //game over either one player runs out of card(deck size is 0)
            if(deck1.size() == 0 ){

                System.out.print("Game Over");
                System.out.print("\n");
                System.out.print(nameOne);
                System.out.print(" Wins the Game!");



                FileWriter writer = new FileWriter("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/winnerfile", true);
                BufferedWriter buffer = new BufferedWriter(writer);

                buffer.write("Player 1 wins the game");
                buffer.newLine();
                buffer.close();
                System.out.println("Success");



        //System.out.println("game over\nPlayer 1 wins the game");
                break;
            }
            else if (deck2.size() == 0){

                System.out.print("Game Over");
                System.out.print("\n");
                System.out.print(nameTwo);
                System.out.print(" Wins the Game!");

                FileWriter writer = new FileWriter("/Users/mikeu/Downloads/JavaCardGame/src/com/teamtreehouse/winnerfile", true);
                BufferedWriter buffer = new BufferedWriter(writer);

                buffer.write("Player 2 wins the game ");
                buffer.newLine();
                buffer.close();
                System.out.println("Success");




                break;
            }

        }//end while

    }//end main
}//end Main class