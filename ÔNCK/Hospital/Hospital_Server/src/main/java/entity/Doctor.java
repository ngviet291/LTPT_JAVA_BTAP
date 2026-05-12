package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Setter
@Getter
//@ToString(exclude = "treatments")
@NoArgsConstructor
@SuperBuilder
public class Doctor extends Person{
    private  String speciality;
    @OneToMany(mappedBy = "doctor")
    private Set<Treatment> treatments;

    @Override
    public String toString() {
        return "Doctor{" +
                "speciality='" + speciality + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
