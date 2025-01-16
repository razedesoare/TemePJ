package lab11.aplicatie.mapper;

import lab11.aplicatie.dto.EvenimenteDto;
import lab11.aplicatie.models.EvenimenteEntity;

import java.util.ArrayList;
import java.util.List;

public class EvenimenteMapper {

    public static EvenimenteDto mapEvenimenteToDto(EvenimenteEntity evenimenteEntity) {
        EvenimenteDto evenimenteDto = new EvenimenteDto();
        evenimenteDto.setData(evenimenteEntity.getData());
        evenimenteDto.setDenumire(evenimenteEntity.getDenumire());
        evenimenteDto.setLocatie(evenimenteEntity.getLocatie());
        evenimenteDto.setPretBilet(evenimenteEntity.getPretBilet());
        evenimenteDto.setTimp(evenimenteDto.getTimp());
        return evenimenteDto;

    }

    public static List<EvenimenteDto> mapMultipleEvenimenteToDto(List<EvenimenteEntity> evenimenteEntity) {
        List<EvenimenteDto> evenimenteDtos = new ArrayList<>();
        evenimenteEntity.forEach(evenimenteEntityDto -> {
            evenimenteDtos.add(mapEvenimenteToDto(evenimenteEntityDto));
        });

        return evenimenteDtos;

    }

    public static EvenimenteEntity mapDtoToEvenimenteEntity(EvenimenteDto evenimenteDto) {
        EvenimenteEntity  eveniment = new EvenimenteEntity();

        eveniment.setData(evenimenteDto.getData());
        eveniment.setDenumire(evenimenteDto.getDenumire());
        eveniment.setLocatie(evenimenteDto.getLocatie());
        eveniment.setPretBilet(evenimenteDto.getPretBilet());
        eveniment.setTimp(evenimenteDto.getTimp());

        return eveniment;

    }

    public static List<EvenimenteEntity> mapDtosToEvenimenteEntity(List<EvenimenteDto> evenimenteDto) {
        List<EvenimenteEntity> evenimente = new ArrayList<>();
        evenimenteDto.forEach(evenimenteEntity -> {
            evenimente.add(mapDtoToEvenimenteEntity(evenimenteEntity));
        });

        return evenimente;
    }

}
