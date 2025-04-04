package com.maximillian.graph.maximillian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaximillianApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaximillianApplication.class, args);
	}

}
/*
DIRECTIVES
===============
There are two categories of directives: schema and operation. Schema directives are used on the
schema elements and operation directive are used in operations within a graphQL document.

-@deprecated schema directive is used to indicate deprecated schema elements

For graphQL there are specific locations to determine if the directive is a schema or operation directive.
E.g FIELD_DEFINITION is a schema directive, whereas a directive on a FIELD is an operation directive.
There are four built-in directives: @skip, @include, @deprecated and @specifiedBy. The last two are schema
directives.

skip and include directives work as the JSON include for rest services, when we include or skip we should be able to do these using variables
in the sense that when the query is to be executed, then we can pass the value that we want the skip or include to have at runtime.

We have already covered the @deprecated directives, the @specifiedBy directive is used to provide a scalar specification URL
to describe the behaviour of custom scalar types. We will be covering scalars very soon.

DEFINING CUSTOM DIRECTIVE:
check the graphLQL schema for these.

For the schema direction, there are about eleven locations where these directives can be used:
OBJECT this is declared on the object type like Query.
SCHEMA this is defined on the schema
SCALAR defined on a scalar
FIELD_DEFINITION defined on field types like String, Int and ID
ARGUMENT_DEFINITION declared on the argument
INTERFACE, UNION, ENUM, ENUM_VALUE
INPUT_OBJECT defined on the input object
INPUT_FIELD_DEFINITION

for Operation directives:
FIELD which is different from the FIELD_DEFINITION, directives like that of the operation directives can have arguments
Note that all the directives except the built-in directives do not have any effect until there has been an implementation.

There are eight locations for the operation directives:
QUERY, MUTATION, SUBSCRIPTION, FIELD, FRAGMENT_DEFINITION, FRAGMENT_SPREAD, INLINE_FRAGMENT, VARIABLE_DEFINITION
We have learnt that fragments helps to reduce code repetition in the schema, and you can use the on to define and spread fragments
query Animal{
  pets{
    ...on Dog{
      ...AnimalDetails
    }
    ...on Duck{
      ...AnimalDetails
    }
    ...on Cat{
      ...AnimalDetails
    }
  }
}
fragment AnimalDetails on Animal{
    id
    classification {
        name
        species
    }
    age
}
fragment AuthorDetails on Author{
    id
    firstName
    lastName
}
query getBooksById{
  bookById(id : "book-1"){
    id
    name
    author{
      ...AuthorDetails
    }
  }
}

Repeatable directives, we can define schema and operation directives as repeatable,
enabling it to be used multiple times in the same location. If repeatable is not included
in the directive definition, the directive will be non-repeatable by default.
- directive @owner(name: String) repeatable on FIELD_DEFINITION..

==A big catch here is that the schema directives are used in the schema and never at the client side, so the
client does not need to pass it as part of the query to the backend so to say. For operation directives they are
supposed to alter or help the client send in dynamic data to the backend, based on this dynamism different results can
be retrieved from the server side.
 */