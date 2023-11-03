package src;
/**
* A specific type of savings account that extends the Savings class.
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public class MoneyMarket extends Savings {
    private static final double ANNUAL_INTEREST_RATE = 0.045;
    private static final double MONTHLY_FEE = 25.0;
    private int withdrawal; // number of withdrawals

    /**
    * Constructor of the parent Savings class with the holder, balance, and true parameters. 
    * @param Profile (account holder)
     * @param double representing the initial balance
    */
    public MoneyMarket(Profile holder, double balance) {
        super(holder, balance, true);
        withdrawal = 0;
    }

    /**
    * Allows for withdrawals if the balance is sufficient, updates the withdrawal count, and adjusts the balance accordingly.
    * @param double amount that is checked if it is bigger than the account balance
    * @return boolean that potentially allows for the withdrawals to proceed
    */
    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            withdrawal += 1;
            balance -= amount;
            if (balance < 2000) {
                isLoyal = false;
            }
            return true;
        }
        return false;
    }

    /**
    * Allows deposits and updates the balance.
    * @param double amount that represents the amount of money
    */
    @Override
    public void deposit(double amount) {

        balance += amount;
        if (balance >= 2000) {
            isLoyal = true;
        }
    }


    /**
     * Gets the current account type
     * @return "MM" that represents the account type of MoneyMarket
     */
    @Override
    public String getAccountType() {
        return "MM";
    }

    /**
    * Calculates the monthly interest based on the balance and loyalty status.
    * @return balance with an additional 0.25% interest rate
    */
    @Override
    public double monthlyInterest() {

        double interestRate = ANNUAL_INTEREST_RATE;
        if (isLoyal) {
            interestRate += 0.0025;
        }
        return (balance * (interestRate / 12));
    }

    /**
    * Calculates the monthly fee based on the balance and the number of withdrawals
    * @return the monthly fee owed by the account owner based on the conditions
    */
    @Override
    public double monthlyFee() {

        if (balance >= 2000) {
            if (withdrawal > 3) {
                return 10.0;
            } else {
                return 0.0;
            }
        } else {
            isLoyal = false;
            if (withdrawal > 3) {
                return MONTHLY_FEE + 10.0;
            } else {
                return MONTHLY_FEE;
            }
        }
    }

    /**
     * Converts the account to String representation
     * @return String that represents the MoneyMarket account along with the number of withdrawals
     */
    @Override
    public String toString() {
        return super.toString() + "::withdrawal: " + withdrawal;
    }

    /**
     * Resets the withdrawals to 0
     */
    @Override
    public void resetWithdrawals() {
        withdrawal = 0;
    }
}
