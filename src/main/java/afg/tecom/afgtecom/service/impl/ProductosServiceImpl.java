package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Productos;
import afg.tecom.afgtecom.repository.ProductosRepository;
import afg.tecom.afgtecom.service.ProductosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Primary
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductosServiceImpl implements ProductosService {

    protected Logger log = LoggerFactory.getLogger(ProductosServiceImpl.class);

    @Autowired
    protected ProductosRepository productosRepository;

    @Override
    public Optional<Productos> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.productosRepository.findById(id);
    }

    @Override
    public List<Productos> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<Productos> lista = this.productosRepository.findAll(sort);
        return lista;
    }

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Productos completarDatosBean(Productos bean) {
        BigDecimal data = new BigDecimal(0.00);
        if (Optional.ofNullable(bean.getPrecio()).isPresent()) {
            bean.setPrecio(bean.getPrecio().setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected Productos setCompletarDatosBean(Productos bean) {
        return bean;
    }


    @Override
    public Productos save(Productos dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.productosRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected void setSave(Productos dto) {
        return;
    }

    protected Productos setBeforeSave(Productos dto) {
        return dto;
    }

    protected Productos setAfterSave(Productos dto) {
        return dto;
    }

    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.productosRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.productosRepository.deleteAll();
    }

    protected void setDeleteAll() {
        return;
    }
}
