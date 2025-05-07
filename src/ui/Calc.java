package ui;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Calc extends Launch{
    private FileWriter outputFile;
    private BufferedWriter buffWr;
    private String lineOfText;

    public Calc() {
        try {
            outputFile = new FileWriter("investgrowth.txt");
            buffWr = new BufferedWriter(outputFile);
           
            System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
            lineOfText="REPORT:\n";
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
        } catch (IOException e) {
            System.out.println("IO problem - investgrowth.txt could not be created.");
        }
        
        // System.out.println("reached for debugging");
        
        if (choice == 1) {
            //If the user chose choice 1 (calculate the value of the user's investment each year over a time horizon) at the begining.
            calcHorizon();
        } else {
            //If the user chose choice 2 (calculate when the user's investment will meet the target value) at the begining.
            reachTarget();
        }    

        try {
            outputFile.close();
            buffWr.close();
        } catch (IOException e) {
            System.out.println("IO problem - investgrowth.txt could not be created.");
        }
    }

    private void calcHorizon() {
        int yearNumber = getYears();
        
        //Declare an array that will store the investment values of each year.
        double[] investmentArr = new double[21];
        investmentArr[0] = principalToCent;
        
        try {
            lineOfText="You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"% for "+yearNumber+" year(s).\n";
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
        } catch (IOException e) {
            System.out.println("IO problem - investgrowth.txt could not be written to.");
        }
        
        //Calculate the investment value depending on the compound type that user specified.
        //Store the value in investmentArr, output to the user, and save the report to a text named investgrowth.txt.
        if(compoundingType == 1) {
            calcHorizonHelper(yearNumber, investmentArr, 1, "1 (annual compounding).");
        }else if(compoundingType==2){
            calcHorizonHelper(yearNumber, investmentArr, 2, "2 (semi-annual compounding).");
        }else if(compoundingType==3){
            calcHorizonHelper(yearNumber, investmentArr, 4, "4 (quarterly compounding).");
        }else{
            calcHorizonHelper(yearNumber, investmentArr, 12, "12 (monthly compounding).");
        }
    }

    private void calcHorizonHelper(int yearNumber, double[] investmentArr, int compoundPeriod, String compoundingType) {
        try{
            compoundingTypeHelper(compoundingType);
        
            lineOfText="Year   Investment Value ($, rounded to nearest cent)";
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
            
            lineOfText="0"+String.format("%19.2f",investmentArr[0]);
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
        
            for(int i=1; i<=yearNumber; i++){
                investmentArr[i]=principalToCent*Math.pow((1+annualInterestRate/compoundPeriod),i*compoundPeriod);
                lineOfText=i+String.format("%19.2f",investmentArr[i]);
                System.out.println(lineOfText);
                buffWr.write(lineOfText, 0, lineOfText.length());
                buffWr.newLine();
            }
        }
        catch(IOException e){
            System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
        }
    }

    private void reachTarget() {
        //Prompt the user to enter their target value.
        double targetValueToCent = getTarget();
        
        //Calculate the investment value depending on the compound type that user specified.
        //Calculate the value until it reaches the target value, output to the user, and save the report to a text named investgrowth.txt.
        if(compoundingType == 1){
            reachTargetHelper(1, targetValueToCent, "1 (annual compounding).");
        } else if(compoundingType == 2){
            reachTargetHelper(2, targetValueToCent, "2 (semi-annual compounding).");
        } else if(compoundingType == 3){
            reachTargetHelper(4, targetValueToCent, "4 (quarterly compounding).");
        } else{
            reachTargetHelper(12, targetValueToCent, "12 (monthly compounding).");
        }
    }

    public double getTarget() {
        numNotEntered=true;                                                            //Reset the boolean value to ensure the loop run properly.
        double targetValue = 0.0;
        //Continuously prompt the user to enter a number, until a number in correct form is actaully entered.
        do{
            //Contains the code (parseDouble()) that could throw an exception as the program is running
            try{
                System.out.print("Enter your target value ($, non-negative) (will round to cent while calculating): ");
                inputStr = theScanner.nextLine();
                targetValue = Double.parseDouble(inputStr);
                numNotEntered=false;
            }
            //Catches and deals with the exception that could be thrown.
            catch(NumberFormatException e){
                System.out.println("\nYOU NEED TO ENTER A NUMBER.\n");
            }
        }
        while(numNotEntered||targetValue<0);
        double targetValueToCent = Math.round(targetValue*100.0)/100.0;                  //Round the target value the user entered to cent.
        System.out.print("\n");
        return targetValueToCent;
    }

    public int getYears() {
        //Prompt the user to enter for the number of years.
        int yearNumber = 0;
        boolean numNotEntered = true;
        boolean numNotInt = true;
        //Continuously prompt the user to enter a number, until a number in correct form is actaully entered.
        do{
            //Contains the code (parseInt()) that could throw an exception as the program is running
            try{
                System.out.print("Enter the number of years of investment (in integer form, where 1<= # of years <=20): ");
                inputStr = theScanner.nextLine();
                yearNumber = Integer.parseInt(inputStr);

                numNotEntered = false;
                numNotInt = false;
            }
            //Catches and deals with the exception that could be thrown.
            catch(NumberFormatException e){
                System.out.println("\nYOU NEED TO ENTER AN INTEGER WITHIN THE RANGE.\n");
            }
        }
        while (numNotEntered||numNotInt||yearNumber<1||yearNumber>20);
        System.out.print("\n");
        return yearNumber;
    }

    /*
     * @1st param, Compounding type(the number of times interest is earned and compounded per year).
     */
    private void reachTargetHelper(int numOfInterestEarned, double targetValueToCent, String compoundingType) {
        double amountOfInvestment = 0.0;                     //Initialize this variable for storing each year's investment value later on.
        int i = 1;

        try{
            lineOfText="Your target value is $ " + targetValueToCent + ". You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"%.\n";
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
            
            compoundingTypeHelper(compoundingType);
            
            lineOfText="The following table will show the number of years and your investment value at the end of each year until it equals or surpasses the target value.\n";
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
        
            lineOfText="Year   Investment Value ($, rounded to nearest cent)";
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
            
            lineOfText="0"+String.format("%19.2f",principalToCent);
            System.out.println(lineOfText);
            buffWr.write(lineOfText, 0, lineOfText.length());
            buffWr.newLine();
        
            while(amountOfInvestment != targetValueToCent && amountOfInvestment < targetValueToCent){
                amountOfInvestment= principalToCent*Math.pow((1+annualInterestRate/numOfInterestEarned),i*numOfInterestEarned);
                lineOfText=i+String.format("%19.2f",amountOfInvestment);
                System.out.println(lineOfText);
                buffWr.write(lineOfText, 0, lineOfText.length());
                buffWr.newLine();
                i++;
            }
        }
        catch(IOException e){
            System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
        }
    }

    private void compoundingTypeHelper(String compoundingType) {
        lineOfText = "The number of compounding periods per year you have specified is ";
        System.out.print(lineOfText);
        try {
            buffWr.write(lineOfText, 0, lineOfText.length());
            System.out.println(compoundingType);
            buffWr.write(compoundingType, 0, compoundingType.length());
            buffWr.newLine();
        } catch (IOException e) {
            System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
        }  
    }
}
