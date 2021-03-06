package com.firstSpring.wahkit.dao;

import com.firstSpring.wahkit.model.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {
    String TABLE_NAME = "message";
    String INSERT_FIELDS = "from_id,to_id,content,created_date,has_read,conversation_id";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{fromId},#{toId},#{content},#{createdDate},#{hasRead},#{conversationId})"})
    int addMessage(Message message);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where conversation_id=#{conversationId} order by created_date desc limit #{offset},#{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
                                                @Param("offset") int offset,
                                                @Param("limit") int limit);
    @Select({"select",INSERT_FIELDS,",count(id) as id from (select *from",TABLE_NAME,"where from_id=#{userId} or to_id =#{userId} order by created_date desc) tt group by conversation_id order by created_date desc limit #{offset},#{limit}"})
    List<Message> getConvesationList(@Param("userId") int userId,
                                     @Param("offset") int offset,
                                     @Param("limit") int limit);
    @Select({"select count(id) from",TABLE_NAME,"where has_read = 0 and to_id = #{userId} and conversation_id = #{conversationId}"})
    int getConversationUnreadCount(@Param("userId") int userId,@Param("conversationId") String conversationId);

    @Update({"update",TABLE_NAME,"set has_read = 1 where conversation_id=#{conversationId} and has_read=0"})
    int udateHasRead(@Param("conversationId") String conversationId);

}
