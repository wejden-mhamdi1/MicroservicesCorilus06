package tn.corilus.corilus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.corilus.corilus.Entities.File;

import java.util.List;
import java.util.Optional;


@Repository
public interface FileRepository extends JpaRepository<File,Integer> {
   // List<File> findAllByAuthId(Integer id);
    List<File> findAllById(Integer id);
}
