//package micro.support.zipkin_server.config;
//
//import org.springframework.cloud.stream.annotation.Input;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.SubscribableChannel;
//
//public interface ZipkinProcessor {
//    String INPUT = "input";
//    String OUTPUT = "output";
//
//    @Input(INPUT)
//    SubscribableChannel input();
//
//    @Output(OUTPUT)
//    MessageChannel output();
//}
