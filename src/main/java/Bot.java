import com.vdurmont.emoji.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public String calculatorInput = null;
    public int count = 0;
    Calculator calcul = new Calculator();
    boolean flag = false;
    final String  numberNull = EmojiParser.parseToUnicode(":zero:");
    final String numberOne = EmojiParser.parseToUnicode(":one:");
    final String numberTwo = EmojiParser.parseToUnicode(":two:");
    final String numberThree = EmojiParser.parseToUnicode(":three:");
    final String numberFour = EmojiParser.parseToUnicode(":four:");
    final String numberFive = EmojiParser.parseToUnicode(":five:");
    final String numberSix = EmojiParser.parseToUnicode(":six:");
    final String numberSeven = EmojiParser.parseToUnicode(":seven:");
    final String numberEight = EmojiParser.parseToUnicode(":eight:");
    final String numberNine = EmojiParser.parseToUnicode(":nine:");
    final String multiplication = EmojiParser.parseToUnicode(":heavy_multiplication_x:");
    final String plus = EmojiParser.parseToUnicode(":heavy_plus_sign:");
    final String minus = EmojiParser.parseToUnicode(":heavy_minus_sign:");
    final String ok = EmojiParser.parseToUnicode(":ok:");
    final String division = EmojiParser.parseToUnicode(":heavy_division_sign:");
    final String backspace = EmojiParser.parseToUnicode(":arrow_left:");


    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();

//        sendMessage.enableMarkdown(true);//???

        sendMessage.setText("Не понял");
        sendMessage.setChatId(chatId);

        try {
            setButtons(sendMessage, s);
            if(flag == true ){
                calculatorInput = null;
                flag = false;
            }
            if(calcul.ToSting(s) != null && calculatorInput != null){
                calculatorInput = calculatorInput + calcul.ToSting(s);
                System.out.println(calculatorInput);
                calculatorInput = calcul.handler(calculatorInput);
                System.out.println(calculatorInput);
                if(calculatorInput.charAt(calculatorInput.length()-1) == '='){
                    calculatorInput = calcul.calculator(calculatorInput);
                    flag = true;
                }
                System.out.println(calculatorInput);
                sendMessage.setText(calculatorInput);
            }
            if(calcul.ToSting(s) != null && calculatorInput == null){
               calculatorInput = calcul.ToSting(s);
               System.out.println(calculatorInput);
               sendMessage.setText(calculatorInput);
            }
            execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "myCalculator45bot";
    }

    public String getBotToken() {
        return "1023670854:AAEUN--o6qsLhPW7k38jUBp8YytEMyHN5MI";
    }

    public synchronized void setButtons(SendMessage sendMessage, String enteredMsg) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        KeyboardRow keyboardThirdRow = new KeyboardRow();

        KeyboardRow keyboardFourthRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);


//        // Первая строчка клавиатуры
//
//        // Добавляем кнопки в первую строчку клавиатуры
//        keyboardFirstRow.add(new KeyboardButton("Привет"));
//
//
//        // Добавляем кнопки во вторую строчку клавиатуры
//        keyboardSecondRow.add(new KeyboardButton("Помощь"));
//
//        // Добавляем все строчки клавиатуры в список
//        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);
//        // и устанваливаем этот список нашей клавиатуре
//        replyKeyboardMarkup.setKeyboard(keyboard);
        if(enteredMsg.equals("Меню")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardSecondRow.clear();
            keyboardFirstRow.add("Калькулятор");
            keyboardFirstRow.add("Помощь");
            keyboardSecondRow.add("Другие боты");
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            sendMessage.setText("Выбрать...");
            replyKeyboardMarkup.setKeyboard(keyboard);
        }
        if(enteredMsg.equals("Калькулятор")){
            keyboard.clear();
            keyboardFirstRow.clear();
            keyboardSecondRow.clear();
            keyboardThirdRow.clear();
            keyboardFourthRow.clear();
            keyboardFourthRow.add(plus);
            keyboardFourthRow.add(numberNull);
            keyboardFourthRow.add(minus);
            keyboardFourthRow.add(ok);


            keyboardFirstRow.add(numberOne);
            keyboardFirstRow.add(numberTwo);
            keyboardFirstRow.add(numberThree);
            keyboardFirstRow.add(backspace);

            keyboardSecondRow.add(numberFour);
            keyboardSecondRow.add(numberFive);
            keyboardSecondRow.add(numberSix);
            keyboardSecondRow.add(multiplication);

            keyboardThirdRow.add(numberSeven);
            keyboardThirdRow.add(numberEight);
            keyboardThirdRow.add(numberNine);
            keyboardThirdRow.add(division);

            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            keyboard.add(keyboardThirdRow);
            keyboard.add(keyboardFourthRow);

            sendMessage.setText("Введите выражение:");
            replyKeyboardMarkup.setKeyboard(keyboard);
        }
    }
}