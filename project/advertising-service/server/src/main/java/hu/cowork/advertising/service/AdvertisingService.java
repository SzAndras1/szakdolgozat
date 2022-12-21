package hu.cowork.advertising.service;

import hu.cowork.advertising.entity.Advertising;
import hu.cowork.advertising.mapper.AdvertisingMapper;
import hu.cowork.advertising.model.AdvertisingDto;
import hu.cowork.advertising.model.PageDto;
import hu.cowork.advertising.model.PageResultDto;
import hu.cowork.advertising.repository.AdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdvertisingService {

    private final AdvertisingRepository advertisingRepository;
    private final AdvertisingSearchService advertisingSearchService;
    private final AdvertisingMapper advertisingMapper;

    public AdvertisingDto createAdvertising(AdvertisingDto advertisingDto) {
        Advertising savedAdvertising = advertisingMapper.toEntity(advertisingDto);
        return advertisingMapper.toDto(advertisingRepository.save(savedAdvertising));
    }
    
    public AdvertisingDto getAdvertising(Long id) {
        return advertisingMapper.toDto(advertisingRepository.findByUserId(id).get());
    }
    
    public PageResultDto searchAdvertising(PageDto pageDto) {
        return advertisingSearchService.search(pageDto);
    }
    
    public AdvertisingDto updateAdvertising(AdvertisingDto advertisingDto) {
        Advertising savedAdvertising = advertisingMapper.toEntity(advertisingDto);
        return advertisingMapper.toDto(advertisingRepository.save(savedAdvertising));
    }

    public List<AdvertisingDto> getUserAdvertising(Long userId) {
        return advertisingRepository.findAllByUserId(userId).stream()
                .map(advertisingMapper::toDto)
                .collect(Collectors.toList());
    }

    public AdvertisingDto setAdvertisingStatus(Long id) {
        Optional<Advertising> expectedAdvertising = Optional.of(advertisingRepository.findById(id).get());
        expectedAdvertising.get().setIsActive(
                !expectedAdvertising.get().getIsActive());
        return advertisingMapper.toDto(advertisingRepository.save(expectedAdvertising.get()));
    }
}
