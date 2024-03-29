package api_learning.gson;

import com.google.gson.Gson;
import test_data.models.LoginCred;

public class TestGson {

    public static void main(String[] args) {
        LoginCred loginCred = new LoginCred("user","12345678");

        Gson gson = new Gson();
        System.out.println(gson.toJson(loginCred));

        String loginCredJson = "{\"email\":\"user\",\"passsword\":\"12345678\"}\n";

        LoginCred convertFromJson = gson.fromJson(loginCredJson,LoginCred.class);
        System.out.println(convertFromJson);
    }
}
