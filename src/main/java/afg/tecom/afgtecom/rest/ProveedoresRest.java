package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.Proveedores;
import afg.tecom.afgtecom.service.ProveedoresService;
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
@RequestMapping("/api/Proveedores")
public class ProveedoresRest {

    private final Logger log = LoggerFactory.getLogger(ProveedoresRest.class);

    @Autowired
    protected ProveedoresService proveedoresService;

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
     * Find by id Proveedores.
     */
    @ApiOperation(value = "Busca registro de tipo Proveedores en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedores> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id Proveedores : {}", id);
        try {
            return Optional.ofNullable(this.proveedoresService.findOne(id).get()).map(proveedores -> new ResponseEntity<>(proveedores, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion Proveedores.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo Proveedores", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Proveedores>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.proveedoresService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created Proveedores.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo Proveedores", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Proveedores> save(@RequestBody @Valid Proveedores proveedores, BindingResult bindingResult) throws URISyntaxException {
        log.debug("Ingresando Update ProveedoresRest : {}", proveedores);
        try {
            Proveedores result = this.proveedoresService.save(proveedores);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id Proveedores.
     */
    @ApiOperation(value = "Elimina registro de tipo Proveedores", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id Proveedores : {}", id);
        try {
            this.proveedoresService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete Proveedores en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo Proveedores", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll ProveedoresRest");
        try {
            this.proveedoresService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }
}
