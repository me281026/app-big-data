package com.app.data.clean.flink;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class StreamSerializer extends Serializer<Class> {


    @Override
    public void write(Kryo kryo, Output output, Class t) {
        kryo.writeClassAndObject(output, t);
    }

    @Override
    public Class read(Kryo kryo, Input input, Class t) {
        return  kryo.readClassAndObject(input).getClass();
    }
}
