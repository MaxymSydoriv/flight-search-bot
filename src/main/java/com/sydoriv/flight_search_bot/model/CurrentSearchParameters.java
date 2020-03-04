package com.sydoriv.flight_search_bot.model;

import com.sydoriv.flight_search_bot.domain.RequestStatus;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "CURRENT_SEARCH_PARAMETERS")
@Data
public class CurrentSearchParameters {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DEPARTURE")
    private String departure;

    @Column(name = "ARRIVAL")
    private String arrival;

    @Column(name = "DATE", columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_USER_ID", nullable = false)
    private ChatUserImpl chatUser;

    public static CurrentSearchParameters emptyWithStatus(RequestStatus requestStatus) {
        CurrentSearchParameters searchParameters = new CurrentSearchParameters();
        searchParameters.setRequestStatus(requestStatus);
        return searchParameters;
    }
}
