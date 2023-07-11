package net.digitocean.sjc.feature.fp.chapter02;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: haoshichuan
 * @date: 2023/7/10 11:35
 */
public class Test {
    private JButton button;

    /**
     * 使用匿名内部类将行为和按钮单击进行关联
     */
    void anonymousInternalClass() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button click");
            }
        });
    }

    /**
     * 使用 Lambda 将行为和按钮单击进行关联
     */
    void lambda_1() {
        button.addActionListener(
                event -> System.out.println("button click")
        );
    }

    public static void main(String[] args) {
        new Test().lambda_1();
    }
}
