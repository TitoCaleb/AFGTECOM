package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.Usuarios;
import afg.tecom.afgtecom.service.UsuariosService;
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
@RequestMapping("/api/Usuarios")
public class UsuariosRest {

    private final Logger log = LoggerFactory.getLogger(UsuariosRest.class);

    @Autowired
    protected UsuariosService usuariosDeltaService;

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
     * Find by id Usuarios.
     */
    @ApiOperation(value = "Busca registro de tipo Usuarios en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuarios> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id Usuarios : {}", id);
        try {
            return Optional.ofNullable(this.usuariosDeltaService.findOne(id).get()).map(usuarios -> new ResponseEntity<>(usuarios, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion Usuarios.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo Usuarios", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuarios>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.usuariosDeltaService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created Usuarios.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo Usuarios", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuarios> save(@RequestBody @Valid Usuarios usuarios, BindingResult bindingResult) throws URISyntaxException {
        log.debug("Ingresando Update UsuariosRest : {}", usuarios);
        try {
            Usuarios result = this.usuariosDeltaService.save(usuarios);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id Usuarios.
     */
    @ApiOperation(value = "Elimina registro de tipo Usuarios", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id Usuarios : {}", id);
        try {
            this.usuariosDeltaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete Usuarios en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo Usuarios", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll UsuariosRest");
        try {
            this.usuariosDeltaService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }
}
