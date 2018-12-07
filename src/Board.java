//Eoghan McDermott - 15345451

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Board {

    private ArrayList<Integer> possiblePositions;
    private int depthCharges,player1Pos,player2Pos,currPosition;
    private boolean gameOver = false;
    private boolean p1Turn = true;
    private int currPlayerPosition = player1Pos;
    //player 1's turn initially, !p1Turn is player 2's turn

    public Board()
    {
        depthCharges = 0b00000_00000_00000_00000_00000;//no charges initially

        player1Pos = 0b00000_00000_00000_00001_00010;//starts bottom right

        player2Pos = 0b01000_10000_00000_00000_00000;//starts top left

        currPosition = player1Pos | player2Pos;
    }

    public void play()
    {
        System.out.println(printBoard());
        System.out.println("GAME START");
        while(!gameOver)
            processTurn();

        //determine winner
    }

    private String padZeroes(String input)
    {
        String output = input;

        while (output.length() < 25)
            output = "0" + output;

        return output;
    }

    public String printBoard()
    {
        String p1 = Integer.toBinaryString(player1Pos).replace("1", "#");
        String p2 = Integer.toBinaryString(player2Pos);
        String dc = Integer.toBinaryString(depthCharges);
        //surfer and depth charge positions positions

        p1 = padZeroes(p1);
        p2 = padZeroes(p2);
        dc = padZeroes(dc);
        //pad with extra zeroes so that


        StringBuilder boardBuilder = new StringBuilder(p1);
        //only represents player 1's position currently

        //now need to add in player 2 and depth charges
        //replace 1's in binary string with appropriate token
        for(int i=0;i<25;i++)
        {
            if(p2.substring(i,i+1).equals("1"))
                boardBuilder.setCharAt(i,'@');

            if(dc.substring(i,i+1).equals("1"))
                boardBuilder.setCharAt(i,'x');
        }

        String board = boardBuilder.toString();
        //now break it up into 5x5
        board = "5-" + board.substring(0,5) + "\n" +
                "4-" + board.substring(5,10) + "\n" +
                "3-" + board.substring(10,15) + "\n" +
                "2-" + board.substring(15,20) + "\n" +
                "1-" + board.substring(20) + "\n" +
                "  ABCDE";


        return board;
    }


    private void processTurn()
    {
        if(p1Turn)
            System.out.println("Player 1, please enter your move");
        else
            System.out.println("Player 2, please enter your move");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        inputValid(input);//check if valid input


        System.out.println("TURN OVER");
        p1Turn = !p1Turn;//swap players

        if(!p1Turn)
            currPlayerPosition=player2Pos;
        else
            currPlayerPosition=player1Pos;//keep track of current player's position
    }

    private boolean inputValid(String input)
    {
        boolean valid = true;//innocent until proven guilty

        String regex = "^[ABCDE][12345]\\s[ABCDE][12345]\\s[ABCDE][12345]$";

        valid = Pattern.matches(regex, input);
        //use a regular expression to check for valid syntax

        if(!valid)
        {
            System.out.println("ERROR: INVALID SYNTAX");
            return valid;
        }

        int start = toInt(input.substring(0,1));
        int finish = toInt(input.substring(3,4));
        int depth = toInt(input.substring(6,7));

        //now to check if a surfer is at the position so they can be moved
        if(!positionOccupied(start, currPlayerPosition))
        //!positionOccupied as we want a surfer to be there
        {
            System.out.println("ERROR: NO SURFER AT THIS POSITION TO MOVE");
            valid = false;
            return valid;
        }


        return valid;
    }

    private int toInt(String input)//takes individual A4 as input converts to int
    {
        int position = 0b00000_00000_00000_00000_00001; //position E1

        int row, column=0;//will use these to track where a move is going

        String letter = input.substring(0, 1);//extract letter from input

        switch (letter) {
            case "A":
                column = 4;
                break;
            case "B":
                column = 3;
                break;
            case "C":
                column = 2;
                break;
            case "D":
                column = 1;
                break;
            case "E":
                column = 0;
                break;
        }
        row = Integer.parseInt(input.substring(1))-1*5;
        //-1*5 because we want to jump up a row, all relative to bottom row


        position = position << row;
        position = position << column;
        //shift bit from E1 to desired position

        return position;
    }

    private boolean positionOccupied(int input, int position)
    {//can a piece be placed at a given position
        boolean valid = true;

        if((input & position) == 0)//AND returns 0 if square unoccupied
            valid = false;

        return valid;
    }

    private boolean straightLine(int start, int finish, int position)
    {
        boolean straight = true;

        //straight line 3 squares in any direction
        //shift 1,2,3 for 1,2,3 horizontal
        //shift 7,13,19 for 1,2,3 diagonal
        //shift 5,10,15 for 1,2,3 vertical

        return straight;
    }
}