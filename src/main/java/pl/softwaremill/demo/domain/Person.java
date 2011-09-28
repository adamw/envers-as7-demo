package pl.softwaremill.demo.domain;
import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

@Entity
public class Person implements java.io.Serializable {
  private static final long serialVersionUID=1L;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id",updatable=false,nullable=false)
  private Long id=null;

  @Version
  @Column(name="version")
  private int version=0;

  @Column
  private String firstName;

  @Column
  private String lastName;

  public Long getId(){
    return this.id;
  }
  public void setId(  final Long id){
    this.id=id;
  }
  public int getVersion(){
    return this.version;
  }
  public void setVersion(  final int version){
    this.version=version;
  }

  public String getFirstName(){
    return this.firstName;
  }
  public void setFirstName(  final String firstName){
    this.firstName=firstName;
  }

  public String getLastName(){
    return this.lastName;
  }
  public void setLastName(  final String lastName){
    this.lastName=lastName;
  }
}
