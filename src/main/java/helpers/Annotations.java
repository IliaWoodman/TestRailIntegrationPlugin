package helpers;

public class Annotations {
    public static final String LINK_LONG = "io.qameta.allure.Link"; // TODO поменять потом на нижнию
//    public static final String LINK_LONG = "io.qameta.allure.kotlin.Link";
    public static final String LINK = "@Link(name = \"Тест-кейс\", url = \"%s\")";

    public static final String DISPLAY_NAME_LONG = "io.qameta.allure.junit4.DisplayName";
//    public static final String DISPLAY_NAME_LONG = "io.qameta.allure.kotlin.junit4.DisplayName";
    public static final String DISPLAY_NAME = "@DisplayName(\"%s\")";

    public static final String REGRESSION_LONG = "com.kaspersky.kaspresso.annotations.Regression";
    public static final String REGRESSION = "@Regression";

}
