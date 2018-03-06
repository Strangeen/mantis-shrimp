package online.dinghuiye.mantisshrimp;

import online.dinghuiye.MantisShrimpApplication;
import online.dinghuiye.starter.Starters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        Starters.startH2Server();
		return application.sources(MantisShrimpApplication.class);
	}
}
