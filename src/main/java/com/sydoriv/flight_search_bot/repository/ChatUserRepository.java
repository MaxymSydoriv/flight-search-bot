package com.sydoriv.flight_search_bot.repository;

import com.botscrew.botframework.domain.user.ChatUser;
import com.sydoriv.flight_search_bot.model.ChatUserImpl;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUserImpl, Long> {

    List<ChatUserImpl> findByChatIdAndPageId(Long chatId, Long pageId);
}
