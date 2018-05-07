package conceptStore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Model;

// Referenced classes of package application:
//            MagazzinoController

public class Main extends Application
{

    public void start(Stage primaryStage)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Magazzino.fxml"));
            BorderPane root = (BorderPane)loader.load();
         
            Model model = new Model();
            MagazzinoController controller = (MagazzinoController)loader.getController();
            controller.setModel(model);
           
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String args[])
    {
        launch(args);
    }
}
