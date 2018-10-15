package com.chad.hlife.gson;

import com.chad.hlife.entity.mob.OilPriceInfo;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class OilPricesTypeAdapter extends TypeAdapter<OilPriceInfo> {

    @Override
    public void write(JsonWriter out, OilPriceInfo value) throws IOException {
    }

    @Override
    public OilPriceInfo read(JsonReader in) throws IOException {
        return null;
    }
}
