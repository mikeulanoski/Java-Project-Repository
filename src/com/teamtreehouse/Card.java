//Final Build 3/27/19
package com.teamtreehouse;

public class Card {

    //initialize the rank (2,3,4...King, Ace)
    private int rank;

    //initialize the suit (spades, hearts...)
    private int suit;

    public Card(int suit, int rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getCard() {
        return rank;
    }

    @Override
    public String toString() {

//combine rank and suit together into a single string (example: Jack of Clubs)
        StringBuilder displayCard = new StringBuilder();


        switch (rank) {

//rank is an integer type, match integer 11 to String jack...14 to Ace
            case 11:
                displayCard.append("Jack");
                break;
            case 12:
                displayCard.append("Queen");
                break;
            case 13:
                displayCard.append("King");
                break;
            case 14:
                displayCard.append("Ace");
                break;
            default:

//card numbers 2 to 10 do not need to be modified
                displayCard.append(rank);
                break;

        }

//setting the format of the output
        displayCard.append(" of ");

        switch (suit) {
            case 0:
                displayCard.append("Spades");
                break;
            case 1:
                displayCard.append("Hearts");
                break;
            case 2:
                displayCard.append("Clubs");
                break;
            case 3:
                displayCard.append("Diamonds");
                break;
            default:
                break;
        }

//return the result of an entire combined string
        return displayCard.toString();

    }

}
