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
custom plugins, tasks, configurations, and utility functions, across all projects in your build. [buildSrc guide](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html)

## DDD and Hexagonal Architecture
**Domain Driven Design**: Focuses on accurately understanding and modeling the business domain, helping to create systems that accurately reflect business processes and concepts.
**Hexagonal Architecture**: Focuses on decoupling the core business from implementation details, making it easier to replace and evolve the technical infrastructure over time.
[Detailed comparison](https://es.linkedin.com/pulse/explorando-los-fundamentos-de-desarrollo-software-vs-g-sanchez-d5npe)