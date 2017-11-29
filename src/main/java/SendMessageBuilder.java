import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class SendMessageBuilder {
    public SendMessage buildTestMessage(String text, TelegramLongPollingBot bot)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(new FirstCommandWordDeleter().deleteFirstWord(text));
        return sendMessage;
    }
}
