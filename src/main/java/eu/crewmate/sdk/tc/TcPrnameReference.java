package eu.crewmate.sdk.tc;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import com.jetbrains.python.psi.PyFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TcPrnameReference extends TcReference {

    public TcPrnameReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        PsiElement element = elementPointer.getElement();
        List<ResolveResult> results = new ArrayList<>();
        if (element != null) {
            final List<PyFunction> keys = ProjectInstance.findProcedure(element.getText());
            for (final PyFunction pyFunction : keys) {
                results.add(new PsiElementResolveResult(pyFunction));
            }
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Override
    public @Nullable PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @Override
    public Object @NotNull [] getVariants() {
        final List<PyFunction> keys = ProjectInstance.findProcedures();
        List<LookupElement> variants = new ArrayList<>();
        String mod;
        String key;
        for (final PyFunction function : keys) {
            key = function.getName();
            mod = function.getContainingFile().getVirtualFile().getPath().toLowerCase().split("src")[1];
            if (mod != null) {
                mod = mod.contains("common") ? ";common;run;" : ";project;run;";
            } else mod = "";
            if (key == null) continue;
            variants.add(LookupElementBuilder
                    .create(key + mod).withPresentableText(key).withCaseSensitivity(false)
            );

        }
        return variants.toArray();
    }

}
