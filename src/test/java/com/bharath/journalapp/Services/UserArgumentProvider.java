package com.bharath.journalapp.Services;

import com.bharath.journalapp.Entities.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(Arguments.of(User.builder().username("Harisha").password("12345").build()),
                         Arguments.of(User.builder().username("Jaishankar").password("12345").build()),
                         Arguments.of(User.builder().username("Jeevan").password("12345").build()));
    }

    //So this is a custom argument provider that provides a stream of arguments to the test method that requires
    // objects of some class type, implement the interface, and its method and create the objects of that class
    //either using the constructor or use the builder annotation on that class and build the objects for testing
    //and this stream is used to extract individual object from the stream and use it to perform the tests
}
