package pl.nazaweb.jboss.hot.deploy;

import java.io.File;

/**
 *
 * @author naza
 */
public class Paths {

    private static final Paths instance = new Paths();
    private File jbossPath;
    private File projectPath;
    private boolean isProjectPathSet = false;

    private Paths() {
    }

    public static Paths getInstance() {
        return instance;
    }

    public void setJbossPath(File path) {
        this.jbossPath = path;
    }

    public File getJbossPath() {
        return jbossPath;
    }

    public File getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(File projectPath) {
        this.projectPath = projectPath;
        this.isProjectPathSet = true;
    }

    public boolean shouldAttachWatcher() {
        return isProjectPathSet == false;
    }

}
