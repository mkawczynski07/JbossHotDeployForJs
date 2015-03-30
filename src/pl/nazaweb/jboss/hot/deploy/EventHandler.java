package pl.nazaweb.jboss.hot.deploy;

import java.io.File;

/**
 *
 * @author naza
 */
public interface EventHandler {

    void handle(File file);

}
