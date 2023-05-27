package com.lunici.structmap;

public class Main {
    public static void main(String[] args) {
        StudentMapper studentMapper;

        Student student = new Student();
        student.setId(1);
        student.setName("hang");

        StudentDto studentDto = studentMapper.toDto(student);
    }
}
