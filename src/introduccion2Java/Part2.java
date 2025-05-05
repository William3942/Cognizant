package introduccion2Java;

/**
 *
 * @author Guillermo
 */
public class Part2 {
    public static void main(String[] args) {
        //Parte 2
        int counter = 10;
        counter++;
        System.out.println(counter);
        counter--;
        System.out.println(counter);
        for(counter = 10; counter< 15; counter++)
        {
            System.out.println(counter);
        }
        
        while(counter >= 10)
        {
            System.out.println(counter);
            counter--;
        }
    }
    
}
