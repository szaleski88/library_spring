package pl.sda.libraryproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.libraryproject.model.Borrower;

import java.util.List;

@Repository
public interface BorrowerRepository extends CrudRepository<Borrower, Long>{

    List<Borrower> findAll();

    List<Borrower> findAllByFirstNameAndLastName(String firstName, String lastName);
}
