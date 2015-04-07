package pl.nazaweb.jboss.hot.deploy.events;

import java.io.File;
import java.util.Optional;
import pl.nazaweb.jboss.hot.deploy.CurrentPaths;
import pl.nazaweb.jboss.hot.deploy.jboos.CurrentDeploymentModulePathExtractor;

/**
 *
 * @author naza
 */
public abstract class AbstractEventState {

    public final String MAVEN_PATH
            = "src" + File.separator + "main" + File.separator + "webapp" + File.separator;

    public abstract void handle(File file);

    protected Optional<File> getDestination(File current) {
        Optional<File> deployedDirectory = getDeployedDirectory();
        if (deployedDirectory.isPresent() && deployedDirectory.get().exists()) {
            File projectDirectory = CurrentPaths.getInstance().getProjectFile();
            String toPath = current.getAbsolutePath()
                    .replaceFirst(projectDirectory.getAbsolutePath(),
                            deployedDirectory.get().getAbsolutePath());
            toPath = toPath.replaceFirst(MAVEN_PATH, "");
            return Optional.of(new File(toPath));
        }
        return Optional.<File>empty();
    }

    protected Optional<File> getDeployedDirectory() {
        return new CurrentDeploymentModulePathExtractor().extract();
    }

}
