package org.apache.shiro.samples;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

/**
 * @author Silence_Lurker
 */
@Component
public class TestBeanPostProcessorForDebug implements BeanPostProcessor, BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBean'");
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBean'");
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBean'");
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBean'");
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBean'");
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBeanProvider'");
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBeanProvider'");
    }

    @Override
    public boolean containsBean(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsBean'");
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSingleton'");
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPrototype'");
    }

    @Override
    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTypeMatch'");
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isTypeMatch'");
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    @Override
    public Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    @Override
    public String[] getAliases(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAliases'");
    }

}
