package com.bpp.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.bpp.beans.InstanceCountBeanPostProcessor;
import com.bpp.beans.Robot;
import com.bpp.beans.Rocket;

public class BPPTest {
	
	public static int instances=0;
	
	public static void main(String[] args) {
		
		/**How the Bean Post Processors will work ??
		 *                (Or)
		 * What will happen when i tried getting the object from the IOC Container ??
		 * 
		 * When I called factory.getBean("rocket",Rocket.class)
		 * My Bean factory will goes to the in memory meta-data of the IOC container, 
		 * searching for the bean definition "rocket" whose bean id is 'rocket'.
		 * Once the bean definition has been identified it checks for the corresponding class whether it is existed or not.
		 * Then it checks for the scope the bean definition. If the scope is singleton then it will check whether the object is already created or not.
		 * If it has not been created it will do the bunch of validations(like circular dependency check, idref, dependency check).
		 * once all the validation are passed it starts creating the object bean definition.
		 * while creating the object of bean definition it will perform constructor injection. after creating the object of bean definition it will perform setter injection.
		 * then it will check if you configured any bean post processor or not, if you configure any bean post processor it will pass the object as an input to the
		 * bean post processor and calling postProcessBeforeInitialization(.. , ..) on bean post processor.
		 * once the postProcessBeforeInitialization() method complete its execution it will return object to IOC Container.
		 * Then IOC Container will perform the Bean LifeCycle initialization method, once it is completed again it is going to call 
		 * postProcessAfterInitialization(.. , ..) once it is also completed it will return the object to the IOC Container.
		 * And the reference of the object will be given to the End User.
		 */
		
		BeanFactory factory=new XmlBeanFactory(new ClassPathResource("com/bpp/common/application-context.xml"));
		
		//instead of configuring we are directly adding that InstanceCountBeanPostProcessor object to the IOC Container
		/*((ConfigurableListableBeanFactory)factory).addBeanPostProcessor(new InstanceCountBeanPostProcessor());*/
		
		//getting the InstanceCountBeanPostProcessor Object from Spring bean configuration file(application-context.xml).
		((ConfigurableListableBeanFactory)factory).addBeanPostProcessor(factory.getBean("instanceCount",InstanceCountBeanPostProcessor.class));
		
		Rocket r=factory.getBean("rocket",Rocket.class);
		System.out.println("instances:"+instances);
		
		Rocket r1=factory.getBean("rocket",Rocket.class);
		System.out.println("instances:"+instances);
		
		Robot robot=factory.getBean("robot",Robot.class);
		System.out.println("instances:"+instances);
	}

}
