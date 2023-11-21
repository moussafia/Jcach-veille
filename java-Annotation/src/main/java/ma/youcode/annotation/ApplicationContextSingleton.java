package ma.youcode.annotation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

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
