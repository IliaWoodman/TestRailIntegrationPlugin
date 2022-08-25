//import com.intellij.openapi.actionSystem.AnAction;
//import com.intellij.openapi.actionSystem.AnActionEvent;
//import com.intellij.openapi.actionSystem.PlatformDataKeys;
//import com.intellij.openapi.application.ApplicationManager;
//import com.intellij.openapi.command.CommandProcessor;
//import com.intellij.openapi.project.Project;
//import com.intellij.psi.*;
//import com.intellij.psi.codeStyle.JavaCodeStyleManager;
//import com.intellij.psi.search.GlobalSearchScope;
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.kotlin.psi.*;
//import org.jetbrains.uast.*;
//import org.jetbrains.uast.kotlin.declarations.KotlinUMethod;
//
//import java.util.List;
//import java.util.Optional;
//
//;
//@Deprecated
//public class AddDisplayName extends AnAction {
//    String LINK = "io.qameta.allure.Link";
//    String NAME = "io.qameta.allure.junit4.DisplayName";
//
////    void write(Project project, Runnable runnable, String name, Object groupId) {
////        CommandProcessor.getInstance().executeCommand(project, runnable, name, groupId);
////    }
////
////    static void write2(Project project, Runnable runnable) {
////
////
////        CommandProcessor.getInstance().executeCommand(project,
////                () -> ApplicationManager.getApplication().runWriteAction(runnable),
////                "name", null);
////    }
//
//    @Override
//    public void actionPerformed(@NotNull AnActionEvent e) {
//
//
//
//
//
//
////        if (uMethod.hasAnnotation(LINK)){
////            KtNamedFunction function = (KtNamedFunction) uMethod;
////            KotlinUMethod method = (KotlinUMethod) uMethod;
////
////            System.out.println(function.getName());
////        }
//
////        write2(uMethod.getProject(), () -> {
////            uMethod.add(uMethod);
////            uMethod.add(uMethod);
////        });
////        CommandProcessor.getInstance().executeCommand(uMethod.getProject(), () ->
////                ApplicationManager.getApplication().runWriteAction(() -> {
////                    uMethod.add(createAnnotation("@DisplayName()", uClass));
////                }), "Adding Display Name Annotation", null);
////        System.out.println("--------------------");
//
//
////        if (element instanceof KtClass) {
////            KtClass ktClass = (KtClass) element;
////            List<KtNamedFunction> functions = ktClass.getBody().getFunctions();
////            for (KtNamedFunction func: functions){
////                System.out.println(func.getName());
////            }
////        }
//        PsiElement element = e.getData(PlatformDataKeys.PSI_ELEMENT);
//        UElement uElement = UastContextKt.toUElement(element);
//        UClass uClass = (UClass) uElement;
//        UMethod[] methods = uClass.getMethods();
//        UMethod uMethod = methods[0];
//        KotlinUMethod method = (KotlinUMethod) uMethod;
//        KtClass psiClass = (KtClass) element;
//////        annotationEntry.valueArguments.firstOrNull { it.getArgumentName()?.asName?.asString() == name }?.getArgumentExpression()
////
//        CommandProcessor.getInstance().executeCommand(psiClass.getProject(), () ->
//                ApplicationManager.getApplication().runWriteAction(() -> {
//                    KtNamedFunction ktNamedFunction = psiClass.getBody().getFunctions().get(0);
//                    KtPsiFactory psiFactory = new KtPsiFactory(psiClass.getProject());
//
//
//
//                    KtFile containingKtFile = psiClass.getContainingKtFile();
//                    List<KtImportDirective> imports = containingKtFile.getImportList().getImports();
//                    imports.forEach(i -> {
//                        System.out.println(i.getImportedName().asString());
//                        System.out.println(i.getImportPath().getPathStr()); /// this
//                        System.out.println(i.getImportPath().toString());
//                        System.out.println("-----------");
//                    });
//
//                    System.out.println(containingKtFile.getName());
//                    String trDisplayName = "@DisplayName(\"asfaf\")";
//                    PsiAnnotation annotation = createAnnotation(trDisplayName, ktNamedFunction);
//                    UAnnotation uAnnotation = method.getUAnnotations().get(0);
//                    uMethod.getSourcePsi().addAfter(annotation,uAnnotation.getSourcePsi());
//
////                    KtImportDirective importDirective = psiFactory.createImportDirective(ImportPath.fromString("io.qameta.allure.junit4.DisplayName"));
////
////                    containingKtFile.addAfter(importDirective, ktImportDirective);
////                    ktNamedFunction.getModifierList().addBefore(annotation, ktNamedFunction);
//                }), "Adding Display Name Annotation", null);
//    }
//
////    @Override
////    public void actionPerformed(@NotNull AnActionEvent e) {
////
////        PsiElement element = e.getData(PlatformDataKeys.PSI_ELEMENT);
////        KtClass psiClass = (KtClass) element;
////                    CommandProcessor.getInstance().executeCommand(psiClass.getProject(), () ->
////                    ApplicationManager.getApplication().runWriteAction(() -> {
////                        KtNamedFunction ktNamedFunction = psiClass.getBody().getFunctions().get(0);
//////                        io.qameta.allure.junit4.DisplayName
////                        KtPsiFactory psiFactory = new KtPsiFactory(psiClass.getProject());
////                        KtFile containingKtFile = psiClass.getContainingKtFile();
////                        KtImportDirective ktImportDirective = containingKtFile.getImportList().getImports().get(0);
////                        System.out.println(containingKtFile.getName());
////                        String trDisplayName = "@DisplayName(\"asfaf\")";
////                        PsiAnnotation annotation = createAnnotation(trDisplayName, ktNamedFunction);
////                        KtImportDirective importDirective = psiFactory.createImportDirective(ImportPath.fromString("io.qameta.allure.junit4.DisplayName"));
////                        containingKtFile.addAfter(importDirective, ktImportDirective);
////                        ktNamedFunction.getModifierList().addBefore(annotation, ktNamedFunction);
//////                        addImport(psiClass.getContainingFile(), "io.qameta.allure.junit4.DisplayName");
////
//////                        psiClass.getBody().getFunctions().get(0).delete();
////                    }), "Adding Display Name Annotation", null);
////
//////        PsiMethod method = psiClass.getMethods()[0];
//////        PsiAnnotation annotation = method.getAnnotation("io.qameta.allure.Link");
//////        System.out.println(annotation.findAttributeValue("url"));
//////        annotation.delete();
////    }
//
//    public static PsiAnnotation createAnnotation(final String annotation, final PsiElement context) {
//        final PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(context.getProject());
//        return factory.createAnnotationFromText(annotation, context);
//    }
//
//    public static void optimizeImports(final PsiFile file) {
//        if (file instanceof PsiJavaFile) {
//            optimizeImports((PsiJavaFile) file);
//        }
//    }
//
//    public static void optimizeImports(final PsiJavaFile file) {
//        JavaCodeStyleManager.getInstance(file.getProject()).shortenClassReferences(file);
//        JavaCodeStyleManager.getInstance(file.getProject()).removeRedundantImports(file);
//    }
//
//    public static void addImport(final PsiFile file, final String qualifiedName) {
//        if (file instanceof PsiJavaFile) {
//            addImport((PsiJavaFile) file, qualifiedName);
//        }
//    }
//
//    public static void addImport(final PsiJavaFile file, final String qualifiedName) {
//        final Project project = file.getProject();
//
//        Optional<PsiClass> possibleClass = Optional.ofNullable(JavaPsiFacade.getInstance(project)
//                .findClass(qualifiedName, GlobalSearchScope.everythingScope(project)));
//        possibleClass.ifPresent(psiClass -> JavaCodeStyleManager.getInstance(project).addImport(file, psiClass));
//    }
//
//
//    @Override
//    public boolean isDumbAware() {
//        return super.isDumbAware();
//    }
//
//}
////            CommandProcessor.getInstance().executeCommand(element.getProject(), () ->
////                    ApplicationManager.getApplication().runWriteAction(() -> {
////                    addImport(testMethod.getContainingFile(), NAME_ANNOTATION);
////                    testMethod.getModifierList().addAfter(annotation, testM);
////                    optimizeImports(testMethod.getContainingFile());
////                    }), "Adding Display Name Annotation", null);