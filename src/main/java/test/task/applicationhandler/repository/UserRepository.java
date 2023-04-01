package test.task.applicationhandler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.applicationhandler.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
