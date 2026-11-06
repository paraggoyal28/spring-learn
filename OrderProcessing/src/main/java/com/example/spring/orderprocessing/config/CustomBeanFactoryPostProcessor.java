package com.example.spring.orderprocessing.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("->[BFPP] Executing BeanFactoryPostProcessor: Reading bean definitions before initialization....");
        int beanCount = beanFactory.getBeanDefinitionCount();
        System.out.println("->[BFPP]: Total Bean definitions Found: " + beanCount);
    }
}
