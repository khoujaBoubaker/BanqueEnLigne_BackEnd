package mbdemoapp.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Administrateur implements UserDetails { 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private String email;
	
	private String addresse;
	
	private String poste;
	
	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	@Lob
	private byte[] pic;
	
	@Temporal(TemporalType.DATE)
	private Date dateDeNaissance;
	
	@Column(unique=true)
	private String username;
	
	
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles=new ArrayList<Role>();
	
	@Temporal(TemporalType.DATE)
	private Date dateDeCreation;
	
	@OneToMany(fetch=FetchType.LAZY)
	private List<Client> clients;
	
   public List<Client> getClients() {
		List<Client> cls=new ArrayList<Client>();
		return cls;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

public Administrateur() {}
   
   public Administrateur(String nom, String prenom, String email, Date dateDeNaissance, String username, String password,
			Date dateDeCreation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.username = username;
		this.password = password;
		this.dateDeCreation = dateDeCreation;
	}




  


	public Administrateur(int id, String nom, String prenom, String email, Date dateDeNaissance, String login,
		String password, Date dateDeCreation, List<Client> clients) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.dateDeNaissance = dateDeNaissance;
	this.username = login;
	this.password = password;
	this.dateDeCreation = dateDeCreation;
	this.clients = clients;
}



	@Override
	public String toString() {
		return "Administrateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email
				+ ", dateDeNaissance=" + dateDeNaissance + ", username=" + username + ", password=" + password
				+ ", dateDeCreation=" + dateDeCreation + "]";
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}






	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}



	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}







	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}







	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}







	public String getEmail() {
		return email;
	}







	public void setEmail(String email) {
		this.email = email;
	}







	public Date getDateDeCreation() {
		return dateDeCreation;
	}







	public void setDateDeCreation(Date dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

}
