package pl.nazaweb.jboss.hot.deploy.events;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import org.openide.util.Exceptions;

/**
 *
 * @author naza
 */
public class DeleteEventState extends AbstractEventState {

    @Override
    public void handle(File file) {
        Optional<File> destination = getDestination(file);
        if (destination.isPresent() && destination.get().exists()) {
            tryDeleteFile(destination);
        }

    }

    private void tryDeleteFile(Optional<File> destination) {
        try {
            Files.delete(destination.get().toPath());
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
