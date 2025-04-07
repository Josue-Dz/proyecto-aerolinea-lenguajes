package hn.unah.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "API de Gestión de Vuelos y Reservas",
        version = "1.0.0",
        description = "Esta API permite gestionar vuelos, reservas, usuarios, boletos y otros elementos relacionados con un sistema de aerolínea.",
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        ),
        contact = @Contact(
            name = "Equipo de Desarrollo: Aida, Ronny, Jared, Jose Daniel",
            email = "soporte@lenguajes.hn",
            url = "http://apirestdocumentation.hn"
        )
    ),
    servers = {
        @Server(description = "Ambiente Local", url = "http://localhost:8080/"),
		@Server(description = "Ambiente Dev expuesto por Apigateway", url = "http://dev.api.lenguajes.hn"),
        @Server(description = "Ambiente QA expuesto por Apigateway", url = "http://qa.api.lenguajes.hn"),
        @Server(description = "Ambiente Prod expuesto por Apigateway", url = "http://api.lenguajes.hn")
    }
    
)

@SpringBootApplication
public class AerolineaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AerolineaApplication.class, args);
	}

}
