package mainpackage.view.login;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AuthorizationOnVkCom {
    private static TransportClient transportClient = HttpTransportClient.getInstance();
    private static VkApiClient vkApiClient = new VkApiClient(transportClient);
    private static UserActor actor;

    private static String ACCESS_TOKEN;
    private static Integer vk_id;
    private String vkFirstName;

    private String ip_h;
    private String lg_h;
    private String to;
    private String remixlhk;
    private String email;
    private String pass;

    private String linkAccept;

    public static UserActor getActor() {
        return actor;
    }

    public static VkApiClient getVkApiClient() {
        return vkApiClient;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVkFirstName() {
        return vkFirstName;
    }

    public void go() {
        try {
            authorizeAndGetCookieremixlhk();
            Thread.sleep(100);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            fullAuthorization();
            Thread.sleep(100);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            acceptAndGetToken();
            Thread.sleep(100);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        try {
            getVkFirstNameCurrentUser();
            Thread.sleep(100);
        } catch (IOException | InterruptedException | ClientException | ApiException e) {
            e.printStackTrace();
        }
    }

    private void authorizeAndGetCookieremixlhk() throws IOException {
        URL url = new URL("https://oauth.vk.com/authorize?client_id=6022600&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,wall,offline,groups,email&response_type=token&v=5.64&revoke=1");
        ArrayList<String> listHeaders = new ArrayList<>();
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        for (Map.Entry<String, List<String>> pair : conn.getHeaderFields().entrySet()) {
            listHeaders.addAll(pair.getValue());
        }

        for (String listi : listHeaders) {
            if (listi.contains("remixlhk")) {
                remixlhk = listi.split("=", 2)[1].split(";", 2)[0];
            }
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        while (in.ready()) {
            String temp = in.readLine();
            if (temp.contains("ip_h"))
                ip_h = temp.split("value=\"", 2)[1].split("\"")[0];
            if (temp.contains("lg_h"))
                lg_h = temp.split("value=\"", 2)[1].split("\"")[0];
            if (temp.contains("name=\"to\""))
                to = temp.split("value=\"", 2)[1].split("\"")[0];
        }

        in.close();
    }

    private void fullAuthorization() throws IOException {
        URL url = new URL("https://login.vk.com/?act=login&soft=1");
        Map<String, Object> params = new LinkedHashMap<>();

        params.put("_origin", "https://oauth.vk.com");
        params.put("email", email);
        params.put("expire", "0");
        params.put("ip_h", ip_h);
        params.put("lg_h", lg_h);
        params.put("pass", pass);
        params.put("to", to);
        params.put("Cookie", "remixlhk="+remixlhk);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);

        conn.getOutputStream().write(postDataBytes);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        while (in.ready()) {
            String temp = in.readLine();
            if (temp.contains("grant_access")) {
                linkAccept = temp.split("form method=\"post\" action=\"")[1].split("\"", 2)[0];
            }
        }
    }

    private void acceptAndGetToken() throws IOException {
        URL url = new URL(linkAccept);
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("Cookie", "remixlhk="+remixlhk);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String URL = conn.getURL().toString();

        ACCESS_TOKEN = URL.split("token=", 2)[1].split("&", 2)[0];
        vk_id = Integer.valueOf(URL.split("user_id=", 2)[1].split("&", 2)[0]);
    }

    private void getVkFirstNameCurrentUser() throws IOException, ClientException, ApiException {
        actor = new UserActor(vk_id, ACCESS_TOKEN);

        vkFirstName = vkApiClient.account().getProfileInfo(actor).execute().getFirstName();
    }
}
