package com.fi.ls.mapping;

import com.fi.ls.dto.course.CourseDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Course;
import com.fi.ls.entity.Lecture;
import com.fi.ls.service.LectureService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Named
public class CourseFromDTO extends PropertyMap<CourseDTO, Course> {

    @Inject
    LectureService lectureService;
    
    @Override
    protected void configure() {
        using(lectureConverter).map(source.getListOfLectures()).setListOfLectures(null);
    }
    
    Converter<List<LectureDTO>, List<Lecture>> lectureConverter = new AbstractConverter<List<LectureDTO>, List<Lecture>>() {
        @Override
        protected List<Lecture> convert(List<LectureDTO> s) {
            List<Lecture> lectures = new ArrayList<>();
            for(LectureDTO lectureDTO : s) {
                Lecture lecture = lectureService.findById(lectureDTO.getId());
                lectures.add(lecture);
            }
            return lectures;
        }
    };
}
