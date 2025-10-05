package eu.crewmate.sdk.ts;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import eu.crewmate.sdk.ts.psi.TsProperty;
import eu.crewmate.sdk.ts.psi.TsValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TsReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {

    private final TsValue element;

    public TsReference(@NotNull TsValue element, TextRange textRange) {
        super(element, textRange);
        this.element = element;
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        byte keyByte = ((TsProperty) element.getParent()).getKey().getBytes()[0];
        // "c" and "s", but in bytecode
        if (keyByte != 99 && keyByte != 115) return ResolveResult.EMPTY_ARRAY;
        String name = element.getName().trim().replace("\\", "/");
        String[] items = name.split("/");
        name = items[items.length - 1];
        String[] subDirs = Arrays.copyOf(items, items.length - 1);
        PsiDirectory psiDir = element.getContainingFile().getContainingDirectory();
        psiDir = items.length == 1 ? psiDir : TsUtil.findSubDir(psiDir, subDirs);
        if (psiDir == null) return ResolveResult.EMPTY_ARRAY;
        PsiFile tcFile = psiDir.findFile(name);
        List<ResolveResult> results = new ArrayList<>();
        if (tcFile != null) {
            results.add(new PsiElementResolveResult(tcFile));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Override
    public @Nullable PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }
}
