package com.sydoriv.flight_search_bot.service;

import com.botscrew.botframework.annotation.ChatEventsProcessor;
import com.botscrew.botframework.annotation.Text;
import com.botscrew.nlpclient.provider.NlpClient;
import com.sydoriv.flight_search_bot.model.ChatUserImpl;
import org.springframework.beans.factory.annotation.Autowired;

@ChatEventsProcessor
public class FacebookEventHandler {

    @Autowired
    private NlpClient nlpClient;

    @Text(states = "DEFAULT")
    public void handleUserInput(ChatUserImpl chatUser, @Text String text) {
        nlpClient.query(chatUser, text, chatUser.getSessionId());
    }
}
