package ru.stqa.mantis.managers;

import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;

public class HttpHelper extends HelperBase {
    private OkHttpClient client;

    public HttpHelper(ApplicationManager manager) {
        super(manager);
        this.client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(new CookieManager())).build();
    }

    public void login(String username, String password) {
        RequestBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(manager.getProperty("web.baseUrl") + "/login.php")
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoggedIn() {
        Request request = new Request.Builder()
                .url(manager.getProperty("web.baseUrl") + "/account_page.php")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
            String responseBody=response.body().string();
            return responseBody.contains("user-info");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}