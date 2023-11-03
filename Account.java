package src;

/**
 Represents an Account with information about the holder 
 and the amount of money in the account itself
 @author Pranav Gummaluri, Yasasvi Tallapaneni
 */
public abstract class Account implements Comparable<Account> {
    protected Profile holder;
    protected double balance;

    /**
     * This constructor initializes the holder and balance 
     * fields when an Account object is created.
     * @param Profile holder representing the full name and date of birth
     * @param double balance representing the account balance in dollars
     */
    public Account(Profile holder, double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    /**
    * Calculates the monthly interest for the account
    * @return a double representing the monthly interest
    */
    public abstract double monthlyInterest();
    /**
     * Calculates the monthly fee for the account
     * @return a double representing the monthly fee
     */
    public abstract double monthlyFee();

    /**
     * Gets the holder of the account
     * @return Profile of the holder
     */    public Profile getHolder() {
        return holder;
    }

    /**
    * Returns the balance of the account
    * @return a double representing the balance of account
    */
    public double getBalance() {
        return balance;
    }

    /**
    * Returns the type of the account
    * @return a String representing the type of account
    */
    public abstract String getAccountType();

    /**
    * Adds the specified amount to the account balance.
    * @param double amount representing the amount of money the user wants to deposit
    */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
    * Returns a boolean representing if the withdrawal was a success or failure
    * @param double amount representing the amount of money the user wants to withdraw
    * @return boolean representing if the withdrawal was a success or failure
    */
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
    * A comparison method for sorting accounts. 
    * @param account object representing account types and names and dates of birth
    * @return 1, 0, -1 representing comparison status
    */
    @Override
    public int compareTo(Account other) {
        int accountTypeComparison = this.getAccountType().compareTo(other.getAccountType());
        if (accountTypeComparison != 0) {
            return accountTypeComparison;
        }
        int lastNameComparison = this.getHolder().getLname().compareTo(other.getHolder().getLname());

        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        int firstNameComparison = this.getHolder().getFname().compareTo(other.getHolder().getFname());

        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        return this.getHolder().getDob().compareTo(other.getHolder().getDob());
    }



    /**
    * Compares Account objects based on the holder profiles.
    * @param Object obj representing account holder and profiles
    * @return boolean indicating equal or unequal objects
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Account other = (Account) obj;
        return holder.equals(other.holder);
    }

    /**
    * Generates a hashcode based on the holder profiles.
    * @return an int hashcode based on the holder profile
    */
    @Override
    public int hashCode() {
        return holder.hashCode();
    }

    /**
    * Returns a string of all the details of the Account object
    * @return a string representation of the Account object
    */
    @Override
    public String toString() {

        return holder.toString() + String.format("::Balance $%,.2f", balance);

    }

    /**
     * Resets withdrawals
     */
    public void resetWithdrawals() {}
}
