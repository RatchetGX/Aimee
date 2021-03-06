/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ratchetgx.aimee.system.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author czg
 */

@Controller
@RequestMapping(value = "/captcha")
public class CaptchaController implements InitializingBean {

    private static final long serialVersionUID = 1L;
    // 验证码图片的宽度。
    private int width = 98;
    // 验证码图片的高度。
    private int height = 38;
    // 验证码字符个数
    private int codeCount = 4;
    private int x = 0;
    // 字体高度
    private int fontHeight;
    private int codeY;
//    char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
//        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
//        'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
//    char[] codeSequence = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
//        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
//        'x', 'y', 'z'};

    /**
     * 初始化验证图片属性
     */
    private void init() {

        x = width / (codeCount + 1);
        fontHeight = height - 2;
        codeY = height - 4;

    }

    @RequestMapping(method= RequestMethod.GET)
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        HttpSession session = req.getSession();

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 创建一个随机数生成器类
        Random random = new Random();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        // 设置字体。
        g.setFont(font);

        // 画边框。
        //g.setColor(Color.BLACK);
        //g.drawRect(0, 0, width - 1, height - 1);

        // 随机产生20条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(Color.BLACK);
        for (int i = 0; i < 0; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        g.setFont(new Font("Arial", Font.ITALIC, 30));
        g.setColor(new Color(random.nextInt(99), random.nextInt(199), random.nextInt(255)));
        g.drawLine(5, 14, 98, 14);
        g.drawLine(5, 15, 98, 15);
        g.setColor(new Color(random.nextInt(99), random.nextInt(199), random.nextInt(255)));
        g.drawLine(5, 26, 98, 26);
        g.drawLine(5, 27, 98, 27);
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。

        // 随机产生codeCount数字的验证码。
        StringBuffer randomCode = new StringBuffer();  
      	//char[] codeSequence = "abcdefghijklnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();  
      	char[] codeSequence = "abcdefghijklnopqrstuvwxyz0123456789".toCharArray();  
      	int index, len = codeSequence.length;  
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。

            index = random.nextInt(len);  
      		g.setColor(new Color(random.nextInt(99), random.nextInt(199), random.nextInt(255)));  
      		g.setFont(new Font("Arial", Font.ITALIC, 34));// 输出的字体和大小                    
      		g.drawString("" + codeSequence[index], (i * 21) + 8, 32);//写什么数字，在图片   
      		randomCode.append(codeSequence[index]); 
        }
        // 将四位数字的验证码保存到Session中。
        session.setAttribute("validateCode", randomCode.toString());

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/jpeg");

        // 清空缓存
        g.dispose();

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }

    public void afterPropertiesSet() throws Exception {
        init();
    }
}
