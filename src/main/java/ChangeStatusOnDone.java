import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import helpers.HttpHelper;
import org.jetbrains.annotations.NotNull;

public class ChangeStatusOnDone extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor data = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = data.getSelectionModel().getSelectedText();
        if (selectedText == null) {
            Messages.showMessageDialog("Нужно выделить айди тест кейса", "Qa Bcs Plugin", Messages.getWarningIcon());
            return;
        }
        HttpHelper.changeAutomationStatus(selectedText, "3");
        NotificationGroup notificationGroup = new NotificationGroup("Tr plugin notification", NotificationDisplayType.BALLOON);
        notificationGroup.createNotification("Qa Bcs Plugin", String.format("Тест кейс - %s\nИзменен на 'Done'", selectedText), NotificationType.INFORMATION, null).notify(event.getProject());
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
