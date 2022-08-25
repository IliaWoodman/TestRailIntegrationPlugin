import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import helpers.HttpHelper;
import models.TestCaseModel;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class GetTestCaseStatus extends AnAction {
   private static final Map<Integer, String> automationStatus = Map.of(
            1, "None",
            2, "In progress",
            3, "Done",
            4, "Need to improve",
            5, "Cannot be automated"
    );
   private static final Map<Integer, String> priority = Map.of(
            1, "Low",
            2, "Medium",
            3, "High",
            4, "Critical",
            5, "Blocker"
    );

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        Editor data = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = data.getSelectionModel().getSelectedText();
        if (selectedText == null) {
            Messages.showMessageDialog("Нужно выделить айди тест кейса", "Qa Bcs Plugin", Messages.getWarningIcon());
            return;
        }
        TestCaseModel testCaseModel = HttpHelper.getTestCaseInfo(selectedText);

        String message = String.format("Тест кейс с айди - '%s'\nНазвание - '%s'\nСтатус автоматизации - '%s'\nПриоритет - '%s'",
                selectedText, testCaseModel.title, automationStatus.get(testCaseModel.custom_auto), priority.get(testCaseModel.priority_id)
        );
        Messages.showMessageDialog(message, "Qa Bcs Plugin", Messages.getInformationIcon());
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
