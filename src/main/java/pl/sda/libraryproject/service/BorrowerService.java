package pl.sda.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.libraryproject.model.Borrower;
import pl.sda.libraryproject.model.BorrowerDetails;
import pl.sda.libraryproject.repository.BorrowerDetailsRepository;
import pl.sda.libraryproject.repository.BorrowerRepository;

import java.util.List;

@Service
public class BorrowerService {

    private BorrowerRepository borrowerRepository;
    private BorrowerDetailsRepository borrowerDetailsRepository;

    @Autowired
    public BorrowerService(BorrowerRepository borrowerRepository, BorrowerDetailsRepository borrowerDetailsRepository) {
        this.borrowerRepository = borrowerRepository;
        this.borrowerDetailsRepository = borrowerDetailsRepository;
    }

    public boolean checkIfBorrowerHasIdenticalBorrowerDetails(Borrower borrower){
        BorrowerDetails details = borrower.getBorrowerDetails();
        return borrowerDetailsRepository.findAllByEmailAndPhone(details.getEmail(), details.getPhone()).isEmpty();
    }

    public boolean checkIfBorrowerDetailsExist(BorrowerDetails details){
        return borrowerDetailsRepository.findAllByEmailAndPhone(details.getEmail(), details.getPhone()).isEmpty();
    }


    public boolean checkIfBorrowerExists(Borrower borrower){
        return borrowerRepository.findAllByFirstNameAndLastName(borrower.getFirstName(), borrower.getLastName()).isEmpty();
    }

    public Borrower addBorrowerToDatabase(Borrower borrower, BorrowerDetails borrowerDetails) {
        borrower.setBorrowerDetails(borrowerDetails);
        return borrowerRepository.save(borrower);
    }

    public List<Borrower> getListOfAllBorrowers(){
        return borrowerRepository.findAll();
    }


}
