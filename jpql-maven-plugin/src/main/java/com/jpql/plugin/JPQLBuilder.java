package com.jpql.plugin; /**
 * Created by kasra.haghpanah on 01/05/2018.
 */

import com.jpql.api.enums.DependencyInjectionType;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactCollector;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.settings.Settings;
import org.apache.maven.shared.dependency.tree.DependencyNode;
import org.apache.maven.shared.dependency.tree.DependencyTreeBuilder;
import org.apache.maven.shared.dependency.tree.DependencyTreeBuilderException;
import org.apache.maven.shared.dependency.tree.traversal.CollectingDependencyNodeVisitor;

import java.io.File;
import java.util.*;


//mvn com.jersey.client:create-maven-plugin:1.0.0:hello
@Mojo(name = "jpql", defaultPhase = LifecyclePhase.INSTALL)
//@Execute(goal = "compile", phase = LifecyclePhase.COMPILE)
public class JPQLBuilder extends AbstractMojo {

    @Parameter(property = "dependencyInjectionType", required = true, defaultValue = "NONE")
    private DependencyInjectionType dependencyInjectionType;

    @Parameter(property = "destPackage", defaultValue = "", required = true)
    private String destPackage;

    @Parameter(property = "persistence", required = true)
    private String persistence;

    @Parameter(property = "enable", required = true)
    private boolean enable;

    @Parameter(property = "mavenProject", defaultValue = "${project}", required = false, readonly = true)
    private MavenProject mavenProject;

    @Parameter(property = "localRepository", defaultValue = "${localRepository}", readonly = true)
    private ArtifactRepository localRepository;

    //@Parameter(property = "treeBuilder", defaultValue = "${project}", required = false, readonly = true)
    @Component
    private DependencyTreeBuilder treeBuilder;

    //@Parameter(property = "artifactFactory"/*, defaultValue = "${project}"*/, required = false, readonly = true)
    @Component
    private ArtifactFactory artifactFactory;

    //@Parameter(property = "artifactMetadataSource"/*, defaultValue = "${project}"*/, required = false, readonly = true)
    @Component
    private ArtifactMetadataSource artifactMetadataSource;

    //@Parameter(property = "artifactCollector"/*, defaultValue = "${project}"*/, required = false, readonly = true)
    @Component
    private ArtifactCollector artifactCollector;

    //@Parameter(property = "artifactFilter", defaultValue = "${project}", required = false, readonly = true)
    private ArtifactFilter artifactFilter;

    @Component
    private Settings settings;


    @Parameter(defaultValue = "${maven.home}")
    protected File mavenHome;


    public void execute() throws MojoExecutionException {

        //getTreeDependencies();
        if (!isEnable()) {
            return;
        }
        String mHome = mavenHome.getAbsoluteFile().toString().replaceAll("\\\\", "/") + "/bin/mvn";
        String projectAddress = getProjectAddress();
        String targetPackage = projectAddress + "/src/main/java/" + getDestPackage().replaceAll("\\.", "\\/");
        String[] jars = getJars();
        addJarFile(mHome, projectAddress, getDestPackage(), targetPackage, jars);
    }

    public void addJarFile(String mvnBat, String projectAddress, String targetPackage, String targetPackagePath, String[] jarFiles) {
        Generator.analyzer(mvnBat, projectAddress, getPersistence(), targetPackage, targetPackagePath, jarFiles, getDependencyInjectionType());
    }


    public String getJPQLMavenPluginAddress() {

        Map map = mavenProject.getProjectReferences();

        String value = "";
        for (Object key : map.keySet()) {
            String[] values = map.get(key).toString().split(" ");
            value = values[values.length - 1];
            value = value.replaceAll("(\\\\){1,}", "/");
            value = value.substring(0, value.indexOf("/pom.xml"));
        }

        return value;
    }


    public String getProjectAddress() {
        String projectAddress = mavenProject.getBasedir().getAbsolutePath();
        projectAddress = projectAddress.replaceAll("(\\\\)+", "\\/");
        return projectAddress;
    }


    public String getDestPackage() {
        return destPackage;
    }

    public void setDestPackage(String destPackage) {
        this.destPackage = destPackage;
    }

    public List<String> getSystemPaths() {

        List<String> list = new ArrayList<String>();
        List<Dependency> dependencyList = mavenProject.getDependencies();

        if (dependencyList == null || dependencyList.size() == 0) {
            return list;
        }
        int length = dependencyList.size();

        for (int i = 0; i < length; i++) {
            String systemPath = dependencyList.get(i).getSystemPath();
            if (systemPath != null) {
                list.add(systemPath);
            }
        }

        return list;

    }

