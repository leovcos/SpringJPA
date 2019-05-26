package br.gov.sp.fatec.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.view.View;

@Entity
@Table(name = "heros")
public class Hero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonView({View.HeroShort.class, View.HeroComplete.class})
	private Integer id;
    
    @Column(name = "name", length = 255, nullable = false)
    @JsonView({View.HeroShort.class, View.HeroCompleteExceptId.class})
    private String name;
    
    @Column(name = "power", length = 11, nullable = false)
    @JsonView({View.HeroShort.class, View.HeroCompleteExceptId.class})
    private Integer power;
    
    @Column(name = "image", length = 255, nullable = true)
    @JsonView({View.HeroShort.class, View.HeroCompleteExceptId.class})
    private String image;
    
    @Column(name = "birthday", nullable = true)
    @JsonView({View.HeroShort.class, View.HeroCompleteExceptId.class})
    private Date birthday;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quirk_id", nullable = false)
    @JsonView({View.HeroComplete.class, View.HeroCompleteExceptId.class})
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Quirk quirk;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "classroom_id", nullable = false)
    @JsonView({View.HeroComplete.class, View.HeroCompleteExceptId.class})
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Classroom classroom;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Quirk getQuirk() {
		return quirk;
	}

	public void setQuirk(Quirk quirk) {
		this.quirk = quirk;
	}

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public int getPower() {
		return this.power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

}
