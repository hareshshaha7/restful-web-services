package com.haresh.spring.restfulwebservices;

public class HelloWorldBean {
    private final String helloWorld;

    public HelloWorldBean(String helloWorld) {
        this.helloWorld = helloWorld;
    }

    public String getHelloWorld() {
        return helloWorld;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "helloWorld='" + helloWorld + '\'' +
                '}';
    }
}
