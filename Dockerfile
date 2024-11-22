#Definindo a versão do Java para publicar o projeto
FROM openjdk:21

#Pasta do servidor para executar o projeto
WORKDIR /app

#Copiando os arquivos do projeto para a pasta acima
COPY . /app

#Comando para publicar o projeto no DOCKER
RUN ./mvnw -B clean package -DskipTests

#Porta para execução do projeto
EXPOSE 8082

#Executando o projeto publicado no servidor
CMD ["java", "-jar", "target/apiProdutos-0.0.1-SNAPSHOT.jar"]