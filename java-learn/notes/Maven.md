# Maven Interview Questions

## How have you used Maven?

Used Maven in spring Boot /micronuat framework project to manage dependencies used inside the service, compile code, run tests, and package the application. In my current work, My E2E and release-automation work also makes me focused on repeatable builds and clear test gates.


## Core Maven Questions

### 1. What is Maven ? 
Maven is a Java build and dependency management tool. It uses pom.xml to standardize compiling, testing, packaging, and publishing.

### 2. What is a POM ? 
POM means Project Object Model. The pom.xml defines project metadata, dependencies, plugins, Java version, profiles and modules.

### 3. What are Maven coordinates ? 

Maven identifies an artifact mainly by groupId, artifactId, and version identify an artifact. 
xml
<groupId>com.example</groupId>
<artifactId>orders-service</artifactId>
<version>1.0.0</version>

### 4. What is Maven's build lifecycle ? 

Maven has standard lifecycle phases. The main flow is 
validate -> compile -> test -> package -> verify -> install -> deploy

**If asked**: Running a later phase runs all earlier phases automatically.

### 5. What does 'mvn clean verify' do ? 

'clean' removes old build output, and 'verify' compiles, tests, packages, and runs configured checks up to verification phase. it is a common CI command.  

### 6. What is the difference between a phase and a goal ? 

A phase is a lifecycle step, such as 'test'. A goal is an action from a plugin, such as 'surefire:test', which Maven runs in that phase.

### 7. What is the difference among package, install, and deploy ? 

- package: creates the JAR/WAR in target
- install: also puts it into the local Maven repository
- deploy: publishes it to remote artifact repository

### 8. What is the difference between a dependency and a plugin ? 

A dependency is a library used by application code. A plugin performs a build task, such as compiling Java, running tests, or packaging a JAR.

### 9. What is the difference between Parent POM and aggregate POM ? 

A Parent POM shares configuration with child modules. An aggregate POM list modules so Maven can build them together. One root POM can do both.

### 10. What is the difference between plugin and pluginManagement ? 

plugins activates plugins for a module. 
pluginManagement centrally define plugin versions and default configuration, but does not activate a plugin itself.

### 11. What is the difference between -DskipTests and -Dmaven.test.skip=true ? 

-DskipTests skips test execution but normally still compiles tests. 
-Dmaven.test.skip=true skips both compiling and running tests.

### 12. What is a BOM ? 

A BOM is a POM that provides compatible versions for a group of libraries. Spring Boot and Micronaut projects commonly use a parent POM or BOM to keep framework dependencies aligned.

### 13. What is a transitive dependency and how are conflicts resolved ? 

A transitive dependency is brought in by another dependency. Maven usually selects the nearest version in the dependency tree. I prefer managing the intended version instead of relying on that rule.

### 14. What should we check when a build passes locally but fails in CI ? 

Compare the JDK, Maven wrapper versions, command, active profiles, environment variables, repository access, effective POM, and dependency tree. Reproduce with CI command before changing code or skipping tests.

### 15. How do you set the Java version in Maven ? 

maven.compiler.release
and confirm with mvn -v

