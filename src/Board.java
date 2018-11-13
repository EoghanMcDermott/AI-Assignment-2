//Eoghan McDermott - 15345451

import java.util.ArrayList;

public class Board {

    private ArrayList<Long> positions;
    private long currPosition;

    public Board()
    {
        positions = new ArrayList<>(); //array list so don't need to fix size

        positions.add(0b0L);
        //clear board with no depth charges

        positions.add(0b1L);
        //board with 1 depth charge in corner

        positions.add(positions.get(1) << 1  );
        //board with 1 depth charge 2 squares in



    }

    public String printPosition()
    {
        String str = "";

        for(Long pos : positions)
            System.out.println(Long.toBinaryString(pos));


        return str;
    }
}