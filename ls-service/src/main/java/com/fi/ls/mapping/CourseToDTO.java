package com.fi.ls.mapping;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

/**
 *
 * @author Lukas Daubner (410034)
 */
public class CourseToDTO extends PropertyMap<Course, CourseDTO> {

    @Override
    protected void configure() {
        using(lectureConverter).map(source.getListOfLectures()).setListOfLectures(null);
    }
    
    Converter<List<Lecture>, List<LectureDTO>> lectureConverter = new AbstractConverter<List<Lecture>, List<LectureDTO>>() {
        @Override
        protected List<LectureDTO> convert(List<Lecture> s) {
            List<LectureDTO> lectures = new ArrayList<>();
            for(Lecture lecture : s) {
                LectureDTO lectureDTO = new LectureDTO();
                lectureDTO.setId(lecture.getId());
                lectures.add(lectureDTO);
            }
            return lectures;
        }
    };
}
