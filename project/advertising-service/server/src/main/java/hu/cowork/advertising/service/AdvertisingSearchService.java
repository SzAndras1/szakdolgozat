package hu.cowork.advertising.service;

import hu.cowork.advertising.entity.Advertising;
import hu.cowork.advertising.mapper.AdvertisingMapper;
import hu.cowork.advertising.model.PageDto;
import hu.cowork.advertising.model.PageFilterDto;
import hu.cowork.advertising.model.PageResultDto;
import hu.cowork.advertising.repository.AdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class AdvertisingSearchService {

    private final AdvertisingRepository advertisingRepository;
    private final AdvertisingMapper advertisingMapper;

    @Transactional
    public PageResultDto search(PageDto pageDto) {
        handleDefaultValues(pageDto);
        Map<String, String> filters = pageDto.getFilters().stream()
                .collect(Collectors.toMap(PageFilterDto::getField, PageFilterDto::getFilter));

        Page<Advertising> searchHits = advertisingRepository.search(
                filters.getOrDefault("id", null),
                filters.getOrDefault("userId", null),
                filters.getOrDefault("email", null),
                filters.getOrDefault("text", null),
                filters.getOrDefault("priority",null),
                filters.getOrDefault("address", null),
                Boolean.valueOf(filters.getOrDefault("isActive", null)),
                PageRequest.of(pageDto.getPage(), pageDto.getSize())
        );

        return new PageResultDto()
                .content(searchHits.get()
                        .map(advertisingMapper::toDto)
                        .collect(toList()))
                .totalElements(searchHits.getTotalElements());
    }
    
    private void handleDefaultValues(PageDto pageDto) {
        if(pageDto.getPage() == null) {
            pageDto.setPage(0);
        }
        if(pageDto.getSize() == null) {
            pageDto.setSize(20);
        }
        if(pageDto.getFilters() == null) {
            pageDto.setFilters(emptyList());
        }
    }
}
