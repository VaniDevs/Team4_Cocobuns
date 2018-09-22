package com.swiftavenue.vanhacks2018.repositories.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.swiftavenue.vanhacks2018.domain.Message;
import org.springframework.jdbc.core.RowMapper;

public class MessageRowMapper implements RowMapper<Message> {
  @Override
  public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
    Message message = new Message();
    message.setMessage(rs.getString("message"));
    return message;
  }
}
