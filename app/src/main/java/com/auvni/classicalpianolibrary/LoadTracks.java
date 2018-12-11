package com.auvni.classicalpianolibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoadTracks {
    public static Map<String, Integer> hashMap = new HashMap<>();
    public static ArrayList<TrackInfo> returnArrayList = new ArrayList<>();
    public static ArrayList<TrackInfo> loadTracks() {
        TrackInfo track = new TrackInfo("Polonaise in B-flat Minor, Op. 21", "Alexander Scriabin, Halida Dinova", "spotify:track:5UIV0Vy9Ui1IrK1jT5mMwl");
        hashMap.put(track.getSongName(), R.drawable.scriabin_polonaise);
        returnArrayList.add(track);

        TrackInfo track2 = new TrackInfo("Nocturne in E Flat Major, Op. 9 No. 2", "Frederic Chopin", "spotify:track:7yMSBYlmVEZYZS6V1SLrth");
        hashMap.put(track2.getSongName(), R.drawable.nocturne92);
        returnArrayList.add(track2);

        TrackInfo track3 = new TrackInfo("Ballade in G Minor, Op. 23 No. 1", "Frederic Chopin", "spotify:track:2onIYBpbtdyAz2BMOi5GCD");
        hashMap.put(track3.getSongName(), R.drawable.ballade_no1);
        returnArrayList.add(track3);

        TrackInfo track4 = new TrackInfo("Nocturne in F Major, Op. 15 No. 1", "Frederic Chopin","spotify:track:0zJRpPE55zt81pXP8N7R9E");
        hashMap.put(track4.getSongName(), R.drawable.nocturnefmajor);
        returnArrayList.add(track4);

        TrackInfo track5 = new TrackInfo("Ballade in F Major, Op. 38 No. 1", "Frederic Chopin","spotify:track:4y8mPX3eFVGR929eMzIA43");
        hashMap.put(track5.getSongName(), R.drawable.balladefmajor);
        returnArrayList.add(track5);

        TrackInfo track6 = new TrackInfo("Nocturne in C Minor, Op. 48 No. 1", "Frederic Chopin","spotify:track:7fDm0EPyvkwkIbLYuHzZs2");
        hashMap.put(track6.getSongName(), R.drawable.nocturnecminor);
        returnArrayList.add(track6);

        TrackInfo track7 = new TrackInfo("Ballade in A-Flat Major, Op. 47 No. 3", "Frederic Chopin","spotify:track:7b0Cfn4gd0jcFk6jLFSKOx");
        hashMap.put(track7.getSongName(), R.drawable.balladeaflat);
        returnArrayList.add(track7);

        TrackInfo track8 = new TrackInfo("Nocturne in B Major, Op. 62 No. 1", "Frederic Chopin","spotify:track:7bGmblkMU1AXjgyN8Yc0hS");
        hashMap.put(track8.getSongName(), R.drawable.nocturnebmajor);
        returnArrayList.add(track8);

        TrackInfo track9 = new TrackInfo("Ballade in F Minor, Op. 52 No. 4", "Frederic Chopin","spotify:track:6qRZN5SZU5dby03EoayvEy");
        hashMap.put(track9.getSongName(), R.drawable.balladefminor);
        returnArrayList.add(track9);

        TrackInfo track10 = new TrackInfo("Fantaisie Impromptu in C# Minor, Op. 66", "Frederic Chopin","spotify:track:4L424kWg7jMtZa7vGgp5YW");
        hashMap.put(track10.getSongName(), R.drawable.fantaisieimpromprtu);
        returnArrayList.add(track10);

        TrackInfo track11 = new TrackInfo("Polonaise in A Flat Major, Op. 53", "Frederic Chopin","spotify:track:21FU1oSrVGQAE9zdvtveN9");
        hashMap.put(track11.getSongName(), R.drawable.polonaiseaflat);
        returnArrayList.add(track11);

        TrackInfo track12 = new TrackInfo("Bolero, Op. 19", "Frederic Chopin","spotify:track:1N2UhTXTVuBabHnvTwPquf");
        hashMap.put(track12.getSongName(), R.drawable.bolerochopin);
        returnArrayList.add(track12);

        TrackInfo track13 = new TrackInfo("Fantaisie in F Minor, Op. 49", "Frederic Chopin","spotify:track:2d5v8uDYkgRefB4rn0tVS2");
        hashMap.put(track13.getSongName(), R.drawable.fantaisiefminor);
        returnArrayList.add(track13);

        TrackInfo track14 = new TrackInfo("Piano Concerto No. 1 in E Minor, Op. 11 Mvmt 1", "Frederic Chopin","spotify:track:5ZRmsXtAkmuS5SVIAByP7D");
        hashMap.put(track14.getSongName(), R.drawable.pianoconcerteminor);
        returnArrayList.add(track14);

        TrackInfo track15 = new TrackInfo("Piano Concerto No. 1 in E Minor, Op. 11 Mvmt 2", "Frederic Chopin","spotify:track:5M2s2hg5KcoNN7Ws54gJrH");
        hashMap.put(track15.getSongName(), R.drawable.pianoconcerteminor);
        returnArrayList.add(track15);

        TrackInfo track16 = new TrackInfo("Piano Concerto No. 1 in E Minor, Op. 11 Mvmt 3", "Frederic Chopin","spotify:track:5uLNSRJ3jSXfoPChsnJQNO");
        hashMap.put(track16.getSongName(), R.drawable.pianoconcerteminor);
        returnArrayList.add(track16);

        TrackInfo track17 = new TrackInfo("Piano Concerto No. 2 in C Minor, Op. 18 Mvmt 1", "Sergei Rachmaninoff", "spotify:track:0WqmkFibOtOoilu8KDNN6G");
        hashMap.put(track17.getSongName(), R.drawable.pianoconcertcminor);
        returnArrayList.add(track17);

        TrackInfo track18 = new TrackInfo("Piano Concerto No. 2 in C Minor, Op. 18 Mvmt 2", "Sergei Rachmaninoff", "spotify:track:7r4BWBoer6xgFoVv0EFqyc");
        hashMap.put(track18.getSongName(), R.drawable.pianoconcertcminor);
        returnArrayList.add(track18);

        TrackInfo track19 = new TrackInfo("Piano Concerto No. 2 in C Minor, Op. 18 Mvmt 3", "Sergei Rachmaninoff", "spotify:track:3QSxUH6HIIiNN8q0NcNqqj");
        hashMap.put(track19.getSongName(), R.drawable.pianoconcertcminor);
        returnArrayList.add(track19);

        TrackInfo track20 = new TrackInfo("Prelude in C-sharp Minor, Op. 3 No. 2", "Sergei Rachmaninoff", "spotify:track:3rd1Chqzxr95fcyBaJl0JZ");
        hashMap.put(track20.getSongName(), R.drawable.preludecsharp);
        returnArrayList.add(track20);

        return returnArrayList;
    }
}
