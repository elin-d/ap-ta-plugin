package eu.crewmate.sdk.ts.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import eu.crewmate.sdk.ts.TsReference;
import eu.crewmate.sdk.ts.psi.TsProperty;
import eu.crewmate.sdk.ts.psi.TsTypes;
import eu.crewmate.sdk.ts.psi.TsValue;

public class TsPsiImplUtil {

    public static String getName(TsValue element) {
        return element.getText();
    }

    public static String getKey(TsProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(TsTypes.KEY);
        return keyNode == null ? null : keyNode.getText();
    }

    public static PsiElement setName(TsValue element, String newName) {
        return null;
    }

    public static PsiReference getReference(TsValue element) {
        return element == null ? null : new TsReference(element, TextRange.from(0, element.getText().length()));
    }

    public static PsiElement getNameIdentifier(TsValue element) {
        return element.getNode().getPsi();
    }
}