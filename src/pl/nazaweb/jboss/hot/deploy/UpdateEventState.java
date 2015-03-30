package pl.nazaweb.jboss.hot.deploy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import org.openide.util.Exceptions;

/**
 *
 * @author naza
 */
public class UpdateEventState implements EventHandler {

    public final String MAVEN_PATH
            = "src" + File.separator + "main" + File.separator + "webapp" + File.separator;

    @Override
    public void handle(File file) {
        File jbossDir = Paths.getInstance().getJbossPath();
        File projectDir = Paths.getInstance().getProjectPath();

        String toPath = file.getAbsolutePath()
                .replaceFirst(projectDir.getAbsolutePath(), jbossDir.getAbsolutePath());

        toPath = toPath.replaceFirst(MAVEN_PATH, "");

        File destination = new File(toPath);
        destination.mkdirs();

        try {
            Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

}
