package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.AnnouncementDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncementMapper extends MapperInterface<Announcement, AnnouncementDto> {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    @Override
    AnnouncementDto toDto(Announcement model);

    @Override
    Announcement toModel(AnnouncementDto dto);


}
