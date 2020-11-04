//package hello.hellospring.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class LoggingAop {
//
//  @Around("execution(* hello.hellospring..*(..))")
//  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
//    System.out.println("Start = " + joinPoint.toString());
//
//    try {
//      Object result = joinPoint.proceed();
//      return joinPoint.proceed();
//    } finally {
//      System.out.println("END = " + joinPoint.toString());
//    }
//  }
//}
