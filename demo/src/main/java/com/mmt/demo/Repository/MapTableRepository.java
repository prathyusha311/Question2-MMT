package com.mmt.demo.Repository;

import com.mmt.demo.Entity.MapTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapTableRepository extends CrudRepository<MapTable,String> {


}
