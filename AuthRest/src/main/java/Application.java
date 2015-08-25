import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
public class Application {

 private static final String RESOURCE_ID = "blog_resource";

 public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
 }

 @Configuration
 @EnableAuthorizationServer // [1]
 protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

      @Autowired
      private AuthenticationManager authenticationManager;

      @Override // [2]
      public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
           endpoints.authenticationManager(authenticationManager);
      }

      @Override // [3]
      public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
           // @formatter:off
           clients.inMemory()
           .withClient("client-with-registered-redirect")
           .authorizedGrantTypes("authorization_code")
           .authorities("ROLE_CLIENT")
           .scopes("read", "trust")
           .resourceIds(RESOURCE_ID)
           .redirectUris("http://anywhere?key=value")
           .secret("secret123")
           .and()
           .withClient("my-client-with-secret")
           .authorizedGrantTypes("client_credentials", "password")
           .authorities("ROLE_CLIENT")
           .scopes("read")
           .resourceIds(RESOURCE_ID)
           .secret("secret");
           // @formatter:on
      } 

 }
}