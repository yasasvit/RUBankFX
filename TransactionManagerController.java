import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class is made to load the accounts from bankAccounts.txt
 * @authors Pranav Gummaluri, Yasasvi Tallapaneni
 */
public class TransactionManagerController {

    @FXML
    private TextField accountInput;

    @FXML
    private TextField transactionAmount;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button exitButton;

    @FXML
    private Label balanceLabel;

    // Initialize your AccountDatabase or other necessary data structures here

    @FXML
    public void initialize() {
        // This method is called when the FXML file is loaded
        // You can use it to initialize your application
    }

    @FXML
    private void handleDeposit(ActionEvent event) {
        String accountNumber = accountInput.getText();
        String amountText = transactionAmount.getText();

        if (accountNumber.isEmpty() || amountText.isEmpty()) {
            showInfoDialog("Error", "Please enter both account number and amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            // Search for the account in your AccountDatabase
            // Replace AccountDatabase with your actual class name
            Account account = AccountDatabase.getAccount(accountNumber);

            if (account == null) {
                showInfoDialog("Error", "Account not found.");
                return;
            }

            if (amount <= 0) {
                showInfoDialog("Error", "Invalid deposit amount.");
            } else {
                account.deposit(amount);  // Implement the deposit method in your Account class
                updateBalanceLabel(account);
                showInfoDialog("Success", "Deposit successful.");
            }
        } catch (NumberFormatException e) {
            showInfoDialog("Error", "Invalid amount format.");
        }
    }

    @FXML
    private void handleWithdraw(ActionEvent event) {
        String accountNumber = accountInput.getText();
        String amountText = transactionAmount.getText();

        if (accountNumber.isEmpty() || amountText.isEmpty()) {
            showInfoDialog("Error", "Please enter both account number and amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountText);

            // Search for the account in your AccountDatabase
            // Replace AccountDatabase with your actual class name
            Account account = AccountDatabase.getAccount(accountNumber);

            if (account == null) {
                showInfoDialog("Error", "Account not found.");
                return;
            }

            if (amount <= 0) {
                showInfoDialog("Error", "Invalid withdrawal amount.");
            } else if (account.getBalance() < amount) {
                showInfoDialog("Error", "Insufficient balance.");
            } else {
                account.withdraw(amount);  // Implement the withdraw method in your Account class
                updateBalanceLabel(account);
                showInfoDialog("Success", "Withdrawal successful.");
            }
        } catch (NumberFormatException e) {
            showInfoDialog("Error", "Invalid amount format.");
        }
    }

    private void updateBalanceLabel(Account account) {
        balanceLabel.setText("Balance: $" + String.format("%.2f", account.getBalance()));
    }


    @FXML
    private void handleExit(ActionEvent event) {
        // Close the application
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private void showInfoDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
