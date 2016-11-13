package com.fi.ls.facade;

import com.fi.ls.dto.lecture.LectureCreateDTO;
import com.fi.ls.dto.lecture.LectureDTO;
import com.fi.ls.entity.Lecture;
import com.fi.ls.mapping.BeanMapping;
import com.fi.ls.service.LectureService;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marek Nedbal (357293)
 */
@Service
@Transactional
public class LectureFacadeImpl implements LectureFacade {
    
    @Inject
    private LectureService lectureService;
    
    @Inject
    private BeanMapping beanMapping;
    
    @Override
    public void createLecture(LectureCreateDTO lecture) {
        
        lectureService.create(beanMapping.mapTo(lecture, Lecture.class).get());
        
    }

    @Override
    public Optional<LectureDTO> getLectureById(Long id) {
        
        Optional<Lecture> lecture = Optional.of(lectureService.findById(id));
        return lecture.isPresent() ? beanMapping.mapTo(lecture.get(), LectureDTO.class) : Optional.empty();
        
    }

    @Override
    public List<LectureDTO> getAllLectures() {
        
        return beanMapping.mapTo(lectureService.findAll(), LectureDTO.class);
        
    }

    @Override
    public Optional<LectureDTO> updateLecture(Long id) {
        
        Optional<Lecture> lecture = Optional.of(lectureService.update(lectureService.findById(id)));
        return lecture.isPresent() ? beanMapping.mapTo(lecture.get(), LectureDTO.class) : Optional.empty();
        
    }

    @Override
    public void deleteLecture(Long id) {
        
        lectureService.remove(lectureService.findById(id));
        
    }
    
}
