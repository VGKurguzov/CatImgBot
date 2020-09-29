package com.saxakiil;

import com.saxakiil.api.ImageCat;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class CatImageBot extends TelegramLongPollingBot {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message msg = update.getMessage();

            switch (msg.getText()) {
                case "/start":
                    sendTextMsg(msg, TextResources.HELLO);
                    try {
                        sendImageCatMsg(msg);
                        Thread.sleep(150);
                        setKeyboard();
                        sendTextMsg(msg, TextResources.HELLO_NEXT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Получить нового котика \uD83D\uDC08":
                    sendImageCatMsg(msg);
                    break;
                default:
                    break;
            }

        }
    }

    private void sendTextMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        if (replyKeyboardMarkup != null)
            s.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    private void setKeyboard() {
        replyKeyboardMarkup.setResizeKeyboard(true).setSelective(true);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton("Получить нового котика \uD83D\uDC08");
        keyboardRow.add(keyboardButton);
        keyboardRowList.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    private void sendImageCatMsg(Message msg) {

        SendPhoto s = new SendPhoto();
        ImageCat img = new ImageCat();
        s.setChatId(msg.getChatId());
        s.setPhoto(img.getNameFile(), img.getFile());
        s.setReplyMarkup(replyKeyboardMarkup);
        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        } finally {
            img.closeStream();
            img.updateImage();
        }
    }

    @Override
    public String getBotUsername() {
        return EnvResources.usernameBot;
    }

    @Override
    public String getBotToken() {
        return EnvResources.tokenBot;
    }
}