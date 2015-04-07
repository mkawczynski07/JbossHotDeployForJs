package pl.nazaweb.jboss.hot.deploy.jboos;

import java.io.File;
import java.util.Optional;
import pl.nazaweb.jboss.hot.deploy.CurrentPaths;
import pl.nazaweb.jboss.hot.deploy.utils.LastModifiedFileStartsWithFinder;

/**
 *
 * @author naza
 */
public class CurrentDeploymentModulePathExtractor {

    private final File jbossPath;
    private final String projectName;
    private Optional<File> deploymentDirectory;
    private Optional<File> moduleDirectory;
    private final LastModifiedFileStartsWithFinder finder
            = new LastModifiedFileStartsWithFinder();

    public CurrentDeploymentModulePathExtractor() {
        this.jbossPath = CurrentPaths.getInstance().getJbossPath();
        this.projectName = CurrentPaths.getInstance().getProjectName();
    }

    public Optional<File> extract() {
        setDeploymentDirectory();
        findDeployedModuleDirectory();
        return moduleDirectory;
    }

    private void setDeploymentDirectory() {
        String vfsPath = jbossPath.getAbsolutePath()
                + File.separator
                + "standalone"
                + File.separator
                + "tmp"
                + File.separator
                + "vfs";
        deploymentDirectory = finder.find(new File(vfsPath), "deployment");
    }

    private void findDeployedModuleDirectory() {
        moduleDirectory = finder.find(deploymentDirectory.get(), projectName);
    }

}
