package com.soprasteria.tse.atelierdevops.services.mappers;

import com.soprasteria.tse.atelierdevops.models.dto.TaskResponse;
import com.soprasteria.tse.atelierdevops.models.entity.Task;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Named(value = "toTaskSimpleResponse")
    TaskResponse toSimpleResponse(Task task);

    @Named("toTaskSimpleResponseList")
    @IterableMapping(qualifiedByName = "toTaskSimpleResponse")
    List<TaskResponse> toSimpleResponseList(List<Task> tasks);
}
