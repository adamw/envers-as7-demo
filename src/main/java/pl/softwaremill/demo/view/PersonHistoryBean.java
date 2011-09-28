package pl.softwaremill.demo.view;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.query.AuditEntity;
import org.jboss.seam.transaction.Transactional;
import pl.softwaremill.demo.domain.Person;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Warski (adam at warski dot org)
 */
@Transactional
@Named
@RequestScoped
public class PersonHistoryBean {
    @Inject
    private EntityManager entityManager;

    private List<PersonHistoryEntry> list;

    public List<PersonHistoryEntry> getList() {
        if (list == null) {
            AuditReader auditReader = AuditReaderFactory.get(entityManager);
            @SuppressWarnings({"unchecked"}) List<Object[]> revDatas = (List<Object[]>) auditReader
                    .createQuery()
                    .forRevisionsOfEntity(Person.class, false, false)
                    .add(AuditEntity.id().eq(id))
                    .getResultList();

            list = new ArrayList<PersonHistoryEntry>();
            for (Object[] revData : revDatas) {
                list.add(new PersonHistoryEntry((Person) revData[0], (DefaultRevisionEntity) revData[1]));
            }
        }

        return list;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static class PersonHistoryEntry {
        private final Person person;
        private final String changeDate;

        public PersonHistoryEntry(Person person, DefaultRevisionEntity revision) {
            this.person = person;
            this.changeDate = DateFormat.getTimeInstance().format(revision.getRevisionDate());
        }

        public String getChangeDate() {
            return changeDate;
        }

        public Person getPerson() {
            return person;
        }
    }
}
