package com.dreamworks.corps.resources;

import com.dreamworks.corps.repository.ServiceTwoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmonti on 5/14/17.
 */
@RestController
public class ServiceOne {

    @Value(value = "${service.two.url}")
    private String serviceTwoUrl;

    private RestTemplate restTemplate;
    private ServiceTwoRepository serviceTwoRepository;

    @Autowired
    public ServiceOne(final RestTemplate restTemplate, final ServiceTwoRepository serviceTwoRepository) {
        this.restTemplate = restTemplate;
        this.serviceTwoRepository = serviceTwoRepository;
    }

    @RequestMapping(value = "/service/one/{name}", method = RequestMethod.GET)
    public ResponseEntity sample(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(new HashMap(){{
            put("service", ServiceOne.class.getCanonicalName());
        }});
    }

    @RequestMapping(value = "/service/one/{name}/rt", method = RequestMethod.GET)
    public ResponseEntity rt(@PathVariable(value = "name") final String name) {
        final Map<String, Object> response = restTemplate.getForObject(serviceTwoUrl + "/" + name, Map.class);
        return ResponseEntity.ok(new HashMap(){{
            put("service", ServiceOne.class.getCanonicalName());
            put("response", response);
        }});
    }

    @RequestMapping(value = "/service/one/{name}/feign", method = RequestMethod.GET)
    public ResponseEntity feign(@PathVariable String name) {
        final Map<String, Object> response = serviceTwoRepository.name(name);
        return ResponseEntity.ok(new HashMap(){{
            put("service", ServiceOne.class.getCanonicalName());
            put("response", response);
        }});
    }
}

