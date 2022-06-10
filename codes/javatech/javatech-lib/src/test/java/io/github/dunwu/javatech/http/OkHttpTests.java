package io.github.dunwu.javatech.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * okhttp API 测试
 *
 * @author Zhang Peng
 * @date 2022-06-09
 */
@Slf4j
public class OkHttpTests {

    OkHttpClient client = new OkHttpClient();

    @Test
    @DisplayName("OkHttp 同步 Get 请求")
    public void testSyncGet() throws IOException {
        String ip = "127.0.0.1";
        String url = "http://realip.cc/?ip=" + ip;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            System.out.println("请求结果：" + json);
        }
    }

    @Test
    @DisplayName("OkHttp 异步 Get 请求")
    public void testAsyncGet() {
        String ip = "127.0.0.1";
        String url = "http://realip.cc/?ip=" + ip;
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                String json = response.body().string();
                System.out.println("请求结果：" + json);
            }
        });
    }

}
