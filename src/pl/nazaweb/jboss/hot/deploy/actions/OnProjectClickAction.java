package pl.nazaweb.jboss.hot.deploy.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import pl.nazaweb.jboss.hot.deploy.watcher.FileChangeWatcher;
import pl.nazaweb.jboss.hot.deploy.CurrentPaths;

@ActionID(
        category = "Project",
        id = "pl.nazaweb.jboss.hot.deploy.SomeAction"
)
@ActionRegistration(
        displayName = "#CTL_SomeAction"
)
@Messages("CTL_SomeAction=Start jboss js deploy")
@ActionReference(path = "Projects/Actions")
public final class OnProjectClickAction implements ActionListener {

    private final static String OPEN_DIALOG_TITLE = "Choose jboss directory";
    private final Project context;
    private Optional<File> jbossPath;
    private boolean shouldAttachWatcher;

    public OnProjectClickAction(Project context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        checkIfShouldAttachWatcher();
        chooseJbossFile();
        if (isJbossFileExists()) {
            setJbossPath();
            setProjectPath();
            startWatcher();
        } else {
            showNotChooseJbossFileAlert();
        }
    }

    protected void checkIfShouldAttachWatcher() {
        shouldAttachWatcher = CurrentPaths.getInstance().shouldAttachWatcher();
    }

    protected void chooseJbossFile() {
        File file = new FileChooserBuilder("user-dir")
                .setTitle(OPEN_DIALOG_TITLE).
                setDefaultWorkingDirectory(new File(System.getProperty("user.home")))
                .setApproveText("Open")
                .setDirectoriesOnly(true)
                .showOpenDialog();
        jbossPath = Optional.ofNullable(file);
    }

    protected boolean isJbossFileExists() {
        return jbossPath.isPresent() && jbossPath.get().exists();
    }

    private void setJbossPath() {
        CurrentPaths.getInstance().setJbossPath(jbossPath.get());
    }

    private void startWatcher() {
        if (shouldAttachWatcher) {
            try {
                Path webAppPath = CurrentPaths.getInstance().getProjectWatchPath();
                new FileChangeWatcher(webAppPath).watch();
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    private void setProjectPath() {
        CurrentPaths.getInstance().setProject(context);
    }

    private void showNotChooseJbossFileAlert() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
