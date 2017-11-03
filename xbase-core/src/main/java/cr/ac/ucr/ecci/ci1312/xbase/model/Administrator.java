package cr.ac.ucr.ecci.ci1312.xbase.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

/**
 * Class that represents an administrator of the application.
 * An administrator has an id and a list of
 * @author Rodrigo A. Bartels
 */
@Entity
@Table(name = "administrators")
public class Administrator extends User{

    @Column(name = "admin_id", unique = true)
    private String adminID;

    @OneToMany(mappedBy = "administrator")
    private List<LogEntry> associatedLogs = new LinkedList<>();

    @Transient
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>(super.getAuthorities());
        if (this.enabled)
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public List<LogEntry> getAssociatedLogs() {
        return associatedLogs;
    }

    public void setAssociatedLogs(List<LogEntry> associatedLogs) {
        this.associatedLogs = associatedLogs;
    }

    public void addLog(LogEntry logEntry){
        associatedLogs.add(logEntry);
    }

}
