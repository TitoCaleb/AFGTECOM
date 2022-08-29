package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.Cotizaciones;
import afg.tecom.afgtecom.service.CotizacionesService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionesRest {

    private final Logger log = LoggerFactory.getLogger(CotizacionesRest.class);

    @Autowired
    protected CotizacionesService cotizacionesService;

    public static String obtieneMensajeErrorException(Exception e) {
        String retorno;
        if (StringUtils.isNotBlank(e.getMessage()))
            return e.getMessage();
        if (StringUtils.isNotBlank(e.getLocalizedMessage()))
            return e.getLocalizedMessage();
        retorno = e.toString();
        return retorno;
    }

    /**
     * Find by id Cotizaciones.
     */
    @ApiOperation(value = "Busca registro de tipo Cotizaciones en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Cotizaciones> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id Cotizaciones : {}", id);
        try {
            return Optional.ofNullable(this.cotizacionesService.findOne(id).get()).map(cotizaciones -> new ResponseEntity<>(cotizaciones, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion Cotizaciones.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo Cotizaciones", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cotizaciones>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        return Optional.of(this.cotizacionesService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
//        try {
//
//        } catch (Exception e) {
//            String error = obtieneMensajeErrorException(e);
//            throw new RuntimeException(error);
//        }
    }

    /**
     * Update or created Cotizaciones.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo Cotizaciones", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Cotizaciones> save(@RequestBody @Valid Cotizaciones cotizaciones, BindingResult bindingResult) throws URISyntaxException {
        log.debug("Ingresando Update CotizacionesRest : {}", cotizaciones);
        try {
            Cotizaciones result = this.cotizacionesService.save(cotizaciones);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id Cotizaciones.
     */
    @ApiOperation(value = "Elimina registro de tipo Cotizaciones", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id Cotizaciones : {}", id);
        try {
            this.cotizacionesService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete Cotizaciones en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo Cotizaciones", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll CotizacionesRest");
        try {
            this.cotizacionesService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

}
