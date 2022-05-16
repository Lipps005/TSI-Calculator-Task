import jdk.jfr.Label;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

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
                //
            }
        }
        while(true);


        System.out.print("Please enter the capacity of the desired paint can, in liters\n");
        bucketCapacity = StringScanner.readFloat();

        System.out.print("What is the square meter per litre listed on the paint can? \n");
        bucketCoverFactor = StringScanner.readFloat();


        for(RoomObject wall : Walls)
        {
            totalPaintableArea += wall.getPaintableSize();
            bucketQuantity += wall.getPaintableSize() / (bucketCapacity * bucketCoverFactor);
        }


        bucketQuantity *= 1.1;


        System.out.printf("The total paintable area is %.2fm\u00b2, which will require %.2f cans of paint. ", totalPaintableArea, bucketQuantity);
        System.out.print("\r\n This estimation includes a 10%% extra, to allow for errors, damages or obstacles.");

    }


}

