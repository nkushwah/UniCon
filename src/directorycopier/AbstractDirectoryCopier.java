package directorycopier;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by Shristi on 9/21/2017.
 */
public class AbstractDirectoryCopier {

    public static final String FILE_PATH="D:/bava/config";
    public static final String DESTINATION_PATH= "D:/bava/backup";
    protected Path sourcePath = Paths.get(FILE_PATH);
    protected Path destPath = Paths.get(DESTINATION_PATH);
    protected boolean firstTime=true; // if it is the first time it is visiting the directory
    protected String rootFolder;
    protected final String DOUBLE_BKW_SLASH= "\\";

    private boolean checkPathExists() {
        Path path = sourcePath;
        boolean pathExists = Files.exists(path , new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        return pathExists;
    }

    protected void copyFilesToDiffDirectory(Path sourcePath, Path destinationPath) {
        try {
            Files.copy(sourcePath , destinationPath);
        } catch (FileAlreadyExistsException ex ) {
            ex.printStackTrace();
        } catch (IOException ex ) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates directory in rooLocation
     * @param path
     * @param destPath
     */
    protected void createDirectory(Path path, Path destPath) {
        String rootLocation = destPath.toString();
        int index;
        if(firstTime) {
            firstTime = false;
            index = path.toFile().getPath().lastIndexOf("\\")+1;//index where cur directory name starts
            rootFolder = path.toFile().getPath().substring(index);
        }
        else {
            index = path.toFile().getPath().indexOf(rootFolder);
        }

        String folderName= path.toFile().getPath().substring(index);
        Path newDirPath = Paths.get(rootLocation + DOUBLE_BKW_SLASH +folderName);
        try {
            Path newDir = Files.createDirectory(newDirPath);
        } catch(FileAlreadyExistsException e){
            // the directory already exists.
            e.printStackTrace();
        } catch (IOException e) {
            //something else went wrong
            e.printStackTrace();
        }
    }

    /**
     * Move the files from one location to another
     * @param sourcePath
     * @param destinationPath
     */
    private void moveFilesToDiffDirectory(Path sourcePath, Path destinationPath) {
        try {
            Files.move(sourcePath, destinationPath,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            //moving file failed.
            e.printStackTrace();
        }
    }

    private String pathUriStringConverter(Path path) {
        return path.toString();
    }
}
