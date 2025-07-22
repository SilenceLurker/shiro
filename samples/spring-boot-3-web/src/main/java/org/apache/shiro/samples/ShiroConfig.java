package org.apache.shiro.samples;

import org.apache.shiro.spring.web.ShiroFilterBeanManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    protected ShiroFilterBeanManager manager(){
        var shiroManager = new ShiroFilterBeanManager();

        shiroManager.setSecurityManager(new DefaultWebSecurityManager());

        return shiroManager;
    }
}
