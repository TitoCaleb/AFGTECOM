/*
 * Project home: https://github.com/jaxio/celerio-angular-quickstart
 * 
 * Source code generated by Celerio, an Open Source code generator by Jaxio.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Source code: https://github.com/jaxio/celerio/
 * Follow us on twitter: @jaxiosoft
 * This header can be customized in Celerio conf...
 * Template pack-angular:src/main/java/repository/EntityRepository.java.e.vm
 */
package afg.tecom.afgtecom.repository;

import afg.tecom.afgtecom.domain.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {

    /**
     * Return the persistent instance of {@link Proveedores} with the given unique property value nombreIdentidad,
     * or null if there is no such persistent instance.
     *
     * @param nombreIdentidad the unique value
     * @return the corresponding {@link Proveedores} persistent instance or null
     */
    Proveedores getByNombreIdentidad(String nombreIdentidad);

    /**
     * Return the persistent instance of {@link Proveedores} with the given unique property value documentoIdentidad,
     * or null if there is no such persistent instance.
     *
     * @param documentoIdentidad the unique value
     * @return the corresponding {@link Proveedores} persistent instance or null
     */
    Proveedores getByDocumentoIdentidad(String documentoIdentidad);

}