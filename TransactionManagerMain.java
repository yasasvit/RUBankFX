package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TransactionManagerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
///Users/yasasvitallapaneni/Desktop/RUBankFX/TransactionManagerView.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransactionManagerView.fxml"));
        Parent root = loader.load();
        TransactionManagerController controller = new TransactionManagerController();
        loader.setController(controller);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Transaction Manager");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
