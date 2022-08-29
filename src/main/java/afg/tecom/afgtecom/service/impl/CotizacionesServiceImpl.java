package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.Cotizaciones;
import afg.tecom.afgtecom.repository.CotizacionesRepository;
import afg.tecom.afgtecom.service.CotizacionesService;
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
public class CotizacionesServiceImpl implements CotizacionesService {

    protected final Logger log = LoggerFactory.getLogger(CotizacionesServiceImpl.class);

    @Autowired
    protected CotizacionesRepository cotizacionesRepository;


    @Transactional(readOnly = true)
    public Optional<Cotizaciones> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.cotizacionesRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Cotizaciones> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        return this.cotizacionesRepository.findAll(sort);
    }

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected Cotizaciones completarDatosBean(Cotizaciones bean) {
        BigDecimal data = new BigDecimal(0.00);
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected Cotizaciones setCompletarDatosBean(Cotizaciones bean) {
        return bean;
    }


    @Override
    public Cotizaciones save(Cotizaciones dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.cotizacionesRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected void setSave(Cotizaciones dto) {
        return;
    }

    protected Cotizaciones setBeforeSave(Cotizaciones dto) {
        return dto;
    }

    protected Cotizaciones setAfterSave(Cotizaciones dto) {
        return dto;
    }

    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.cotizacionesRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }

    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.cotizacionesRepository.deleteAll();
    }

    protected void setDeleteAll() {
        return;
    }
}
