package SLIT;


/**
 * This is a data class. When we start using a real database this will
 * be marked as an entity class, and there will be a corresponding
 * Student table in the database
 *
 * @author evenal
 */
public class Student
{
    private static long nextId = 1;
    private final long id;
    private String name;
    private String mail;
    private String karakter;

    public Student(String name, String mail, String karakter) {
        id = nextId++;
        this.name = name;
        this.mail = mail;
        this.karakter = karakter;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final Student other = (Student) obj;
        if (this.id != other.id) return false;
        return true;
    }

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
