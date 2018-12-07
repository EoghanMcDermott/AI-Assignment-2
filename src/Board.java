//Eoghan McDermott - 15345451

import java.util.ArrayList;

public class Board {

    private ArrayList<Integer> possiblePositions;
    private int depthCharges,player1Pos,player2Pos,currPosition;

    public Board()
    {
        depthCharges = 0b00000_00000_00000_00000_00000;//no charges initially

        player1Pos = 0b00000_00000_00000_00001_00010;//starts bottom right

        player2Pos = 0b01000_10000_00000_00000_00000;//starts top left

        currPosition = player1Pos | player2Pos;

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
}