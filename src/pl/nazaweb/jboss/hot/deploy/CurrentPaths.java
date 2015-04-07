package pl.nazaweb.jboss.hot.deploy;

import java.io.File;
import java.nio.file.Path;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.api.project.ProjectUtils;

/**
 *
 * @author naza
 */
public class CurrentPaths {

    private static final CurrentPaths instance = new CurrentPaths();
    private File jbossPath;
    private Project project;
    private boolean isProjectSet = false;
    private ProjectInformation projectInformation;

    private CurrentPaths() {
    }

    public static CurrentPaths getInstance() {
        return instance;
    }

    public void setJbossPath(File path) {
        this.jbossPath = path;
    }

    public File getJbossPath() {
        return jbossPath;
    }

    public Path getProjectPath() {
        return java.nio.file.Paths.get(project.getProjectDirectory().getPath());
    }

    public File getProjectFile() {
        return getProjectPath().toFile();
    }

    public void setProject(Project project) {
        this.project = project;
        this.projectInformation = ProjectUtils.getInformation(project);
        this.isProjectSet = true;
    }

    public boolean shouldAttachWatcher() {
        return isProjectSet == false;
    }

    public Path getProjectWatchPath() {
        String path = getProjectFile().getAbsolutePath()
                + File.separator
                + "src"
                + File.separator
                + "main"
                + File.separator
                + "webapp";
        return new File(path).toPath();
    }

    public String getProjectName() {
        return projectInformation.getDisplayName();
    }

}
