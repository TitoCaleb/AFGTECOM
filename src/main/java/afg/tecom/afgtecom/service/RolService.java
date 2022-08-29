/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/dto/EntityDTO.java.e.vm
 */
package afg.tecom.afgtecom.service;

import afg.tecom.afgtecom.domain.Rol;
import java.util.List;
import java.util.Optional;

/**
 * Simple Interface for Rol.
 */
public interface RolService {

    Optional<Rol> findOne(Integer id);

    List<Rol> findAll();

    Rol save(Rol dto);

    void delete(Integer id);

    void deleteAll();

}
