package afg.tecom.afgtecom.service.impl;

import afg.tecom.afgtecom.domain.DetalleCotizacion;
import afg.tecom.afgtecom.repository.DetalleCotizacionRepository;
import afg.tecom.afgtecom.service.DetalleCotizacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Primary
public class DetalleCotizacionServiceImpl implements DetalleCotizacionService {

    protected Logger log = LoggerFactory.getLogger(DetalleCotizacionServiceImpl.class);

    @Autowired
    protected DetalleCotizacionRepository detalleCotizacionRepository;

    @Override
    public Optional<DetalleCotizacion> findOne(Integer id) {
        log.debug("Ingresando findOne: ", id);
        return this.detalleCotizacionRepository.findById(id);
    }

    @Override
    public List<DetalleCotizacion> findAll() {
        log.debug("Ingresando findAll");
        Sort sort = Sort.by("id");
        sort = this.setFindAll(sort);
        List<DetalleCotizacion> lista = this.detalleCotizacionRepository.findAll(sort);
        return lista;
    }

    protected Sort setFindAll(Sort sort) {
        return sort;
    }

    protected DetalleCotizacion completarDatosBean(DetalleCotizacion bean) {
        BigDecimal data = new BigDecimal(0.00);
        if (Optional.ofNullable(bean.getPrecioUnitario()).isPresent()) {
            bean.setPrecioUnitario(bean.getPrecioUnitario().setScale(2, RoundingMode.HALF_UP));
        }
        if (Optional.ofNullable(bean.getSubtotal()).isPresent()) {
            bean.setSubtotal(bean.getSubtotal().setScale(2, RoundingMode.HALF_UP));
        }
        if (Optional.ofNullable(bean.getTotal()).isPresent()) {
            bean.setTotal(bean.getTotal().setScale(2, RoundingMode.HALF_UP));
        }
        bean = this.setCompletarDatosBean(bean);
        return bean;
    }

    protected DetalleCotizacion setCompletarDatosBean(DetalleCotizacion bean) {
        return bean;
    }


    @Override
    public DetalleCotizacion save(DetalleCotizacion dto) {
        log.debug("Ingresando save: ", dto);
        if (dto == null) {
            return null;
        }
        dto = this.completarDatosBean(dto);
        dto = this.setBeforeSave(dto);
        this.setSave(dto);
        dto = this.detalleCotizacionRepository.save(dto);
        dto = this.setAfterSave(dto);
        return dto;
    }

    protected void setSave(DetalleCotizacion dto) {
        return;
    }

    protected DetalleCotizacion setBeforeSave(DetalleCotizacion dto) {
        return dto;
    }

    protected DetalleCotizacion setAfterSave(DetalleCotizacion dto) {
        return dto;
    }


    @Override
    public void delete(Integer id) {
        log.debug("Ingresando delete: ", id);
        if (id == null) {
            return;
        }
        this.setDelete(id);
        this.detalleCotizacionRepository.deleteById(id);
    }

    protected void setDelete(Integer id) {
        return;
    }


    @Override
    public void deleteAll() {
        log.debug("Ingresando deleteAll");
        this.setDeleteAll();
        this.detalleCotizacionRepository.deleteAll();
    }

    protected void setDeleteAll() {
        return;
    }

}
