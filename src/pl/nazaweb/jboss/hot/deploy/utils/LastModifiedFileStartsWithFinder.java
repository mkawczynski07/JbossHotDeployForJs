package pl.nazaweb.jboss.hot.deploy.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author naza
 */
public class LastModifiedFileStartsWithFinder {

    public Optional<File> find(File directory, String prefix) {
        if (directory != null && directory.exists()) {
            File[] files = directory.listFiles(new StartsWithFileFilter(prefix));
            Arrays.sort(files, new FileModifiedDateComparator());
            return files.length == 0 ? Optional.<File>empty() : Optional.ofNullable(files[0]);
        }
        return Optional.<File>empty();
    }

}
