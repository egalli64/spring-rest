package com.example.swr.s08;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoderRepo extends JpaRepository<Coder, Integer> {
}
