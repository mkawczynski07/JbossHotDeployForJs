package pl.nazaweb.jboss.hot.deploy.utils;

import java.io.File;
import java.util.Comparator;

/**
 *
 * @author naza
 */
public class FileModifiedDateComparator implements Comparator<File> {

    @Override
    public int compare(File first, File second) {
        return first.lastModified() > second.lastModified() ? 1 : 0;
    }

}
