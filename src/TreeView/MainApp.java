package TreeView;

import TreeView.view.TreeViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Shristi on 9/17/2017.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    public MainApp() {
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tree Viewer App");

        initRootLayout();
        showTreeLayout();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showTreeLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TreeView.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);

            TreeViewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setTreeViewer();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
