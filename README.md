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
--changelogFile=microservices/user/src/main/resources/db/changelog/migrations/003_create_address_table.xml \
--default-schema-name=usr \
--include-objects=address \
--include-schema=true \
--author="Christian W. Aranibar Solaligue"

# Data -> Is necessary specify the table name to avoid populate unnecessary data.
liquibase generateChangeLog \
--include-objects=<table> \
--diff-types=data \
--data-output-directory=src/main/resources/db/changelog/data \
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