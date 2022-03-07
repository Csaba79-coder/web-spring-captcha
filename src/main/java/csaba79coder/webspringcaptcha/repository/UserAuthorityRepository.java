package csaba79coder.webspringcaptcha.repository;

import csaba79coder.webspringcaptcha.model.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}
