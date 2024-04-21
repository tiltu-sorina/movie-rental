package ro.ubb.fantastic3.server.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.ubb.fantastic3.common.model.BaseEntity;
import ro.ubb.fantastic3.common.model.validators.exceptions.ValidatorException;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity<ID>, ID extends Serializable>
        extends JpaRepository<T, ID> {

}
