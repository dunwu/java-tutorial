package io.github.dunwu.javatool.monitor.zipkin;

import brave.CurrentSpanCustomizer;
import brave.SpanCustomizer;
import brave.Tracing;
import brave.context.log4j2.ThreadContextScopeDecorator;
import brave.http.HttpTracing;
import brave.httpclient.TracingHttpClientBuilder;
import brave.propagation.B3Propagation;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.ThreadLocalCurrentTraceContext;
import brave.spring.webmvc.DelegatingTracingFilter;
import brave.spring.webmvc.SpanCustomizingAsyncHandlerInterceptor;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zipkin2.Span;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

/**
 * This adds tracing configuration to any web mvc controllers or rest template clients.
 *
 * <p>This is a {@link Initializer#getRootConfigClasses() root config class}, so the
 * {@linkplain DelegatingTracingFilter} added in {@link Initializer#getServletFilters()} can wire up properly.
 */
@Configuration
// Importing this class is effectively the same as declaring bean methods
@Import(SpanCustomizingAsyncHandlerInterceptor.class)
public class TracingConfiguration extends WebMvcConfigurerAdapter {

    /** Configuration for how to send spans to Zipkin */
    @Bean
    Sender sender() {
        return OkHttpSender.create("http://127.0.0.1:9411/api/v2/spans");
    }

    /** Configuration for how to buffer spans into messages for Zipkin */
    @Bean
    AsyncReporter<Span> spanReporter() {
        return AsyncReporter.create(sender());
    }

    /** Controls aspects of tracing such as the service name that shows up in the UI */
    @Bean
    Tracing tracing(@Value("${zipkin.service:brave-webmvc-example}") String serviceName) {
        return Tracing.newBuilder()
            .localServiceName(serviceName)
            .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user-name"))
            .currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                .addScopeDecorator(ThreadContextScopeDecorator.create()) // puts trace IDs into logs
                .build()
            )
            .spanReporter(spanReporter()).build();
    }

    /** Allows someone to add tags to a span if a trace is in progress. */
    @Bean
    SpanCustomizer spanCustomizer(Tracing tracing) {
        return CurrentSpanCustomizer.create(tracing);
    }

    /** Decides how to name and tag spans. By default they are named the same as the http method. */
    @Bean
    HttpTracing httpTracing(Tracing tracing) {
        return HttpTracing.create(tracing);
    }

    /** adds tracing to any underlying http client calls */
    @Bean
    HttpClient httpClient(HttpTracing httpTracing) {
        return TracingHttpClientBuilder.create(httpTracing).build();
    }

    @Autowired
    SpanCustomizingAsyncHandlerInterceptor serverInterceptor;

    /** adds tracing to the application-defined web controller */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverInterceptor);
    }

}
