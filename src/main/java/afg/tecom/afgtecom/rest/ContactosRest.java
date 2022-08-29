package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.Contactos;
import afg.tecom.afgtecom.service.ContactosService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/contactos")
public class ContactosRest {

    private final Logger log = LoggerFactory.getLogger(ContactosRest.class);

    @Autowired
    protected ContactosService contactosService;

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
     * Find by id Contactos.
     */
    @ApiOperation(value = "Busca registro de tipo Contactos en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Contactos> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id Contactos : {}", id);
        try {
            return Optional.ofNullable(this.contactosService.findOne(id).get()).map(contactos -> new ResponseEntity<>(contactos, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion Contactos.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo Contactos", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contactos>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.contactosService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created Contactos.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo Contactos", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Contactos> save(@RequestBody @Valid Contactos contactos) throws URISyntaxException {
        log.debug("Ingresando Update ContactosRest : {}", contactos);
        try {
            Contactos result = this.contactosService.save(contactos);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id Contactos.
     */
    @ApiOperation(value = "Elimina registro de tipo Contactos", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id Contactos : {}", id);
        try {
            this.contactosService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete Contactos en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo Contactos", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll ContactosRest");
        try {
            this.contactosService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

}
