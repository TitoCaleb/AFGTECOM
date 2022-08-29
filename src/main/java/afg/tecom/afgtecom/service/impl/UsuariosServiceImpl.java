package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Usuarios;
import afg.tecom.afgtecom.repository.UsuariosRepository;
import afg.tecom.afgtecom.service.UsuariosService;
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
public class UsuariosServiceImpl implements UsuariosService {

    protected final Logger log = LoggerFactory.getLogger(UsuariosServiceImpl.class);

    @Autowired
    protected UsuariosRepository usuariosRepository;

    @Override
    public Optional<Usuarios> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.usuariosRepository.findById(id);
    }

    @Override
    public List<Usuarios> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<Usuarios> lista = this.usuariosRepository.findAll(sort);
        return lista;
    }

    protected Usuarios completarDatosBean(Usuarios bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected Usuarios setCompletarDatosBean(Usuarios bean) {
        return bean;
    }

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    @Override
    public Usuarios save(Usuarios dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.usuariosRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected void setSave(Usuarios dto) {
        return;
    }

    protected Usuarios setBeforeSave(Usuarios dto) {
        return dto;
    }

    protected Usuarios setAfterSave(Usuarios dto) {
        return dto;
    }

    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.usuariosRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.usuariosRepository.deleteAll();
    }

    protected void setDeleteAll() {
        return;
    }
}
