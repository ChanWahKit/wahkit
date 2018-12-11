package com.firstSpring.wahkit.dao;

import com.firstSpring.wahkit.model.Question;
import com.firstSpring.wahkit.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    String TABLE_NAME = "question";
    String INSERT_FIELDS = "title,content,user_id,created_date,comment_count";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{title},#{content},#{userId},#{createdDate},#{commentCount})"})
    int addQuestion(Question question);


    @Select({"select",SELECT_FIELDS,"from",TABLE_NAME,"where id = #{id}"})
    Question selectQuestion(int id);

    List<Question> selectLastQuestions(@Param("userId") int userId,
                                      @Param("offset") int offset,
                                      @Param("limit") int limit);

    @Update({"update",TABLE_NAME,"set comment_count = #{count} where id=#{questionId}"})
    int updateCommentCount(@Param("questionId") int questionId,
                           @Param("count") int count);

}
