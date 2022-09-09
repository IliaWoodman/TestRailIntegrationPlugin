import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import helpers.HttpHelper;
import models.TestCaseModel;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
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

    private static final Map<Integer, String> team = new HashMap<>();


    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        team.put(2, "Fargo");
        team.put(3, "Flex");
        team.put(4, "Dobs");
        team.put(5, "Hardcore");
        team.put(6, "Trade");
        team.put(7, "Vector");
        team.put(8, "Nomads");
        team.put(9, "Fintarget");
        team.put(11, "Platform");
        team.put(12, "Страхование жизни");
        team.put(13, "Chats");
        team.put(14, "Start");
        team.put(15, "Delta");
        team.put(16, "Gamma");
        team.put(17, "AlphaCom");
        team.put(19, "AlphaDoc");
        team.put(22, "Markets");

        Editor data = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = data.getSelectionModel().getSelectedText();
        if (selectedText == null) {
            Messages.showMessageDialog("Нужно выделить айди тест кейса", "Qa Bcs Plugin", Messages.getWarningIcon());
            return;
        }
        TestCaseModel testCaseModel = HttpHelper.getTestCaseInfo(selectedText);

        String message = String.format("Тест кейс с айди - '%s'\nНазвание - '%s'\nСтатус автоматизации - '%s'\nПриоритет - '%s'\nКоманда - '%s'",
                selectedText, testCaseModel.title, automationStatus.get(testCaseModel.custom_auto), priority.get(testCaseModel.priority_id), team.get(testCaseModel.custom_team)
        );
        Messages.showMessageDialog(message, "Qa Bcs Plugin", Messages.getInformationIcon());
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
