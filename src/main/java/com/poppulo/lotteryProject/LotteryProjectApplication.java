package com.poppulo.lotteryProject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Lottery Project", version="1.0.0", description = "Poppulo Assignment"))
public class LotteryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryProjectApplication.class, args);
	}

}
