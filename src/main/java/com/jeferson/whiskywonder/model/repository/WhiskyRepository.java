package com.jeferson.whiskywonder.model.repository;

import com.jeferson.whiskywonder.model.entity.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;


@Repository

public interface WhiskyRepository extends JpaRepository<Whisky, Long> {

     @Query("SELECT w FROM Whisky w " +
             "WHERE (:brand IS NULL OR w .brand = :brand) " +
             "AND (:type IS NULL OR w.type = :type)" +
             "AND (:minYear IS NULL OR w. year >= :minYear) " +
             "AND (:maxYear IS NULL OR w. year >= :maxYear) " +
             "AND (:country IS NULL OR w. country = :country)")
     List<Whisky> findByFilters(@Param("brand") String brand,
                                @Param("type") String type,
                                @Param("minYear") Integer minYear,
                                @Param("maxYear") Integer maxYear,
                                @Param("country") String country);


}
