package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Rol;
import afg.tecom.afgtecom.repository.RolRepository;
import afg.tecom.afgtecom.service.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RolServiceImpl implements RolService {

    protected final Logger log = LoggerFactory.getLogger(RolServiceImpl.class);

    @Autowired
    protected RolRepository rolRepository;

    @Override
    public Optional<Rol> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.rolRepository.findById(id);
    }

    @Override
    public List<Rol> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<Rol> lista = this.rolRepository.findAll(sort);
        return lista;
    }
    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Rol completarDatosBean(Rol bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected Rol setCompletarDatosBean(Rol bean) {
        return bean;
    }

    @Override
    public Rol save(Rol dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.rolRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected void setSave(Rol dto) {
        return;
    }

    protected Rol setBeforeSave(Rol dto) {
        return dto;
    }

    protected Rol setAfterSave(Rol dto) {
        return dto;
    }

    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.rolRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.rolRepository.deleteAll();
    }

    protected void setDeleteAll() {
        return;
    }
}
