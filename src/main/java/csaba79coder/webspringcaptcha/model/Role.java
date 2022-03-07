package csaba79coder.webspringcaptcha.model;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_USER(RoleType.READ),
    ROLE_ADMIN(RoleType.CREATE, RoleType.READ, RoleType.UPDATE, RoleType.DELETE);


    public final RoleType[] AUTHS;

    Role(RoleType... auths) {
        AUTHS = auths;
    }
}
