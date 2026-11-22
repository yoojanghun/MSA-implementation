dependencies {
    // Web & GraphQL
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-graphql")

    // MariaDB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mariadb.jdbc:mariadb-java-client")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // GraphQL 단위/통합 테스트
    testImplementation("org.springframework.graphql:spring-graphql-test")

    // 인메모리 테스트용 H2 DB (로컬 테스트 시 활용)
    testImplementation("com.h2database:h2")
}
