package ru.neoflex.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import ru.neoflex.practice.database.resultTable;

import java.util.List;

@Repository
public interface resultRep extends JpaRepository<resultTable, Long> {
    @Query("SELECT r.result FROM resultTable r")
    List<Integer> findAllResultsOnly();
}
