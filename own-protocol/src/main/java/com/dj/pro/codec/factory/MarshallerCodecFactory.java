package com.dj.pro.codec.factory;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;
import org.jboss.marshalling.serial.SerialMarshallerFactory;

import java.io.IOException;

public class MarshallerCodecFactory {

    private static final MarshallerFactory marshallerFactory = new SerialMarshallerFactory();

    public static Marshaller buildMarshaller() throws IOException {
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        return marshallerFactory.createMarshaller(configuration);
    }

    public static Unmarshaller buildUnmarshaller() throws IOException {
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        return marshallerFactory.createUnmarshaller(configuration);
    }
}
