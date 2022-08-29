package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.Clientes;
import afg.tecom.afgtecom.service.ClientesService;
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
@RequestMapping("/api/clientes")
public class ClientesRest {
    private final Logger log = LoggerFactory.getLogger(ClientesRest.class);

    @Autowired
    protected ClientesService clientesService;

    public static String obtieneMensajeErrorException(Exception e) {
        String retorno = null;
        if (StringUtils.isNotBlank(e.getMessage()))
            return e.getMessage();
        if (StringUtils.isNotBlank(e.getLocalizedMessage()))
            return e.getLocalizedMessage();
        retorno = e.toString();
        return retorno;
    }

    /**
     * Find by id Clientes.
     */
    @ApiOperation(value = "Busca registro de tipo Clientes en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Clientes> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id Clientes : {}", id);
        try {
            return Optional.ofNullable(this.clientesService.findOne(id).get()).map(clientes -> new ResponseEntity<>(clientes, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion Clientes.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo Clientes", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Clientes>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.clientesService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created Clientes.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo Clientes", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Clientes> save(@RequestBody @Valid Clientes clientes, BindingResult bindingResult) throws URISyntaxException {
        log.debug("Ingresando Update ClientesRest : {}", clientes);
        try {
            Clientes result = this.clientesService.save(clientes);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id Clientes.
     */
    @ApiOperation(value = "Elimina registro de tipo Clientes", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id Clientes : {}", id);
        try {
            this.clientesService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete Clientes en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo Clientes", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll ClientesRest");
        try {
            this.clientesService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

}

