package org.zaproxy.addon.fragment;

import org.parosproxy.paros.extension.ExtensionAdaptor;
import org.parosproxy.paros.extension.ExtensionHook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtensionFragmentAddon extends ExtensionAdaptor {

    private static final String NAME = "ExtensionFragmentAddon";

    public ExtensionFragmentAddon() {
        super(NAME);
        // 제거 또는 주석 처리
        // this.setI18nPrefix("fragmentaddon");
    }

    @Override
    public void hook(ExtensionHook extensionHook) {
        super.hook(extensionHook);

        // GUI 환경일 때 메뉴 추가
        if (getView() != null) {
            JMenu menu = new JMenu("Fragment Tools");

            JMenuItem runItem = new JMenuItem("Run Fragments");
            runItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    runFragmentScript();
                }
            });

            menu.add(runItem);
            getView().getMainFrame().getMainMenuBar().getMenuTools().add(menu);
        }
    }

    private void runFragmentScript() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python3", "zap_fragment_runner.py");
            pb.inheritIO(); // 콘솔 출력 연결
            pb.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Failed to execute fragment script.\n" + ex.getMessage(),
                    "Fragment Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
