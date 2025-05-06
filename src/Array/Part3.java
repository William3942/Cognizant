package Array;

/**
 *
 * @author Guillermo
 */
public class Part3 {
    public static void main(String[] args) {
        double[] array = {1.2,3.4,5.6,7.8,9.0};
        System.out.println("First array");
        for(double num: array)
            System.out.println("Number: " + num); 
        
        array[2] = 6.5;
        
        System.out.println("Second array");
        for(double num: array)
            System.out.println("Number: " + num); 
    }
    
}
