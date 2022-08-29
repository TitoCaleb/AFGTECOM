package afg.tecom.afgtecom.rest;

import afg.tecom.afgtecom.domain.Productos;
import afg.tecom.afgtecom.service.ProductosService;
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
@RequestMapping("/api/Producto")
public class ProductosRest {

    private final Logger log = LoggerFactory.getLogger(ProductosRest.class);

    @Autowired
    protected ProductosService productosService;

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
     * Find by id Productos.
     */
    @ApiOperation(value = "Busca registro de tipo Productos en base al id enviado", produces = "application/json")
    @GetMapping(value = "/findById/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Productos> findById(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Find by id Productos : {}", id);
        try {
            return Optional.ofNullable(this.productosService.findOne(id).get()).map(productos -> new ResponseEntity<>(productos, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Find All sin paginacion Productos.
     */
    @ApiOperation(value = "Devuelve lista de registros de tipo Productos", produces = "application/json")
    @GetMapping(value = "/findAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Productos>> findAll() throws URISyntaxException {
        log.debug("Ingresando findAll");
        try {
            return Optional.of(this.productosService.findAll()).map(l -> new ResponseEntity<>(l, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Update or created Productos.
     */
    @ApiOperation(value = "Modifica o crea registro de tipo Productos", produces = "application/json")
    @PostMapping(value = "/save", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Productos> save(@RequestBody @Valid Productos productos, BindingResult bindingResult) throws URISyntaxException {
        log.debug("Ingresando Update ProductosRest : {}", productos);
        try {
            Productos result = this.productosService.save(productos);
            return Optional.of(result).map(l -> new ResponseEntity<>(l, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete by id Productos.
     */
    @ApiOperation(value = "Elimina registro de tipo Productos", produces = "application/json")
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws URISyntaxException {
        log.debug("Delete by id Productos : {}", id);
        try {
            this.productosService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception x) {
            // todo: dig exception, most likely
            String error = obtieneMensajeErrorException(x);
            throw new RuntimeException(error);
        }
    }

    /**
     * Delete Productos en forma total.
     */
    @ApiOperation(value = "Elimina todos los registros de tipo Productos", produces = "application/json")
    @DeleteMapping(value = "/deleteAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAll() throws URISyntaxException {
        log.debug("Ingresando deleteAll ProductosRest");
        try {
            this.productosService.deleteAll();
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            String error = obtieneMensajeErrorException(e);
            throw new RuntimeException(error);
        }
    }


}
