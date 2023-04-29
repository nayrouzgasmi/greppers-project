package  tn.esprit.pidev.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI());

    }
    
    public Info infoAPI() {
        return new Info().title("API")
                .description("API")
                .contact(contactAPI());

    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("Greppers")
                .email("essia.litaiem@esprit.tn") ;
        return contact;
    }
    
}
