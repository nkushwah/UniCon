package directorycopier;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by srpandey on 9/20/2017.
 */
public class DirectoryCopier extends AbstractDirectoryCopier {

    private DirectoryCopier() {
    }

    public DirectoryCopier(String sourcePath , String destPath) {
        this.sourcePath = Paths.get(sourcePath);
        this.destPath = Paths.get(destPath);
    }

    public static void main(String[] args ) throws IOException {
        DirectoryCopier copier= new DirectoryCopier();
        copier.backupDirectory(copier.sourcePath);
    }

    /**
     * Will backup any directory given as the sourcePath to it
     *
     */
    private void backupDirectory(Path sourcePath) {
        try {
            walkAndSaveFileTree(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void walkAndSaveFileTree(final Path path) throws IOException {
        Files.walkFileTree(path, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("pre visit dir:" + dir);
                createDirectory(dir, destPath);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visit file: " + file);
                String sourcePath = file.toString();
                int rootFolderIndex = file.toFile().getPath().indexOf(rootFolder);
                String pathPostFix= sourcePath.substring(rootFolderIndex);
                String destRootPath = destPath.toString();
                copyFilesToDiffDirectory(file , Paths.get(destRootPath + DOUBLE_BKW_SLASH+ pathPostFix));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("visit file failed: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("post visit directory: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}