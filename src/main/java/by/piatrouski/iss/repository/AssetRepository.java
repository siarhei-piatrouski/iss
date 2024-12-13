package by.piatrouski.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import by.piatrouski.iss.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

}
