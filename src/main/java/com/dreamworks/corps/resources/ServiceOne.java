package com.dreamworks.corps.resources;

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
    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/service/one/{name}", method = RequestMethod.GET)
    public ResponseEntity sample(@PathVariable String name) {
        return ResponseEntity.ok(new HashMap(){{
            put("name", name);
        }});
    }

    @RequestMapping(value = "/service/one/{name}/call", method = RequestMethod.GET)
    public ResponseEntity call(@PathVariable String name) {
        final Map<String, String> response = restTemplate.getForObject(serviceTwoUrl + "/" + name, Map.class);
        return ResponseEntity.ok(new HashMap(){{
            put("name", name);
            put("message", response);
        }});
    }
}

