package com.lex.springboot.restapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

    @Pointcut(value = "execution(* com.lex.springboot.restapi.controller.*.*(..))")
    public void pc(){}

//    /* 呼叫 Target方法 前，呼叫此 @Before Advice
    @Before(value = "pc()")
    public void beforeAdvice(JoinPoint point){
        String methodName = point.getSignature().getName();
        System.out.println("@Before Advice 執行 : " + methodName + " 方法開始執行... ");
    }
//     */

//    /* 呼叫 Target方法 後，最後(finally)都會呼叫此 @After Advice，不管拋出異常或方法結束後都會執行
    @After(value = "pc()")
    public void afterAdvice(JoinPoint point){
        String methodName = point.getSignature().getName();
        System.out.println("@After Advice 執行 : " + methodName + " 方法執行結束... ");
    }
//    */

//    /* 呼叫 Target方法 成功後(success execution)，呼叫此 @AfterReturning Advice
    @AfterReturning(value = "pc()", returning = "result")
    public void afterReturningAdvice(JoinPoint point, Object result){
        String methodName = point.getSignature().getName();
        System.out.println("@AfterReturning Advice 執行 ： " + methodName + " 方法返回值： " + result.toString());
    }
//     */

    /*
    @AfterThrowing(value = "pc()", throwing = "e")
    public void afterThrowingAdvice(JoinPoint point, Exception e){
        String methodName = point.getSignature().getName();
        System.out.println(methodName + " 方法異常，拋出的異常是： " + e.getMessage());
    }
     */

//    /* @Around 環繞在 @Before 之前執行， @After之後執行，最後 return pjp.proceed();
    @Around(value = "pc()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("- @Around Before -");
        Object object = new Object();
        try {
            object = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("- @Around After -");
        return object;
    }
//     */
}
