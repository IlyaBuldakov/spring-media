package finalproject.infrastructure.repositories;


import finalproject.domain.entities.notifications.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRepository extends JpaRepository<Notification, Integer> {
}
