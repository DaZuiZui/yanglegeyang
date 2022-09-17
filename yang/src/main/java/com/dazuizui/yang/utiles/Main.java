package com.dazuizui.yang.utiles;

import io.netty.handler.codec.http.HttpResponseStatus;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import reactor.netty.http.client.HttpClient;

/**
 * @author dazui
 * 此代码只提供学习，以上传到dazui的github 提供学习
 * 此代码最终解释权归 dazui所有
 */
public class Main {
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        System.out.println("请勿传播，此代码只提供学习");
        Executors.newScheduledThreadPool(7).scheduleAtFixedRate(new DO(), 0, 1, TimeUnit.MILLISECONDS);
        System.out.println("by dazui from www.dazuizui.com");

    }

    public static class DO implements Runnable {
        @Override
        public void run() {
            try {
                dazui666();
            } catch (Exception e) {
                System.out.println("error! " + e.getMessage());
            }
        }

        private static void dazui666() {
            HttpClient.create()
                .baseUrl("https://cat-match.easygame2021.com")
                .headers((headers) -> {
                    headers.add("Host", "cat-match.easygame2021.com");
                    headers.add("Connection", "close");
                    headers.add("xweb_xhr", "1");
                    headers.add("t",
                        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2OTQzMDgwMzQsIm5iZiI6MTY2MzIwNTgzNCwiaWF0IjoxNjYzMjA0MDM0LCJqdGkiOiJDTTpjYXRfbWF0Y2g6bHQxMjM0NTYiLCJvcGVuX2lkIjoiIiwidWlkIjo2MzQ0Mzk1NCwiZGVidWciOiIiLCJsYW5nIjoiIn0.L4Gtx3a0BsKsHwvwJU0RRB8_WFt_5J8laUJepk2nwKc");
                    headers.add("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36 MicroMessenger/7.0.4.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF");
                    headers.add("Content-Type", "application/json");
                    headers.add("Accept", "*/*");
                    headers.add("Sec-Fetch-Site", "cross-site");
                    headers.add("Sec-Fetch-Mode", "cors");
                    headers.add("Sec-Fetch-Dest", "empty");
                    headers.add("Referer", "https,//servicewechat.com/wx141bfb9b73c970a9/17/index.html");
                    headers.add("Accept-Language", "en-us,en");
                    headers.add("Accept-Encoding", "gzip, deflate");
                    headers.add("Content-Length", "2");
                })
                .responseTimeout(Duration.ofMillis(100))
                .doOnRequest(
                    (req, con) -> {
                        System.out.println("request!～～～～～～");
                    })
                .doOnRequestError(
                    (req, e) -> {
                        System.out.println("request error! " + e.getMessage());
                    })
                .doOnResponseError(
                    (response, e) -> {
                        System.out.println("response error! " + e.getMessage());
                    })
                .doAfterResponseSuccess(
                    (response, conn) -> {
                        int count = 0;
                        if (HttpResponseStatus.OK.equals(response.status())) {
                            count = Main.count.incrementAndGet();
                        }
                        System.out.println("success! st [" + response.status() + "], count [" + count + "]");
                    })
                .get()
                .uri("/sheep/v1/game/game_over?rank_score=1&rank_state=1&rank_time=1314&rank_role=1&skin=1")
                .response()
                .block();
        }
    }
}