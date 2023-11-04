import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TransactionManagerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TransactionManagerView.fxml"));
        Parent root = loader.load();
        TransactionManagerController controller = loader.getController();

        primaryStage.setTitle("Bank Account Manager");
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        controller.init(); 
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

