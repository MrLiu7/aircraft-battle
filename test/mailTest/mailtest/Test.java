package mailTest.mailtest;//测试类

import java.util.Random;
import javax.mail.MessagingException;

public class Test {
    public static void main(String[] args) throws MessagingException {
        String code = "";
        String[] emails = {"bjj1265431578@qq.com", "3065524048@qq.com", "120542804@qq.com", "3259946376@qq.com", "2825449628@qq.com"};
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                code += random.nextInt(10);
            }
            SendMessages.Send("验证码", code, emails[i]);
            System.out.println(emails[i] + " " + code);
            code = "";
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}