package com.zpoif.soprojekt.api;



import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class testmain {


    public static void main(String args[]) throws IOException {
        // to działa bo api zwraca gzip
        /*URL url = new URL("https://api.stackexchange.com/2.2/info?site=stackoverflow");
        final HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        final JsonParser jp = new JsonParser();
        final JsonElement root = jp.parse(new InputStreamReader(new GZIPInputStream((InputStream) request.getContent())));
        final JsonObject rootobj = root.getAsJsonObject();

        JsonObject json = rootobj.getAsJsonArray("items").get(0).getAsJsonObject();

        Gson gson = new Gson();
        Type stringStringMap = new TypeToken<Map<String, String>>(){}.getType();
        Map<String,String> map = gson.fromJson(json, stringStringMap);
        System.out.println(map.get("total_users"));*/

        // to nie działa bo api zwraca gzip
        //https://httpbin.org/get?color=red&shape=oval
        //https://api.stackexchange.com/2.2/info?site=stackoverflow
        /*URL url = new URL("https://api.stackexchange.com/2.2/info?site=stackoverflow");
        InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8"); //CP857 UTF-8
        int data = reader.read();
        while(data != -1){
            System.out.print((char) data);
            data = reader.read();
        }

        reader.close();
        MyDto dto = new Gson().fromJson(reader, MyDto.class);
*/

        /*//LanguageTagCounter time test
        StopWatch sw = new org.springframework.util.StopWatch();
        TimePeriod timePeriod = new TimePeriod(new Date(1514764800000L), new Date(1517356800000L));

        sw.start("jedenWątek");
            Table testData1 = new LanguageTagCounterAlpha().receiveData(timePeriod);
        sw.stop();
        sw.start("wieleWatkow");
            Table testData2 = new LanguageTagCounter2(timePeriod).receiveData2();
        sw.stop();
        System.out.println(testData1);
        System.out.println(testData2);
        System.out.println("Table describing all tasks performed :\n"+sw.prettyPrint());*/


        /*final StackExchangeApiClientFactory factory = StackExchangeApiClientFactory
                .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                        StackExchangeSite.STACK_OVERFLOW);
        final AsyncStackExchangeApiClient client = factory
                .createAsyncStackExchangeApiClient();
        Future<List<Question>> questionsFuture = client.getQuestions(timePeriod);
        List<Question> questions = null;
        try {
            questions = questionsFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(questions.size());*/
        /*


        //System.out.print(testData.printHtml());

        Plot.show(
                PiePlot.create("Dane", testData, "jezyk", "liczba"));*/

        // API test
        /*StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory
                .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                        StackExchangeSite.STACK_OVERFLOW);

        String filter = "default";
        String tag = "python";
        TimePeriod timePeriod = new TimePeriod(new Date(1543622400000L), new Date(1543622400000L));
        Paging paging = new Paging(1, 100); // pageNumber gets u next page
        PagedList<Question> questions = queryFactory.newQuestionApiQuery().withTimePeriod(timePeriod)
                .withPaging(paging).withFilter(filter)
                .withSort(Question.SortOrder.MOST_VOTED) //MOST_HOT
                .withTags(tag).list();

        System.out.println(questions.size());
        for(int i=1; i < questions.size(); i++){
            Question a = questions.get(i);
            System.out.print(a.getCreationDate());
            //System.out.println(a.getBody()); // null :C
            System.out.print(" View Count:" + a.getViewCount());
            System.out.print(" Score:" + a.getScore());
            //System.out.print(a.getAnswers());
            //System.out.print(a.getTags());
            System.out.println("  " + a.getTitle());

        }*/


        // przykład korzystający z API tags - pokazuje że to query nie działa
        /*StackExchangeApiQueryFactory queryFactory = StackExchangeApiQueryFactory
                .newInstance("4ccOeYINdrpbkpWOgBTZSw((",
                        StackExchangeSite.STACK_OVERFLOW);

        TimePeriod timePeriod = new TimePeriod(new Date(1543622400000L), new Date(1545004800000L));
        Paging paging = new Paging(1, 100); // pageNumber gets u next page

        PagedList<Tag> tags = queryFactory.newTagApiQuery().withTimePeriod(timePeriod).withPaging(paging).withFilter("default").list();
        for(int i=1; i < tags.size(); i++){
            Tag a = tags.get(i);
            System.out.println(a.getName());
            System.out.println("Count:" + a.getCount());
        }*/

    }
}
