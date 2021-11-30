package com.dj.cloud.test.undertow;

public class XNIOHelloWorldServer {
    public static void main(String[] args) {
//        Xnio xnio = Xnio.getInstance();
//
//        XnioWorker worker = xnio.createWorker(OptionMap.builder()
//                .set(Options.WORKER_IO_THREADS, ioThreads)
//                .set(Options.WORKER_TASK_CORE_THREADS, workerThreads)
//                .set(Options.WORKER_TASK_MAX_THREADS, workerThreads)
//                .set(Options.TCP_NODELAY, true)
//                .getMap());
//
//        OptionMap socketOptions = OptionMap.builder()
//                .set(Options.WORKER_IO_THREADS, ioThreads)
//                .set(Options.TCP_NODELAY, true)
//                .set(Options.REUSE_ADDRESSES, true)
//                .getMap();
//
//        Pool<ByteBuffer> buffers = new ByteBufferSlicePool(BufferAllocator.DIRECT_BYTE_BUFFER_ALLOCATOR,bufferSize, bufferSize * buffersPerRegion);
//
//
//        if (listener.type == ListenerType.AJP) {
//            AjpOpenListener openListener = new AjpOpenListener(buffers, serverOptions, bufferSize);
//            openListener.setRootHandler(rootHandler);
//            ChannelListener<AcceptingChannel<StreamConnection>> acceptListener = ChannelListeners.openListenerAdapter(openListener);
//            AcceptingChannel<? extends StreamConnection> server = worker.createStreamConnectionServer(new InetSocketAddress(Inet4Address.getByName(listener.host), listener.port), acceptListener, socketOptions);
//            server.resumeAccepts();
//        } else if (listener.type == ListenerType.HTTP) {
//            HttpOpenListener openListener = new HttpOpenListener(buffers, OptionMap.builder().set(UndertowOptions.BUFFER_PIPELINED_DATA, true).addAll(serverOptions).getMap(), bufferSize);
//            openListener.setRootHandler(rootHandler);
//            ChannelListener<AcceptingChannel<StreamConnection>> acceptListener = ChannelListeners.openListenerAdapter(openListener);
//            AcceptingChannel<? extends StreamConnection> server = worker.createStreamConnectionServer(new InetSocketAddress(Inet4Address.getByName(listener.host), listener.port), acceptListener, socketOptions);
//            server.resumeAccepts();
//        } else if (listener.type == ListenerType.HTTPS){
//            HttpOpenListener openListener = new HttpOpenListener(buffers, OptionMap.builder().set(UndertowOptions.BUFFER_PIPELINED_DATA, true).addAll(serverOptions).getMap(), bufferSize);
//            openListener.setRootHandler(rootHandler);
//            ChannelListener<AcceptingChannel<StreamConnection>> acceptListener = ChannelListeners.openListenerAdapter(openListener);
//            XnioSsl xnioSsl;
//            if(listener.sslContext != null) {
//                xnioSsl = new JsseXnioSsl(xnio, OptionMap.create(Options.USE_DIRECT_BUFFERS, true), listener.sslContext);
//            } else {
//                xnioSsl = xnio.getSslProvider(listener.keyManagers, listener.trustManagers, OptionMap.create(Options.USE_DIRECT_BUFFERS, true));
//            }
//            AcceptingChannel <SslConnection> sslServer = xnioSsl.createSslConnectionServer(worker, new InetSocketAddress(Inet4Address.getByName(listener.host), listener.port), (ChannelListener) acceptListener, socketOptions);
//            sslServer.resumeAccepts();
//        }
    }
}
