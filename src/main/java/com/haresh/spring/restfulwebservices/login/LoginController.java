package com.haresh.spring.restfulwebservices.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LoginController {
    @GetMapping("/get-role")
    public MappingJacksonValue getLogin() {
        Cred cred = new Cred("Haresh", "Shaha", "Admin");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username","role");
        FilterProvider filters = new SimpleFilterProvider().addFilter("CredFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(cred);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/get-creds")
    public MappingJacksonValue getLogins() {
        List<Cred> credList = Arrays.asList(new Cred("Haresh", "Shaha", "Admin"), new Cred("Prachi", "Shaha", "User"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("username","password");
        FilterProvider filters = new SimpleFilterProvider().addFilter("CredFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(credList);
        mapping.setFilters(filters);
        return mapping;
    }
}
