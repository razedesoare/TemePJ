package lab11.aplicatie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import lab11.aplicatie.models.EvenimenteEntity;

import java.util.Date;
import java.util.List;
@org.springframework.stereotype.Repository

public interface EvenimenteRepo extends JpaRepository<EvenimenteEntity, Integer> {

    @Query(value = "SELECT ev FROM EvenimenteEntity ev")
    List<EvenimenteEntity> getAllEvents();

    @Query(value = "SELECT ev FROM EvenimenteEntity ev where ev.data = :data ")
    List<EvenimenteEntity> getAllEventsByDate(@Param("data") Date data);

    @Query(value ="SELECT ev FROM EvenimenteEntity ev where ev.locatie = :loc")
    List<EvenimenteEntity> getAllEventsByLoc(@Param("loc") String loc);

}
