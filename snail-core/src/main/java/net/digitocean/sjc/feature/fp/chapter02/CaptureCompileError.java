package net.digitocean.sjc.feature.fp.chapter02;


import javax.swing.JButton;

/**
 * @author: haoshichuan
 * @date: 2023/7/10 11:20
 */
public class CaptureCompileError {

    private JButton button;

    public void error() {
        String name = getUserName();
        name = formatUserName(name);
        //  取消注释此行会导致编译错误（Uncommenting this line should cause a compile error）
          button.addActionListener(event -> System.out.println("hi " + event));
    }

    private String formatUserName(String name) {
        return name.toLowerCase();
    }

    private String getUserName() {
        return "RICHARD";
    }


}
