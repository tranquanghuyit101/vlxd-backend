# ==========================================
# STAGE 1: Build source code with Maven & Java 21
# ==========================================
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# 1. Tận dụng Docker cache layer cho dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Copy mã nguồn và đóng gói JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ==========================================
# STAGE 2: Lightweight Runtime JRE 21
# ==========================================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy file .jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Render sẽ cấp biến môi trường $PORT (mặc định 8080 nếu chạy local)
EXPOSE 8080
ENV PORT=8080

ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar -Dserver.port=${PORT} app.jar"]
