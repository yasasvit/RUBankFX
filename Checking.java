package src;
/**
* extends the Account class. It defines the behavior of a checking account, 
* including annual interest, monthly fees, and the account type
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public class Checking extends Account {
    private static final double ANNUAL_INTEREST_RATE = 0.01;
    private static final double MONTHLY_FEE = 12.0;

    /**
    * Constructor that creates a checking account
     * @param Profile object representing holder of account
     * @param double representing balance of account
    */
    public Checking(Profile holder, double balance) {
        super(holder, balance);
    }

    
    /**
    * Overrides the getAccountType method from the parent Account class
    * @return "C"
    */
    @Override
    public String getAccountType() {
        return "C";
    }

    /**
    * Calculates the monthly interest. 
    * @return double representing monthly interest;
    */
    @Override
    public double monthlyInterest() {
        // not sure if divide by 12
        return balance * (ANNUAL_INTEREST_RATE / 12);   
    }

    /**
    * Calculates the monthly fee based on the current balance.
    * @return returns 0 if the balance is greater than or equal to $1000, else MONTHLY_FEE
    */
    @Override
    public double monthlyFee() {

        if (balance >= 1000) {
            return 0;
        } else {
            return MONTHLY_FEE;
        }
    }
}
