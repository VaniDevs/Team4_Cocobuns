package com.swiftavenue.vanhacks2018.services;

import java.util.List;
import com.swiftavenue.vanhacks2018.domain.Message;
import com.swiftavenue.vanhacks2018.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {

  @Autowired
  private MessageRepository repo;

  public List<Message> getMessages() {
    return repo.getMessages();
  }

  public void processMessage(Message msg) {
    repo.save(msg);
  }

}
