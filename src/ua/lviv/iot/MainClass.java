package ua.lviv.iot;
import java.util.ArrayList;
import java.util.List;

public class MainClass {

    private static final Cortej[] cortej = new Cortej[]{
    		
    // new Cortej(10, 12),
    // new Cortej(3, 5),
    // new Cortej(8, 10),
    // new Cortej(2, 3),
    // new Cortej(2, 6),
    // new Cortej(0, 1),

	new Cortej( 0, 1 ),
	new Cortej( 3, 5 ),
	new Cortej( 4, 8 ),
	new Cortej( 10, 12 ),
	new Cortej( 9, 10 )
   };

    public static void main(String[] args) {

        Cortej[] sortedList = new CortejSorter(cortej).sort();
        for ( Cortej c : sortedList ) {
            System.out.println( c );
        }
    }
}
