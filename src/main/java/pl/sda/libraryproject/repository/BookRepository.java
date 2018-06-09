package pl.sda.libraryproject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.libraryproject.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{

    List<Book> findAllByTitle(String title);

//    @Query("Select b from Book b where b.title is not null")
    List<Book> findAll();

    List<Book> findAllById(Long id);
}
