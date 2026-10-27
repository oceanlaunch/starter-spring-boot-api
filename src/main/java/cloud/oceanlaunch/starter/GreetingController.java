package cloud.oceanlaunch.starter;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** A small JSON API to start from. Change it, push, and OceanLaunch deploys the new version. */
@RestController
class GreetingController {

    record Greeting(long id, String message, Instant createdAt) {
    }

    record NewGreeting(@NotBlank @Size(max = 200) String message) {
    }

    private final AtomicLong ids = new AtomicLong();
    // Kept in memory, so it starts empty after each deploy. Add a database to keep data.
    private final List<Greeting> greetings = new CopyOnWriteArrayList<>();

    @GetMapping("/")
    Map<String, Object> index() {
        return Map.of(
                "app", "Spring Boot REST API starter",
                "endpoints", List.of("GET /api/hello?name=you", "GET /api/greetings", "POST /api/greetings", "GET /actuator/health"));
    }

    @GetMapping("/api/hello")
    Map<String, String> hello(@RequestParam(defaultValue = "world") String name) {
        String clean = name.strip();
        return Map.of("message", "Hello, " + (clean.isEmpty() ? "world" : clean.substring(0, Math.min(clean.length(), 50))) + "!");
    }

    @GetMapping("/api/greetings")
    List<Greeting> greetings() {
        return greetings;
    }

    @PostMapping("/api/greetings")
    @ResponseStatus(HttpStatus.CREATED)
    Greeting add(@Valid @RequestBody NewGreeting body) {
        var greeting = new Greeting(ids.incrementAndGet(), body.message().strip(), Instant.now());
        greetings.add(greeting);
        return greeting;
    }
}
