package cr.ac.ucr.ecci.ci1312.xbase.support.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

/**
 * Base class for all the tests running against a Spring Bean Factory Container.
 *
 * @author Rodrigo Bartels
 */
@ContextConfiguration(locations = {
        "/xbase-core.spring.xml",
        "/xbase-hibernate.spring.xml",
        "/xbase-security.spring.xml"
})
public abstract class BaseTest {

    protected transient final Logger logger = LoggerFactory.getLogger(getClass());


}
