package TreeView.view;

import TreeView.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;

/**
 * Created by Shristi on 9/17/2017.
 */
public class TreeViewController {

    @FXML
    private TreeView<String> treeViewer;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;



    public static final String CONFIG_DIRECTORY = "D:/JAVA PROJECT FILES/config";
    // Reference to the main application.
    private MainApp mainApp;

    private TreeItem<String> rootNode = null;

    public TreeViewController() {
    }


    public void setTreeViewer() {
        File mainFolder = new File(CONFIG_DIRECTORY);
        File[] allFiles = mainFolder.listFiles();
        String folderName = mainFolder.getName();
        rootNode = new TreeItem<>(folderName);
        rootNode.setExpanded(true);
        listFilesInDirectory(allFiles , folderName , rootNode);
        treeViewer.setRoot(rootNode);

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    private static void listFilesInDirectory(File[] allFiles , String folderName , TreeItem curRootItem){
        File currentFile;
        for(int i=0;i<allFiles.length;i++) {
            currentFile = allFiles[i];
            if(currentFile.isFile()) {
                //@@ need to have model inside TreeItem with fileDirectory getPath
                TreeItem<String> fileItem = new TreeItem<String>(currentFile.getName());
                curRootItem.getChildren().add(fileItem);
            } else if (currentFile.isDirectory()) {
                File[] directoryFiles = currentFile.listFiles();
                String curfolderName = currentFile.getName();
                TreeItem<String> folderItem = new TreeItem<String>(curfolderName);
                curRootItem.getChildren().add(folderItem);
                listFilesInDirectory(directoryFiles , curfolderName , folderItem);
            }
        }
    }
}
