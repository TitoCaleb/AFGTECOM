package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Proveedores;
import afg.tecom.afgtecom.repository.ProductosRepository;
import afg.tecom.afgtecom.repository.ProveedoresRepository;
import afg.tecom.afgtecom.service.ProductosService;
import afg.tecom.afgtecom.service.ProveedoresService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.rmi.server.ExportException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProveedoresServiceImpl implements ProveedoresService {

    protected Logger log = LoggerFactory.getLogger(ProductosServiceImpl.class);

    @Autowired
    protected ProveedoresRepository proveedoresRepository;

    @Override
    public Optional<Proveedores> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.proveedoresRepository.findById(id);
    }

    @Override
    public List<Proveedores> findAll() {
        log.debug("Ingresando finAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<Proveedores> lista = this.proveedoresRepository.findAll(sort);
        return lista;
    }

    protected Sort setFindAll(Sort sort){
        return sort;
    }

    protected Proveedores completarDatosBean(Proveedores bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected Proveedores setCompletarDatosBean(Proveedores bean) {
        return bean;
    }

    @Override
    public Proveedores save(Proveedores dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.proveedoresRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected void setSave(Proveedores dto) {
        return;
    }

    protected Proveedores setBeforeSave(Proveedores dto) {
        return dto;
    }

    protected Proveedores setAfterSave(Proveedores dto) {
        return dto;
    }

    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.proveedoresRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.proveedoresRepository.deleteAll();
    }

    protected void setDeleteAll() {
        return;
    }
}
