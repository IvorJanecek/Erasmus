package frontend.Erasmus.model;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ugovori")
@AllArgsConstructor
public class Ugovori {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    public Ugovori() {
    }
    public Ugovori(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
}