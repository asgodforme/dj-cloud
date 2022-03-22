package com.dj.grpc.server;

import com.dj.grpc.routeguide.Feature;
import com.dj.grpc.routeguide.FeatureOrBuilder;
import com.dj.grpc.routeguide.Point;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RouteGuideServer {

    private static final Logger logger = LoggerFactory.getLogger(RouteGuideServer.class);

    private final int port;

    private final Server server;

    private static final List<Feature> features = new ArrayList<>();
    static {
        features.add(Feature.newBuilder().setName("feature1").setLocation(Point.newBuilder().setLatitude(1).setLongitude(2).build()).build());
        features.add(Feature.newBuilder().setName("feature2").setLocation(Point.newBuilder().setLatitude(1).setLongitude(2).build()).build());
        features.add(Feature.newBuilder().setName("feature3").setLocation(Point.newBuilder().setLatitude(1).setLongitude(2).build()).build());
        features.add(Feature.newBuilder().setName("feature4").setLocation(Point.newBuilder().setLatitude(1).setLongitude(2).build()).build());
    }

    public RouteGuideServer(int port) {
        this.port = port;
        server = ServerBuilder.forPort(port).addService(new RouteGuideService(features)).build();
    }

    public void start() throws IOException {
        server.start();
        logger.info("server start on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    RouteGuideServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        RouteGuideServer server = new RouteGuideServer(8980);
        server.start();
        server.blockUntilShutdown();
    }
}
