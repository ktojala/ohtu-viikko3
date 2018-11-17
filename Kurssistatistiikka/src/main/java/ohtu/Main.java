package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import org.apache.http.client.fluent.Request;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";

        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String kurssit = Request.Get(url).execute().returnContent().asString();

        System.out.println("json-muotoinen data:");
        System.out.println( kurssit );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(kurssit, Submission[].class);
        
        Map<String, List<Submission>> subsPercourse = new HashMap();

        System.out.println("opiskelijanumero "+studentNr);
        System.out.println("");
        int tehtsum = 0;
        int tuntisum = 0;
        for (Submission submission : subs) {
            System.out.println(submission);
            tehtsum += submission.getExercises().length;
            tuntisum += submission.getHours();
            if (!subsPercourse.containsKey(submission.getCourse())) {
                subsPercourse.put(submission.getCourse(), new ArrayList());
            }
            subsPercourse.get(submission.getCourse()).add(submission);
        }
        System.out.println("");
        System.out.println("yhteensä: "+tehtsum+" tehtävää "+tuntisum+" tuntia");

        String url2 = "https://studies.cs.helsinki.fi/courses/courseinfo";
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();
        System.out.println("");
        System.out.println("TEHTÄVÄT 2 ja 3");
//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText2 );

        Gson mapper2 = new Gson();
        CourseInfo[] courses = mapper2.fromJson(bodyText2, CourseInfo[].class);

        System.out.println("");
        System.out.println("opiskelijanumero "+studentNr);
        System.out.println("");

        Map<String,JsonObject> kurssidata = new HashMap();

        String url3 = "https://studies.cs.helsinki.fi/courses/rails2018/stats";
        String railsdata = Request.Get(url3).execute().returnContent().asString();
        JsonParser parseri = new JsonParser();
        JsonObject parsittu = parseri.parse(railsdata).getAsJsonObject();
        kurssidata.put("rails2018",parsittu);

        String url4 = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";
        String ohtudata = Request.Get(url4).execute().returnContent().asString();
        parsittu = parseri.parse(railsdata).getAsJsonObject();
        kurssidata.put("ohtu2018",parsittu);

        int tehtavatall;

        for (String name : subsPercourse.keySet()) {
            Optional<CourseInfo> paikka = Arrays.stream(courses).filter(c -> (c.getName().equals(name))).findFirst();

            if (!paikka.isPresent()) {
                continue;
            }

            CourseInfo kurz = paikka.get();
            System.out.println(kurz.getFullName() + " syksy 2018");
            System.out.println("");
            tehtavatall = kurz.tehtSum();

            tehtsum = 0;
            tuntisum = 0;

            for (Submission submission : subsPercourse.get(name)) {
                System.out.println("viikko " + submission.getWeek());

                String tehdyt = Arrays.toString(submission.getExercises());
                tehdyt = tehdyt.substring(1,tehdyt.length()-1);
                int maxWeek = kurz.getExercises()[submission.getWeek()];
                int tehtavia = submission.getExercises().length;
                int tunteja = submission.getHours();
                tehtsum += tehtavia;
                tuntisum += tunteja;
                System.out.println("tehtyjä tehtäviä "+ tehtavia + " / " + tehtavia
                        + " aikaa kului " + tunteja + " " + " tehdyt tehtävät: " +  tehdyt);
            }
            System.out.println("");
            System.out.println("yhteensä: " + tehtsum +  "/" + kurz.tehtSum() + " tehtävää "
                                            + tuntisum + " tuntia");
            int kaikkienPalautukset = 0;
            int kaikkienTunnit = 0;
            int kaikkienTehtavat = 0;
            String kurssiAll;
            if (name.equals("rails2018")) {
                parsittu = parseri.parse(railsdata).getAsJsonObject();
            } else {
                parsittu = parseri.parse(ohtudata).getAsJsonObject();
            }

            for (int i=1; i<parsittu.size(); i++) {
                kaikkienPalautukset += parsittu.get(Integer.toString(i)).getAsJsonObject().get("hour_total").getAsInt();
                kaikkienTunnit += parsittu.get(Integer.toString(i)).getAsJsonObject().get("exercise_total").getAsInt();
                kaikkienTehtavat += parsittu.get(Integer.toString(i)).getAsJsonObject().get("students").getAsInt();
            }

            System.out.println("Kurssilla yhteensä " + kaikkienPalautukset + " palautusta, palautettuja tehtäviä "
                                + kaikkienTunnit + " kpl, aikaa käytetty yhteensä " + kaikkienTehtavat + " tuntia");
        }
        System.out.println("");
    }
}