package rekrutacja.zad4.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
class EmailEntity
{
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String content;
    private String subject;
}
