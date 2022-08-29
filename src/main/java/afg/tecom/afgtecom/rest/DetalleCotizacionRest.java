package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.DetalleCotizacion;
import afg.tecom.afgtecom.service.DetalleCotizacionService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("/api/DetalleCotizacion")
public class DetalleCotizacionRest {

    private final Logger log = LoggerFactory.getLogger(DetalleCotizacionRest.class);

    @Autowired
    protected DetalleCotizacionService detalleCotizacionService;

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
     * Find by id DetalleCotizacion.
     */
    @ApiOperation(value = "Busca registro de tipo DetalleCotizacion en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DetalleCotizacion> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id DetalleCotizacion : {}", id);
        try {
            return Optional.ofNullable(this.detalleCotizacionService.findOne(id).get())
                    .map(detalleCotizacion -> new ResponseEntity<>(detalleCotizacion, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion DetalleCotizacion.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo DetalleCotizacion", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DetalleCotizacion>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.detalleCotizacionService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created DetalleCotizacion.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo DetalleCotizacion", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DetalleCotizacion> save(@RequestBody @Valid DetalleCotizacion detalleCotizacion, BindingResult bindingResult)
            throws URISyntaxException {
        log.debug("Ingresando Update DetalleCotizacionRest : {}", detalleCotizacion);
        try {
            DetalleCotizacion result = this.detalleCotizacionService.save(detalleCotizacion);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id DetalleCotizacion.
     */
    @ApiOperation(value = "Elimina registro de tipo DetalleCotizacion", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id DetalleCotizacion : {}", id);
        try {
            this.detalleCotizacionService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete DetalleCotizacion en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo DetalleCotizacion", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll DetalleCotizacionRest");
        try {
            this.detalleCotizacionService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

}
