import jdk.jfr.Label;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //TESTING
        try
        {
            //Re-assigns the standard input stream to a text file containing pre-set test parameters.
            System.setIn(new FileInputStream(new File("src/main/java/org/example/testScenario.txt")));
        }catch (FileNotFoundException fnfe)
        {
            System.out.println("Unable to open test file.");
            System.exit(-1);
        }

        float bucketCapacity = 0.0f;
        float bucketCoverFactor = 0.0f;
        float bucketQuantity = 0.0f;
        float totalPaintableArea = 0.0f;

        List<RoomObject.Builder> WallBuilders = new ArrayList<>();
        List<RoomObject> Walls = new ArrayList<>();

        //outer loop. requires >1 walls, so do first.
        do
        {
            //Dynamically add a new RoomObject builder to the list.
            System.out.print("Please enter the size of the wall in meters, width first, then height. \n");
            WallBuilders.add(new RoomObject.Builder().setSize(StringScanner.readFloat(), StringScanner.readFloat()));

            //add all the sockets required for a wall
            while(true)
            {
                //.add adds the object to the end of the list. size -1 should get the current (last added) builder.
                //not safe, as if size is 0, size-1 = -1, which would result in an error.
                System.out.print("Please enter the size of the socket in meters, width first, then height.\n");
                WallBuilders.get(WallBuilders.size()-1).addSocket(StringScanner.readFloat(), StringScanner.readFloat());

                System.out.print("Would you like to add another socket? (YES/NO)");
                String choice = StringScanner.readString();

                if(choice.equals("NO"))
                {
                    break;
                    //exit current while loop (no more sockets for this wall)
                }
            }
            System.out.print("Building your wall... \r\n ");
            Walls.add(WallBuilders.get(WallBuilders.size()-1).build());
            //build RoomObject and add to new Array.

            System.out.print("Would you like to add another Wall? (YES/NO)");
            String choice = StringScanner.readString();

            if(choice.equals("NO"))
            {
                break;
                //exit the current while loop (no more walls to be added)
            }
        }
        while(true);

        System.out.print("Please enter the capacity of the desired paint can, in liters\n");
        bucketCapacity = StringScanner.readFloat();

        System.out.print("What is the square meter per litre listed on the paint can? \n");
        bucketCoverFactor = StringScanner.readFloat();

        System.out.print("Building your room... \r\n ");
        for(RoomObject wall : Walls)
        {
            //calculate the total paintable area
            totalPaintableArea += wall.getPaintableSize();

            //calculate the number of buckets required for the wall, by taking the capacity
            //of the bucket(l) and dividing it by the liters per square meter (l/m2).
            bucketQuantity += wall.getPaintableSize() / (bucketCapacity * bucketCoverFactor);
        }

        //add a bit more paint in case they make mistakes.
        bucketQuantity *= 1.1;


        System.out.printf("The total paintable area is %.2fm\u00b2, which will require %.2f cans of paint. ", totalPaintableArea, bucketQuantity);
        System.out.print("\r\n This estimation includes a 10% extra, to allow for errors, damages or obstacles.");


    }


}

