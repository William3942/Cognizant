package Array;

import java.util.Scanner;

/**
 *
 * @author Guillermo
 */
public class StudentGradesManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] grades = new double[10];
        int i = 0;
        int j = 0;
        do{
            j = i+1;
            System.out.println("Enter the " + j +"° grade");
            grades[i] = scanner.nextDouble();
            i++;
        }while(i < 10);
        
        double sum = 0;
        for(int k = 0; k < grades.length; k++)
        {
            sum = sum + grades[k];
        }
        double average = sum/grades.length;
        System.out.println("Average:" + average);
        
        double highest = grades[0];
        double lowest = grades[0];
        
        for(double high:grades)
        {
            if(highest < high)
                highest = high;
                
        }
        
        System.out.println("Highest grade: "+ highest);
        
        for(double low:grades)
        {
            if(lowest > low)
                lowest = low;
        }
        System.out.println("Lowest grade: "+ lowest);
        
        System.out.println("Grades");
        for(double grade:grades)
           System.out.println(grade);
        
        
    }
    
}
