package com.chad.hlife.entity.mob;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class OilPricesTypeAdapter extends TypeAdapter<OilPricesInfo> {

    @Override
    public void write(JsonWriter out, OilPricesInfo value) throws IOException {

    }

    @Override
    public OilPricesInfo read(JsonReader in) throws IOException {
        return null;
    }
}
