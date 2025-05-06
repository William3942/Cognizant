package Array;

/**
 *
 * @author Guillermo
 */
public class Part4 {
    public static void main(String[] args) {
        int[] array = new int[10];
        
        for (int i = 0; i < 10; i++) {
            array[i] = (i + 1) * 3;
        }
        
        System.out.println("Length of the array: " + array.length);
        
        /*System.out.println("Array elements:");
        for (int num : array) {
            System.out.print(num + " ");
        }*/
    }
    
}
