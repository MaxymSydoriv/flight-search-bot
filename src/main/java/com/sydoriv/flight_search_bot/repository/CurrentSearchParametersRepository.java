package com.sydoriv.flight_search_bot.repository;

import com.sydoriv.flight_search_bot.domain.RequestStatus;
import com.sydoriv.flight_search_bot.model.CurrentSearchParameters;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrentSearchParametersRepository extends JpaRepository<CurrentSearchParameters, Long> {

    @Query("FROM CurrentSearchParameters AS csp JOIN csp.chatUser AS cu WHERE cu.id=?1 AND csp.requestStatus=?2")
    List<CurrentSearchParameters> findByChatUserIdAndStatus(Long chatUserId, RequestStatus requestStatus);
}
