package com.maximillian.graph.maximillian.controllers;

import com.maximillian.graph.maximillian.model.Dog;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/sse")
public class ServerSentEventController {

    private static ConcurrentHashMap<String, SseEmitter> events = new ConcurrentHashMap<>();

    @GetMapping("/events")
    public SseEmitter events(@RequestParam(required = true) String event) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(() -> events.remove(event));
        emitter.onTimeout(() -> events.remove(event));
        emitter.onError(e -> events.remove(event));

        try{
            emitter.send(SseEmitter.event()
                    .name("connection_event")
                    .data("CONNECTED with event: " + event)
            );
        }catch (IOException e){
            emitter.completeWithError(e);

        }
        events.put(event, emitter);
        return emitter;
    }

    @PostMapping("/post-event/{event-name}")
    public ResponseEntity<String> postEvent(@RequestBody Dog dog, @PathVariable(name = "event-name") String event) throws IOException {
        SseEmitter emitter = events.get(event);

        if (!Objects.isNull(emitter)) {
            emitter.send(dog);
            emitter.complete();
            return ResponseEntity.ok("Event posted");
        }
        return ResponseEntity.badRequest().build();
    }
}
