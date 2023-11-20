package ma.youcode.cach;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/* allows access to spring application context by the singleton  */
@Component
public class ApplicationContextSingleton {
    private static ApplicationContext applicationContext;
    public ApplicationContextSingleton(ApplicationContext applicationContext) {
        ApplicationContextSingleton.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
