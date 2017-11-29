import org.telegram.telegrambots.api.methods.send.SendPhoto;

import java.io.File;

public class SendPhotoBuilder {


    public SendPhoto buildGovnocodeImage(String string)
    {
        return buildPostWithAbstractPhoto(new CodeGatherer().getCode(string));
    }

    public SendPhoto buildSelfCreatedCodePhoto(String string)
    {
        return buildPostWithAbstractPhoto(new FirstCommandWordDeleter().deleteFirstWord(string));
    }

    private SendPhoto buildPostWithAbstractPhoto(String string)
    {
        File file=null;
        SendPhoto sendPhoto = new SendPhoto();

            file = new ToImageConverter().convert(string);

        if(file==null)
            System.out.println("FileInputStream exept");
        else
            sendPhoto.setNewPhoto(file);

        return sendPhoto;
    }



}
