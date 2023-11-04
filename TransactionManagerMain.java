import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TransactionManagerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransactionManagerView.fxml"));
        Parent root = loader.load();
        
        // Set the controller for the FXML file
        TransactionManagerController controller = loader.getController();
        controller.setStage(primaryStage);
        
        primaryStage.setTitle("Transaction Manager");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
