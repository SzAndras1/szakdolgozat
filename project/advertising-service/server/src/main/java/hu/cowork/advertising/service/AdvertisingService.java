package hu.cowork.advertising.service;

import hu.cowork.advertising.entity.Advertising;
import hu.cowork.advertising.mapper.AdvertisingMapper;
import hu.cowork.advertising.model.AdvertisingDto;
import hu.cowork.advertising.model.PageDto;
import hu.cowork.advertising.model.PageResultDto;
import hu.cowork.advertising.repository.AdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdvertisingService {

    private final AdvertisingRepository advertisingRepository;
    private final AdvertisingSearchService advertisingSearchService;
    private final AdvertisingMapper advertisingMapper;

    List<AdvertisingDto> visitedAdvertisings = new LinkedList<>();
    public AdvertisingDto getAdvertising(Long id) {
        AdvertisingDto advertisingDto = advertisingMapper.toDto(advertisingRepository.findById(id).get());
        if (visitedAdvertisings.isEmpty()) {
            visitedAdvertisings.add(advertisingDto);
            return advertisingDto;
        }
        if (visitedAdvertisings.contains(advertisingDto)) {
            visitedAdvertisings.remove(advertisingDto);
        }
        visitedAdvertisings.add(advertisingDto);
        return advertisingDto;
    }

    public List<AdvertisingDto> getUserAdvertising(Long userId) {
        return advertisingRepository.findAllByUserId(userId).stream()
                .map(advertisingMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisingDto> getFavoriteAds() {
        return advertisingRepository.findAllByIsFavorite(true).stream()
                .map(advertisingMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AdvertisingDto> getHistory() {
        return visitedAdvertisings;
    }

    public AdvertisingDto createAdvertising(AdvertisingDto advertisingDto) {
        Advertising savedAdvertising = advertisingMapper.toEntity(advertisingDto);
        return advertisingMapper.toDto(advertisingRepository.save(savedAdvertising));
    }

    public PageResultDto searchAdvertising(PageDto pageDto) {
        return advertisingSearchService.search(pageDto);
    }

    public AdvertisingDto updateAdvertising(AdvertisingDto advertisingDto) {
        Advertising updatedAdvertising = advertisingMapper.toEntity(advertisingDto);
        return advertisingMapper.toDto(advertisingRepository.save(updatedAdvertising));
    }

    public AdvertisingDto setAdvertisingStatus(Long id) {
        Optional<Advertising> expectedAdvertising = Optional.of(advertisingRepository.findById(id).get());
        expectedAdvertising.get().setIsActive(
                !expectedAdvertising.get().getIsActive());
        return advertisingMapper.toDto(advertisingRepository.save(expectedAdvertising.get()));
    }

    public AdvertisingDto setAdFavoriteStatus(Long id) {
        Optional<Advertising> expectedAdvertising = Optional.of(advertisingRepository.findById(id).get());
        expectedAdvertising.get().setIsFavorite(
                !expectedAdvertising.get().getIsFavorite());
        return advertisingMapper.toDto(advertisingRepository.save(expectedAdvertising.get()));
    }

    public AdvertisingDto deleteAdvertising(Long id) {
        AdvertisingDto advertisingDto = advertisingMapper.toDto(advertisingRepository.findById(id).get());
        advertisingRepository.deleteById(id);
        return advertisingDto;
    }
}