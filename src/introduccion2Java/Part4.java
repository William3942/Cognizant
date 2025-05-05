package introduccion2Java;

/**
 *
 * @author Guillermo
 */
public class Part4 {
    public static void main(String[] args) {
        int a = 30;
        int b = 71;
        int c = 98;
        
        int averageScore = (a+b+c)/3;
        if(averageScore >= 90 && averageScore <= 100)
        {
            System.out.println("Excelent");
        }else if(averageScore >= 70 && averageScore <= 89)
        {
            System.out.println("Good");
        }else if(averageScore >= 50 && averageScore <= 69)
        {
            System.out.println("Regular");
        }else
        {
            System.out.println("Poor");
        }
        
        int day = 4;
        switch(day)
        {
            case 1: System.out.println("Monday");
                    break;
            case 2: System.out.println("Thuesday");
                    break;
            case 3: System.out.println("Wenedsay");
                    break;
            case 4: System.out.println("Thursday");
                    break;
            case 5: System.out.println("Friday");
                    break;
            case 6: System.out.println("Saturday");
                    break;
            case 7: System.out.println("Sunday");
                    break;
            default: break;
        }
    }
    
}
