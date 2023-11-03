public class TransactionManagerController {
    // ...

    public void loadAccountsFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("bankAccounts.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                if (accountData.length >= 6) {
                    // Determine the account type (e.g., "C" for Checking, "S" for Savings, "MM" for Money Market)
                    String accountType = accountData[0];
                    
                    // Create the appropriate account object and populate it with data
                    Account account = createAccountFromData(accountData);

                    // Add the account to the account database (Model)
                    accountDatabase.addAccount(account);
                }
            }
            reader.close();
        } catch (IOException e) {
            // Handle any file reading or parsing errors
        }
    }

    private Account createAccountFromData(String[] accountData) {
        // Implement logic to create the appropriate account object based on accountData
        // Populate the account object with data from accountData
        // Return the created account object
    }
}
