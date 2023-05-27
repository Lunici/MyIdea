package com.lunici.structmap;

import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    StudentDto toDto(Student student);
}
