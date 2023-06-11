package ru.netology.geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.stream.Stream;

public class GeoServiceImplTest {
    public static Stream<Arguments> idParams() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("96.43.183.149", new Location("New York", Country.USA, null,  0)),
                Arguments.of("172.32.32.11", new Location("Moscow", Country.RUSSIA, null, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("idParams")
    public void byIpTest(String ip, Location location){
        //arrange
        GeoServiceImpl geoService = new GeoServiceImpl();

        //act
        String expended = geoService.byIp(ip).getCity();

        //assert
        assertThat(expended, equalTo(location.getCity()));
    }
}
