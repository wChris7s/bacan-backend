# Basics

## Gradle

Gradle Build Tool is a fast, dependable, and adaptable open-source build automation tool with an elegant
and extensible declarative build language.

### Structuring Builds

#### buildSrc

Subprojects in a multi-project build often share common dependencies.

Rather than duplicating the same dependency declarations across multiple build scripts, Gradle allows you to
centralize shared build logic in a special directory. This way, you can declare the dependency version in one place
and have it automatically apply to all subprojects.

**buildSrc** is a special directory in a Gradle build that allows you to organize and share build logic, such as
custom plugins, tasks, configurations, and utility functions, across all projects in your
build. [buildSrc guide](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html)

## DDD and Hexagonal Architecture

**Domain Driven Design**: Focuses on accurately understanding and modeling the business domain, helping to create
systems that accurately reflect business processes and concepts.
**Hexagonal Architecture**: Focuses on decoupling the core business from implementation details, making it easier to
replace and evolve the technical infrastructure over time.
[Detailed comparison](https://es.linkedin.com/pulse/explorando-los-fundamentos-de-desarrollo-software-vs-g-sanchez-d5npe)

## Config

### Liquibase

#### Install

Reference: [https://docs.liquibase.com/pro/get-started/install-liquibase-on-linux-with-debian-or-ubuntu](https://docs.liquibase.com/pro/get-started/install-liquibase-on-linux-with-debian-or-ubuntu)

```bash
wget -O- https://repo.liquibase.com/liquibase.asc | gpg --dearmor > liquibase-keyring.gpg && \
cat liquibase-keyring.gpg | sudo tee /usr/share/keyrings/liquibase-keyring.gpg > /dev/null && \
echo 'deb [arch=amd64 signed-by=/usr/share/keyrings/liquibase-keyring.gpg] https://repo.liquibase.com stable main' | sudo tee /etc/apt/sources.list.d/liquibase.list

# Then
sudo apt-get update
sudo apt-get install liquibase
liquibase --version
```

#### Commands

Reference: [https://docs.liquibase.com/reference-guide/generate-changelog](https://docs.liquibase.com/reference-guide/generate-changelog)

```bash
# Table
liquibase generateChangeLog \
--changelogFile=microservices/store/src/main/resources/db/changelog/migrations/004_create_product_category_table.xml \
--default-schema-name=store \
--include-objects=product_category \
--include-schema=true \
--author="Christian W. Aranibar Solaligue"

# Data -> Is necessary specify the table name to avoid populate unnecessary data.
liquibase generateChangeLog \
--changelogFile=microservices/user/src/main/resources/db/changelog/migrations/005_populate_user_table.xml \
--default-schema-name=person \
--include-objects=user \
--diff-types=data \
--include-schema=true \
--data-output-directory=microservices/user/src/main/resources/db/changelog/data \
--author="Christian W. Aranibar Solaligue"
```

### Terraform

Move to terraform folder and then:

```bash
aws sso login --profile dev

terraform init
terraform plan
terraform apply
```

## Deploy

### Docker

- Start container: `./start-docker-containers.sh` or `docker-compose -f ./docker/docker-compose.yaml up -d`
- Start containers with logs: `docker-compose -f ./docker/docker-compose.yaml up`
- Verify running container: `docker ps`
- View container logs: `docker logs <container-id>`
- Stop containers: `./stop-docker-containers.sh` or `docker-compose -f ./docker/docker-compose.yaml down`

### Gradle

- Stage for start microservice:
  - Stage one:
    - `./gradlew :cloud:config-server:clean`
    - `./gradlew :cloud:config-server:bootRun`
    - (Optional): `./gradlew :cloud:gateway:clean`
    - (Optional): `./gradlew :cloud:gateway:bootRun -Dspring.profiles.active=local`
  - Stage two:
    - `./gradlew :microservices:<ms>:clean`
    - `./gradlew :microservices:<ms>:bootRun -Dspring.profiles.active=local`

### Swagger

All microservices have documentations about it API, to access to that info its only need to config the microservice port
and run the next link:
`http://localhost:<port>/bcn/api/openapi/ui/swagger-ui/index.html`

### Build docker images

After run the above commands, execute: `./star-docker-containers.sh docker`

- Config server: `./gradlew :cloud:config-server:docker`
- Gateway server: `./gradlew :cloud:gateway:docker`
- User microservice: `./gradlew :microservices:user:docker`
- Location microservice: `./gradlew :microservices:location:docker`
- Store microservice: `./gradlew :microservices:store:docker`

#### To use pushed images:

```bash
docker login
```

#### To push images into docker hub:

- Config server: `./gradlew :cloud:config-server:dockerPush`
- Gateway server: `./gradlew :cloud:gateway:dockerPush`
- User microservice: `./gradlew :microservices:user:dockerPush`
- Location microservice: `./gradlew :microservices:location:dockerPush`
- Store microservice: `./gradlew :microservices:store:dockerPush`

### Pull docker images to aws

- `docker push 556180171691.dkr.ecr.us-east-2.amazonaws.com/config-server:1.0.0`
- `docker push 556180171691.dkr.ecr.us-east-2.amazonaws.com/user:1.0.0`
- `docker push 556180171691.dkr.ecr.us-east-2.amazonaws.com/location:1.0.0`
- `docker push 556180171691.dkr.ecr.us-east-2.amazonaws.com/store:1.0.0`

### Run stopped containers

[https://stackoverflow.com/questions/65953634/how-do-i-inspect-the-stopped-docker-container-files](https://stackoverflow.com/questions/65953634/how-do-i-inspect-the-stopped-docker-container-files)

- `docker run -d --entrypoint sleep <image> 3600`
- `docker exec -ti <container-id_or_hash> sh`

## Kubernetes

Install the above dependencies:

### eksctl

[https://docs.aws.amazon.com/eks/latest/eksctl/installation.html](https://docs.aws.amazon.com/eks/latest/eksctl/installation.html)

```bash
eksctl version
```

### helm

[https://helm.sh/docs/intro/install/](https://helm.sh/docs/intro/install/)

```bash
helm version
```

### kubectl

[https://v1-32.docs.kubernetes.io/docs/tasks/tools/install-kubectl-linux/](https://v1-32.docs.kubernetes.io/docs/tasks/tools/install-kubectl-linux/)

```bash
kubectl version --client
```

## Commands for Helm and k8s

Before run the above commands, run: `minikube start --cpus=4 --memory=8g`

### k8s

```bash
# Addons
minikube addons enable ingress
minikube addons enable metrics-server
```

```bash
# Load local image into minikube cluster
minikube image load <image:tag>
```

```bash
kubectl get pods # Get active pods
kubectl get services # Get active services
kubectl describe pod <podName> # Describe pod info
kubectl logs <podName> # See pod logs
```

- Scaling pods using user microservice:

```bash
# Autoscaling
kubectl autoscale deployment ms-user --cpu=50% --min=1 --max=5
# Monitoring
kubectl get hpa -w

# Charge (Op1)
while true; do
  wget -qO- http://192.168.49.2/bcn/api/user
  sleep 0.1
done

# Charge (Op2)
ab -n 1000 -c 50 http://192.168.49.2/bcn/api/user
```

### Helm

```bash
# To install release based on chart:
helm install <.Release.Name> <ChartPath> --set <CustomValue>=<value> -n <NameSpace>
```

```bash
helm install postgres ./k8s/database
helm install ingress ./k8s/ingress
helm install config-server ./k8s/microservices --set port=8982,springProfile=native,replicas=1 -n default
helm install ms-user ./k8s/microservices --set port=9090,springProfile=k8s,replicas=1 -n default
helm install ms-location ./k8s/microservices --set port=9092,springProfile=k8s,replicas=1 -n default
helm install ms-store ./k8s/microservices --set port=9093,springProfile=k8s,replicas=1 -n default
```

### Access to Ingress Controller or Gateway

Run `minikube ip` and then:

```bash
curl -i "http://<minikube-ip>/bcn/api/user" # User microservice (user, role and address services)
curl -i "http://<minikube-ip>/bcn/api/category?page=0&size=5&direction=ASC&property=id" # Store microservice (store, product and category services) 
curl -i "http://<minikube-ip>/bcn/api/location/country" # Location microservice
```

## Frontend

Frontend developers need to review the swagger files generated by the microservices. These files contain the information
needed to create resources or retrieve resources from the backend. These files should be entered for reading:
[https://editor.swagger.io/](https://editor.swagger.io/)