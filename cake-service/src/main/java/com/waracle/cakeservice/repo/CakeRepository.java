package com.waracle.cakeservice.repo;

import com.waracle.cakeservice.entity.Cake;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CakeRepository extends CassandraRepository<Cake, String> {
}
