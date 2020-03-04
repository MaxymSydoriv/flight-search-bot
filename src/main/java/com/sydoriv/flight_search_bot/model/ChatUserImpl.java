package com.sydoriv.flight_search_bot.model;

import com.botscrew.messengercdk.model.MessengerUser;
import com.sydoriv.flight_search_bot.domain.UserState;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CHAT_USER")
@Data
public class ChatUserImpl implements MessengerUser {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CHAT_ID", unique = true)
    private Long chatId;

    @Column(name = "PAGE_ID")
    private Long pageId;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @OneToMany(mappedBy = "chatUser", fetch = FetchType.LAZY)
    private Set<CurrentSearchParameters> searchParameters;

    @Override
    public Long getChatId() {
        return this.chatId;
    }

    @Override
    public String getState() {
        return UserState.DEFAULT.getStringName();
    }


}
