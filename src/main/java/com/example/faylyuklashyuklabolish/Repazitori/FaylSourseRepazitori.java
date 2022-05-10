package com.example.faylyuklashyuklabolish.Repazitori;

import com.example.faylyuklashyuklabolish.Model.FaylSourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaylSourseRepazitori extends JpaRepository<FaylSourse,Integer> {
    Optional<FaylSourse> findByFayl_Id(Integer id);
}
