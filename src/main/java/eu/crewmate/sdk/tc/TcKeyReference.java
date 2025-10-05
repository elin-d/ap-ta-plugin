package eu.crewmate.sdk.tc;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementResolveResult;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.util.IncorrectOperationException;
import eu.crewmate.sdk.tc.psi.TcKey;
import eu.crewmate.sdk.tc.psi.impl.TcPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TcKeyReference extends TcReference {

    public TcKeyReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        PsiElement element = elementPointer.getElement();
        final List<XmlAttributeValue> keys = ProjectInstance.findMapping(element);
        List<ResolveResult> results = new ArrayList<>();
        for (XmlAttributeValue psiElement : keys) {
            results.add(new PsiElementResolveResult(psiElement));
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
        final List<XmlAttributeValue> keys = ProjectInstance.findKeyVariants(elementPointer.getElement());
        List<LookupElement> variants = new ArrayList<>();
        for (final XmlAttributeValue key : keys) {
            variants.add(LookupElementBuilder
                    .create(key.getValue()).withCaseSensitivity(false).withIcon(AllIcons.FileTypes.Xml)
            );
        }
        return variants.toArray();
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        TcKey key = (TcKey) elementPointer.getElement();
        if (key != null) TcPsiImplUtil.rename(key, newElementName);
        return elementPointer.getElement();
    }
}
