package com.zpoif.soprojekt.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

public class SiteStatsGetter {

    public static Table receiveData(){
        /* funkcja zwraca dane na temat strony stackoverflow.com w formie tabelki
        nie wykorzystuje biblioteki do api, ponieważ dla tych danych jest ona przestarzała i wyrzuca błąd
        w związku z tym, ręcznie pozyskuje dane z linku, a następnie parsuje je i zamienia na objekt java*/

        URL url = null;
        try {
            url = new URL("https://api.stackexchange.com/2.2/info?site=stackoverflow&key=4ccOeYINdrpbkpWOgBTZSw((");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) Objects.requireNonNull(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser jp = new JsonParser();
        JsonElement root = null;

        try {
            root = jp.parse(new InputStreamReader(new GZIPInputStream((InputStream) request.getContent())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final JsonObject rootobj = root.getAsJsonObject();

        JsonObject json = rootobj.getAsJsonArray("items").get(0).getAsJsonObject();

        Gson gson = new Gson();
        Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> dataMap = gson.fromJson(json, stringStringMap);

        String[] names = (String[]) dataMap.keySet().toArray();
        Double[] values = (Double[]) dataMap.values().toArray();


        /*Table data = Table.create("SiteStatsData");
        data.addColumns(StringColumn.create("jezyk",names), DoubleColumn.create("liczba",values));*/
        return TableMaker.make("język", names, "liczba", values);
    }

}
