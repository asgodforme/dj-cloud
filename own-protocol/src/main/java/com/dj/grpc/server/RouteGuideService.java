package com.dj.grpc.server;

import com.dj.grpc.routeguide.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class RouteGuideService extends RouteGuideGrpc.RouteGuideImplBase {

    private static final Logger logger = LoggerFactory.getLogger(RouteGuideService.class);

    private final Collection<Feature> features;

    private final ConcurrentMap<Point, List<RouteNote>> routeNotes = new ConcurrentHashMap<>();

    public RouteGuideService(Collection<Feature> features) {
        this.features = features;
    }

    @Override
    public void getFeature(Point request, StreamObserver<Feature> responseObserver) {
        responseObserver.onNext(checkFeature(request));
        responseObserver.onCompleted();
    }

    private Feature checkFeature(Point location) {
        Optional<Feature> first = features.stream().filter(feature -> feature.getLocation().getLatitude() == location.getLatitude()
                && feature.getLocation().getLongitude() == location.getLongitude()).findFirst();
        return first.orElseGet(() -> Feature.newBuilder().setName("").setLocation(location).build());
    }

    @Override
    public void listFeature(Rectangle request, StreamObserver<Feature> responseObserver) {
        features.stream().forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Point> recordRoute(StreamObserver<RouteSummary> responseObserver) {
        return new StreamObserver<Point>() {
            int pointCount;
            int featureCount;
            int distance;
            Point previous;
            final long startTime = System.nanoTime();

            @Override
            public void onNext(Point point) {
                pointCount++;
                featureCount++;
                distance += 1;
            }

            @Override
            public void onError(Throwable t) {
                logger.error("recordRoute cancelled");
            }

            @Override
            public void onCompleted() {
                long seconds = NANOSECONDS.toSeconds(System.nanoTime() - startTime);
                responseObserver.onNext(RouteSummary.newBuilder().setPointCount(pointCount)
                        .setFeatureCount(featureCount).setDistance(distance)
                        .setElapsedTime((int) seconds).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<RouteNote> routeChat(StreamObserver<RouteNote> responseObserver) {
        return new StreamObserver<RouteNote>() {
            @Override
            public void onNext(RouteNote note) {
                List<RouteNote> notes = getOrCreateNotes(note.getLocation());

                for (RouteNote prevNote : notes.toArray(new RouteNote[0])) {
                    responseObserver.onNext(prevNote);
                }

                notes.add(note);
            }

            @Override
            public void onError(Throwable t) {
                logger.error("routeChat cancelled");
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    private List<RouteNote> getOrCreateNotes(Point location) {
        List<RouteNote> notes = Collections.synchronizedList(new ArrayList<RouteNote>());
        List<RouteNote> prevNotes = routeNotes.putIfAbsent(location, notes);
        return prevNotes != null ? prevNotes : notes;
    }
}
