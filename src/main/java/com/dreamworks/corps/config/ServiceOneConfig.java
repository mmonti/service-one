package com.dreamworks.corps.config;

import com.dreamworks.corps.repository.ServiceTwoRepository;
import com.uber.jaeger.Tracer;
import com.uber.jaeger.propagation.TextMapCodec;
import com.uber.jaeger.samplers.ProbabilisticSampler;
import io.opentracing.propagation.Format;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mmonti on 8/17/17.
 */
@EnableFeignClients(clients = ServiceTwoRepository.class)
@Configuration
public class ServiceOneConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public io.opentracing.Tracer jaegerTracer(){
        Tracer.Builder tracerBuilder =  new com.uber.jaeger.Configuration("service-one",
                new com.uber.jaeger.Configuration.SamplerConfiguration(ProbabilisticSampler.TYPE, 1),
				new com.uber.jaeger.Configuration.ReporterConfiguration())
//                new Configuration.ReporterConfiguration(Boolean.TRUE,"10.35.16.64", 6831, 1000, 100))
                .getTracerBuilder();

        TextMapCodec httpCodec = new TextMapCodec(false);
        tracerBuilder.registerInjector(Format.Builtin.HTTP_HEADERS, httpCodec);

        return tracerBuilder.build();
    }
}
