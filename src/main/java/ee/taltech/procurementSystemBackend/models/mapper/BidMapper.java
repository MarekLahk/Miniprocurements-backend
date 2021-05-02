package ee.taltech.procurementSystemBackend.models.mapper;

import ee.taltech.procurementSystemBackend.models.Dto.BidDto;
import ee.taltech.procurementSystemBackend.models.MapperInterface;
import ee.taltech.procurementSystemBackend.models.model.Bid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BidMapper extends MapperInterface<Bid, BidDto> {

    BidMapper INSTANCE = Mappers.getMapper(BidMapper.class);

    @Override
    BidDto toDto(Bid model);

    @Override
    @Mapping(target = "procurement", ignore = true)
    Bid toModel(BidDto dto);
}
