package src;
/**
 This a class that manages a database of accounts.
 @author Pranav Gummaluri, Yasasvi Tallapaneni
 */
public class AccountDatabase {
    private Account[] accounts;
    private int numAccounts;

    /**
    * Constructor that initializes the accounts array 
    * with a size of 4 and sets numAccounts to 0.
    */
    public AccountDatabase() {
        accounts = new Account[4];
        numAccounts = 0;
    }

    /**
    * Method to search for an account in the database and return its index. 
    * @param Account object representing the account
    * @return -1 if the account is not found and index if account is find.
    */
    public int find(Account account) {
        for (int i = 0; i < numAccounts; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Grows the accounts array by 4
     */
    private void grow() {
        int newSize = accounts.length + 4;
        Account[] newAccounts = new Account[newSize];
        for (int i = 0; i < numAccounts; i++) {
            newAccounts[i] = accounts[i];
        }
        accounts = newAccounts;
    }

    /**
    * A method that checks if an account exists in the database
    * @param Account object that represent the account
    * @return true if account is found and false if otherwise 
    */
    public boolean contains(Account account) {
        return find(account) != -1;
    }

    /**
    * Adds a new account to the database if it doesn't already exist
    * @param Account object that represent the account
    * @return true if no similar account is found and adds it to the list, also updates numAccounts
    */
    public boolean open(Account account) {
        if (contains(account)) {
            return false;
        }
        if (accounts.length == numAccounts) {
            grow();
        }
        accounts[numAccounts] = account;
        numAccounts++;
        return true;
    }

    /**
    * Closes an account to the database if it exists
    * @param Account object that represent the account
    * @return true if no similar account is found and removes it from the list, also updates numAccounts
    */
    public boolean close(Account account) {
        int index = find(account);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < numAccounts - 1; i++) {
            accounts[i] = accounts[i + 1];
        }
        accounts[numAccounts - 1] = null;
        numAccounts--;
        return true;
    }

    /** 
    * Withdraws a specified amount from an account.
    * @param Account object that represent the account, double amount to represent dollar amount
    * @return 0 if account isn't found, 1 if insufficient funds in account 2 if the withdrawal is successful.
    */
    public int withdraw(Account account, double amount) {
        int index = find(account);
        if (index == -1) {
            return 0;
        }
        if (accounts[index].getBalance() < amount) {
            return 1;
        }
        accounts[index].withdraw(amount);
        return 2;
    }

    /**
    * Deposits a specified amount into an account.
    * @param Account object that represent the account, double amount to represent dollar amount
    * @return true if the deposit is successful, or false if the account is not found.
    */ 
    public boolean deposit(Account account, double amount) {
        int index = find(account);
        if (index == -1) {
            return false;
        }
        accounts[index].deposit(amount);
        return true;
    }

    /**
     * Prints the account information sorted by account type and holder name
     * @return String with the sorted output
     */
    public String printSortedFX() {
        if (numAccounts == 0) {
            return "Account Database is empty!\n";
        }

        StringBuilder sortedAccountInfo = new StringBuilder();

        for (int i = 0; i < numAccounts - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numAccounts; j++) {
                if (accounts[j].compareTo(accounts[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Account temp = accounts[i];
                accounts[i] = accounts[minIndex];
                accounts[minIndex] = temp;
            }
        }

        for (int i = 0; i < numAccounts; i++) {
            Account account = accounts[i];
            String accountType = account.getAccountType();
            switch (accountType) {
                case "C":
                    sortedAccountInfo.append("Checking::");
                    break;
                case "CC":
                    sortedAccountInfo.append("College Checking::");
                    break;
                case "MM":
                    sortedAccountInfo.append("Money Market::Savings::");
                    break;
                case "S":
                    sortedAccountInfo.append("Savings::");
                    break;
            }
            sortedAccountInfo.append(account.toString()).append("\n");
        }
        sortedAccountInfo.append("\n");
        return sortedAccountInfo.toString();
    }


    /**
    * Checks if a profile has a checking or college checking account
    * @param Profile object representing account holders, String accountType represents the type of account
    * @return true if "C" or "CC" and false if otherwise
    */
    public boolean hasCheckingOrCollegeChecking(Profile profile, String accountType) {
        for (int i = 0; i < numAccounts; i++) {
            Account account = accounts[i];
            if (account.getHolder().equals(profile)) {
                if (account.getAccountType().equals("C") || account.getAccountType().equals("CC")) {
                    if (accountType.equals("C") || accountType.equals("CC")) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    /**
     * Returns account information along with monthly fees and interest earned for each account.
     * @return String representing the correct output with fees and interests
     */
    public String printFeesAndInterestsFX() {
        if (numAccounts == 0) {
            return "Account Database is empty!\n";
        }

        StringBuilder feesAndInterestsInfo = new StringBuilder();

        for (int i = 0; i < numAccounts - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numAccounts; j++) {
                if (accounts[j].compareTo(accounts[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Account temp = accounts[i];
                accounts[i] = accounts[minIndex];
                accounts[minIndex] = temp;
            }
        }

        for (int i = 0; i < numAccounts; i++) {
            Account account = accounts[i];
            String accountType = account.getAccountType();
            switch (accountType) {
                case "C":
                    feesAndInterestsInfo.append("Checking::");
                    break;
                case "CC":
                    feesAndInterestsInfo.append("College Checking::");
                    break;
                case "MM":
                    feesAndInterestsInfo.append("Money Market::Savings::");
                    break;
                case "S":
                    feesAndInterestsInfo.append("Savings::");
                    break;
            }
            feesAndInterestsInfo.append(account.toString());
            double interest = account.monthlyInterest();
            double fee = account.monthlyFee();
            feesAndInterestsInfo.append(String.format("::fee $%.2f::monthly interest $%.2f%n", fee, interest));
        }
        feesAndInterestsInfo.append("\n");
        return feesAndInterestsInfo.toString();
    }

    /**
     * Calculates and updates the balances of all accounts by adding monthly interest and subtracting monthly fees.
     */
    public void calculateFeesAndInterests() {
        for (int i = 0; i < numAccounts; i++) {
            Account account = accounts[i];
            double interest = account.monthlyInterest();
            double fee = account.monthlyFee();
            account.balance = account.balance + interest - fee;
        }
    }
    /**
     * Resets the withdrawal count for money market accounts.
     */
    public void resetMoneyMarketWithdrawals() {
        for (int i = 0; i < numAccounts; i++) {
            Account account = accounts[i];
            if (account.getAccountType().equals("MM")) {
                account.resetWithdrawals();
            }
        }
    }
}
