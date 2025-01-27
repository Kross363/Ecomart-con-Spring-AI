package com.example.ecomart.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagen")//http://localhost:8080/imagen
public class GeneradorDeImagenesController {
    private final ImageModel imageModel;

    public GeneradorDeImagenesController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping()//http://localhost:8080/imagen?prompt=cepillo de dientes de bambu
    public String generadorDeImagenes(String prompt){
        var options= ImageOptionsBuilder.builder()
                .withHeight(1024)
                .withWidth(1024)
                .build();
        var respuesta=imageModel.call(new ImagePrompt(prompt,options));
        return respuesta.getResult().getOutput().getUrl();
    }
}
