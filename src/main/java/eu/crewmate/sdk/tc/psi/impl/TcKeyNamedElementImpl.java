package eu.crewmate.sdk.tc.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import eu.crewmate.sdk.tc.psi.TcKeyNamedElement;
import org.jetbrains.annotations.NotNull;

public abstract class TcKeyNamedElementImpl extends ASTWrapperPsiElement implements TcKeyNamedElement {

    public TcKeyNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
