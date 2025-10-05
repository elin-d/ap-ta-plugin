package services;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.psi.xml.XmlFile;
import com.intellij.util.xml.DomFileElement;
import com.intellij.util.xml.DomManager;
import com.jetbrains.python.psi.PyFunction;
import eu.crewmate.sdk.common.TaSettingsState;
import eu.crewmate.sdk.tc.dom.Element;
import eu.crewmate.sdk.tc.dom.Guimap;
import eu.crewmate.sdk.tc.psi.TcAction;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProjectUtilsService {

    private static final String xmlBaseDir = "/UIMaps/Allplan/XMLMaps/";
    private static final String TASrcDir = "/TaRobot/src/";
    private static final String prBaseDir = "/TARobot/src/Procedures/";

    private static final String rootGUIFile = "AllplanGUIMap.xml";
    private final Project project;

    private final Guimap guimap;
    private final String basePath;
    public Map<String, Set<String>> ContentFiles = Map.of(
            "toolbar", new HashSet<>(),
            "dialog", new HashSet<>(),
            "menu", new HashSet<>(),
            "statusbar", new HashSet<>(),
            "frame", new HashSet<>(),
            "function", new HashSet<>(),
            "cinema4d", new HashSet<>());
    private String lang;

    public ProjectUtilsService(Project project) {
        this.project = project;
        String projectPath = project.getBasePath();
        if (projectPath != null) {
            // if we are direct under TestAutomation dir
            if (projectPath.endsWith("TestAutomation")) {
                basePath = projectPath;
                // if we somewhere under TestAutomation dir
            } else if (projectPath.contains("TestAutomation/")) {
                basePath = projectPath.split("TestAutomation")[0] + "TestAutomation";
                // Probably direct under root or TestAutomation somewhere in the project
            } else {
                VirtualFile vf = LocalFileSystem.getInstance().findFileByPath(projectPath);
                if (vf != null) {
                    basePath = Arrays.stream(vf.getChildren())
                            .filter(c -> c.getName().equalsIgnoreCase("testautomation"))
                            .findFirst().map(VirtualFile::getPath).orElse(projectPath);
                } else basePath = projectPath;
            }
        } else basePath = "";
        addSourceFolder();
        String savedLang = project.getService(TaSettingsState.class).languageId;
        if (savedLang == null) savedLang = getPredictedLocalization();
        lang = savedLang.equals("none") ? "" : savedLang;
        XmlFile root = (XmlFile) getFileByRelativePath(xmlBaseDir + rootGUIFile);
        DomManager dm = DomManager.getDomManager(project);
        DomFileElement<Guimap> domElement = dm.getFileElement(root, Guimap.class);
        this.guimap = domElement != null ? domElement.getRootElement() : null;
        updateContentFilesMap();
    }

    public static List<XmlAttributeValue> findAttributesInXmls(Set<PsiFile> psiSet, String tag) {
        Collection<XmlAttribute> res = new HashSet<>();
        for (PsiFile psiFile : psiSet) {
            res.addAll(PsiTreeUtil.collectElementsOfType(psiFile, XmlAttribute.class));
        }
        return res.stream().filter(Objects::nonNull).
                filter((x -> x.getValue() != null)).filter(x -> x.getValue().equalsIgnoreCase(tag)).
                filter(x -> x.getName().equals("name")).map(XmlAttribute::getValueElement).collect(Collectors.toList());
    }

    public static String clearKey(String key){
        return key.replace("?", "").replaceFirst("\\[.*]", "");
    }

    public static List<XmlAttributeValue> findAttributesInXmlsByPrefix(Set<PsiFile> psiSet) {
        Collection<XmlAttribute> res = new HashSet<>();
        for (PsiFile psiFile : psiSet) {
            res.addAll(PsiTreeUtil.collectElementsOfType(psiFile, XmlAttribute.class));
        }
        return res.stream().filter(Objects::nonNull).
                filter((x -> x.getValue() != null)).filter((x -> !x.getValue().isEmpty())).
                filter(x -> x.getName().equals("name")).map(XmlAttribute::getValueElement).collect(Collectors.toList());
    }

    public void setLanguage(String language) {
        this.lang = language;
        updateContentFilesMap();
    }

    public String getPredictedLocalization() {
        VirtualFile vf = LocalFileSystem.getInstance().findFileByPath(basePath + xmlBaseDir + "Windows.deu.xml");
        return vf == null ? "none" : "deu";
    }

    public void updateContentFilesMap() {
        if (guimap == null) return;
        List<Element> searchTree = guimap.getElements();
        for (var entry : ContentFiles.entrySet()) {
            entry.getValue().clear();
            entry.getValue().addAll(searchForElementTypeMapping(searchTree, entry.getKey(), lang));
        }
    }

    public Set<String> searchForElementTypeMapping(List<Element> searchTree, String elementName, String lang) {
        Set<String> result = new HashSet<>();
        while (!searchTree.isEmpty()) {
            List<Element> elementToSearch = new ArrayList<>();
            for (Element ke : searchTree) {
                if (ke.getName().exists() && ke.getName().getStringValue().equalsIgnoreCase(elementName)) {
                    if (ke.getXfile().exists())
                        result.add(ke.getXfile().getStringValue().replace("{%LanguageConfig.LanguageTag%}", lang));
                    else if (!ke.getElements().isEmpty()) {
                        if (ke.getXdeep().exists()) {
                            Element ne = ke;
                            while (ne.getElements().size() > 0) {
                                ne = ne.getElements().get(0);
                                if (ne.getXfile().exists()) {
                                    result.add(ne.getXfile().getStringValue());
                                    break;
                                }
                            }
                        } else {
                            ke = ke.getElements().get(0);
                            if (ke.getXfile().exists())
                                result.add(ke.getXfile().getStringValue()
                                        .replace("{%LanguageConfig.LanguageTag%}", lang));
                        }
                    } else if (ke.getXref().exists()) {
                        String xref = ke.getXref().getStringValue();
                        String res;
                        if (xref != null) {
                            res = searchByXref(xref);
                            if (res != null) result.add(res);
                                //special case - stupid mapping: two slashes
                            else {
                                res = searchByXref(xref.replace("//", "/"));
                                if (res != null) result.add(res);
                            }
                        }
                    }
                } else {
                    elementToSearch.addAll(ke.getElements());
                }
            }
            searchTree = elementToSearch;
        }
        return result;
    }

    public Set<PsiFile> getKeyXmlFiles(PsiElement ele) {
        Set<PsiFile> result = new HashSet<>();
        TcAction line = (TcAction) ele.getParent();
        PsiElement firstKey = ele.getParent().getChildren()[0];
        boolean isFirstElement = ele == firstKey;
        for (String s : ContentFiles.get(line.getFirstChild().getText().toLowerCase())) {
            result.add(getFileByRelativePath(xmlBaseDir + s));
        }
        if (isFirstElement) {
            return result;
        }
        DomManager dm = DomManager.getDomManager(project);
        List<Element> updateResults = new ArrayList<>();
        for (PsiFile psiFile : result) {
            DomFileElement<Element> domElement = dm.getFileElement((XmlFile) psiFile, Element.class);
            if (domElement != null && domElement.exists()) updateResults.add(domElement.getRootElement());
        }
        Set<String> files = searchForElementTypeMapping(updateResults, clearKey(firstKey.getText()), lang);
        result.clear();
        for (String s : files) {
            result.add(getFileByRelativePath(xmlBaseDir + s));
        }
        return result;
    }

    public String searchByXref(String xref) {
        String result = null;
        String[] keys = xref.split("/");
        int depth = 0;
        boolean found = false;
        Element current = null;
        while (depth < keys.length) {
            String key = keys[depth];
            for (Element uie : current == null ? guimap.getElements() : current.getElements()) {
                boolean nameAttrExist = uie.getName().getStringValue() != null;
                if ((!nameAttrExist && key.isEmpty()) || (nameAttrExist && uie.getName().getStringValue().equals(key))) {
                    found = true;
                    depth++;
                    if (depth == keys.length) result = uie.getXfile().getStringValue();
                    current = uie;
                }
            }
            // this means xref is not exist!
            if (!found) break; else found = false;
        }
        return result;
    }

    public List<XmlAttributeValue> findMapping(PsiElement ele) {
        Set<PsiFile> psiFile = getKeyXmlFiles(ele);
        if (psiFile == null) return new ArrayList<>();
        return findAttributesInXmls(psiFile, clearKey(ele.getText()));
    }

    public List<XmlAttributeValue> findKeyVariants(PsiElement ele) {
        Set<PsiFile> psiFile = getKeyXmlFiles(ele);
        if (psiFile == null) return new ArrayList<>();
        return findAttributesInXmlsByPrefix(psiFile);
    }

    public List<PyFunction> findProcedure(String proc) {
        return findProcedures().stream().filter(x -> x.getName() != null)
                .filter(x -> x.getName().
                        equalsIgnoreCase(proc)).collect(Collectors.toList());
    }

    public List<PyFunction> findProcedures() {
        PsiDirectory psiDir = getDirByRelativePath(prBaseDir);
        if (psiDir == null) return new ArrayList<>();
        Collection<PyFunction> res = PsiTreeUtil.collectElementsOfType(psiDir, PyFunction.class);
        return res.stream().filter(x -> x.getName() != null).filter(x -> !x.getName().startsWith("_")).collect(Collectors.toList());
    }

    public PsiFile getFileByRelativePath(String rPath) {
        VirtualFile vf = LocalFileSystem.getInstance().findFileByPath(basePath + rPath);
        return vf == null ? null : PsiManager.getInstance(project).findFile(vf);
    }

    public PsiDirectory getDirByRelativePath(String rPath) {
        VirtualFile vf = LocalFileSystem.getInstance().findFileByPath(basePath + rPath);
        return vf == null ? null : PsiManager.getInstance(project).findDirectory(vf);
    }

    public String getFrameworkSrcDir() {
        return basePath + TASrcDir;
    }

    public void addSourceFolder() {

        Module[] module = ModuleManager.getInstance(project).getModules();
        if (module.length == 0) return;
        VirtualFile vf = LocalFileSystem.getInstance().refreshAndFindFileByPath(basePath + TASrcDir);
        if (vf == null) return;
        VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(vf);
        // Source dir already set
        if (moduleSourceRoot != null) return;

        Runnable runnable = () -> {
            //getting RootModel for default module (with hope it's TestAutomation)
            ModifiableRootModel rootModel = ModuleRootManager.getInstance(module[0]).getModifiableModel();
            //at least default file should exist
            ContentEntry ce = rootModel.addContentEntry(vf);
            ce.addSourceFolder(vf, false);
            rootModel.commit();
        };

        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(() -> {
            Application application = ApplicationManager.getApplication();
            application.invokeLater(() -> application.runWriteAction(runnable));
        }, 1, TimeUnit.MINUTES);

    }

    public String getDrive() {
        return basePath.split(":")[0];
    }
}
