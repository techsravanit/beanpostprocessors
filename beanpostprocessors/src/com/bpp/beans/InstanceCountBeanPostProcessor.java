package com.bpp.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.bpp.test.BPPTest;

public class InstanceCountBeanPostProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessAfterInitialization(Object obj, String beanName) throws BeansException {
		BPPTest.instances++;
		return obj;
	}

	@Override
	public Object postProcessBeforeInitialization(Object obj, String beanName) throws BeansException {
		return obj;
	}

}
