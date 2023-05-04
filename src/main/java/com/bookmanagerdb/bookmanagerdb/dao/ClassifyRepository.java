package com.bookmanagerdb.bookmanagerdb.dao;

import com.bookmanagerdb.bookmanagerdb.entity.Classify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface ClassifyRepository extends JpaRepository<Classify, String>, JpaSpecificationExecutor<Classify> {
}
