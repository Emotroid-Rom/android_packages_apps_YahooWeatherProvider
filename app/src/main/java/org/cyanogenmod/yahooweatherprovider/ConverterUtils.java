/**
 * Copyright (C) 2016 The CyanogenMod Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cyanogenmod.yahooweatherprovider;

import org.cyanogenmod.yahooweatherprovider.yahoo.response.Admin1;
import org.cyanogenmod.yahooweatherprovider.yahoo.response.Admin2;
import org.cyanogenmod.yahooweatherprovider.yahoo.response.Admin3;
import org.cyanogenmod.yahooweatherprovider.yahoo.response.Forecast;
import org.cyanogenmod.yahooweatherprovider.yahoo.response.Locality1;
import org.cyanogenmod.yahooweatherprovider.yahoo.response.Place;
import org.cyanogenmod.yahooweatherprovider.yahoo.response.Postal;

import java.util.ArrayList;
import java.util.List;

import cyanogenmod.weather.WeatherInfo;
import cyanogenmod.weather.WeatherLocation;

public class ConverterUtils {

    public static ArrayList<WeatherInfo.DayForecast> convertForecastsToDayForecasts(List<Forecast> forecasts) {
        ArrayList<WeatherInfo.DayForecast> ret = new ArrayList<>();
        for (Forecast forecast : forecasts) {
            WeatherInfo.DayForecast dayForecast = new WeatherInfo.DayForecast.Builder(
                    Integer.parseInt(forecast.getCode()))
                    .setHigh(Double.parseDouble(forecast.getHigh()))
                    .setLow(Double.parseDouble(forecast.getLow()))
                    .build();
            ret.add(dayForecast);
        }
        return ret;
    }

    public static List<WeatherLocation> convertPlacesToWeatherLocations(List<Place> places) {
        List<WeatherLocation> ret = new ArrayList<>();
        for (Place place : places) {
            Postal postal = place.getPostal();
            Admin1 admin1 = place.getAdmin1();
            if (admin1 != null && admin1.getContent() != null) {
                WeatherLocation weatherLocation = new WeatherLocation.Builder(admin1.getWoeid(),
                        admin1.getContent())
                        .setCountry(place.getCountry().getContent())
                        .setCountryId(place.getCountry().getCode())
                        .setPostalCode(postal == null ? "" : postal.getContent())
                        .build();
                ret.add(weatherLocation);
            }

            Admin2 admin2 = place.getAdmin2();
            if (admin2 != null && admin2.getContent() != null) {
                WeatherLocation weatherLocation = new WeatherLocation.Builder(admin2.getWoeid(),
                        admin2.getContent())
                        .setCountry(place.getCountry().getContent())
                        .setCountryId(place.getCountry().getCode())
                        .setPostalCode(postal == null ? "" : postal.getContent())
                        .build();
                ret.add(weatherLocation);
            }

            Admin3 admin3 = place.getAdmin3();
            if (admin3 != null && admin3.getContent() != null) {
                WeatherLocation weatherLocation = new WeatherLocation.Builder(admin3.getWoeid(),
                        admin3.getContent())
                        .setCountry(place.getCountry().getContent())
                        .setCountryId(place.getCountry().getCode())
                        .setPostalCode(postal == null ? "" : postal.getContent())
                        .build();
                ret.add(weatherLocation);
            }

            Locality1 locality1 = place.getLocality1();
            if (locality1 != null && locality1.getContent() != null) {
                WeatherLocation weatherLocation = new WeatherLocation.Builder(
                        locality1.getWoeid(),
                        locality1.getContent())
                        .setCountry(place.getCountry().getContent())
                        .setCountryId(place.getCountry().getCode())
                        .setPostalCode(postal == null ? "" : postal.getContent())
                        .build();
                ret.add(weatherLocation);
            }
        }
        return ret;
    }
}
