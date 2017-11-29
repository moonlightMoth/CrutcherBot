import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MessageSender {



    public void sendGovnocodePost(String chatId, String text, TelegramLongPollingBot bot)
    {
        abstractPhotoSendOperation(chatId, new SendPhotoBuilder().buildGovnocodeImage(text), bot);
    }

    public void sendSelfCreatedPost(String chatId, String text, TelegramLongPollingBot bot)
    {
        abstractPhotoSendOperation(chatId, new SendPhotoBuilder().buildSelfCreatedCodePhoto(text), bot);
    }
    public void sendTestText(String chatId, String text, TelegramLongPollingBot bot)
    {
        abstractTextSendOperation(chatId, new SendMessageBuilder().buildTestMessage(text, bot), bot);
    }





    private void abstractPhotoSendOperation(String chatId, SendPhoto sendPhoto, TelegramLongPollingBot bot)
    {
        sendPhoto.setChatId(chatId);
        try {
            System.out.println(sendPhoto.getPhoto());
            bot.sendPhoto(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private  void abstractTextSendOperation (String chatId, SendMessage sendMessage, TelegramLongPollingBot bot)
    {
        sendMessage.setChatId(chatId);
        try {
            bot.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
