package br.com.example.model;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.String; 
import java.lang.String; 
import java.lang.Integer; 
import java.util.Date; 


@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "age")
    private Integer age;

    @Column(name = "dataCreated")
    private Date dataCreated;

	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setMail(String mail) {this.mail = mail;}
	public String getMail() {return mail;}
	public void setAge(Integer age) {this.age = age;}
	public Integer getAge() {return age;}
	public void setDataCreated(Date dataCreated) {this.dataCreated = dataCreated;}
	public Date getDataCreated() {return dataCreated;}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
}