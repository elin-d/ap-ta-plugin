package eu.crewmate.sdk.tc;

import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.util.IncorrectOperationException;
import eu.crewmate.sdk.tc.psi.TcPrname;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class TcReplaceOldProceduresAction extends BaseIntentionAction {
    public static final Map<String, String> oldProcedures = Map.of(
            "pixelbild_export", "Bitmap_export",
            "ganzesbilddarstellen", "ZoomShowAll"
    );
    protected final SmartPsiElementPointer<PsiElement> elementPointer;
    final String key;

    public TcReplaceOldProceduresAction(PsiElement element) {
        this.elementPointer = SmartPointerManager.getInstance(element.getProject()).createSmartPsiElementPointer(element);
        this.key = element.getText().toLowerCase();
    }

    @NotNull
    @Override
    public String getText() {
        return "Replace procedure with " + oldProcedures.get(key);
    }

    @Override
    public @NotNull String getFamilyName() {
        return "Rename reference";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return oldProcedures.containsKey(key);
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        TcPrname proc = (TcPrname) elementPointer.getElement();
        if (proc != null) proc.setName(oldProcedures.get(key));
    }
}
