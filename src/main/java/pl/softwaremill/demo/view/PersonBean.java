package pl.softwaremill.demo.view;
import java.util.List;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import pl.softwaremill.demo.domain.Person;
import org.metawidget.forge.navigation.MenuItem;
import org.metawidget.forge.persistence.PaginationHelper;
import org.metawidget.forge.persistence.PersistenceUtil;
import org.jboss.seam.transaction.Transactional;
@Transactional @Named @RequestScoped public class PersonBean extends PersistenceUtil implements MenuItem {
  private static final long serialVersionUID=1L;
  private List<Person> list=null;
  private Person person=new Person();
  private long id=0;
  private PaginationHelper<Person> pagination;
  public Class<?> getItemType(){
    return Person.class;
  }
  public String getLiteralPath(){
    return null;
  }
  public String getLabel(){
    return null;
  }
  public void load(){
    person=findById(Person.class,id);
  }
  public String create(){
    create(person);
    return "view?faces-redirect=true&id=" + person.getId();
  }
  public String delete(){
    delete(person);
    return "list?faces-redirect=true";
  }
  public String save(){
    save(person);
    return "view?faces-redirect=true&id=" + person.getId();
  }
  public long getId(){
    return id;
  }
  public void setId(  long id){
    this.id=id;
    if (id > 0) {
      load();
    }
  }
  public Person getPerson(){
    return person;
  }
  public void setPerson(  Person person){
    this.person=person;
  }
  public List<Person> getList(){
    if (list == null) {
      list=getPagination().createPageDataModel();
    }
    return list;
  }
  public void setList(  List<Person> list){
    this.list=list;
  }
  public PaginationHelper<Person> getPagination(){
    if (pagination == null) {
      pagination=new PaginationHelper<Person>(10){
        @Override public int getItemsCount(){
          return count(Person.class);
        }
        @Override public List<Person> createPageDataModel(){
          return new ArrayList<Person>(findAll(Person.class,getPageFirstItem(),getPageSize()));
        }
      }
;
    }
    return pagination;
  }
  public void setPagination(  final PaginationHelper<Person> helper){
    pagination=helper;
  }
}
