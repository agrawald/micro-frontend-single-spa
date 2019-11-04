package com.aus.asx.config;

import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.aus.asx.service.NotificationSvc;
import com.google.common.io.Resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GraphQLConfig {
    @Value("classpath:schema.graphqls")
    private Resource schemas;

    private final NotificationSvc notificationSvc;

    @Bean
    public GraphQL graphQL() throws Exception {
        final URL url = schemas.getURL();
        String sdl = Resources.toString(url, StandardCharsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) throws Exception {
        final TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        final RuntimeWiring runtimeWiring = buildWiring();
        final SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() throws Exception {
        return RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("allNotifications", notificationSvc.findAllDataFetcher()))
                .build();
    }
}