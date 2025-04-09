package com.example.demo.Controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Temp {

    @RequestMapping("/map")
    public String map(@RequestBody SampleObject sampleObject) {
        System.out.println(sampleObject.a + " " + sampleObject.b);
        return sampleObject.a + " " + sampleObject.b;
    }

    class SampleObject {
        public String a;
        public String b;
    }

}
