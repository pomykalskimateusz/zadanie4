package rekrutacja.zad4.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EmailMessage {
    private String email;
    private String content;
    private String subject;

    public EmailEntity toEntity()
    {
        return new EmailEntity(null, email, content, subject);
    }
}
