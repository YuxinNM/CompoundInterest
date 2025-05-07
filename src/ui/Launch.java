package ui;

import java.util.Scanner;

public class Launch {
    protected String inputStr;
    protected Scanner theScanner;
    protected int compoundingType;
    protected double principalToCent;
    protected double annualInterestRate;
    protected int choice;
    protected double annualInterestPercent;

    protected boolean numNotEntered;
    protected boolean numNotInt;
   

    public Launch() {
        theScanner = new Scanner(System.in);
        inputStr = "";

        //Prompt the user to choose what they want to calculate.
        choice = 0;
        numNotEntered = true;
        numNotInt = true;

        getChoice();
        getValues();
        getCompoundingType();

        new Calc(); 
    }

    private void getChoice() {
        //Continuously prompt the user to enter a number, until an expected choice number is actaully entered.
        do{
            //Contains the code (parseInt()) that could throw an exception as the program is running
            try{
                System.out.println("Choose The Following Option That Meets Your Need : \n");
                System.out.println("1. Calculate The Value Of Your Investment At The End Of Each Year Over Your Time Horizon (based on compound interest)\n");
                System.out.println("2. Calculate When Your Investment Will Reach Your Target Value (based on compound interest)\n");
             
                System.out.print("Enter the number of your choice : ");
                 
                inputStr = theScanner.nextLine();
                choice = Integer.parseInt(inputStr);
 
                numNotEntered = false;
                numNotInt = false;
            }
            //Catches and deals with the exception that could be thrown.
            catch(NumberFormatException e){
                System.out.println("\nYOU NEED TO ENTER THE NUMBER OF THE CHOICE.\n");
            }
         }
         while (numNotEntered || numNotInt || choice>2 || choice<=0);
         System.out.print("\n");  
    }

    private void getValues() {
        //Prompt the user to enter the value of the principal.
        double principal = 0.0;
        numNotEntered = true;                                                             //Reset the boolean value to ensure the loop run properly.
        //Continuously prompt the user to enter a number, until a number in correct form is actaully entered. 
        do{
            //Contains the code (parseDouble()) that could throw an exception as the program is running
            try{
                System.out.print("Enter the value of the principal ($, original amount invested, non-negative) (will round to cent while calculating): ");
                inputStr = theScanner.nextLine();
                principal = Double.parseDouble(inputStr);
    
                numNotEntered = false;
            }
            //Catches and deals with the exception that could be thrown.
            catch(NumberFormatException e){
                System.out.println("\nYOU NEED TO ENTER A NON-NEGATIVE NUMBER.\n");
            }
        }
        while (numNotEntered||principal<0);
        principalToCent = Math.round(principal*100.0)/100.0;                       //Round the principal value the user entered to cent.
        System.out.print("\n");
        
        
        //Prompt the user to enter the annual interest rate.
        annualInterestPercent = 0.0;
        numNotEntered = true;                                                             //Reset the boolean value to ensure the loop run properly.
        //Continuously prompt the user to enter a number, until a number in correct form is actaully entered.
        do{
            //Contains the code (parseDouble()) that could throw an exception as the program is running
            try{
                System.out.print("Enter the annual interest rate percentage (%, where 0<rate<100): ");
                inputStr = theScanner.nextLine();
                annualInterestPercent = Double.parseDouble(inputStr);

                numNotEntered = false;
            }
            //Catches and deals with the exception that could be thrown.
            catch(NumberFormatException e){
                System.out.println("\nYOU NEED TO ENTER A NUMBER WITHIN THE RANGE.\n");
            }
        }
        while (numNotEntered||annualInterestPercent<=0||annualInterestPercent>=100);
        annualInterestRate = annualInterestPercent/100;                            //Convert the percentage the user entered into decimal form.
        System.out.print("\n");
    }


    private void getCompoundingType() {
         //Prompt the user to choose the compounding type.
         compoundingType=0;
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
         
    }
}
