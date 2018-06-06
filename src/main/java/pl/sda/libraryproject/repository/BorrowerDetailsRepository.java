package pl.sda.libraryproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.libraryproject.model.BorrowerDetails;

import java.util.List;

@Repository
public interface BorrowerDetailsRepository extends CrudRepository<BorrowerDetails, Long>{

    List<BorrowerDetails> findAllByEmailAndPhone(String email, String phone);
}
