package com.intelligentLibrary.test.recommand;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.domain.User;

public class TestRecommand {
	
	
	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-spring.xml");
	
	
	SessionFactory sf=ctx.getBean(SessionFactory.class);
	
	@Test
	public void testKKNNArithmetic() {
	
	}
}
