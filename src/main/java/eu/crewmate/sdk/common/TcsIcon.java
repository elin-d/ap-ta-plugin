package eu.crewmate.sdk.common;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiElement;
import eu.crewmate.sdk.tc.psi.TcFile;
import eu.crewmate.sdk.ts.psi.TsFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TcsIcon extends IconProvider {

    public static final Icon FILE = IconLoader.getIcon("/icons/pluginIcon.svg", TcsIcon.class);
    public static final Icon TC_ICON = IconLoader.getIcon("/icons/tc.svg", TcsIcon.class);
    public static final Icon TS_ICON = IconLoader.getIcon("/icons/ts.svg", TcsIcon.class);

    @Override
    public @Nullable Icon getIcon(@NotNull PsiElement element, int flags) {
        if (element instanceof TcFile) return TC_ICON;
        if (element instanceof TsFile) return TS_ICON;
        return null;
    }
}
