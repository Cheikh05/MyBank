package sn.cheikh.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class BanqueSecurity extends WebSecurityConfigurerAdapter {
    /*
     * Cette classe permet de personnaliser la sécurité fournit par spring
     * security
     */
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {

        // Cette methode permet de definir la manière dont on va récupérer les
        // utilisateurs de notre application

        // permet de fixer les utilisateurs

        auth.inMemoryAuthentication().withUser( "ADMIN" ).password(
                "{noop}admin" ).roles( "ADMIN", "USER" );
        auth.inMemoryAuthentication().withUser( "USER" ).password(
                "{noop}user" ).roles( "USER" );

        // Demander a spring sécurity de récuperer les users a partir de la base
        // de données

        /*
         * auth.jdbcAuthentication() .dataSource( dataSource )
         * .usersByUsernameQuery(
         * "select username as principal , password as credentials , active from users where username = ?"
         * ) .authoritiesByUsernameQuery(
         * "select username as principal , role as role from users_roles where username=?"
         * ) .rolePrefix( "ROLE_" ).passwordEncoder( new Md4PasswordEncoder() );
         */
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http.formLogin().loginPage( "/login" );
        http.authorizeRequests().antMatchers( "/bank", "/consulterCompte" ).hasRole( "USER" );
        http.authorizeRequests().antMatchers( "/saveOperation" ).hasRole( "ADMIN" );
        http.exceptionHandling().accessDeniedPage( "/403" );
    }

}
