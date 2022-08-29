package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Categoria;
import afg.tecom.afgtecom.domain.Contactos;
import afg.tecom.afgtecom.repository.ContactosRepository;
import afg.tecom.afgtecom.service.CategoriaService;
import afg.tecom.afgtecom.service.ContactosService;
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
public class ContactosServiceImpl implements ContactosService {

    protected final Logger log = LoggerFactory.getLogger(ContactosServiceImpl.class);

    @Autowired
    protected ContactosRepository contactosRepository;

    @Transactional(readOnly = true)
    public Optional<Contactos> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.contactosRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Contactos> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<Contactos> lista = this.contactosRepository.findAll(sort);
        return lista;
    }

    protected Contactos setCompletarDatosBean(Contactos bean) {
        return bean;
    }

    protected Contactos completarDatosBean(Contactos bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected void setSave(Contactos dto) {
        return;
    }

    protected Contactos setBeforeSave(Contactos dto) {
        return dto;
    }

    protected Contactos setAfterSave(Contactos dto) {
        return dto;
    }

    @Override
    public Contactos save(Contactos dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.contactosRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected Sort setFindAll(Sort sort) {
        return sort;
    }


    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.contactosRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.contactosRepository.deleteAll();
    }

    protected void setDeleteAll() {
        return;
    }
}
