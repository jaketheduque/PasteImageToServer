package me.jaketheduque.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("me.jaketheduque")
@SpringBootApplication
public class PasteImageToServer {

	public static void main(String[] args) {

		SpringApplication.run(PasteImageToServer.class, args);
	}

}
