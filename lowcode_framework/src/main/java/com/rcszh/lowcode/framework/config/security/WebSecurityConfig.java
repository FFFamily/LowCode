//package com.rcszh.lowcode.framework.config.security;
//
//import com.rcszh.lowcode.framework.config.security.handler.UnauthorizedHandler;
//import jakarta.annotation.Resource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//import org.springframework.web.filter.CorsFilter;
//
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfig {
//    @Resource
//    private UnauthorizedHandler unauthorizedHandler;
//    /**
//     * 跨域过滤器
//     */
//    @Autowired
//    private CorsFilter corsFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                // CSRF禁用，因为不使用session
//                .csrf(AbstractHttpConfigurer::disable)
//                // 禁用HTTP响应标头
//                .headers(headers -> headers.cacheControl(HeadersConfigurer.CacheControlConfig::disable))
//                // 认证失败处理类
//                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler))
//                // 基于token，所以不需要session
//                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                //过滤请求
//                .authorizeRequests()
//                // 对于登录login 注册register 验证码captchaImage 允许匿名访问
//                .requestMatchers("/login", "/register", "/captchaImage").permitAll()
//                .requestMatchers("/weiXin/cp/getAuthUrl", "/weiXin/cp/loginByCpCode", "/weboffice/**").permitAll()
//                .requestMatchers("/l/*").permitAll()
//                .requestMatchers("/unAuthenticatedAccess/*").permitAll()
//                // 静态资源，可匿名访问
//                .requestMatchers(HttpMethod.GET, "/", "/*.html", "/**.html", "/**.css", "/**.js", "/profile/**").permitAll()
//                .requestMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
//                // 配置要放开的接口 -----------------------------------
//                .requestMatchers("/actuator/**").permitAll()
//                .requestMatchers("/metrics/**").permitAll()
//                .requestMatchers("/trace").permitAll()
//                .requestMatchers("/heapdump").permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated().and()
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
////                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler))
////                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
////                .addFilterBefore(corsFilter,JwtAuthenticationTokenFilter.class)
////                .addFilterBefore(corsFilter, LogoutFilter.class).authenticationProvider(wxCodeAuthenticationProvider)
//        ;
//        return httpSecurity.build();
//    }
//
//    /**
//     * 解决 无法直接注入 AuthenticationManager
//     *
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    /**
//     * 强散列哈希加密实现
//     */
////    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
//
//}
