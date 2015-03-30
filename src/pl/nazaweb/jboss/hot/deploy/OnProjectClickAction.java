package pl.nazaweb.jboss.hot.deploy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import org.netbeans.api.project.Project;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.NbBundle.Messages;

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

    private final Project context;

    public OnProjectClickAction(Project context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        boolean shouldAttachWatcher = Paths.getInstance().shouldAttachWatcher();
        File toAdd = new FileChooserBuilder("user-dir").setTitle("Pick deployed app directory").
                setDefaultWorkingDirectory(new File(System.getProperty("user.home")))
                .setApproveText("Open")
                .setDirectoriesOnly(true)
                .showOpenDialog();
        Paths.getInstance().setJbossPath(toAdd);
        Path path = java.nio.file.Paths.get(context.getProjectDirectory().getPath());
        Paths.getInstance().setProjectPath(path.toFile());
        if (shouldAttachWatcher) {
            try {
                new FileChangeWatcher(path).watch();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
