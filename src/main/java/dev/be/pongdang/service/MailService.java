package dev.be.pongdang.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendMail() {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);

            String from = "신기용 <hello.goodgid@gmail.com>";
            String to = "hello.goodgid@gmail.com";
            String replyTo = "goodgid-reply@be.com";
            String subject = "[퐁당퐁당] 연못이 생성되었습니다.";
            String text = "<!doctype html><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head><title></title><!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]--><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"><style type=\"text/css\">#outlook a { padding:0; }\n"
                          + "          body { margin:0;padding:0;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%; }\n"
                          + "          table, td { border-collapse:collapse;mso-table-lspace:0pt;mso-table-rspace:0pt; }\n"
                          + "          img { border:0;height:auto;line-height:100%; outline:none;text-decoration:none;-ms-interpolation-mode:bicubic; }\n"
                          + "          p { display:block;margin:13px 0; }</style><!--[if mso]>\n"
                          + "        <noscript>\n"
                          + "        <xml>\n"
                          + "        <o:OfficeDocumentSettings>\n"
                          + "          <o:AllowPNG/>\n"
                          + "          <o:PixelsPerInch>96</o:PixelsPerInch>\n"
                          + "        </o:OfficeDocumentSettings>\n"
                          + "        </xml>\n"
                          + "        </noscript>\n"
                          + "        <![endif]--><!--[if lte mso 11]>\n"
                          + "        <style type=\"text/css\">\n"
                          + "          .mj-outlook-group-fix { width:100% !important; }\n"
                          + "        </style>\n"
                          + "        <![endif]--><style type=\"text/css\">@media only screen and (min-width:480px) {\n"
                          + "        .mj-column-per-100 { width:100% !important; max-width: 100%; }\n"
                          + "      }</style><style media=\"screen and (min-width:480px)\">.moz-text-html .mj-column-per-100 { width:100% !important; max-width: 100%; }</style><style type=\"text/css\">@media only screen and (max-width:480px) {\n"
                          + "      table.mj-full-width-mobile { width: 100% !important; }\n"
                          + "      td.mj-full-width-mobile { width: auto !important; }\n"
                          + "    }</style></head><body style=\"word-spacing:normal;\"><div><!--[if mso | IE]><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\" style=\"width:600px;\" width=\"600\" ><tr><td style=\"line-height:0px;font-size:0px;mso-line-height-rule:exactly;\"><![endif]--><div style=\"margin:0px auto;max-width:600px;\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\"><tbody><tr><td style=\"direction:ltr;font-size:0px;padding:20px 0;text-align:center;\"><!--[if mso | IE]><table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td class=\"\" style=\"vertical-align:top;width:600px;\" ><![endif]--><div class=\"mj-column-per-100 mj-outlook-group-fix\" style=\"font-size:0px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\"><tbody><tr><td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\"><tbody><tr><td style=\"width:550px;\"><img height=\"auto\" src=\"https://tiltil.s3.ap-northeast-2.amazonaws.com/pongdang/1.png\" style=\"border:0;display:block;outline:none;text-decoration:none;height:auto;width:100%;font-size:13px;\" width=\"550\"></td></tr></tbody></table></td></tr></tbody></table></div><!--[if mso | IE]></td></tr></table><![endif]--></td></tr></tbody></table></div><!--[if mso | IE]></td></tr></table><![endif]--></div></body></html>";

            // validate address
            InternetAddress internetAddress = new InternetAddress(to);
            internetAddress.validate();

            helper.setFrom(from);
            helper.setTo(to);
            helper.setReplyTo(replyTo);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("[MessagingException] ", e);
        }
    }
}


/*
https://mjml.io/try-it-live/

// 피치플래너 email format
<mjml>
  <mj-font name="Pretendard" href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard/dist/web/static/pretendard.css" />
  <mj-body background-color="#f8f9fa">
    <mj-spacer height="10%" />
    <mj-section background-color="white" padding="4% 2% 2% 2%">
      <mj-column>

        <mj-image align='left' width="150px" src="https://peach-planner-static.s3.ap-northeast-2.amazonaws.com/peachplanner/ic_logo.png" />

        <mj-spacer height="15px" />

        <mj-text font-size="20px" font-weight="1000" color="#000000" font-family="SpoqaHanSans">피치플래너 회원가입이 완료되었습니다.</mj-text>
        <mj-spacer height="10px" />

        <mj-text font-size="16px" color="#495057" font-family="SpoqaHanSans">
          안녕하세요 {{nickName}}님!<br />
          웨딩플래너 검색 서비스 피치플래너 가입을 진심으로 축하드립니다.
        </mj-text>
        <mj-spacer height="10px" />
        <mj-divider border-color="#E5E7EB" border-width="1px" />

        <mj-button href="https://peachplanner.com" font-family="SpoqaHanSans" background-color="#e64980" color="white" width="240px" height="40px" padding-top="20px" padding-bottom="30px">피치플래너 홈
        </mj-button>
      </mj-column>
    </mj-section>
    <mj-section background-color="#f1f3f5">
      <mj-column>
        <mj-text align="left" font-family="SpoqaHanSans" font-size="12px" line-height="0.5" color="#868e96">
          본 메일은 발신 전용으로 회신이 되지 않습니다.
        </mj-text>
        <mj-text align="left" font-family="SpoqaHanSans" font-size="12px" line-height="0.5" color="#868e96">
          Peachplanner. All Rights Reserved.
        </mj-text>
        <mj-text align="left" font-family="Pretendard, Arial" font-size="12px" line-height="0.5" color="#868e96">
          <a href="https://peachplanner.com/termsOfUse" target="_blank" rel="noopener noreferrer nofollow">이용약관</a>
          <span>·</span>
          <a href="https://peachplanner.com/privacyPolicy" target="_blank" rel="noopener noreferrer nofollow">개인정보처리방침</a>
        </mj-text>
      </mj-column>
    </mj-section>
  </mj-body>
</mjml>
 */