package ru.maximen.configuration;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
@Order(2)
public class SecurotyInitializer extends AbstractSecurityWebApplicationInitializer {

}
