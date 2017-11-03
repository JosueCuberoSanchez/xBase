package cr.ac.ucr.ecci.ci1312.xbase.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Class that represents a user within the application, a user has a login and
 * password and all the information inherited from the
 * {@link cr.ac.ucr.ecci.ci1312.xbase.model.BasicEntity} class.
 *
 * @author Rodrigo A. Bartels
 */
@MappedSuperclass
public abstract class User extends Person implements UserDetails {

    public enum Status {ACTIVE, INACTIVE, SUSPENDED}

    /**
     * Username, it can't be empty, null and can't have duplicates. It will be
     * the user's email address.
     */
    @Column(name = "username", unique = true, nullable = false)
    protected String username;

    /**
     * Password (will be clear for transient instances of User and encrypted
     * for persistent instances).
     */
    @Column(name = "password", nullable = false)
    protected String password;

    /**
     * Whether the user is enabled (allowed to log in) or not in the
     * application.
     */
    @Column(name = "enabled")
    protected boolean enabled;

    /**
     * Whether or not the user needs to change their password.
     */
    @Column(name = "password_needs_change")
    protected boolean passwordNeedsChange;

    /**
     * Timestamp that marks the last login.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_timestamp")
    private Date lastLoginTimestamp;

    /**
     * The user account status
     *
     * @see Status
     */
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * WARNING - A transient user's password will appear clear. All persisted
     * users passwords will be encrypted.
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns <code>true</code> if the user has been activated,
     * <code>false</code> otherwise.
     *
     * @return whether the user is enabled or not.
     */
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getPasswordNeedsChange() {
        return passwordNeedsChange;
    }

    public void setPasswordNeedsChange(boolean passwordNeedsChange) {
        this.passwordNeedsChange = passwordNeedsChange;
    }

    /**
     * @return array of granted authorities.
     */
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>();
        if (this.enabled)
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    /**
     * TODO: implement
     *
     * @return whether the account has expired or not.
     */
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * TODO: implement
     *
     * @return whether the account is locked or not.
     */
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * TODO: implement
     *
     * @return whether the credentials expire or not.
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean onEquals(Object o) {
        if (o instanceof User) {
            User u = (User) o;
            return username == null ? u.getUsername() == null : username.equals(u.getUsername()) &&
                    password == null ? u.getPassword() == null : password.equals(u.getPassword());
        }
        return false;
    }

    public int onHashCode(int result) {
        result = 29 * result + (username != null ? username.hashCode() : 0);
        result = 29 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    protected StringBuilder toStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append(", username = ").append(getUsername());
        sb.append(", enabled = ").append(isEnabled());
        sb.append(", passwordNeedChange = ").append(getPasswordNeedsChange());
        return sb;
    }
    
    public Date getLastLoginTimestamp() {
        return lastLoginTimestamp;
    }

    public void setLastLoginTimestamp(Date lastLoginTimestamp) {
        this.lastLoginTimestamp = lastLoginTimestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
