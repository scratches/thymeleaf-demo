package com.example;

import java.time.Duration;
import java.util.UUID;

import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import reactor.core.publisher.Flux;

@SpringBootApplication
@Controller
public class DemoApplication {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("greetings",
                new ReactiveDataDriverContextVariable(Flux.interval(Duration.ofSeconds(1))
                        .take(4).map(num -> new Greeting("foo " + num))));
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

class Greeting {

    private String id = UUID.randomUUID().toString();

    private String msg;

    @SuppressWarnings("unused")
    private Greeting() {
    }

    public Greeting(String msg) {
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Greeting [msg=" + msg + "]";
    }

}