import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.psi.KtBlockCodeFragment;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtPsiFactory;
import org.jetbrains.uast.UElement;
import org.jetbrains.uast.UFile;
import org.jetbrains.uast.UastContextKt;


// IN PROGRESS
public class CreateClassAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiElement element = e.getData(PlatformDataKeys.PSI_ELEMENT);
        UElement uElement = UastContextKt.toUElement(element);
        UFile uFile = (UFile) uElement;
        KtFile file = (KtFile) element;

        CommandProcessor.getInstance().executeCommand(file.getProject(), () ->
                ApplicationManager.getApplication().runWriteAction(() -> {
                            String b = "import androidx.test.filters.MediumTest\n" +
                                    "import com.kaspersky.kaspresso.kaspresso.Kaspresso\n" +
                                    "import com.kaspersky.kaspresso.testcases.api.testcase.TestCase\n" +
                                    "import io.qameta.allure.android.runners.AllureAndroidJUnit4\n" +
                                    "import io.qameta.allure.kotlin.Epic\n" +
                                    "import io.qameta.allure.kotlin.Feature\n" +
                                    "import io.qameta.allure.kotlin.Owner\n" +
                                    "import io.qameta.allure.kotlin.Story\n" +
                                    "import main.helpers.AppSetup\n" +
                                    "import main.helpers.withCustomAllureSupport\n" +
                                    "import org.junit.Before\n" +
                                    "import org.junit.runner.RunWith\n" +
                                    "\n" +
                                    "@MediumTest\n" +
                                    "@RunWith(AllureAndroidJUnit4::class)\n" +
                                    "@Owner()\n" +
                                    "@Epic()\n" +
                                    "@Story()\n" +
                                    "@Feature()\n" +
                                    "class %s : AppSetup,\n" +
                                    "                  TestCase(kaspressoBuilder = Kaspresso.Builder.withCustomAllureSupport()) {\n" +
                                    "\n" +
                                    "    @Before\n" +
                                    "    fun before() = run {\n" +
                                    "    }\n" +
                                    "}";

                            System.out.println(file.getPackageFqName().asString());
                            System.out.println(file.getPackageFqName().asString());
                            System.out.println(file.getPackageFqName().asString());
                            System.out.println(file.getPackageDirective().getName());
                            System.out.println(file.getPackageDirective().getName());
                            System.out.println(file.getPackageDirective().getName());
                            System.out.println(file.getName().substring(0,file.getName().indexOf(".")));
                            System.out.println(file.getName().substring(0,file.getName().indexOf(".")));
                            System.out.println(file.getName().substring(0,file.getName().indexOf(".")));


//                            KtImportDirective importDirective = new KtPsiFactory(file.getProject()).createImportDirective(ImportPath.fromString("io.qameta.allure.junit4.DisplayName"));
//                            KtImportDirective importDirective2 = new KtPsiFactory(file.getProject()).createImportDirective(ImportPath.fromString("io.qameta.allure.Link"));
//                            KtImportDirective importDirective3 = new KtPsiFactory(file.getProject()).createImportDirective(ImportPath.fromString("org.junit.Test"));
//                            PsiElement newLine = new KtPsiFactory(file.getProject()).createNewLine();
//                            PsiElement whiteSpace = new KtPsiFactory(file.getProject()).createWhiteSpace();
//
////                            new KtPsiFactory(file.getProject()).createStringTemplate()
//                            KtBlockExpression block = new KtPsiFactory(file.getProject()).createBlock(b);
//                            KtClass bClass = new KtPsiFactory(file.getProject()).createClass(b);
                            KtBlockCodeFragment blockCodeFragment = new KtPsiFactory(file.getProject()).createBlockCodeFragment(String.format(b,file.getName().substring(0,file.getName().indexOf("."))), file.getContext());
                            file.add(blockCodeFragment);
//                            file.add(importDirective);
//                            file.add(importDirective2);
//                            file.add(importDirective3);
//                            file.add(newLine);
//                            file.add(whiteSpace);
//                            file.add(bClass);
                        }
                ), "Add Import", null);
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
