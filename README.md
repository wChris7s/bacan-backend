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
--changelogFile=microservices/store/src/main/resources/db/changelog/migrations/005_populate_categories_table.xml \
--default-schema-name=store \
--include-objects=category \
--diff-types=data \
--include-schema=true \
--data-output-directory=microservices/store/src/main/resources/db/changelog/data \
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