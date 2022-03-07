package csaba79coder.webspringcaptcha.model;

public enum Role {

    ROLE_USER(UserAuthority.READ),
    ROLE_ADMIN(UserAuthority.CREATE, UserAuthority.READ, UserAuthority.UPDATE, UserAuthority.DELETE);


    public final UserAuthority[] AUTHS;

    Role(UserAuthority... auths) {
        AUTHS = auths;
    }
}
