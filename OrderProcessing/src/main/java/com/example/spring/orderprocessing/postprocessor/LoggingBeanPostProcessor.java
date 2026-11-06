package com.example.spring.orderprocessing.postprocessor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (beanName.equals("auditLogger")) {
            System.out.println("-> [BPP] postProcessBeforeInitialization for: " + beanName);
        }
        return bean; //// Can return modified or proxy objects
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("auditLogger")) {
            System.out.println("-> [BPP] postProcessAfterInitialization for: " + beanName);
        }
        return bean;
    }
}
