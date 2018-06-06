package pl.sda.libraryproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.libraryproject.model.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {


    Author findFirstByFirstNameAndLastName(String firstName, String  LastName);

}
