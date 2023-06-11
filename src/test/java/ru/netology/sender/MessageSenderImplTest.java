package ru.netology.sender;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MessageSenderImplTest {
    static GeoService geoService;
    static LocalizationService localizationService;
    @BeforeAll
    public static void initMock(){
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
    }

    @Test
    public void sendTestRus() {
        // arrange
        Mockito.when(geoService.byIp(anyString()))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(geoService.byIp("").getCountry()))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        //act
        String expended = messageSender.send(headers);

        //assert
        assertThat(expended, equalTo("Добро пожаловать"));
    }

    @Test
    public void sendTestEng() {
        // arrange
        Mockito.when(geoService.byIp(anyString()))
                .thenReturn(new Location("New York", Country.USA, null,  0));
        Mockito.when(localizationService.locale(geoService.byIp("").getCountry()))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        //act
        String expended = messageSender.send(headers);

        //assert
        assertThat(expended, equalTo("Welcome"));
    }

}
