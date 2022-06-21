package mailTest.mailtest;//发送消息类
//把发消息的代码 封装为一个类 使用的时候调用方法 传入邮件标题 邮件内容就可以了

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMessages {
    public static void Send(String tittle, String code, String email) throws MessagingException {
        //创建Properties 类记录邮箱的一些属性
        Properties properties = new Properties();
        //下面内容时SMTP发送邮件，必须进行身份验证
        properties.put("mail.smtp.auth", "true");
        //填写SMTP服务器
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名 QQ邮箱
        //properties.put("mail.smtp.host", "smtp.163.com");
        // QQ邮箱端口号587  网易端口25
        properties.put("mail.smtp.port", 25 );
        //properties.put("mail.smtp.port", 587 );
        //填写写信人账号
        properties.put("mail.user", "liujijiwuhan@163.com");
        //填写16位写信人账号
        properties.put("mail.password", "XKDLVNGJUAIFQRKH");
        //构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //用户名和密码
                String username = properties.getProperty("mail.user");
                String password = properties.getProperty("mail.password");
                return new PasswordAuthentication(username, password);
            }
        };

        //使用环境授权属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(properties, authenticator);
        //创建邮件信息
        MimeMessage message = new MimeMessage(mailSession);
        //设置发件人
        InternetAddress from = new InternetAddress(properties.getProperty("mail.user"));
        message.setFrom(from);
        //设置收件人
        InternetAddress to = new InternetAddress(email);
        message.setRecipient(Message.RecipientType.TO, to);

        //设置邮件主题
        message.setSubject(tittle);

        //设置邮箱体
        message.setContent("【小柳编程之路】 您正在登录验证，验证码： " + code + "，切勿将验证码泄漏于他人。本条验证码有效期为15分钟。如非本人操作，请忽略。", "text/html;charset=UTF-8");

        //发送邮件
        Transport.send(message);
    }
}