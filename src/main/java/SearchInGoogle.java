import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class SearchInGoogle extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Editor data = event.getData(PlatformDataKeys.EDITOR);
        String selectedText = data.getSelectionModel().getSelectedText();
        if (selectedText == null) {
            Messages.showMessageDialog("Нужно выделить какой то текст", "Qa Bcs Plugin", Messages.getWarningIcon());
            return;
        }
        System.out.println(selectedText);
        BrowserUtil.browse("https://www.google.ru/search?q=" + selectedText);
    }
    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
