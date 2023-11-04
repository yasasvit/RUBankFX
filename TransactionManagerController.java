/**
* This class is made to load the accounts from bankAccounts.txt
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TransactionManagerController {
    @FXML
    private TextField accountHolderNameField;

    @FXML
    private TextField accountBalanceField;

    @FXML
    private TextField transactionAmountField;

    @FXML
    private Label accountInfoLabel;

    @FXML
    private Button openAccountButton;

    @FXML
    private Button closeAccountButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    
    @FXML
    private void handleOpenAccount() {
        
        String accountHolderName = accountHolderNameField.getText();
        double accountBalance = Double.parseDouble(accountBalanceField.getText());

        
        accountInfoLabel.setText("Account opened for: " + accountHolderName);
    }

    @FXML
    private void handleCloseAccount() {
        
        accountInfoLabel.setText("Account closed.");
    }

    @FXML
    private void handleDeposit() {
        
        accountInfoLabel.setText("Money deposited.");
    }

    @FXML
    private void handleWithdraw() {
        
        accountInfoLabel.setText("Money withdrawn.");
    }

    
}
