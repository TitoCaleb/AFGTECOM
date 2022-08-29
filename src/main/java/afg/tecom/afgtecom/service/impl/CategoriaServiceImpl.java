package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Categoria;
import afg.tecom.afgtecom.repository.CategoriaRepository;
import afg.tecom.afgtecom.service.CategoriaService;
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

@Service
@Primary
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CategoriaServiceImpl implements CategoriaService {

    protected final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    protected CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Optional<Categoria> findOne(Integer id) {
       log.debug("Ingresando findOne: ", id);
       return this.categoriaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Categoria> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<Categoria> lista = this.categoriaRepository.findAll(sort);
        return lista;
    }

    protected Sort setFindAll(Sort sort) {
        return sort;
    }
    protected Categoria setCompletarDatosBean(Categoria bean) {
        return bean;
    }

    protected Categoria completarDatosBean(Categoria bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected Categoria setBeforeSave(Categoria dto) {
        return dto;
    }

    protected void setSave(Categoria dto) {
        return;
    }

    protected Categoria setAfterSave(Categoria dto) {
        return dto;
    }

    @Override
    public Categoria save(Categoria dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.categoriaRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }


    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.categoriaRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }

    protected void setDeleteAll() {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.categoriaRepository.deleteAll();
    }
}
