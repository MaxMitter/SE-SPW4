package spw4.exploration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

@ExtendWith(MockitoExtension.class)
class MockitoDemo {

    @Test
    @Tag("mockito")
    void mockitoDemoTest() {
        var calc = mock(Calculator.class);
        //when(calc.add(anyFloat(), anyFloat())).thenReturn(42f);
        when(calc.add(eq(1f), eq(2f))).thenReturn(42f);

        var result = calc.add(1, 2);
        var notDefined = calc.add(1, 3); // no stub defined

        assertEquals(42f, result);
        assertEquals(0f, notDefined);
    }

    @Test
    @Tag("mockito")
    void mockitoStaticDemoTest() {
        try (var staticMock = mockStatic(Calculator.class)) {
            staticMock.when(Calculator::pi).thenReturn(42.0);
            assertEquals(42.0, Calculator.pi());
        }
    }

    @Test
    @Tag("mockito")
    void registerWithValidNameAndAgeSucceeds() {
        PersonRepository rep = mock(PersonRepository.class);
        PersonService service = new PersonService(rep);

        service.register("Alice", 25);

        //verify(rep).createPerson(any());
        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        verify(rep).createPerson(captor.capture());

        assertAll(
                () -> assertEquals("Alice", captor.getValue().getName()),
                () -> assertEquals(25, captor.getValue().getAge())
        );
    }

    @Test
    @Tag("mockito")
    void registerWithInvalidNameAndAgeFails() {
        PersonRepository rep = mock(PersonRepository.class);
        PersonService service = new PersonService(rep);

        assertThrows(IllegalArgumentException.class, () -> {
            service.register("Bob", 10);
        });

        verifyNoInteractions(rep);
    }

    @Test
    @Tag("mockito")
    void getAverageAge_whenPersonRegistered_returnsAverageAge() {
        PersonRepository rep = mock(PersonRepository.class);
        when(rep.readAllPersons()).thenReturn(
                Stream.of(new Person("a", 35), new Person("b", 28))
        );
        double expected = (35 + 28) / 2f;
        PersonService sut = new PersonService(rep);

        var result = sut.getAverageAge();

        assertEquals(expected, result);
    }

    @Test
    @Tag("mockito")
    void verifyByNumberOfCalls(@Mock PersonRepository rep) {
        when(rep.readPersonById(anyInt())).thenReturn(new Person("a"));

        var personA = rep.readPersonById(0);
        var personB = rep.readPersonById(1);

        verify(rep, times(2)).readPersonById(anyInt());
        verify(rep, atLeast(1)).readPersonById(anyInt());
        verify(rep, atLeastOnce()).readPersonById(anyInt());
        verify(rep, atMost(2)).readPersonById(anyInt());
        verify(rep, never()).readPersonById(2);
    }

    @Test
    @Tag("mockito")
    void multipleMatchingStubs(@Mock PersonRepository rep) {
        when(rep.readPersonById(anyInt())).thenReturn(new Person("X"));
        when(rep.readPersonById(anyInt())).thenReturn(new Person("A"))
                                          .thenReturn(new Person("B"));
        when(rep.readPersonById(3)).thenReturn(new Person("C"));

        doReturn(new Person("X")).when(rep).readPersonById(anyInt());

        Person personA = rep.readPersonById(1);
        Person personB = rep.readPersonById(2);
        Person personC = rep.readPersonById(3);

        assertAll(
                () -> assertEquals("B", personA.getName()),
                () -> assertEquals("B", personB.getName()),
                () -> assertEquals("C", personC.getName())
        );
    }
}
