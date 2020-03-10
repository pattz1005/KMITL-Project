package L10_2;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class DomainObject implements Cloneable
{
    protected Long id;
    protected int version;
    protected Date createDate;
    protected Date modifiedDate;

    @Id
    @GeneratedValue
    public Long getId()
    {
        return this.id;
    }

    protected void setId(Long id)
    {
        this.id = id;
    }

    @Version
    public int getVersion()
    {
        return version;
    }

    protected void setVersion(int version)
    {
        this.version = version;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate()
    {
        return createDate;
    }

    protected void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifiedDate()
    {
        return modifiedDate;
    }

    protected void setModifiedDate(Date modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    @PrePersist
    protected void handleCreateDate() {
        setCreateDate(new Date());
        handleModifiedDate();
    }

    @PreUpdate
    protected void handleModifiedDate()
    {
        setModifiedDate(new Date());
    }

    public Object clone() throws CloneNotSupportedException {
        DomainObject domainObject = (DomainObject) super.clone();
        domainObject.setId(null);
        domainObject.setVersion(0);

        return domainObject;
    }
}