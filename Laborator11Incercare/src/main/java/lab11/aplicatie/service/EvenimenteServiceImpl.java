package lab11.aplicatie.service;
import org.springframework.stereotype.Service;
import lab11.aplicatie.models.EvenimenteEntity;
import lab11.aplicatie.repository.EvenimenteRepo;;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EvenimenteServiceImpl implements Evenimente {
    private EvenimenteRepo repo;

    public EvenimenteServiceImpl(EvenimenteRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<EvenimenteEntity> getEvenimente() {

        return  repo.getAllEvents();
    }

    @Override
    public EvenimenteEntity addEveniment(EvenimenteEntity eveniment) {

        return repo.save(eveniment);
    }

    @Override
    public List<EvenimenteEntity> getEventsByDate(Date data) {
       return  repo.getAllEventsByDate(data);
    }

    @Override
    public List<EvenimenteEntity> getEvenimenteByLoc(String loc) {
        return repo.getAllEventsByLoc(loc);
    }

    @Override
    public EvenimenteEntity updateEveniment(Integer id, EvenimenteEntity eveniment) {
      Optional<EvenimenteEntity> evenimentDb = repo.findById(id);
      if(evenimentDb.isPresent()) {
          evenimentDb.get().setTimp(eveniment.getTimp());
          evenimentDb.get().setData(eveniment.getData());
          evenimentDb.get().setLocatie(eveniment.getLocatie());
          evenimentDb.get().setPretBilet(eveniment.getPretBilet());
          evenimentDb.get().setDenumire(eveniment.getDenumire());
      }

      return repo.save(evenimentDb.get());
    }

    @Override
    public void deleteEveniment(Integer id) {
        repo.deleteById(id);
    }


}
