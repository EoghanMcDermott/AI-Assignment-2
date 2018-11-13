import java.nio.ByteBuffer;

public class Board {

    private long[] positions;
    private long currPosition;

    public Board()
    {
        positions = new long[100]; //arbitrary length - will change later

        //clear board with no depth charges
        positions[0] = 0_0000000_0000000_0000000_0000000_0000000_0000000_0000001;
        //sign bit, then top to bottom right to left as java is big endian


        currPosition = positions[0];
    }

    public String printPosition()
    {
        String str = "";
        final ByteBuffer buffer = ByteBuffer.allocate(50);
        buffer.putLong(currPosition);
        System.out.println(buffer.get());

        for(int i=0;i<50;i++)
        {
            //add each row to str
            System.out.println(i + " bit: " + buffer.get(i));

        }


        return str;
    }
}
