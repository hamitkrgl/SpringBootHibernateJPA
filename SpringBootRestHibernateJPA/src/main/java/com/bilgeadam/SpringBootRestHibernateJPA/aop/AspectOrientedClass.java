package com.bilgeadam.SpringBootRestHibernateJPA.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectOrientedClass {

	@Before(value = "execution(* com.bilgeadam.SpringBootRestHibernateJPA.endpoints.OgrenciResource.*Name*(..))")
	public void icindeNameGecen() {
		System.err.println("Metod adında Name geçenler");
	}

	@Before(value = "within(com.bilgeadam.SpringBootRestHibernateJPA.endpoints.*)")
	private void allMethodsInsideControllerPackageBefore() {
		System.err
				.println("--before--All methods of all classes of com.bilgeadam.SpringBootRestHibernateJPA.endpoints");
	}

	@After(value = "within(com.bilgeadam.SpringBootRestHibernateJPA.endpoints.*)")
	private void allMethodsInsideControllerPackageAfter() {
		System.err.println("--after--All methods of all classes of com.bilgeadam.SpringBootRestHibernateJPA.endpoints");
	}

	// öğrenci resource içindeki bütün metodlar
	@Pointcut(value = "execution(* com.bilgeadam.SpringBootRestHibernateJPA.endpoints.OgrenciResource.*(..))")
	public void ogrenciResource() {
		System.err.println("Öğrenci resource içinde bir metod çalıştı.");
	}

	@Before(value = "ogrenciResource()")
	public void beforeOgrenciResource() {
		System.err.println("==> before OgrenciResource");
	}

	@After(value = "ogrenciResource()")
	public void afterOgrenciResource() {
		System.err.println("==> after OgrenciResource");
	}

	// hem öncesi hem sonrası
	@Around(value = "ogrenciResource()")
	public Object aroundOgrenciResource(ProceedingJoinPoint point) throws Throwable {
		System.err.println("==> around OgrenciResource işlemi");
		long startTime = System.currentTimeMillis();
		Object object = point.proceed();
		long endTime = System.currentTimeMillis();
		System.err.println("Class Name : " + point.getSignature().getDeclaringTypeName() + "Method Name : "
				+ point.getSignature().getName() + ". Time taken for execution is : " + (endTime - startTime) + " ms");
		System.err.println("==> around OgrenciResource işlemi bitiş");
		// spring kaldığı yerden devam etsin diye return koyduk
		return object;
	}

}
