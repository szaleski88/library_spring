package pl.sda.libraryproject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.libraryproject.model.Book;
import pl.sda.libraryproject.model.Borrow;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long>{
    Borrow findByBookId(Long id);
}
