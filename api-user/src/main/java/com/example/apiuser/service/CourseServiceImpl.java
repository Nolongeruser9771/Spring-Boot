package com.example.apiuser.service;

import com.example.apiuser.entity.Course;
import com.example.apiuser.entity.User;
import com.example.apiuser.exception.NotFoundException;
import com.example.apiuser.model.CourseDTO;
import com.example.apiuser.model.CourseDTOMapper;
import com.example.apiuser.model.CoursePageDTO;
import com.example.apiuser.model.UpsertCourseRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {

    //create userdata
    User u1 = new User("Json", "Json@gmail.com","0704390011","");
    User u2 = new User("Watson", "Watson@gmail.com","1704690011","");
    User u3 = new User("Lisa", "Lisa@gmail.com","0704290011","");
    User u4 = new User("Time", "Time@gmail.com","0704290011","");
    User u5 = new User("Tyliza", "Tyliza@gmail.com","0404690011","");

    List<User> users = new ArrayList<>(Arrays.asList(u1, u2, u3, u4, u5));

    //create course data
    Course c1 = new Course("Course 1", "course 1","online", Arrays.asList("java","backend"),"",1);
    Course c2 = new Course("Course 2", "course 2","onlab", Arrays.asList("javascript","frontend"),"",2);
    Course c3 = new Course("Course 3", "course 3","online", Arrays.asList("reactjs"),"",3);
    Course c4 = new Course("Course 4", "course 4","onlab", Arrays.asList("PHP","backend"),"",4);
    Course c5 = new Course("Course 5", "course 5","online", Arrays.asList("java","C++"),"",5);

    List<Course> courses = new ArrayList<>(Arrays.asList(c1,c2,c3,c4,c5));

    @Override
    public List<CourseDTO> getListByPara(String name, String type, String topic) {
        List<Course> courseList = new ArrayList<>();
        List<CourseDTO> result = new ArrayList<>();

        //chưa nghĩ ra cách tốt hơn...
        if (name==null && type==null && topic==null) {
            return getAll();
        }

        if (name!=null) {
            findCourseByName(name, courseList);
        } else {
            courseList.addAll(courses);
        }

        if (courseList.size()>0 && type!=null) {
            findCourseByType(type, courseList);
        }

        if (courseList.size()>0 && topic!=null) {
            findCourseByTopic(topic, courseList);
        }

        if (courseList.size()>0) {
            for (Course course:courseList) {
                result.add(CourseDTOMapper.toCourseDTOMapper(course,getUserById(course.getUserId())));
            }
            return result;
        }
        return new ArrayList<>();
    }

    //helper function
    private void findCourseByName(String name, List<Course> courseList) {
        for (Course course:courses) {
            if (course.getName().compareToIgnoreCase(name)==0) {
                courseList.add(course);
            }
        }
    }

    private void findCourseByType(String type, List<Course> courseList) {
        courseList.removeIf(course -> !course.getType().equals(type));
    }

    private void findCourseByTopic(String topic, List<Course> courseList) {
        courseList.removeIf(course -> !course.getTopics().contains(topic));
    }

    @Override
    public User getUserById(Integer id) {
        for (User user:users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new NotFoundException("Not found user " + id);
    }

    @Override
    public List<CourseDTO> getAll() {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course:courses) {
            courseDTOList.add(CourseDTOMapper.toCourseDTOMapper(course,getUserById(course.getUserId())));
        }
        return courseDTOList;
    }

    @Override
    public CourseDTO getCourseById(Integer id) {
        for (Course course: courses) {
            if (course.getId().equals(id)) {
                return CourseDTOMapper.toCourseDTOMapper(course,getUserById(course.getUserId()));
            }
        }
        throw new NotFoundException("Not found course ID = "+ id);
    }

    @Override
    public CourseDTO addNewCourse(UpsertCourseRequest req) {
        Course newCourse = new Course(req.getName(),
                req.getDescription(),
                req.getType(),
                req.getTopics(),
                req.getThumbnail(),
                req.getUserId());

        return CourseDTOMapper.toCourseDTOMapper(newCourse,getUserById(req.getUserId()));
    }

    @Override
    public CourseDTO updatedCourse(UpsertCourseRequest req, Integer id) {
        for (Course course:courses){
            if (course.getId().equals(id)) {
                course.setName(req.getName());
                course.setDescription(req.getDescription());
                course.setType(req.getType());
                course.setTopics(req.getTopics()!=null? req.getTopics() : course.getTopics());
                course.setThumbnail(req.getThumbnail()!=null? req.getThumbnail() : course.getThumbnail());
                course.setUserId(req.getUserId());

                return CourseDTOMapper.toCourseDTOMapper(course,getUserById(req.getUserId()));
            }
        }

        throw new NotFoundException("Not Found course ID = " + id);
    }

    @Override
    public void deleteCourse(Integer id) {
        courses.removeIf(course -> course.getId().equals(id));
        throw new NotFoundException("Not found course id "+id);
    }

    @Override
    public CoursePageDTO getCourseByPage(Integer page, Integer pageSize) {
        List<CoursePageDTO> coursePageDTOList = pageDivider(pageSize);
        return coursePageDTOList.get(page-1);
    }

    public List<CoursePageDTO> pageDivider(Integer pageSize) {
        Integer totalPage = courses.size()%pageSize!=0?
                            (courses.size()/pageSize)+1:
                            (courses.size()/pageSize);
        int n = 0;

        List<CoursePageDTO> coursePageList = new ArrayList<>();
        //n=0, totalPage = 3;
        while (n<=totalPage) {
            //list page
            CoursePageDTO coursePageDTO = new CoursePageDTO();
            coursePageDTO.setCurrentPage(n+1); //page = 1
            coursePageDTO.setPageSize(pageSize); //pageSize = 2
            coursePageDTO.setTotalPages(totalPage); //totalPage = 3
            coursePageDTO.setTotalItems(courses.size()); // totalItems = 5

            //data field
            List<CourseDTO> courseList = new ArrayList<>();
            for (int i = 0; i < pageSize; i++) {
                //out of index :
                if (courses.size()-(n*pageSize+i) <= 0) {
                    break;
                }
                Course foundedCourse = courses.get(pageSize * n + i); //1 2, 3 4
                courseList.add(CourseDTOMapper.toCourseDTOMapper(foundedCourse,getUserById(foundedCourse.getUserId())));
            }
            coursePageDTO.setData(courseList);
            coursePageList.add(coursePageDTO);
            n++;
        }
        return coursePageList;
    }
}
