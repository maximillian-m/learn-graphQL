package com.maximillian.graph.maximillian.configurations;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    @GraphQlExceptionHandler(RuntimeException.class)
    public GraphQLError handleRuntimeException(RuntimeException runtimeException, DataFetchingEnvironment env) {
            return GraphQLError.newError()
                    .message(runtimeException.getMessage())
                    .path(env.getExecutionStepInfo().getPath())
                    .build();
    }
}
