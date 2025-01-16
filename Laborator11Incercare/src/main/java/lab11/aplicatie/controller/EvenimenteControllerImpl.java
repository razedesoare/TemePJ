package lab11.aplicatie.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import lab11.aplicatie.dto.EvenimenteDto;
import lab11.aplicatie.mapper.EvenimenteMapper;
import lab11.aplicatie.models.EvenimenteEntity;
import lab11.aplicatie.service.EvenimenteServiceImpl;

import java.util.Date;
import java.util.List;

@Controller
public class EvenimenteControllerImpl implements Evenimente {
    EvenimenteServiceImpl   evenimenteService;

    public EvenimenteControllerImpl(EvenimenteServiceImpl evenimenteService) {
        this.evenimenteService = evenimenteService;
    }

    @Override
    public ResponseEntity<List<EvenimenteDto>> getEvenimente() {

        List<EvenimenteEntity> evenimente = evenimenteService.getEvenimente();

        var eventsDto = EvenimenteMapper.mapMultipleEvenimenteToDto(evenimente);

        return ResponseEntity.ok(eventsDto);

    }

    @Override
    public ResponseEntity<EvenimenteEntity> addEveniment(EvenimenteDto evenimenteDto) {

        var eventsEntity = EvenimenteMapper.mapDtoToEvenimenteEntity(evenimenteDto);
        var event = evenimenteService.addEveniment(eventsEntity);

        return ResponseEntity.ok(event);

    }

    @Override
    public ResponseEntity<List<EvenimenteDto>> getEvenimenteByDate(Date data) {

      var eventsByDate = evenimenteService.getEventsByDate(data);

      if(eventsByDate.isEmpty()) {
          return ResponseEntity.ok(List.of());
      }
      else {
          var eventsDto = EvenimenteMapper.mapMultipleEvenimenteToDto(eventsByDate);
          return ResponseEntity.ok(eventsDto);
      }

    }

    @Override
    public ResponseEntity<List<EvenimenteDto>> getEvenimenteByLocatie(String locatie) {
        try {

            var eventsByLoc = evenimenteService.getEvenimenteByLoc(locatie);
            if (eventsByLoc.isEmpty()) {
                return ResponseEntity.ok(List.of());
            } else {
                var eventsDto = EvenimenteMapper.mapMultipleEvenimenteToDto(eventsByLoc);
                return ResponseEntity.ok(eventsDto);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        return ResponseEntity.badRequest().body(List.of());
        }
    }

    @Override
    public ResponseEntity<EvenimenteDto> updateEveniment(int id, EvenimenteDto evenimenteDto) {
        var eventUpdated = evenimenteService.updateEveniment(id ,EvenimenteMapper.mapDtoToEvenimenteEntity(evenimenteDto));

        return ResponseEntity.ok(EvenimenteMapper.mapEvenimenteToDto(eventUpdated));
    }

    @Override
    public ResponseEntity<Void> deleteEveniment(int id) {

        deleteEveniment(id);

        return ResponseEntity.ok().build();
    }


}
