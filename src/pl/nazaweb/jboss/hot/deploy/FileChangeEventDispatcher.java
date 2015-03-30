package pl.nazaweb.jboss.hot.deploy;

import java.io.File;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author naza
 */
public class FileChangeEventDispatcher {

    private final static Map<WatchEvent.Kind, EventHandler> EVENT_STATES = new HashMap();

    static {
        EVENT_STATES.put(ENTRY_CREATE, new CreateEventState());
        EVENT_STATES.put(ENTRY_DELETE, new DeleteEventState());
        EVENT_STATES.put(ENTRY_MODIFY, new UpdateEventState());
    }

    public void handleEvent(File file, WatchEvent.Kind kind) {
        EventHandler handler = EVENT_STATES.get(kind);
        if (handler != null) {
            handler.handle(file);
        }
    }

}
