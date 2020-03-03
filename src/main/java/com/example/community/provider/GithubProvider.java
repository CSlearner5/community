package com.example.community.provider;

import com.alibaba.fastjson.JSON;
import com.example.community.dto.AccessTokenDTO;
import com.example.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;

@Component
public class GithubProvider {

    /**
     * 向GitHub请求令牌
     * 利用okhttp架包-Post
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String temp = response.body().string();
            //System.out.println(temp);
            String token = temp.split("&")[0].split("=")[1];
            //System.out.println(token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息-Get
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String temp = response.body().string();
            GithubUser githubUser = JSON.parseObject(temp, GithubUser.class);
            return githubUser;
        } catch(SocketException e) {
            System.out.println("[ERROR]:Connection Reset.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
