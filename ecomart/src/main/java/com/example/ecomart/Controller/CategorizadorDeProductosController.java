package com.example.ecomart.Controller;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.EncodingType;
import com.knuddels.jtokkit.api.ModelType;
import org.apache.catalina.User;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorizador")//http://localhost:8080/generador
public class CategorizadorDeProductosController {
    private final ChatClient chatClient;
    public CategorizadorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4o-mini")
                        .build())
                .build();
    }
    @GetMapping()//http://localhost:8080/categorizador?producto=celular
    public String categorizarDeProductos(String producto){
        var system= """
                    Actua como un categorizador de productos y debes responder solo el nombre de la categoria del producto informado
                   
                    Escoge una categoria de la siguiente lista
                   
                    1.Higiene personal
                    2.Electronicos
                    3.Deportes
                    4 Otros
                   
                    Ejemplos de uso
                    
                    Producto: Pelota de futbol
                    Respuesta: Deportes
               """;
        var tokens=ContadorDeTokens(system,producto);
        var pregunta="genera 5 productos ecologicos";
        return this.chatClient.prompt()
                .system(system)
                .user(producto)
                .options(ChatOptionsBuilder.builder()
                        .withTemperature(0.82).build())
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
    }
    private int ContadorDeTokens (String system, String user){
        var registry = Encodings.newDefaultEncodingRegistry();
        var enc = registry.getEncodingForModel(ModelType.GPT_4O_MINI);
        return enc.countTokens(system + user);
    }
}
