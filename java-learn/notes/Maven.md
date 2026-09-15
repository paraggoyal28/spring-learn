# Maven Interview Questions

## How have you used Maven?

Used Maven in spring/micronuat framework project to manage dependencies used inside the service, compile code, run tests, and package the application. In my current work, I also understand the importance of repeatable builds and test gates because I work with E2E and release automation pipelines.  

If asked: Mention only Maven plugins, parent POMs, CI commands, or repository work that I actually worked on.

## Core Maven Questions

### 1. What is Maven ? 
Maven is a Java build and dependency management tool. It uses pom.xml to define how a project is compiled, tested, packaged, and published.

Maven is not a CI tool or application runtime; CI tools call Maven as part of a pipeline.

### 2. What is a POM ? 
Project Object Model. The pom.xml contains project details, dependencies, plugins, Java version, build settings, profiles and module information.

### 3. What are Maven coordinates ? 

Maven identifies an artifact mainly by groupId, artifactId, and version.
xml
<groupId>com.example</groupId>
<artifactId>orders-service</artifactId>
<version>1.0.0</version>

### 4. What is Maven's build lifecycle ? 

Maven has standard lifecycle phases. The main flow is 
validate -> compile -> test -> package -> verify -> install -> deploy

**If asked**: Running a later phase runs all earlier phases automatically.

### 5. What does 'mvn clean verify' do ? 

'clean' removes old build output, and 'verify' builds and runs all configured checks up to the verification phase. It is a common CI command.

### 6. What is the difference between a phase and a goal ? 

A phase is a lifecycle step, such as 'test'. A goal is an action from a plugin, such as 'surefire:test', which Maven runs in that phase.

### 7. What is the difference among 'package', 'install' and 'deploy' ? 

package - Creates a JAR, WAR, or other distributable artifacts in 'target/'.
install - Copies the artifact to the local Maven repository for local projects.
deploy - Publishes the artifact to a remote repository for others/CI to use.

### 8. What are Maven's standard directories ? 

Production code goes in 'src/main/java', resources in 'src/main/resources', tests in 'src/test/java', and build output in 'target/'.

