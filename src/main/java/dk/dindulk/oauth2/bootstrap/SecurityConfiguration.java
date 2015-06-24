package dk.dindulk.oauth2.bootstrap;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Security configuration, delegates authentication to authenticationProvider, and specifies which URLs are protected
 *
 * This class works together with OAuthConfigurations which creates filters for a number of different URL starting with /oauth/
 * The initial request "/oauth/authorize" is made by the 3rd party website and includes the following required parameters "clientId", and "secret"
 * and may also contain the following optional parameters "scope", "authorizedGrantTypes". When this request is received
 * the client_id and secret is used to authorize the calling client. The response to this request is a login page with
 * form based login where the user will authenticate. The end-user authentication creates a grant which may then be used
 * to create the oauth access token.
 *
 *
 * @author Klaus Groenbaek
 *         Created 09/03/15.
 */
@Component
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        builder.parentAuthenticationManager(new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return new UsernamePasswordAuthenticationToken("klaus", "klaus", Lists.newArrayList(new SimpleGrantedAuthority("USER_ROLE")));
            }
        });
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().formLogin();
        //@formatter:off
//        http
//                .authorizeRequests().antMatchers("/ping").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/login").permitAll()
//                .and()
//                .authorizeRequests().antMatchers("/static/**").permitAll()
//                .and()
//                .authorizeRequests().anyRequest().access("hasRole('ROLE_USER')")
//                .and()
//                .requiresChannel().anyRequest().requiresSecure()
//                .and()
//                .formLogin().loginPage("/login")
//                .and()
//                .csrf().disable();
        //@formatter:on
    }

}
