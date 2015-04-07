package pl.nazaweb.jboss.hot.deploy.watcher;

import pl.nazaweb.jboss.hot.deploy.events.CreateEventState;
import pl.nazaweb.jboss.hot.deploy.events.UpdateEventState;
import pl.nazaweb.jboss.hot.deploy.events.DeleteEventState;
import java.io.File;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;
import pl.nazaweb.jboss.hot.deploy.events.AbstractEventState;

/**
 *
 * @author naza
 */
public class FileChangeEventDispatcher {

    private final static Map<WatchEvent.Kind, AbstractEventState> EVENT_STATES = new HashMap();

    static {
        EVENT_STATES.put(ENTRY_CREATE, new CreateEventState());
        EVENT_STATES.put(ENTRY_DELETE, new DeleteEventState());
        EVENT_STATES.put(ENTRY_MODIFY, new UpdateEventState());
    }

    public void handleEvent(File file, WatchEvent.Kind kind) {
        AbstractEventState handler = EVENT_STATES.get(kind);
        if (handler != null) {
            handler.handle(file);
        }
    }

}
