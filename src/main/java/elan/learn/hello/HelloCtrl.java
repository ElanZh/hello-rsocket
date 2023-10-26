package elan.learn.hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
public class HelloCtrl {

    @MessageMapping("hello")
    public Mono<String> hello(String name) {
        return Mono.just("Hello, " + name + "!");
    }
    @MessageMapping("stream")
    public Flux<String> stream() {
        Flux<String> initialFlux = Flux.just("apple", "banana", "orange");

        Flux<String> fluxWithInterval = Flux.interval(Duration.ofSeconds(5))
                .zipWith(initialFlux.repeat())
                .map(tuple -> "New Fruit - " + tuple.getT2());
        return fluxWithInterval;
    }
}
