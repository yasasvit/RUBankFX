package src;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Calendar;
import java.time.LocalDate;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class TransactionManagerController {
    AccountDatabase accountDatabase;
    // TAB 1 (OPEN / CLOSE)
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField initialBalance;
    @FXML
    private RadioButton checking;
    @FXML
    private RadioButton collegeChecking;
    @FXML
    private RadioButton savings;
    @FXML
    private RadioButton moneyMarket;
    @FXML
    private ToggleGroup accountTypes;
    @FXML
    private RadioButton nb;
    @FXML
    private RadioButton newark;
    @FXML
    private RadioButton camden;
    @FXML
    private ToggleGroup campuses;
    @FXML
    private CheckBox loyalCustomer;
    @FXML
    private Button openButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextArea output;
    // TAB 2 (DEPOSIT / WITHDRAW)
    @FXML
    private TextField userFirstName;
    @FXML
    private TextField userLastName;
    @FXML
    private DatePicker userDOB;
    @FXML
    private RadioButton userChecking;
    @FXML
    private RadioButton userCollegeChecking;
    @FXML
    private RadioButton userSavings;
    @FXML
    private RadioButton userMoneyMarket;
    @FXML
    private ToggleGroup userAccountTypes;
    @FXML
    private TextField userAmount;
    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private TextArea outputTab2;
    @FXML
    private TextArea printOutput;

    /**
    * Initializes the instance variable
    */
    public void initialize() {
        this.accountDatabase = new AccountDatabase();
        disableAllOptions();

    }
    /**
     * Disables certain radio buttons and checkboxes on the user interface 
     */
    private void disableAllOptions() {
        nb.setDisable(true);
        newark.setDisable(true);
        camden.setDisable(true);
        loyalCustomer.setDisable(true);
    }

    /**
     * Handles the selection of account types on the user interface. 
     * @param ActionEvent object event representing user input
     */
    @FXML
    private void handleAccountTypeSelection(ActionEvent event) {
        RadioButton selectedAccountType = (RadioButton) accountTypes.getSelectedToggle();
        if (selectedAccountType != null) {
            disableAllOptions();
            if (selectedAccountType.equals(collegeChecking)) {
                nb.setDisable(false);
                newark.setDisable(false);
                camden.setDisable(false);
            } else if (selectedAccountType.equals(savings) || selectedAccountType.equals(moneyMarket)) {
                loyalCustomer.setDisable(false);
                nb.setSelected(false);
                newark.setSelected(false);
                camden.setSelected(false);
            } else {
                nb.setSelected(false);
                newark.setSelected(false);
                camden.setSelected(false);
            }
        }
    }
    /** Checks if date of birth is valid
     * @param month representing month
     * @param day representing day
     * @param year representing year
     * @return true if dob is valid, false otherwise
     */
    private boolean isValidDOB(int month, int day, int year) {
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH);
        int currDay = calendar.get(Calendar.DATE);

        if (year > currYear || (year == currYear && month - 1 >= currMonth) || (year == currYear && month - 1 == currMonth && day >= currDay)) {
            return false;
        }
        Date dob = new Date(month, day, year);
        if (!dob.isValid()) {
            return false;
        }

        return true;
    }

    /**
     * Returns the abbreviation for a given account type.
     * @param String accountType representing the type of account specified
     */
    private String getAbbreviation(String accountType) {
        switch (accountType.toLowerCase()) {
            case "checking":
            case "userchecking":
                return "C";
            case "collegechecking":
            case "usercollegechecking":
                return "CC";
            case "savings":
            case "usersavings":
                return "S";
            case "moneymarket":
            case "usermoneymarket":
                return "MM";
            default:
                return "Invalid";
        }
    }
    /**
     * Calculates the age of the user based on dob
     * @param Date object representing date of birth
     * @return int representing age
     */
    private int getAge(Date dob) {
        Date currentDate = new Date();
        int age = currentDate.getYear() - dob.getYear();
        if (currentDate.getMonth() < dob.getMonth() || (currentDate.getMonth() == dob.getMonth() && currentDate.getDay() < dob.getDay())) {
            age--;
        }
        return age;
    }
    /** Creates a new account based on the inputs of the user
     * @param String representing account type
     * @param Profile object representing profile
     * @param double representing initial deposit
     * @param int  representing the campus code
     * @param boolean representing if account user is loyal or not
     * @return Account representing Account that just got created
     */
    private Account createNewAccount(String accountType, Profile profile, double initial, int campusCode, boolean isLoyal) {
        Date dob = profile.getDob();
        int age = getAge(dob);
        if (age < 16) {
            String invalidDOBStr = "DOB invalid: " + dob.toString() + " under 16.\n";
            printAllOutputs(invalidDOBStr);
            return null;
        }
        switch (accountType) {
            case "C":
                return new Checking(profile, initial);
            case "CC":
                if (age >= 24) {
                    String invalidDOBStr = "DOB invalid: " + dob.toString() + " over 24.\n";
                    printAllOutputs(invalidDOBStr);
                    return null;
                }
                return new CollegeChecking(profile, initial, campusCode);
            case "S":
                return new Savings(profile, initial, isLoyal);
            case "MM":
                if (initial < 2000) {
                    String invalidAmountStr = "Minimum of $2000 to open a Money Market account.\n";
                    printAllOutputs(invalidAmountStr);
                    return null;
                }
                return new MoneyMarket(profile, initial);
            default:
                String invalidAccountStr = "Invalid account type.\n";
                printAllOutputs(invalidAccountStr);
                return null;
        }
    }
    /**
     * Prints out the specified error statement if the account already exists
     * @param String for first name
     * @param String for last name
     * @param Date object for date of birth
     * @param String representing account type
     */
    private void printError(String firstName, String lastName, Date dob, String accountType) {
        String errorStr = firstName + " " + lastName + " " + dob.toString() + "(" + accountType + ") is already in the database.\n";
        printAllOutputs(errorStr);
    }

    /**
    * Handles the action when the "Open" button is clicked, creating a new bank account.
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void handleOpenButtonAction(ActionEvent event) {
        String firstNameText = firstName.getText().trim();
        String lastNameText = lastName.getText().trim();
        LocalDate dobDate = dob.getValue();
        String initialDepositString = initialBalance.getText().trim();
        double initialAmount = 0;
        try {
            initialAmount = Double.parseDouble(initialDepositString);
        } catch (NumberFormatException e) {
            String invalidBalanceStr = "Please enter valid initial balance.\n";
            printAllOutputs(invalidBalanceStr);
            return;
        }
        RadioButton selectedAccountType = (RadioButton) accountTypes.getSelectedToggle();
        RadioButton selectedCampus = (RadioButton) campuses.getSelectedToggle();
        boolean isLoyal = loyalCustomer.isSelected();
        if (firstNameText.isEmpty() || lastNameText.isEmpty() || dobDate == null || selectedAccountType == null || initialDepositString.isEmpty()) {
            String invalidInputStr = "Please fill in all required fields.\n";
            printAllOutputs(invalidInputStr);
            return;
        }
        String accountType = selectedAccountType.getId();
        Date dob = new Date(dobDate.getMonthValue(), dobDate.getDayOfMonth(), dobDate.getYear());
        if (!isValidDOB(dobDate.getMonthValue(), dobDate.getDayOfMonth(), dobDate.getYear())) {
            String invalidDOBStr = "Invalid date of birth.\n";
            printAllOutputs(invalidDOBStr);
            return;
        }
        Profile profile = new Profile(firstNameText, lastNameText, dob);
        String accountTypeAbbrev = getAbbreviation(accountType);
        if (accountTypeAbbrev.equals("CC") || accountTypeAbbrev.equals("C")) {
            if (accountDatabase.hasCheckingOrCollegeChecking(profile, accountType)) {
                printError(firstNameText, lastNameText, dob, accountType);
                return;
            }
        }
        int campusCode = -1;
        if (selectedCampus != null) {
            campusCode = getCampusCode(selectedCampus.getId());
        }
        Account account = createNewAccount(accountTypeAbbrev, profile, initialAmount, campusCode, isLoyal);
        if (account == null) {
            return;
        }
        if (accountDatabase.open(account)) {
            String openOutputStr = profile.toString() + " (" + accountTypeAbbrev + ") opened.\n";
            printAllOutputs(openOutputStr);
        } else {
            String errorOpenStr = profile.toString() + " (" + accountTypeAbbrev + ") is already in the database.\n";
            printAllOutputs(errorOpenStr);
        }
    }
    
    
    /**
    * Returns the campus code based on the selected campus radio button.
    * @param String campus that represents the college campus 
    */
    private int getCampusCode(String campus) {
        switch (campus.toLowerCase()) {
            case "nb":
                return 0;
            case "newark":
                return 1;
            case "camden":
                return 2;
            default:
                return -1;
        }
    }
    /**
     * Creates a temporary Account object
     * @param String  accountType representing the account type
     * @param Profile object that represents profile
     * @return Account object representing temporary account
     */
    private Account createTempAccount(String accountType, Profile profile) {
        Date dob = profile.getDob();
        switch (accountType) {
            case "C":
                return new Checking(profile, 5000);
            case "CC":
                return new CollegeChecking(profile, 5000, 0);
            case "S":
                return new Savings(profile, 5000, true);
            case "MM":
                return new MoneyMarket(profile, 5000);
            default:
                return null;
        }
    }
    /**
    * Handles the action when the "Close" button is clicked, closing an existing bank account.
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        RadioButton selectedAccountType = (RadioButton) accountTypes.getSelectedToggle();
        String accountType = selectedAccountType.getId();
        String firstNameText = firstName.getText().trim();
        String lastNameText = lastName.getText().trim();
        LocalDate dobDate = dob.getValue();
        if (selectedAccountType == null || firstNameText.isEmpty() || lastNameText.isEmpty() || dobDate == null) {
            String invalidInputStr = "Please fill in all required fields.\n";
            printAllOutputs(invalidInputStr);
            return;
        }
        Date dob = new Date(dobDate.getMonthValue(), dobDate.getDayOfMonth(), dobDate.getYear());
        if (!isValidDOB(dob.getMonth(), dob.getDay(), dob.getYear())) {
            return;
        }
        Profile profile = new Profile(firstNameText, lastNameText, dob);
        String accountTypeAbbrev = getAbbreviation(accountType);
        Account accountToClose = createTempAccount(accountTypeAbbrev, profile);
        if (accountToClose == null) {
            return;
        }
        if (accountDatabase.close(accountToClose)) {
            String closeStr = profile.toString() + " (" + accountTypeAbbrev + ") has been closed.\n";
            printAllOutputs(closeStr);
        } else {
            String errorCloseStr =profile.toString() + " (" + accountTypeAbbrev + ") is not in the database.\n";
            printAllOutputs(errorCloseStr);
        }
    }

    /**
    * Clears the input fields and resets radio buttons and checkboxes.
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        firstName.clear();
        lastName.clear();
        initialBalance.clear();
        dob.getEditor().clear();
        checking.setSelected(false);
        collegeChecking.setSelected(false);
        savings.setSelected(false);
        moneyMarket.setSelected(false);
        nb.setSelected(false);
        newark.setSelected(false);
        camden.setSelected(false);
        loyalCustomer.setSelected(false);
        output.clear();
        printOutput.clear();
        outputTab2.clear();
        disableAllOptions();
    }

    /**
    * Handles the deposit action, allowing users to deposit money into their accounts.
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void handleDepositButton(ActionEvent event) {
        RadioButton selectedAccountType = (RadioButton) userAccountTypes.getSelectedToggle();
        String accountType = selectedAccountType.getId();
        String firstNameText = userFirstName.getText().trim();
        String lastNameText = userLastName.getText().trim();
        LocalDate dobDate = userDOB.getValue();
        if (selectedAccountType == null || firstNameText.isEmpty() || lastNameText.isEmpty() || dobDate == null) {
            String invalidInputStr = "Please fill in all required fields.\n";
            printAllOutputs(invalidInputStr);
            return;
        }
        String userAmountString = userAmount.getText().trim();
        double deposit = 0;
        try {
            deposit = Double.parseDouble(userAmountString);
        } catch (NumberFormatException e) {
            String invalidAmountStr = "Please enter valid amount.\n";
            printAllOutputs(invalidAmountStr);
            return;
        }
        if (deposit <= 0) {
            String invalidAmountStr = "Deposit - amount cannot be 0 or negative.\n";
            printAllOutputs(invalidAmountStr);
            return;
        }
        Date dob = new Date(dobDate.getMonthValue(), dobDate.getDayOfMonth(), dobDate.getYear());
        Profile profile = new Profile(firstNameText, lastNameText, dob);
        String accountTypeAbbrev = getAbbreviation(accountType);
        Account accountToDeposit = createTempAccount(accountTypeAbbrev, profile);
        if (accountToDeposit == null) {
            return;
        }
        boolean canDeposit = accountDatabase.deposit(accountToDeposit, deposit);
        if (canDeposit) {
            String depositStr =profile.toString() + " (" + accountTypeAbbrev + ") Deposit - balance updated.\n";
            printAllOutputs(depositStr);
        } else {
            String errorDepositStr =profile.toString() + " (" + accountTypeAbbrev + ") is not in the database.\n";
        }
    }

    /**
    * Handles the withdraw action, allowing users to withdraw money from their accounts.
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void handleWithdrawButton(ActionEvent event) {
        RadioButton selectedAccountType = (RadioButton) userAccountTypes.getSelectedToggle();
        String accountType = selectedAccountType.getId();
        String firstNameText = userFirstName.getText().trim();
        String lastNameText = userLastName.getText().trim();
        LocalDate dobDate = userDOB.getValue();
        if (selectedAccountType == null || firstNameText.isEmpty() || lastNameText.isEmpty() || dobDate == null) {
            String invalidInputStr = "Please fill in all required fields.\n";
            printAllOutputs(invalidInputStr);
            return;
        }
        String userAmountString = userAmount.getText().trim();
        double withdrawAmount = 0;
        try {
            withdrawAmount = Double.parseDouble(userAmountString);
        } catch (NumberFormatException e) {
            String invalidAmountStr = "Please enter valid amount.\n";
            printAllOutputs(invalidAmountStr);
            return;
        }
        if (withdrawAmount <= 0) {
            String invalidAmountStr = "Withdraw - amount cannot be 0 or negative.\n";
            printAllOutputs(invalidAmountStr);
            return;
        }
        Date dob = new Date(dobDate.getMonthValue(), dobDate.getDayOfMonth(), dobDate.getYear());
        Profile profile = new Profile(firstNameText, lastNameText, dob);
        String accountTypeAbbrev = getAbbreviation(accountType);
        Account accountToWithdraw = createTempAccount(accountTypeAbbrev, profile);
        if (accountToWithdraw == null) {
            return;
        }
        int canWithdraw = accountDatabase.withdraw(accountToWithdraw, withdrawAmount);
        if (canWithdraw == 2) {
            String withdrawStr =profile.toString() + " (" + accountTypeAbbrev + ") Withdraw - balance updated.\n";
            printAllOutputs(withdrawStr);
        } else if (canWithdraw == 0) {
            String errorWithdrawStr = profile.toString() + " (" + accountTypeAbbrev + ") is not in the database.\n";
            printAllOutputs(errorWithdrawStr);
        } else {
            String invalidWithdrawStr = profile.toString() + " (" + accountTypeAbbrev + ") Withdraw - insufficient fund.\n";
            printAllOutputs(invalidWithdrawStr);
        }
    }

    

    /**
    * Clears input fields in the second tab of the user interface.
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void handleClearButtonActionForTab2(ActionEvent event) {
        userFirstName.clear();
        userLastName.clear();
        userAmount.clear();
        userDOB.getEditor().clear();
        userChecking.setSelected(false);
        userCollegeChecking.setSelected(false);
        userSavings.setSelected(false);
        userMoneyMarket.setSelected(false);
        output.clear();
        printOutput.clear();
        outputTab2.clear();

    }
    /**
    * Appends text to multiple text areas on the user interface.
    * @param String text that represents the output message
    */
    private void printAllOutputs(String text) {
        output.appendText(text);
        outputTab2.appendText(text);
        printOutput.appendText(text);
    }
    /**
    * Prints every account stored in the system
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void printAllAccounts(ActionEvent event) {
        String initialStr = "*Accounts sorted by account type and profile.\n";
        printAllOutputs(initialStr);
        String sortedAccountInfo = accountDatabase.printSortedFX();
        printAllOutputs(sortedAccountInfo);
    }
    /**
    * Prints every account stored in the system
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void printInterestsAndFees(ActionEvent event) {
        String initialStr ="*List of accounts with fee and monthly interest.\n";
        printAllOutputs(initialStr);
        String accountInfo = accountDatabase.printFeesAndInterestsFX();
        printAllOutputs(accountInfo);
    }

    /**
    * Calculates fees and interests, resets Money Market withdrawals, and prints the sorted account information to the UI
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void updateAccounts(ActionEvent event) {
        String initialStr ="*List of accounts with fees and interests applied.\n";
        accountDatabase.calculateFeesAndInterests();
        accountDatabase.resetMoneyMarketWithdrawals();
        String sortedAccountInfo = accountDatabase.printSortedFX();
        printAllOutputs(sortedAccountInfo);
    }

    /**
    * Clears the text areas in the UI 
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void handleClearButtonForPrintTab(ActionEvent event) {

        output.clear();
        printOutput.clear();
        outputTab2.clear();
    }

    /**
    * Checks if the provided account information is valid and can be used to create a new account
    * Validates the account type, first name, last name, date of birth (DOB), and the initial balance.
    * @param String array account which is 
    */
    private Account checkAccount(String [] account) {
        if (account.length < 5 || account.length > 6) {
            return null;
        }
        String accountType = account[0].trim();
        if (!accountType.equals("CC") && !accountType.equals("C") && !accountType.equals("S") && !accountType.equals("MM")) {
            return null;
        }
        String firstName = account[1].trim();
        String lastName = account[2].trim();
        String [] date = account[3].trim().split("/");
        Date dob = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        if (!dob.isValid()) {
            return null;
        }
        String amountStr = account[4].trim();
        double amount = 0;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            return null;
        }
        Profile profile = new Profile(firstName, lastName, dob);
        int campusCode = -1;
        boolean isLoyal = true;
        if (account.length == 6) {
            String lastParamStr = account[5].trim();
            campusCode = Integer.parseInt(lastParamStr);
            if (campusCode == 0) {
                isLoyal = false;
            }
        }
        return createNewAccount(accountType, profile, amount, campusCode, isLoyal);
    }

    
    /**
    * Loads account information from a text file.
    * @param ActionEvent object "event" for a user inputted action
    */
    @FXML
    private void loadFile(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose File to Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile;
        try {
            sourceFile = chooser.showOpenDialog(stage);
        } catch (Exception e) {
            printAllOutputs("Unable to open file\n");
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        String [] account;
        String line = reader.readLine();
        boolean foundInvalidAccount = false;
        int count = 0;
        while (line != null) {
            account = line.split(",");
            Account createdAccount = null;
            try {
                createdAccount = checkAccount(account);
            } catch (Exception e) {
                foundInvalidAccount = true;
            }
            if (createdAccount != null) {
                accountDatabase.open(createdAccount);
            }
            line = reader.readLine();
            count += 1;
        }
        if (foundInvalidAccount) {
            printAllOutputs("Some accounts in text file were invalid.\n");
        }
        printAllOutputs("Accounts loaded.\n");
        reader.close();
    }

}
