package micro.core.product.config;

import brave.handler.SpanHandler;
import brave.Tracing;
import brave.propagation.ThreadLocalCurrentTraceContext;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerTracingConfig {

    /*@Bean
    public Tracer tracer() {
    	Sender sender = URLConnectionSender.create("http://localhost:9411/api/v2/spans");
    	Reporter<zipkin2.Span> reporter = AsyncReporter.create(sender);
    	Tracing tracing = Tracing.newBuilder()
    							.currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder().build())
    							.spanReporter(reporter)
    							.build();
    	return BraveTracer.create(tracing);
    }*/

}