    public String[] getJars() {

        String target = getProjectAddress() + "/target/classes";
        int length = 1;


        //List<DependencyNode> dependencyList = getTreeDependencies();
        List<Dependency> dependencyList = getDependencies();

        //mavenProject.getCompileDependencies()
        // for (Object o : treeBuilder.get()) {
        // }

        if (dependencyList != null) {
            length += dependencyList.size();
        }

        List<String> jars = new ArrayList<String>();//new String[length];
        jars.add(target);

        List<String> systemPaths = getSystemPaths();

        for (int i = 0; i < systemPaths.size(); i++) {
            jars.add(systemPaths.get(i));
        }

        if (dependencyList != null)
            for (int i = 0; i < dependencyList.size(); i++) {

                Dependency dependency = dependencyList.get(i);
                String systemPath = null;//dependency.getArtifact()..getSystemPath();
                String path = getLocalRepositoryPath() + "/";
                String scope = dependency.getScope();
                if (scope == null || !scope.equals("test")) {


                    if (systemPath != null) {
                        path = systemPath;
                    } else {
                        path = path + dependency.getGroupId().replaceAll("\\.", "/") +
                                "/" + dependency.getArtifactId() +
                                "/" + dependency.getVersion() +
                                "/" + dependency.getArtifactId() + "-" + dependency.getVersion() + ".jar";
                    }
                    path = path.replaceAll("\\\\{1,}", "/").replaceAll("/{2,}", "/");

                    if (path != null) {
                        jars.add(path);
                    }

                }
            }

        String[] jarfiles = new String[jars.size()];

        for (int i = 0; i < jars.size(); i++) {
            jarfiles[i] = jars.get(i).replaceAll("\\\\{1,}", "/");
            System.out.println(jarfiles[i] + "-----------------jar-files");
        }


        return jarfiles;

    }

    public List<Dependency> getDependencies() {

        List<Dependency> dependencies = mavenProject.getDependencies();
        if (dependencies == null) {
            new ArrayList<Dependency>();
        }

        return dependencies;

    }

    public List<DependencyNode> getTreeDependencies() {

        //buildDependencyTree(MavenProject var1, ArtifactRepository var2, ArtifactFactory var3, ArtifactMetadataSource var4, ArtifactFilter var5, ArtifactCollector var6) throws DependencyTreeBuilderException;

        List<DependencyNode> dependencyNodes = new ArrayList<DependencyNode>();

        try {
            //System.out.println(treeBuilder+"?????????????????");
            DependencyNode rootNode = treeBuilder.buildDependencyTree(
                    mavenProject,
                    localRepository,
                    artifactFactory,
                    artifactMetadataSource,
                    artifactFilter,
                    artifactCollector
            );

            CollectingDependencyNodeVisitor visitor = new CollectingDependencyNodeVisitor();
            rootNode.accept(visitor);

            List<DependencyNode> nodes = visitor.getNodes();
            for (DependencyNode dependencyNode : nodes) {
                walkDependencyNode(dependencyNode, dependencyNodes);
                //dependencyNode.ge().get(0).
                //  dependencyNodes.add(dependencyNode);
                //System.out.println("dependencyNode-------------" + dependencyNode);
            }

        } catch (DependencyTreeBuilderException e) {
            e.printStackTrace();
        }

        return dependencyNodes;

    }

    public List<DependencyNode> walkDependencyNode(DependencyNode dependencyNode, List<DependencyNode> nodes) {


        if (nodes == null) {
            nodes = new ArrayList<DependencyNode>();
        }
        if (dependencyNode == null) {
            return nodes;
        }

        nodes.add(dependencyNode);

        if (dependencyNode.hasChildren()) {
            for (DependencyNode node : dependencyNode.getChildren()) {
                if (node != null) {
                    nodes.add(node);
                }
                if (node != null && node.hasChildren()) {
                    for (DependencyNode nodeChid : node.getChildren()) {
                        walkDependencyNode(nodeChid, nodes);
                    }
                }
            }

        }

        return nodes;

    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public DependencyInjectionType getDependencyInjectionType() {
        return dependencyInjectionType;
    }

    public void setDependencyInjectionType(DependencyInjectionType dependencyInjectionType) {
        this.dependencyInjectionType = dependencyInjectionType;
    }

    public String getPersistence() {
        return persistence;
    }

    public void setPersistence(String persistence) {
        this.persistence = replaceAdress(persistence);
    }

    public MavenProject getMavenProject() {
        return mavenProject;
    }

    public void setMavenProject(MavenProject mavenProject) {
        this.mavenProject = mavenProject;
    }

    private String replaceAdress(String value) {

        if (value == null || value.equals("")) {
            return value;
        }

        value = value.replaceAll("\\\\", "/");
        value = value.replaceAll("/{1,}", "/");
        return value;
    }

    public ArtifactRepository getLocalRepository() {
        return localRepository;
    }

    public void setLocalRepository(ArtifactRepository localRepository) {
        this.localRepository = localRepository;
    }

    public String getLocalRepositoryPath() {
        return getLocalRepository().getBasedir();
    }

    public DependencyTreeBuilder getTreeBuilder() {
        return treeBuilder;
    }

    public void setTreeBuilder(DependencyTreeBuilder treeBuilder) {
        this.treeBuilder = treeBuilder;
    }

    public ArtifactFactory getArtifactFactory() {
        return artifactFactory;
    }

    public void setArtifactFactory(ArtifactFactory artifactFactory) {
        this.artifactFactory = artifactFactory;
    }

    public ArtifactMetadataSource getArtifactMetadataSource() {
        return artifactMetadataSource;
    }

    public void setArtifactMetadataSource(ArtifactMetadataSource artifactMetadataSource) {
        this.artifactMetadataSource = artifactMetadataSource;
    }

    public ArtifactCollector getArtifactCollector() {

        return artifactCollector;
    }

    public void setArtifactCollector(ArtifactCollector artifactCollector) {
        this.artifactCollector = artifactCollector;
    }

    public ArtifactFilter getArtifactFilter() {
        return artifactFilter;
    }

    public void setArtifactFilter(ArtifactFilter artifactFilter) {
        this.artifactFilter = artifactFilter;
    }
}
