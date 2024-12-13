package by.piatrouski.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.piatrouski.iss.model.Holder;

@Repository
public interface CustomerRepository extends JpaRepository<Holder, Long> {

}
