package eu.crewmate.sdk.tc;

import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.python.PythonLanguage;
import eu.crewmate.sdk.tc.psi.TcContainer;
import eu.crewmate.sdk.tc.psi.TcUnknown;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PyInjector implements MultiHostInjector {
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull PsiElement context) {

        if (!(context instanceof TcUnknown)) return;
        VirtualFile vf = context.getContainingFile().getVirtualFile();
        if (vf == null) return;
        String fileExtension = vf.getExtension();
        if (fileExtension == null) return;
        if (!fileExtension.equalsIgnoreCase("ptc")) return;

        StringBuilder indent = new StringBuilder();
        StringBuilder postindent = new StringBuilder();
        boolean b = false;
        TcUnknown py = (TcUnknown) context;
        TcContainer container = (TcContainer) context.getParent();

        for (TcUnknown ipy : container.getUnknownList()) {
            if (py == ipy) {
                b = true;
            } else {
                if (b) {
                    postindent.append(ipy.getText());
                } else {
                    indent.append(ipy.getText());
                }
            }
        }

        PsiElement sibling = py.getPrevSibling();
        // add intent to injected fragment
        if (sibling instanceof PsiWhiteSpace) {
            indent.append(sibling.getText());
        }

        sibling = py.getNextSibling();
        // add intent to injected fragment
        if (sibling instanceof PsiWhiteSpace) {
            postindent.append(sibling.getText());
        }

        registrar.startInjecting(PythonLanguage.getInstance());
        registrar.addPlace(indent.toString(), postindent.toString(), py, TextRange.from(0, py.getText().length()));
        registrar.doneInjecting();

    }

    @Override
    public @NotNull List<? extends Class<? extends PsiElement>> elementsToInjectIn() {

        return List.of(TcUnknown.class);
    }
}
