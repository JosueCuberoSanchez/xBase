package cr.ac.ucr.ecci.ci1312.xbase.spring;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cr.ac.ucr.ecci.ci1312.xbase.support.test.BaseTest;

/**
 * Units Tests for Spring Related Configuration.
 *
 * @author Rodrigo A. Bartels
 */

@RunWith(SpringJUnit4ClassRunner.class)
public class SpringContextTest extends BaseTest {

    @Autowired
    private ApplicationContext applicationContext;
    
    /**
     * Runs an empty test by loading the Spring Bean Context.
     */
    @Test
    public void applicationContextTest() {

    }

    /**
     * Test if all the @Services bean are annotated with the @Transactional
     * annotation.
     */
    @Test
    public void testServicesTransactionalAnnotations() {
        String[] beansNames = applicationContext.getBeanDefinitionNames();
        StringBuilder sb = new StringBuilder();
        for (String beanName : beansNames) {
            Service serviceAnnotation = applicationContext.findAnnotationOnBean(beanName, Service.class);
            if (serviceAnnotation != null && applicationContext.findAnnotationOnBean(beanName,
                    Transactional.class) == null)
                sb.append("Missing @Transactional Annotation in bean:").append(beanName).append("\n");
        }
        Assert.assertTrue(sb.toString(), StringUtils.isBlank(sb.toString()));
    }

    /**
     * Tests that all the classes marked with the @Service annotation have the name set.
     */
    @Test
    public void testServicesAnnotations() {
        String[] beansNames = applicationContext.getBeanDefinitionNames();
        StringBuilder sb = new StringBuilder();
        for (String beanName : beansNames) {
            Service serviceAnnotation = applicationContext.findAnnotationOnBean(beanName, Service.class);
            if (serviceAnnotation != null && StringUtils.isBlank(serviceAnnotation.value()))
                sb.append("Missing @Service value in bean: ").append(beanName).append("\n");
        }
        Assert.assertTrue(sb.toString(), StringUtils.isBlank(sb.toString()));
    }

    /**
     * Tests that all the @Repository annotations have a valid name for the bean
     */
    @Test
    public void testDaosAnnotations() {
        String[] beansNames = applicationContext.getBeanDefinitionNames();
        StringBuilder sb = new StringBuilder();
        for (String beanName : beansNames) {
            Repository repositoryAnnotation = applicationContext.findAnnotationOnBean(beanName, Repository.class);
            if (repositoryAnnotation != null && StringUtils.isBlank(repositoryAnnotation.value())) {
                sb.append("Missing @Repository value in bean: ").append(beanName).append("\n");
            }
        }
        Assert.assertTrue(sb.toString(), StringUtils.isBlank(sb.toString()));
    }
}
