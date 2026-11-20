dependencies {
    // Web & GraphQL
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-graphql")

    // MongoDB
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // GraphQL 단위/통합 테스트
    testImplementation("org.springframework.graphql:spring-graphql-test")

    // Embedded MongoDB 
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo.spring3x:4.12.2")
}
