package fr.tse.fise2.heapoverflow.tasks;

import fr.tse.fise2.heapoverflow.database.MarvelElementTable;
import fr.tse.fise2.heapoverflow.events.ComicsRequestAdaper;
import fr.tse.fise2.heapoverflow.interfaces.Tasks;
import fr.tse.fise2.heapoverflow.main.FetchData;
import fr.tse.fise2.heapoverflow.marvelapi.*;

import java.io.IOException;
import java.sql.SQLException;

import static fr.tse.fise2.heapoverflow.marvelapi.MarvelRequest.deserializeComics;

public final class FetchAllComicsTask extends ComicsRequestAdaper implements Tasks {

    public FetchAllComicsTask() {
    }

    @Override
    public boolean doTask() {
        boolean isDone;
        MarvelRequest request = new MarvelRequest();
        try {
            int offset = 2000;
            do {
                if (offset == 0) {
                    System.out.println(offset);
                    String response = request.getData("comics?offset=" + offset + "&limit=100");
                    ComicDataWrapper comicDataWrapper = deserializeComics(response);
                    ComicDataContainer dataContainer = comicDataWrapper.getData();
                    offset = offset + 100;
                } else {
                    offset += 100;
                    Thread fetchComics = new FetchData(this, "comics?offset=" + offset + "&limit=100" + "&orderBy=title", FetchData.ComicsType.COMICS);
                    fetchComics.run();
                }
            } while (offset < 4000);

            isDone = true;

        } catch (IOException e) {
            isDone = false;
            e.printStackTrace();
        }
        return isDone;
    }

    @Override
    public void onFetchedComics(Comic[] comics) {
        try {
            System.out.println("saving " + comics.length + " new comics");
            for (Comic c : comics) {
                if (MarvelElementTable.elementsExists(c.getId(), MarvelElements.COMIC) == -1) {
                    MarvelElementTable.insertComic(c.getId(), c.getTitle().toLowerCase());
                } else {
                    System.out.println(c.getTitle() + " has already registered");
                }
            }
            System.out.println("done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

