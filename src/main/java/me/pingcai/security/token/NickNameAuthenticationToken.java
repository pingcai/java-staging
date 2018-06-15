package me.pingcai.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by pingcai at 2018/6/13 14:53
 */
public class NickNameAuthenticationToken extends AbstractAuthenticationToken {

    private Object principal;

    private Object credentials;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public NickNameAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public NickNameAuthenticationToken(String nickname, String password) {
        super(null);
        principal = nickname;
        credentials = password;
        setAuthenticated(false);
    }

    public NickNameAuthenticationToken(Object principal, Object credentials,
                                       Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
