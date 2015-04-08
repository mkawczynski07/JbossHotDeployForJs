package pl.nazaweb.jboss.hot.deploy.events;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import org.openide.util.Exceptions;

/**
 *
 * @author naza
 */
public class UpdateEventState extends AbstractEventState {

    @Override
    public void handle(File file) {
        Optional<File> destination = getDestination(file);
        if (destination.isPresent() == false) {
            return;
        }
        if (file.exists()) {
            destination.get().mkdirs();
            tryCopyFile(file, destination.get());
        }
    }

    protected void tryCopyFile(File file, File destination) {
        try {
            Files.copy(file.toPath(), destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
