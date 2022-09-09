package helpers;

import com.google.gson.Gson;
import com.intellij.openapi.ui.Messages;
import models.TestCaseModel;
import okhttp3.*;

import java.io.IOException;

import static helpers.EncodeString.getBasicAuth;

public class HttpHelper {
    private static OkHttpClient client = new OkHttpClient().newBuilder().build();
    private static TrProperties properties = TrProperties.getProperties();

    public static TestCaseModel getTestCaseInfo(String caseId) {
        Request request = new Request.Builder()
                .url(properties.getBaseUrl() + "get_case/" + caseId)
                .get()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", getBasicAuth(properties.getUserName(), properties.getPassword()))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful() && response.body().contentLength() == 0) {
            Messages.showMessageDialog("Response code: " + response.code() + "Message: " + response.message(), "QA Bcs Plugin", Messages.getErrorIcon());
            throw new NullPointerException("Response code: " + response.code() + "Message: " + response.message());
        }
        String content = null;
        try {
            content = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestCaseModel gson = new Gson().fromJson(content, TestCaseModel.class);
        return gson;
    }

    public static void changeAutomationStatus(String caseId, String status) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create("{\"custom_auto\": " + status + "}", mediaType);
        Request request = new Request.Builder()
                .url(properties.getBaseUrl() + "update_case/" + caseId)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", getBasicAuth(properties.getUserName(), properties.getPassword()))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) {
            Messages.showMessageDialog("Response code: " + response.code() + "Message: " + response.message(), "QA Bcs Plugin", Messages.getErrorIcon());
            throw new NullPointerException("Response code: " + response.code() + "Message: " + response.message());
        }
    }
}


