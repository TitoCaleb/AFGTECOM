package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.Categoria;
import afg.tecom.afgtecom.repository.CategoriaRepository;
import afg.tecom.afgtecom.service.CategoriaService;
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
@RequestMapping("/api/categoria")
public class CategoriaRest{

    private final Logger log = LoggerFactory.getLogger(CategoriaRest.class);

    @Autowired
    protected CategoriaRepository categoriaRepository;

    @Autowired
    protected CategoriaService categoriaService;

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
     * Find by id Categoria.
     */
    @ApiOperation(value = "Busca registro de tipo Categoria en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id Categoria : {}", id);
        try {
            return Optional.ofNullable(this.categoriaService.findOne(id).get()).map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion Categoria.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo Categoria", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Categoria>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.categoriaService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created Categoria.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo Categoria", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Categoria> save(@RequestBody @Valid Categoria categoria, BindingResult bindingResult) throws URISyntaxException {
        log.debug("Ingresando Update CategoriaRest : {}", categoria);
        try {
            Categoria result = this.categoriaService.save(categoria);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id Categoria.
     */
    @ApiOperation(value = "Elimina registro de tipo Categoria", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id Categoria : {}", id);
        try {
            this.categoriaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete Categoria en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo Categoria", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll CategoriaRest");
        try {
            this.categoriaService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }
}
