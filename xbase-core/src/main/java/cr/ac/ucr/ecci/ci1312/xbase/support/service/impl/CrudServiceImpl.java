package cr.ac.ucr.ecci.ci1312.xbase.support.service.impl;

/*
 * All Service implementations providing CRUD operations should inherit from
 * this class, when doing so, the inherited class should call the method
 * <code>setCrudDao</code> from its respective <code>init</code> method with a
 * proper instance of a CrudDao. Example:
 *
 * <code>
 * public class SampleServiceImpl extends CrudServiceImpl implements SampleService {
 *
 *  @Autowired
 *  private SampleDao sampleDao; // sampleDao implements CrudDao
 *
 *  ...
 *
 *  public void init() {
 *      ...
 *      // provides this service impl with crud operations
 *      setCrudDao(sampleDao);
 *  }
 *
 *  ...
 *}
 *
 * </code>
 *
 * @author Rodrigo A. Bartels
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import cr.ac.ucr.ecci.ci1312.xbase.model.BasicEntity;
import cr.ac.ucr.ecci.ci1312.xbase.support.dao.CrudDao;

public abstract class CrudServiceImpl<ModelObjectType extends BasicEntity, KeyType extends Serializable> {

    protected final Logger logger = LoggerFactory.getLogger(getDomainClass());

    private Class domainClass;

    protected Class getDomainClass() {
        if (domainClass == null) {

            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
            domainClass = (Class) thisType.getActualTypeArguments()[0];
        }
        return domainClass;
    }

    private CrudDao<ModelObjectType, KeyType> crudDao;

    public void setCrudDao(CrudDao<ModelObjectType, KeyType> crudDao) {
        this.crudDao = crudDao;
    }

    /**
     * @see cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService#create(cr.ac.ucr.ecci.ci1312.xbase.model.BasicEntity)
     */
    public KeyType create(ModelObjectType entity) {
        return crudDao.create(entity);
    }

    /**
     * @see cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService#update(cr.ac.ucr.ecci.ci1312.xbase.model.BasicEntity)
     */
    public void update(ModelObjectType entity) {
        crudDao.update(entity);
    }

    /**
     * @see cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService#remove(cr.ac.ucr.ecci.ci1312.xbase.model.BasicEntity)
     */
    public void remove(ModelObjectType entity) {
        crudDao.remove(entity);
    }

    /**
     * @see cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService#findById(Serializable)
     */
    public ModelObjectType findById(KeyType entityId) {
        if (entityId != null)
            return crudDao.findById(entityId);
        return null;
    }

    /**
     * @see cr.ac.ucr.ecci.ci1312.xbase.support.service.CrudService#getAll()
     */
    @Transactional
    public List<ModelObjectType> getAll() {
        return crudDao.getAll();
    }
}
