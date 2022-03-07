package csaba79coder.webspringcaptcha.repository;


import csaba79coder.webspringcaptcha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
