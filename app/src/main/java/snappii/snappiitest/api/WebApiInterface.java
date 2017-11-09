package snappii.snappiitest.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import snappii.snappiitest.api.response.UsersResponse;

public interface WebApiInterface {
    @GET("/api/1.1")
    Observable<UsersResponse> getUsers(@Query("page") int page, @Query("results") int limit, @Query("inc") String fields);
}