package steps.rickAndMorty;

import api.rickAndMorty.EpisodeRMApi;
import dto.rickAndMorty.Episode;
import io.qameta.allure.Step;
import org.apache.http.HttpStatus;

public class EpisodeSteps {

    private final EpisodeRMApi episodeApi = new EpisodeRMApi();

    @Step("Get episode by ID: {id}")
    public Episode getEpisodeById(long id) {
        return episodeApi.getEpisode(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(Episode.class);
    }
}
