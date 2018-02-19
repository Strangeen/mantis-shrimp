package online.dinghuiye;

import online.dinghuiye.starter.Starters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties
//@ComponentScan(basePackages = {"online.dinghuiye"})
public class MantisShrimpApplication {

	public static void main(String[] args) {
        Starters.startH2Server();
	    SpringApplication.run(MantisShrimpApplication.class, args);
	}
}
