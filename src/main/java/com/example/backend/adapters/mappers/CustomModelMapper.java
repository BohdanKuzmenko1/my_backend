package com.example.backend.adapters.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * У параметрі <T> передається ентіті об'єкт,
 * в свою чергу у <V> параметр підставляється
 * DTO об'єкт.
 */

@Component
public class CustomModelMapper<T, V> {
    private final ModelMapper modelMapper;

    public CustomModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public V toDTO(T entity, Class<V> classDTO) {
        return modelMapper.map(entity, classDTO);
    }

    public T toEntity(V dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public List<V> listToDTO(List<T> entities, Class<V> dtoClass) {
        return entities.stream()
                .map(entity -> toDTO(entity, dtoClass))
                .collect(Collectors.toList());
    }
}
