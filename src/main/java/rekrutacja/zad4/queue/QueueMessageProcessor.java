package rekrutacja.zad4.queue;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rekrutacja.zad4.repository.EmailMessage;
import rekrutacja.zad4.email.EmailService;
import rekrutacja.zad4.repository.EmailRepository;

@Service
@AllArgsConstructor
class QueueMessageProcessor
{
    private ObjectMapper objectMapper;
    private EmailService emailService;
    private EmailRepository emailRepository;

    public void process(String message)
    {
        try
        {
            EmailMessage emailMessage = objectMapper.readValue(message, EmailMessage.class);
            if(emailService.send(emailMessage))
            {
                emailRepository.save(emailMessage.toEntity());
            }
            else
            {
                System.out.println("Message not sent.");
            }
        }
        catch (JsonProcessingException exception)
        {
            exception.printStackTrace();
        }
    }
}
