package com.ridango.game.repository;

import com.ridango.game.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Result findFirstByOrderByScoreDesc();
}