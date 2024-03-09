# Use a imagem oficial do OpenJDK como base
FROM openjdk:21-jdk

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o JAR da sua aplicação para o contêiner
COPY build/libs/rinha-backend-q1-2024-0.0.1-SNAPSHOT.jar app.jar

# Defina as variáveis de ambiente, se necessário
# ENV VARIABLE_NAME value

# Exponha a porta da sua aplicação
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]