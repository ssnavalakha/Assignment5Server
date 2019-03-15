package com.example.assignment5.repositories;

import com.example.assignment5.model.Module;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ModuleRepository extends CrudRepository<Module,Long> {
    @Query("SELECT module from Module module WHERE module.course.id=:cid ")
    public List<Module> findModulesByCid
            (@Param("cid") long cid);
    @Modifying
    @Transactional
    @Query("Delete from Module m where m.course.id=:cid")
    public void deleteByCourseId(@Param("cid") long cid);
}
