package com.example.faylyuklashyuklabolish.Repazitori;

import com.example.faylyuklashyuklabolish.Model.Fayl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaylRepazitori extends JpaRepository<Fayl,Integer> {

}
