# Modificaciones para el front
Mi parte en el trabajo es el Front asi que tuve que levantar la imagen en este proyecto para ello ise algunas modificaciones para levantar el backend y obtener sus datos
## 1.- Levantar el docker:
en la ruta de  \bacan-backend\docker\template> se deve agregar un . env con estos datos de ejemplo
```bash
POSTGRES_USER=admin
POSTGRES_PASSWORD=admin123
POSTGRES_DB=ecm
```
posterior mente se tendra que levantar el docker con
```bash
docker-compose up -d
```
Verificar la instalacion con:
```bash
docker ps
```
logs:
```bash
docker logs docker-db-1
```
## 2.- Correr local(antes de contenizar)
dejar corriendo config-server(Terminal1):
```bash
.\gradlew :microservices:store:clean

.\gradlew --no-daemon :cloud:config-server:bootRun --args="--spring.profiles.active=local,native"

```
dejar Store config-server(Terminal2):
```bash
.\gradlew :microservices:store:clean

.\gradlew --no-daemon :microservices:store:bootRun `
  --args="--spring.profiles.active=local `
          --spring.cloud.config.uri=http://127.0.0.1:8982 `
          --spring.cloud.config.profile=local"
```
Verificar (Terminal3):
```bash
curl.exe http://127.0.0.1:8982/ms-store/local
curl.exe http://127.0.0.1:8082/actuator/health
```

Logs Local:
```bash
curl.exe -s "http://127.0.0.1:8082/actuator/metrics/r2dbc.pool.acquired"
curl.exe -s "http://127.0.0.1:8082/actuator/metrics/r2dbc.pool.idle"
curl.exe -s "http://127.0.0.1:8082/actuator/metrics/r2dbc.pool.allocated"

curl.exe -s http://127.0.0.1:8082/actuator/metrics | Select-String -SimpleMatch "r2dbc"

```
## 3.- Correr local(antes de contenizar)
Se deve tener en variables de entorno JDK 21
- **Modificaciones necesarias**
  - .\gradlew -version
  - .\gradlew --stop
  - .\gradlew clean build -x test


