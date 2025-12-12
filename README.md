# GHAS Java Demo

A simple Java web application for testing GitHub Advanced Security (GHAS) features. This project demonstrates:
- Java Servlet technology
- JavaServer Pages (JSP)
- Maven build with WAR packaging
- GitHub Advanced Security integration for code scanning

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6 or higher

## Building the Project

To build the project and generate the WAR file:

```bash
mvn clean package
```

This will:
1. Compile the Java source code
2. Run unit tests
3. Package the application into a WAR file located at `target/ghas-java-demo.war`

## Running Tests

To run the unit tests:

```bash
mvn test
```

## Deploying the Application

The generated WAR file can be deployed to any Jakarta EE compatible application server such as:
- Apache Tomcat 10.x or higher
- Eclipse Jetty 11.x or higher
- WildFly
- GlassFish

Simply copy the `target/ghas-java-demo.war` file to your application server's deployment directory.

## Application Features

- **Home Page** (`/index.jsp`): Welcome page with application information
- **Hello Servlet** (`/hello`): A simple servlet that greets users
  - Accepts an optional `name` parameter (e.g., `/hello?name=John`)

## Project Structure

```
ghas-java-demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/servlet/
│   │   │       └── HelloServlet.java
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       └── index.jsp
│   └── test/
│       └── java/
│           └── com/example/servlet/
│               └── HelloServletTest.java
├── pom.xml
└── README.md
```

## GitHub Advanced Security

This project is configured to work with GitHub Advanced Security features:
- **Code Scanning**: Automatically scans code for security vulnerabilities
- **Dependency Review**: Checks dependencies for known vulnerabilities
- **Secret Scanning**: Detects accidentally committed secrets

These features help ensure code quality and security before merging pull requests.
