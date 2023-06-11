package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.stream.Stream;

public class LocalizationServiceImplTest {
    public static Stream<Arguments> countryParams() {
        return Stream.of(
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }

    @ParameterizedTest
    @MethodSource("countryParams")
    public void localTest(Country country, String result){
        //arrange
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        //act
        String expended = localizationService.locale(country);

        //assert
        assertThat(expended, equalTo(result));
    }
}
