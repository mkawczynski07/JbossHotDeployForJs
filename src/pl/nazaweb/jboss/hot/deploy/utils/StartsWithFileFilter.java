package pl.nazaweb.jboss.hot.deploy.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author naza
 */
public class StartsWithFileFilter implements FilenameFilter {

    private final String prefix;

    public StartsWithFileFilter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean accept(File dir, String name) {
        return dir.isDirectory() && name.startsWith(prefix);
    }

}
