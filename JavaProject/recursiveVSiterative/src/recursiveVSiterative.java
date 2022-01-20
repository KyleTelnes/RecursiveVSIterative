//Kyle Telnes
//01/11/2022
//CSC3430-Recursive vs Iterative

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class recursiveVSiterative {

    //Iterative Power Function
    //Finds the value of base^exponent
    //Given a base number and an exponent, multiplies
    //retVal by base for the number of iterations
    //specified by exponent
    double iterativePower(double base, int exponent){
        double retVal = 1.0;
        //first case will not be used, as exponent will start at 1 for this test
        if (exponent < 0) {
            return 1.0 / iterativePower(base, -exponent);
        } else {
            for (int i = 0; i < exponent; i++)
                retVal *= base;
        }
        return retVal;
    }
        
    //Recursive Power Function
    //Finds the value of base^exponent
    //Given a base number and an exponent, multiplies
    //base by another call to itself, decrementing exponent
    //with each call until, exponent hits 0
    double recursivePower(double base, int exponent){
        //first case will not be used, as exponent will start at 1 for this test
        if (exponent < 0) {
            return 1.0 / recursivePower(base, -exponent);
        } else if (exponent == 0) {
            return 1.0;
        } else {
            return base * recursivePower(base, exponent - 1);
        }
    }
    public static void main(String[] args) throws IOException {

        //set up writing to a CSV file
        File outputFile = new File("recursiveVSiterative.csv");

        FileWriter fw = new FileWriter(outputFile);

        BufferedWriter bw = new BufferedWriter(fw);

        //establish base and exponent values
        double base = 3.14159265359;

        int exponent = 1;

        //instantiate tests
        recursiveVSiterative iterative = new recursiveVSiterative();
        recursiveVSiterative recursive = new recursiveVSiterative();

        while (exponent != 2147483647) { //exponent is an int, so the maximum positive value it can hit is 2,147,483,647
        //Iterative Test
            //start time
            long startTime1 = System.nanoTime();

            //perform iterative test
            iterative.iterativePower(base, exponent);

            //end time
            long endTime1 = System.nanoTime();

            long elapsedTime1 = endTime1 - startTime1;

        //Recursive Test
            
            long startTime2 = System.nanoTime();

            recursive.recursivePower(base, exponent);

            long endTime2 = System.nanoTime();

            long elapsedTime2 = endTime2 - startTime2;

            //write both elapsed times to CSV file
            bw.write(exponent + ", " + elapsedTime1 + ", " + elapsedTime2);
            bw.newLine();
            bw.flush();

            //increment exponent for next loop
            exponent++;
        }
        bw.close();
    }
}
