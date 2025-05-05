package introduccion2Java;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class DragonCave {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("You are in a land full of dragons. In front of you,\n" +
            "You see two caves. In one cave, the dragon is friendly\n" +
            "and will share his treasure with you. The other dragon\n" +
            "is greedy and hungry and will eat you on sight.\n" +
            "Which cave will you go into? (1 or 2)");
        
        int election = scanner.nextInt();
        
        if(election == 1)
        {
            System.out.println("You approach the cave…\n" +
                "It is dark and spooky…\n" +
                "A large dragon jumps out in front of you!\n" +
                "He opens his jaws and…\n" +
                "Gobbles you down in one bite!");
        }else{
            System.out.println("You approach the cave…\n" +
                "It is dark and spooky…\n" +
                "A great dragon approaches you and offers to choose a treasure\n" +
                "you choose a golden shield and step aside.\n");
        }
    }
    
}
