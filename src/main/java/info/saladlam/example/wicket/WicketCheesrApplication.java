package info.saladlam.example.wicket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WicketCheesrApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(WicketCheesrApplication.class).run(args);
	}

}
