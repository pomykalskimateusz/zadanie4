package rekrutacja.zad4.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import rekrutacja.zad4.repository.EmailMessage;

@Service
@AllArgsConstructor
public class EmailService
{
    private static final String MAILGUN_DOMAIN = "";
    private static final String MAILGUN_API_KEY = "";
    private static final String SENDER_EMAIL = "";

    public boolean send(EmailMessage emailMessage)
    {
        try {
            HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + MAILGUN_DOMAIN + "/messages")
                    .basicAuth("api", MAILGUN_API_KEY)
                    .field("from", SENDER_EMAIL)
                    .field("to", emailMessage.getEmail())
                    .field("subject", emailMessage.getSubject())
                    .field("text", emailMessage.getContent())
                    .asJson();

            return response.getStatus() == 200;
        }
        catch (UnirestException e)
        {
            return false;
        }
    }
}
