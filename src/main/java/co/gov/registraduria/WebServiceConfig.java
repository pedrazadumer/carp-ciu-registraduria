package co.gov.registraduria;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "customers")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema registraduriaSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CustomersPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://registraduria.gov.co/commoncannonical/v3/schemas");
        wsdl11Definition.setRequestSuffix("Rq");
        wsdl11Definition.setResponseSuffix("Rs");
        wsdl11Definition.setSchema(registraduriaSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema registraduriaSchema() {
        return new SimpleXsdSchema(new ClassPathResource("registraduria.xsd"));
    }

    /*wsdl11Definition.setSchemaCollection(new XsdSchemaCollection() {
            @Override
            public XsdSchema[] getXsdSchemas() {
                return new XsdSchema[]{registraduriaCustomerSchema(), registraduriaCommonSchema()};
            }

            @Override
            public XmlValidator createValidator() {
                return null;
            }
        });*/

    /*@Bean
    public XsdSchema registraduriaCommonSchema() {
        return new SimpleXsdSchema(new ClassPathResource("registraduria-common-schema.xsd"));
    }

    @Bean
    public XsdSchema registraduriaCustomerSchema() {
        return new SimpleXsdSchema(new ClassPathResource("registraduria-customer-schema.xsd"));
    }*/
}