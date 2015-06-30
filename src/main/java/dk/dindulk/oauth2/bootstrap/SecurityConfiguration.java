package dk.dindulk.oauth2.bootstrap;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Security configuration. This is used to protect all urls with form-based authentication (except /oauth/token which
 * has it's own securityFilterChain defined inside Spring protected by basic-auth).
 *
 * The authentication manager accepts any username/password combination.
 *
 * @author Klaus Groenbaek
 *         Created 09/03/15.
 */
@Component
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder builder) {
        builder.parentAuthenticationManager(authentication -> new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Lists.newArrayList(new SimpleGrantedAuthority("USER_ROLE"))));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //@formatter:off
        http
                .authorizeRequests()
                    .antMatchers("/favicon.ico").permitAll()
                    .antMatchers("/static/**").permitAll()
                    .antMatchers("/noauth/**").permitAll()
                    .anyRequest().authenticated()
                .and().formLogin()
                .and().csrf().disable();
        //@formatter:on
    }

}
