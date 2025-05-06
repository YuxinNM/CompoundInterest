
/**
 * CompoundInterest helps user to calculate their investment depending on their input for the principal, annual interest rate, # of years and type of compounding.
 *
 * @author Nancy Ma
 * @version June,18,2021
 */

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import ui.Launch;

public class CompoundInterest{
    public static void main(String[] args){
        new Launch();
 
        //Prompt the user to choose the compounding type.
        int compoundingType=0;
        numNotEntered=true;                                                             //Reset the boolean value to ensure the loop run properly.
        numNotInt=true;                                                                 //Reset the boolean value to ensure the loop run properly.
        //Continuously prompt the user to enter a number, until an expected choice number is actaully entered.
        do{
           //Contains the code (parseInt()) that could throw an exception as the program is running
           try{
               System.out.println("Choose one of the following types of compounding periods : ");
               System.out.println("1. annual compounding");
               System.out.println("2. semi-annual compounding");
               System.out.println("3. quarterly compounding");
               System.out.println("4. monthly compounding");
            
               System.out.print("Enter the number of your choice : ");
                
               inputStr= theScanner.nextLine();
               compoundingType=Integer.parseInt(inputStr);

               numNotEntered=false;
               numNotInt=false;
           }
           //Catches and deals with the exception that could be thrown.
           catch(NumberFormatException e){
               System.out.println("\nYOU NEED TO ENTER THE NUMBER OF THE CHOICE.\n");
           }
        }
        while (numNotEntered||numNotInt||compoundingType>4||compoundingType<=0);
        System.out.print("\n");
        
        
        //If the user chose choice 1 (calculate the value of the user's investment each year over a time horizon) at the begining, 
        //the following if condition will be true and the code will run within the if block.
        if(choice==1){
            //Prompt the user to enter for the number of years.
            int yearNumber=0;
            numNotEntered=true;
            numNotInt=true;
            //Continuously prompt the user to enter a number, until a number in correct form is actaully entered.
            do{
                //Contains the code (parseInt()) that could throw an exception as the program is running
                try{
                    System.out.print("Enter the number of years of investment (in integer form, where 1<= # of years <=20): ");
                    inputStr= theScanner.nextLine();
                    yearNumber=Integer.parseInt(inputStr);

                    numNotEntered=false;
                    numNotInt=false;
                }
                //Catches and deals with the exception that could be thrown.
                catch(NumberFormatException e){
                    System.out.println("\nYOU NEED TO ENTER AN INTEGER WITHIN THE RANGE.\n");
                }
            }
            while (numNotEntered||numNotInt||yearNumber<1||yearNumber>20);
            System.out.print("\n");
        
            
            //Declare an array that will store the investment values of each year.
            double [] investmentArr= new double[21];
            investmentArr[0]=principalToCent;

            //Calculate the investment value depending on the compound type that user specified.
            //Store the value in investmentArr, output to the user, and save the report to a text named investgrowth.txt.
            if(compoundingType==1){
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"% for "+yearNumber+" year(s).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 1 (annual compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Year   Investment Value ($, rounded to nearest cent)";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="0"+String.format("%17.2f",investmentArr[0]);
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    for(int i=1; i<=yearNumber; i++){
                        investmentArr[i]=principalToCent*Math.pow((1+annualInterestRate),i);
                        lineOfText=i+String.format("%17.2f",investmentArr[i]);
                        System.out.println(lineOfText);
                        buffWr.write(lineOfText, 0, lineOfText.length());
                        buffWr.newLine();
                    }
                    buffWr.close();
                    outputFile.close();
                }
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }
        
        
            else if(compoundingType==2){
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                    
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"% for "+yearNumber+" year(s).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 2 (semi-annual compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Year   Investment Value ($, rounded to nearest cent)";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="0"+String.format("%18.2f",investmentArr[0]);
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    for(int i=1; i<=yearNumber; i++){
                        investmentArr[i]=principalToCent*Math.pow((1+annualInterestRate/2),i*2);
                        lineOfText=i+String.format("%18.2f",investmentArr[i]);
                        System.out.println(lineOfText);
                        buffWr.write(lineOfText, 0, lineOfText.length());
                        buffWr.newLine();
                    }
                    buffWr.close();
                    outputFile.close();
                }
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }
            
            
            else if(compoundingType==3){
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                    
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"% for "+yearNumber+" year(s).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 4 (quarterly compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Year   Investment Value ($, rounded to nearest cent)";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="0"+String.format("%19.2f",investmentArr[0]);
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    for(int i=1; i<=yearNumber; i++){
                        investmentArr[i]=principalToCent*Math.pow((1+annualInterestRate/4),i*4);
                        lineOfText=i+String.format("%19.2f",investmentArr[i]);
                        System.out.println(lineOfText);
                        buffWr.write(lineOfText, 0, lineOfText.length());
                        buffWr.newLine();
                    }
                    buffWr.close();
                    outputFile.close();
                }
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }
            
            
            else{
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                    
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"% for "+yearNumber+" year(s).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 12 (monthly compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Year   Investment Value ($, rounded to nearest cent)";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="0"+String.format("%20.2f",investmentArr[0]);
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    for(int i=1; i<=yearNumber; i++){
                        investmentArr[i]=principalToCent*Math.pow((1+annualInterestRate/12),i*12);
                        lineOfText=i+String.format("%20.2f",investmentArr[i]);
                        System.out.println(lineOfText);
                        buffWr.write(lineOfText, 0, lineOfText.length());
                        buffWr.newLine();
                    }
                    buffWr.close();
                    outputFile.close();
                }
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }
        }
        
        //If the user chose choice 2 (calculate when the user's investment will meet the target value) at the begining,
        //the if condition will be false and the code will run within the following else block.
        else{
            //Prompt the user to enter their target value.
            double targetValue=0.0;
            numNotEntered=true;                                                            //Reset the boolean value to ensure the loop run properly.
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
            double targetValueToCent=Math.round(targetValue*100.0)/100.0;                  //Round the target value the user entered to cent.
            System.out.print("\n");
            
        
            double amountOfInvestment=0.0;                                                 //Initialize this variable for storing each year's investment value later on.
            int numOfInterestEarned=0;                                                     //Compounding type(the number of times interest is earned and compounded per year).
            int i=1;

            //Calculate the investment value depending on the compound type that user specified.
            //Calculate the value until it reaches the target value, output to the user, and save the report to a text named investgrowth.txt.
            if(compoundingType==1){
                numOfInterestEarned=1;
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                    
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Your target value is $ "+targetValueToCent+". You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"%.\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 1 (annual compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The following table will show the number of years and your investment value at the end of each year until it equals or surpasses the target value.\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Year   Investment Value ($, rounded to nearest cent)";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="0"+String.format("%17.2f",principalToCent);
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    while(amountOfInvestment != targetValueToCent && amountOfInvestment < targetValueToCent){
                        amountOfInvestment= principalToCent*Math.pow((1+annualInterestRate/numOfInterestEarned),i*numOfInterestEarned);
                        lineOfText=i+String.format("%17.2f",amountOfInvestment);
                        System.out.println(lineOfText);
                        buffWr.write(lineOfText, 0, lineOfText.length());
                        buffWr.newLine();
                        i++;
                    }
                    
                    buffWr.close();
                    outputFile.close();
                }
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }
            
            else if(compoundingType==2){
                numOfInterestEarned=2;
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                    
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Your target value is $ "+targetValueToCent+". You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"%.\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 2 (semi-annual compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The following table will show the number of years and your investment value at the end of each year until it equals or surpasses the target value.\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Year   Investment Value ($, rounded to nearest cent)";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="0"+String.format("%18.2f",principalToCent);
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    while(amountOfInvestment != targetValueToCent && amountOfInvestment < targetValueToCent){
                        amountOfInvestment= principalToCent*Math.pow((1+annualInterestRate/numOfInterestEarned),i*numOfInterestEarned);
                        lineOfText=i+String.format("%18.2f",amountOfInvestment);
                        System.out.println(lineOfText);
                        buffWr.write(lineOfText, 0, lineOfText.length());
                        buffWr.newLine();
                        i++;
                    }
                    
                    buffWr.close();
                    outputFile.close();
                }
            
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }
            
            else if(compoundingType==3){
                numOfInterestEarned=4;
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                    
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Your target value is $ "+targetValueToCent+". You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"%.\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 4 (quarterly compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
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
                    
                    buffWr.close();
                    outputFile.close();
                }
            
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }
            
            else{
                numOfInterestEarned=12;
                try{
                    FileWriter outputFile=new FileWriter("investgrowth.txt");
                    BufferedWriter buffWr=new BufferedWriter(outputFile);
                    String lineOfText = null;
                    
                    System.out.println("THE FOLLOWING LINES WILL BE WRITTEN TO investgrowth.txt.\n");
                    
                    lineOfText="REPORT:\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Your target value is $ "+targetValueToCent+". You have invested $ "+principalToCent+" with an annual interest rate of "+annualInterestPercent+"%.\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The number of compounding periods per year you have specified is 12 (monthly compounding).\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="The following table will show the number of years and your investment value at the end of each year until it equals or surpasses the target value.\n";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    lineOfText="Year   Investment Value ($, rounded to nearest cent)";
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                    
                    lineOfText="0"+String.format("%20.2f",principalToCent);
                    System.out.println(lineOfText);
                    buffWr.write(lineOfText, 0, lineOfText.length());
                    buffWr.newLine();
                
                    while(amountOfInvestment != targetValueToCent && amountOfInvestment < targetValueToCent){
                        amountOfInvestment= principalToCent*Math.pow((1+annualInterestRate/numOfInterestEarned),i*numOfInterestEarned);
                        lineOfText=i+String.format("%20.2f",amountOfInvestment);
                        System.out.println(lineOfText);
                        buffWr.write(lineOfText, 0, lineOfText.length());
                        buffWr.newLine();
                        i++;
                    }
                    
                    buffWr.close();
                    outputFile.close();
                }
            
                catch(IOException e){
                    System.out.println("IO problem - investgrowth.txt could not be created, or written to.");
                }
            }        
        }

       
    }

   
}
