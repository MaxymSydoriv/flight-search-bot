package com.sydoriv.flight_search_bot.service;

import com.botscrew.messengercdk.model.MessengerUser;
import com.botscrew.messengercdk.service.UserProvider;
import com.sydoriv.flight_search_bot.model.ChatUserImpl;
import com.sydoriv.flight_search_bot.repository.ChatUserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserProvider {

    private final ChatUserRepository chatUserRepository;

    @Override
    public MessengerUser getByChatIdAndPageId(Long chatId, Long pageId) {
        return chatUserRepository.findByChatIdAndPageId(chatId, pageId).stream()
            .findFirst()
            .orElse(chatUserRepository.saveAndFlush(constructNewChatUser(chatId, pageId)));
    }

    private ChatUserImpl constructNewChatUser(Long chatId, Long pageId) {
        ChatUserImpl newChatUser = new ChatUserImpl();
        newChatUser.setChatId(chatId);
        newChatUser.setPageId(pageId);
        newChatUser.setSessionId(UUID.randomUUID().toString());
        return newChatUser;
    }
}
