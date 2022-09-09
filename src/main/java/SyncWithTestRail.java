import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import helpers.Annotations;
import helpers.HttpHelper;
import models.TestCaseModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtImportDirective;
import org.jetbrains.kotlin.psi.KtPsiFactory;
import org.jetbrains.kotlin.resolve.ImportPath;
import org.jetbrains.uast.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncWithTestRail extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiElement element = e.getData(PlatformDataKeys.PSI_ELEMENT);
        UElement uElement = UastContextKt.toUElement(element);
        UClass uClass = (UClass) uElement;
        UMethod[] methods = uClass.getMethods();
        AtomicInteger displayNameCounter = new AtomicInteger(0);
        if (methods != null && methods.length > 0) {
            KtClass ktClass = (KtClass) element;
            addImport(ktClass, Annotations.DISPLAY_NAME_LONG);
            Arrays.stream(uClass.getMethods())
                    .filter(m -> m.hasAnnotation(Annotations.LINK_LONG))
                    .forEach(m -> {
                        syncClass(m);
                        displayNameCounter.getAndIncrement();
                    });
            String message = String.format("Обновлено/Добавлено %s @DisplayName", displayNameCounter.get());
            Messages.showMessageDialog(message, "Qa Bcs Plugin", Messages.getInformationIcon());

        }
    }

    private void addImport(KtClass ktClass, String annotation) {
        KtFile ktFile = ktClass.getContainingKtFile();
        List<KtImportDirective> imports = Objects.requireNonNull(ktFile.getImportList()).getImports();
        for (KtImportDirective anImport : imports) {
            if (anImport.getImportPath().getPathStr().equals(annotation)) {
                return;
            }
        }
        KtPsiFactory psiFactory = new KtPsiFactory(ktClass.getProject());
        KtImportDirective importDirective = psiFactory.createImportDirective(ImportPath.fromString(Annotations.DISPLAY_NAME_LONG));

        CommandProcessor.getInstance().executeCommand(ktClass.getProject(), () ->
                ApplicationManager.getApplication().runWriteAction(() -> {
                            ktFile.addBefore(importDirective, imports.get(0));
                        }
                ), "Add Import", null);
    }

    private String getDisplayNameAnnotation(UAnnotation linkAnnotation) {
        String url = (String) Objects.requireNonNull(linkAnnotation.findAttributeValue("url")).evaluate();
        String id = url.substring(url.lastIndexOf('/') + 1);
        TestCaseModel testCase = HttpHelper.getTestCaseInfo(id);
        String displayName = testCase.title;
        if (displayName.contains("\"")) {
            displayName = displayName.replaceAll("\"", "'");
        }
        String displayNameAnnotation = String.format(Annotations.DISPLAY_NAME, displayName);
        return displayNameAnnotation;
    }

    public void syncClass(UMethod method) {
        UAnnotation linkAnnotation = method.findAnnotation(Annotations.LINK_LONG);
        UAnnotation dispNameAnn = method.findAnnotation(Annotations.DISPLAY_NAME_LONG);
        CommandProcessor.getInstance().executeCommand(method.getProject(), () ->
                ApplicationManager.getApplication().runWriteAction(() -> {
                            if (dispNameAnn != null) {
                                dispNameAnn.getSourcePsi().delete();
                            }
                            PsiAnnotation psiAnnotation = createAnnotation(getDisplayNameAnnotation(linkAnnotation), method.getSourcePsi());
                            method.getSourcePsi().addBefore(psiAnnotation, linkAnnotation.getSourcePsi());

                        }
                ), "Sync Class With TestRail", null);
    }

    public static PsiAnnotation createAnnotation(final String annotation, final PsiElement context) {
        final PsiElementFactory factory = PsiElementFactory.getInstance(context.getProject());
        return factory.createAnnotationFromText(annotation, context);
    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
