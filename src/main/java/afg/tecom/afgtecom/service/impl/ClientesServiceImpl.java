package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Clientes;
import afg.tecom.afgtecom.repository.ClientesRepository;
import afg.tecom.afgtecom.service.ClientesService;
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
public class ClientesServiceImpl implements ClientesService {

    protected final Logger log = LoggerFactory.getLogger(ClientesServiceImpl.class);

    @Autowired
    protected ClientesRepository clientesRepository;


    public Optional<Clientes> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.clientesRepository.findById(id);
    }

    @Override
    public List<Clientes> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<Clientes> lista = this.clientesRepository.findAll(sort);
        return lista;
    }

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Clientes setCompletarDatosBean(Clientes bean) {
        return bean;
    }

    protected Clientes completarDatosBean(Clientes bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected void setSave(Clientes dto) {
        return;
    }

    protected Clientes setBeforeSave(Clientes dto) {
        return dto;
    }

    protected Clientes setAfterSave(Clientes dto) {
        return dto;
    }

    @Override
    public Clientes save(Clientes dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.clientesRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected void setDelete(Integer id) {
        return;
    }

    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.clientesRepository.deleteById(id);
    }

    protected void setDeleteAll() {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.clientesRepository.deleteAll();
    }
}
