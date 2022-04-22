package com.example.androideksamenkohortsyv;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Client {


    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("image","/C:/Users/jensj/Downloads/pikachu.png",
                    RequestBody.create(MediaType.parse("application/octet-stream"),
                            new File("/C:/Users/jensj/Downloads/pikachu.png")))
            .build();
    Request request = new Request.Builder()
            .url("http://api-edu.gtl.ai/api/v1/imagesearch/upload")
            .method("POST", body)
            .build();
    Response response;

    {
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
