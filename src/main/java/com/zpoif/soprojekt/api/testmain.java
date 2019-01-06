package com.zpoif.soprojekt.api;

import com.google.code.stackexchange.schema.TimePeriod;

import java.util.Date;

public class testmain {


    public static void main(String args[]) {
        //wykres 2
        /*Date date1 = new Date(1543622400000L); //1543363200000L
        Date date2 = new Date(1546214400000L); //1543622400000L

        TimePeriod timePeriod = new TimePeriod(date1, date2);
        StopWatch sw = new org.springframework.util.StopWatch();
        sw.start("test");
        Table testData = new QuestionCounter(timePeriod).receiveData();
        sw.stop();
        System.out.println("Twój czas działania QuestionCounter wyniósł: " + sw.getLastTaskTimeMillis()/1000 + "sekund");

        Plot.show(TimeSeriesPlot.create("Liczba nowych pytan na stronie w poszczegolnych dniach", testData,"data", "liczba"));*/

        // obliczanie róznicy pomiędzy dwoma dniami z timeperiod i zwracanie liczby dni
        /*TimePeriod timePeriod = new TimePeriod(new Date(1546300800000L), new Date(1546300800000L));
        Duration d =
                Duration.between(                   // Calculate the span of time between two moments as a number of hours, minutes, and seconds.
                        timePeriod.getStartDate().toInstant() ,    // Convert legacy class to modern class by calling new method added to the old class.
                        timePeriod.getEndDate().toInstant()                 // Capture the current moment in UTC. About two and a half hours later in this example.
                );
        int n = (int) d.toDays();
        System.out.println(n);*/

        /*Table testData = SiteStatsGetter.receiveData();
        System.out.println(testData.toString());*/

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
        map.remove("api_revision");
        String[] a = new String[12];
        map.values().toArray(a);

        Double[] doubleValues = Arrays.stream(a)
                .map(Double::valueOf)
                .toArray(Double[]::new);*/

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
