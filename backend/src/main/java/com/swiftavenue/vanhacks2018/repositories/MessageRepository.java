package com.swiftavenue.vanhacks2018.repositories;

import java.util.List;
import com.swiftavenue.vanhacks2018.domain.Message;
import com.swiftavenue.vanhacks2018.repositories.mappers.MessageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  public void save(Message msg) {
    jdbcTemplate.update("INSERT INTO message(id, message) values (nextval('message_seq'), ?)", msg.getMessage());
  }

  public List<Message> getMessages() {
    List<Message> messages = jdbcTemplate.query("SELECT * FROM message", new MessageRowMapper());
    return messages;
  }
}
