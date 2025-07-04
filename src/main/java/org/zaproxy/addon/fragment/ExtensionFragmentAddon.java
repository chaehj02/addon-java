package org.zaproxy.addon.fragment;

import org.parosproxy.paros.extension.ExtensionAdaptor;
import org.parosproxy.paros.extension.ExtensionHook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExtensionFragmentAddon extends ExtensionAdaptor {

    public ExtensionFragmentAddon() {
        super("ExtensionFragmentAddon");
    }

    @Override
    public void hook(ExtensionHook extensionHook) {
        super.hook(extensionHook);

        if (getView() != null) {
            JMenu menu = new JMenu("Fragment Tools");
            JMenuItem runItem = new JMenuItem("Run Fragments");
            runItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    runFragments();
                }
            });
            menu.add(runItem);
            extensionHook.getHookMenu().addToolsMenuItem(menu);
        }
    }

    private void runFragments() {
        try {
            // 외부 스크립트 실행 (예: Python fragment 트리거러)
            Runtime.getRuntime().exec("python3 zap_fragment_runner.py");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
