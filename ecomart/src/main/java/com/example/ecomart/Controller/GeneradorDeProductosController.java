package com.example.ecomart.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generador")//http://localhost:8080/generador
public class GeneradorDeProductosController {
    private final ChatClient chatClient;
    public GeneradorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    @GetMapping()//http://localhost:8080/generador
    public String generadorDeProductos(){
        var pregunta="genera 5 productos ecologicos";
        return this.chatClient.prompt()
                .user(pregunta)
                .call()
                .content();
    }
}
