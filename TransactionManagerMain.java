import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TransactionManagerMain extends Application {
  
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("YourFXMLFileName.fxml"));
        AnchorPane root = loader.load();
    
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    
        primaryStage.setTitle("Your Application Title");
    
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
