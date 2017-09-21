package TreeView.reader;

import java.io.File;

/**
 * Created by Shristi on 9/18/2017.
 */
    public class FolderReader {

        public static void main(String[] args ) {
            File mainFolder = new File("C:/Users/Shristi/Documents/Job");
            File[] allFiles = mainFolder.listFiles();
            String folderName = mainFolder.getName();
            listFilesInDirectory(allFiles , folderName);
        }

        private static void listFilesInDirectory(File[] allFiles , String folderName){
            File currentFile;
            for(int i=0;i<allFiles.length;i++) {
                currentFile = allFiles[i];
                if(currentFile.isFile()) {
                    System.out.println(folderName + "/" + currentFile.getName());
                } else if (currentFile.isDirectory()) {
                    File[] directoryFiles = currentFile.listFiles();
                    String curfolderName = currentFile.getName();
                    String totalPath = folderName + "/" + curfolderName;
                    listFilesInDirectory(directoryFiles , totalPath);
                }
            }
        }
}
