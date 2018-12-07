//Eoghan McDermott - 15345451

public class Main {

    public static void main(String[] args)
    {
        Board board = new Board();

        board.play();

        int a = 0b001;
        int b = 0b100;

        int c = a&b;

        System.out.println(c);
    }
}
