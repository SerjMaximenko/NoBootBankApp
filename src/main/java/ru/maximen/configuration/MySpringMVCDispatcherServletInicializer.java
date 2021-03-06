package ru.maximen.configuration;

        import org.springframework.core.annotation.Order;
        import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(1)
public class MySpringMVCDispatcherServletInicializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses(){return new Class[] {WebSecurityConfig.class};}

    @Override
    protected Class<?>[] getServletConfigClasses() {return new Class[] {SpringConfig.class};}

    @Override
    protected String[] getServletMappings() {return new String[] {"/"};}


}
