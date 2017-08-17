package com.dreamworks.corps;

import com.uber.jaeger.Configuration;
import com.uber.jaeger.Tracer;
import com.uber.jaeger.propagation.TextMapCodec;
import com.uber.jaeger.samplers.ProbabilisticSampler;
import io.opentracing.propagation.Format;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceOneApplication.class, args);
	}

}
