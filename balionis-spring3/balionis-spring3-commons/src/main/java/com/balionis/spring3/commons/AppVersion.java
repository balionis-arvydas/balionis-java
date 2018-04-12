package com.balionis.spring3.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public final class AppVersion {
    private final String projectName;
    private final String projectVersion;
    private final String buildNumber;
    private final String buildTime;

    private AppVersion(String projectName, String projectVersion, String buildNumber, String buildTime) {
        this.projectName = projectName;
        this.projectVersion = projectVersion;
        this.buildNumber = buildNumber;
        this.buildTime = buildTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectVersion() {
        return projectVersion;
    }
        
    public String getBuildNumber() {
        return buildNumber;
    }

    public String getBuildTime() {
        return buildTime;
    }

    @Override
	public String toString() {
		return "AppVersion [projectName=" + projectName + ", projectVersion="
				+ projectVersion + ", buildNumber=" + buildNumber
				+ ", buildTime=" + buildTime + "]";
	}

	public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private ClassLoader classLoader = AppVersion.class.getClassLoader();
        private String filename = "META-INF/MANIFEST.MF";
        
        private Builder() {            
        }

        public Builder withClassLoader(ClassLoader classLoader)  {
            this.classLoader = classLoader; 
            return this;
        }

        public Builder withFilename(String filename)  {
            this.filename = filename; 
            return this;
        }
                
        public AppVersion build() throws AppCrashException {
            
            InputStream in = null;
            try {
                in = classLoader.getResourceAsStream(filename);
                if (in == null) {
                    throw AppCrashExceptionBuilder.newS001(filename);
                }
                
                Manifest manifest = new Manifest(in);
                Attributes attrs = manifest.getMainAttributes();
                
                String specificationTitle = attrs.getValue("Specification-Title");
                String specificationVersion = attrs.getValue("Specification-Version");
                String implementationVersion = attrs.getValue("Implementation-Version");
                String buildTime = attrs.getValue("Build-Time");

                return new AppVersion(specificationTitle, specificationVersion, implementationVersion, buildTime);
                
            } catch(IOException exc) {
                throw AppCrashExceptionBuilder.newS001(filename);
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(AppVersion.builder().build());
        } catch(AppCrashException exc) {
            exc.printStackTrace();
        }
    }    
}