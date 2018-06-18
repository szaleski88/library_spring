package pl.sda.libraryproject.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import pl.sda.libraryproject.model.Book;

@Aspect
@Component
public class LoggerAspect {
    @Before("pl.sda.libraryproject.aspects.AspectTools.allFindAllByFromBookRepository()")
    public void logInfoBefore(JoinPoint joinPoint) {
        System.out.println("[FROM BEFORE ANNOTATION]");
        Object[] args = joinPoint.getArgs();
        System.out.println("Log before " + joinPoint.getSignature() + " with args: " + args[0]);
    }


    @AfterReturning(pointcut = "execution(* pl.sda.libraryproject.repository.BookRepository.save(*))", returning = "book")
    public void logInfoBeforeSave(JoinPoint joinPoint, Book book
    ) {
        System.out.println("[FROM BEFORE ANNOTATION]");
        System.out.println(book +"\t was saved to db");
    }




}
