/* import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRedisHttpSession 
public class HttpSessionConfig {

        // this will hit redis on localhost and the default port
        @Bean
        public JedisConnectionFactory connectionFactory() {
                JedisConnectionFactory factory = new JedisConnectionFactory();  
                // ... customize ...
                return factory;
        }

        // override the default of using cookies and instead use headers
        @Bean
        public HttpSessionStrategy httpSessionStrategy() {
                return new HeaderHttpSessionStrategy(); 
        }
} */