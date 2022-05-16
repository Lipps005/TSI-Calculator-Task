import java.util.Scanner;

public class StringScanner {
    private static StringScanner stringScanner;
    private final Scanner scanner = new Scanner(System.in);

    private StringScanner(){}

    private static StringScanner getInstance()
    {
        if(stringScanner == null)
        {
            stringScanner = new StringScanner();
        }
        return stringScanner;
    }
    public static String readString()
    {
        getInstance();
        return stringScanner.scanner.nextLine();
    }


    public static int readInt()
    {
        getInstance();
        return Integer.parseInt(stringScanner.scanner.nextLine());
    }

    public static float readFloat()
    {
        getInstance();
        return Float.parseFloat(stringScanner.scanner.nextLine());
    }
}
