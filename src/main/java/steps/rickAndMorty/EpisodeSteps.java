package steps.rickAndMorty;

import api.rickAndMorty.EpisodeRMApi;
import dto.rickAndMorty.Episode;
import org.apache.http.HttpStatus;

public class EpisodeSteps {

    private static final EpisodeRMApi episodeApi = new EpisodeRMApi();


    public Episode getEpisodeById(long id) {
        return episodeApi.getEpisode(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
    }
}
