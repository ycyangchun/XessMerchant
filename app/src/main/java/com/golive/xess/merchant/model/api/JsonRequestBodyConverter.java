package com.golive.xess.merchant.model.api;

import android.util.Log;

import com.golive.xess.merchant.utils.Base64Util;
import com.golive.xess.merchant.utils.Des3Util;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by YangChun .
 * on 2017/3/11.
 * 自定义请求RequestBody
 */

public class JsonRequestBodyConverter<T> implements Converter<T , RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */

    public JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        String data = null;
        Log.i("加密", "request中传递的json数据：" + value.toString());
        try {
            data = Base64Util.encode(Des3Util.getInstance(ApiService.SECRET_KEY, ApiService.SECRET_VALUE).encode(value.toString()));
        } catch (Exception e) {
            throw new IOException("Encryption failed");
        }
        Log.i("加密", "转化后的数据：" + data);
        adapter.write(jsonWriter, (T) data);
        jsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, data);
    }

}
