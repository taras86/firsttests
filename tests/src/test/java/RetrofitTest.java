import com.google.gson.JsonObject;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrofitTest {
    public interface GITapi {
        @GET("users/taras86")
        Call<JsonObject> userInfo();
    }

    @Test
    public void invertUserRole() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        GITapi api = retrofit.create(GITapi.class);
        JsonObject userInfoBody = api.userInfo().execute().body();
        assertThat(userInfoBody.get("login").getAsString()).isEqualTo("taras86"); //тест проходит
        assertThat(userInfoBody.get("login").toString()).isEqualTo("taras86"); //тест падает, т.к. значение login обернуто в двойные кавычки
    }
}
