package eu.crewmate.sdk.tc.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import eu.crewmate.sdk.tc.TcKeyReference;
import eu.crewmate.sdk.tc.TcPrnameReference;
import eu.crewmate.sdk.tc.psi.TcElementFactory;
import eu.crewmate.sdk.tc.psi.TcKey;
import eu.crewmate.sdk.tc.psi.TcPrname;

public class TcPsiImplUtil {

    public static String getName(TcKey key) {
        return key.getNode().getText();
    }

    public static String getName(TcPrname tcPrname) {
        return tcPrname.getNode().getText();
    }

    public static PsiElement setName(TcKey key, String newName) {
        ASTNode keyNode = key.getNode();
        TcKey newTcKey = TcElementFactory.createKey(key.getProject(), newName);
        ASTNode newKeyNode = newTcKey.getNode();
        keyNode.getTreeParent().replaceChild(keyNode, newKeyNode);
        return newTcKey;
    }

    public static PsiElement setName(TcPrname key, String newName) {
        ASTNode keyNode = key.getNode();
        TcPrname newTcPrname = TcElementFactory.createPrname(key.getProject(), newName);
        ASTNode newKeyNode = newTcPrname.getNode();
        keyNode.getTreeParent().replaceChild(keyNode, newKeyNode);
        return newTcPrname;
    }

    public static PsiReference getReference(TcKey key) {
        String s = getName(key);
        return new TcKeyReference(key, TextRange.from(0, s.length()));
    }

    public static PsiReference getReference(TcPrname tcPrname) {
        String s = getName(tcPrname);
        return new TcPrnameReference(tcPrname, TextRange.from(0, s.length()));
    }

    public static PsiElement getNameIdentifier(TcKey key) {
        return key.getNode().getPsi();
    }

    public static PsiElement getNameIdentifier(TcPrname tcPrname) {
        return tcPrname.getNode().getPsi();
    }

    public static void rename(TcKey myElement, String newName) {
        myElement.setName(newName);
    }
}