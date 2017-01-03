import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.JoinColumn;
@Entity
@Table(name="EMPLOYEES")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="employeecache")


public class Employee {

	@Id
	@Column(name="MID")
	private String mid;
	
	@Column(name="NAME")
	private String name;
	@Column(name="JOIN_DATE")
	@Temporal(TemporalType.DATE)
	
	Date joinDate;
	@Column(name="EMAIL_ID")
	private String emailId;
	

	
	@ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "EventReg",joinColumns = {
	             @JoinColumn(name = "MID",updatable = false)},
	             inverseJoinColumns = { @JoinColumn(name = "EVENT_ID",updatable = false)}
	    )
	 private Set<Event> events;
	 
	 
	 
	
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
	
	
}
