package online.dinghuiye.mantisshrimp;

import online.dinghuiye.MantisShrimpApplication;
import online.dinghuiye.starter.Starters;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.sql.SQLException;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        Starters.startH2Server();
		return application.sources(MantisShrimpApplication.class);
	}
}
