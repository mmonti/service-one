package com.dreamworks.corps.repository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by mmonti on 8/17/17.
 */
@FeignClient(url = "${service.two.url}", name = "service-two")
public interface ServiceTwoRepository {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    Map<String, Object> name(@PathVariable(value = "name") final String name);

}
