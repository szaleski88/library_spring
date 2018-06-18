package pl.sda.libraryproject.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTools {

    @Pointcut("execution(* pl.sda.libraryproject.repository.BookRepository.findAllBy*(*))")
    public void allFindAllByFromBookRepository() {}


    @Pointcut("execution(* pl.sda.libraryproject.repository.BookRepository.save(*))")
    public void allSavedToBookRepository() {}

}
