## # PDF Assistant with Langchain4J, Spring Boot, Huggingface and Neo4j

This repository contains source code for the PDF Assistant application, that can answer questions based on the information contained in a given PDF.

It uses the LangChain4J framework to interact with OpenAI LLM, Neo4j to store the embeddings, and Spring Boot as the framework to create REST API.

### Pre-requisites
1. Install Neo4j Locally. Create a new database named pratik.
Optionally, create a new user for the pratik database.
2. Create HuggingFace Account and Access Token


### Springboot

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Open the terminal and run the following command to install dependencies:

```bash
mvn install
```
4. Once the dependencies are installed successfully, you can run the application using:

```bash
mvn spring-boot:run
```
5. Once the Spring Boot application is running, you can access the API endpoint at http://localhost:8080/api/chat
