package csaba79coder.webspringcaptcha.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {

    private String username;
    private String password;
    // private String email;
}
